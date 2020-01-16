/**
 * Clase para manipular objetos tipo Incidente con la base de datos
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
import modelo.Incidente;
import utilidad.ConexionBD;

public class IncidenteDao {
  /**
  * Metodo para consultar los incidentes de una reserva en particular
  * @param idReserva identificador de la reserva
  * @return lista de incidentes de la reserva
  */
  public ArrayList consultarIncidentesReserva(int idReserva){
    ArrayList<Incidente> incidentes = new ArrayList<Incidente>();
    try{    
      Connection conexion = ConexionBD.getConexion();
      //obtener incidentes de la reserva
      PreparedStatement consultaIncidentes = conexion.prepareStatement("select * from esquema."
          + "consultarIncidentesReserva(?)");
      consultaIncidentes.setInt(1, idReserva);
      ResultSet respuestaIncidentes = consultaIncidentes.executeQuery();
      while(respuestaIncidentes.next()){
        Incidente incidente = new Incidente();
        incidente.setTipo(respuestaIncidentes.getString("tipo"));
        incidente.setComentario(respuestaIncidentes.getString("comentario"));
        incidente.setFecha(respuestaIncidentes.getDate("fecha"));
        incidentes.add(incidente);//agregar un incidente
      }
    }catch(SQLException e){
      System.out.println(e);
    }
    return incidentes;
  }
    
  /**
   * Metodo para agregar un incidente en la base de datos
   * @param pIncidente Objeto incidente
   * @param carnet identificador del estudiante
   */
  public void agregarIncidenteSala(Incidente pIncidente,String carnet){
    try{
      Connection conexion = ConexionBD.getConexion(); 

      String query = "exec esquema.ingresarIncidente @idReserva=?,@idSala =?,@tipo=?,@comentario=?,"
          + "@fecha=?,@carnet=?";
      CallableStatement consulta = conexion.prepareCall(query);
      consulta.setInt(1,pIncidente.getReserva());
      consulta.setString(2,pIncidente.getSala());
      consulta.setString(3,pIncidente.getTipo());
      consulta.setString(4,pIncidente.getComentario());
      java.sql.Date fechaIncidente = new java.sql.Date(pIncidente.getFecha().getTime());
      consulta.setDate(5,fechaIncidente);
      consulta.setString(6, carnet);
      
      consulta.execute();
    }catch(SQLException e){
      System.out.println(e);
    }
  }
}
