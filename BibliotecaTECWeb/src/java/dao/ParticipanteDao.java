/**
 * Clase para manipular objetos tipo Participante con la base de datos
 * 
 * @author Daniel Quiros, Kevin Robles, Oscar Trejos
 * @version 1.0
 */
package dao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Participante;
import utilidad.ConexionBD;

public class ParticipanteDao {
    
  /**
  * Metodo para registrar un participante en la base de datos
  * @param idReserva reserva a la que pertenece
  * @param participantes lista de participantes
  */
  public boolean ingresarParticipantes(int idReserva,ArrayList<Participante> participantes){
    Connection conexion = ConexionBD.getConexion();
    for(Participante participante:participantes){
      try{
        String query = "exec esquema.ingresarParticipante "
            + "@idReserva=?,"
            + "@correo=?,"
            + "@primerNombre=?,"
            + "@primerApellido=?,"
            + "@segundoApellido=?";
        CallableStatement consulta = conexion.prepareCall(query);
        consulta.setInt(1,idReserva);
        consulta.setString(2,participante.getCorreo());
        consulta.setString(3,participante.getPrimerNombre());
        consulta.setString(4,participante.getPrimerApellido());
        consulta.setString(5,participante.getSegundoApellido());
        consulta.execute();
      }catch(SQLException e){
        System.out.println(e);
        return false;
      }  
    }
    return true;
  }
  
  /**
   * Metodo que devuelve la cantidad de participantes de una reserva
   * @param idReserva identificador de la reserva
   * @return cantidad de participantes
   */
  public int validarCantidadParticipantes(int idReserva){
    try{
      Connection conexion = ConexionBD.getConexion(); 

      String query = "select esquema.cantidadPersonasReserva(?) as cantidad;";
      CallableStatement consulta = conexion.prepareCall(query);
      consulta.setInt(1,idReserva);
      consulta.execute();
      return consulta.getInt("cantidad");
      
    }catch(SQLException e){
      return 0;
    }
  }
}
