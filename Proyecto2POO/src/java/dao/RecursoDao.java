/**
 * Clase para manipular objetos tipo Recurso con la base de datos
 * 
 * @author Daniel Quiros, Kevin Robles, Oscar Trejos
 * @version 1.0
 */
package dao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Recurso;
import utilidad.ConexionBD;

public class RecursoDao {
 /**
  * Metodo para registrar en la base de datos un recurso
  * @param recursos lista de recursos a asiganar
  * @param identificador identificador de la sala
  */
  public void ingresarRecursosSala(ArrayList<Recurso> recursos,String identificador){
    Connection conexion = ConexionBD.getConexion();
      if(!recursos.isEmpty()){
       for (Recurso recurso : recursos) {
        try{
          String query = "exec esquema.ingresarRecursoSala "
              + "@idSala=?,"
              + "@nombreRecurso=?";
          CallableStatement consulta = conexion.prepareCall(query);
          consulta.setString(1, identificador);
          consulta.setString(2, recurso.getNombreRecurso());
          consulta.execute();
        }catch(SQLException e){
            System.out.println(e);
            System.out.println("acá");
        }
      }
    }
  }
  
  /**
  *Metodo para validar la excistencia de un recurso
  * 
  * @param recurso nombre del recurso
  * @return true si existe, falso de lo contrario
  * @throws SQLException
  */
  public boolean validarRecursoExistente(String recurso) throws SQLException{
    Connection conexion = ConexionBD.getConexion();
    String query = "select nombreRecurso from esquema.recurso where nombreRecurso=?";
    PreparedStatement validar = conexion.prepareStatement(query);
    validar.setString(1, recurso);
    ResultSet executeQuery = validar.executeQuery();
    ArrayList<String> resultados = new ArrayList<String>();
    while(executeQuery.next()){
      resultados.add(executeQuery.getString(1));
    }
    if(resultados.isEmpty()){
      return false;
    }
    return true;
  }  
  
  /**
   * Metodo para validar la existencia de un recurso en la sala
   * @param recurso Objeto tipo recurso
   * @param identificador identificador de la sala
   * @return true de ser corrector, false de lo contrario
   * @throws SQLException 
   */
  public boolean validarRecursoExistente(String recurso, String identificador) throws SQLException{
    Connection conexion = ConexionBD.getConexion();
    String query = "select nombreRecurso from esquema.recurso,esquema.sala where nombreRecurso=? and "
        + "sala.identificador=?";
    PreparedStatement validar = conexion.prepareStatement(query);
    validar.setString(1, recurso);
    validar.setString(2, identificador);
    ResultSet executeQuery = validar.executeQuery();
    ArrayList<String> resultados = new ArrayList<String>();
    while(executeQuery.next()){
      resultados.add(executeQuery.getString(1));
    }
    if(resultados.isEmpty()){
      return false;
    }
    return true;
  }  
  
  
  /**
  * Metodo para obtener los recursos de una sala
  * 
  * @param idSala correspondiente a la sala
  * @return String con todos los recursos de la sala
  * @throws SQLException
  */
  public String obtenerRecursosSala(String idSala) throws SQLException{
    Connection conexion = ConexionBD.getConexion();
    String query = "select recurso.nombreRecurso from esquema.SalaRecurso,esquema.recurso,esquema.sala"
        + " where SalaRecurso.idSala=? and sala.identificador=SalaRecurso.idSala "
        + "and recurso.idRecurso=SalaRecurso.idRecurso;";
    PreparedStatement validar = conexion.prepareStatement(query);
    validar.setString(1, idSala);
    ResultSet executeQuery = validar.executeQuery();
    ArrayList<Recurso> resultados = new ArrayList<Recurso>();
    while(executeQuery.next()){
      resultados.add(new Recurso(executeQuery.getString(1)));
    }
    String recursos = "";
    int contadorRecursos = 0;
    while(resultados.size() > contadorRecursos){
      recursos += resultados.get(contadorRecursos) + ",";
      contadorRecursos ++ ;
    }
    return recursos;
  }
}
