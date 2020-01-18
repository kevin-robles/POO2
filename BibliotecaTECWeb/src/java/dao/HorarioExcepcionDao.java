/**
 * Clase para manipular objetos tipo HorarioExcepcion con la base de datos
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
import java.text.ParseException;
import java.util.ArrayList;
import modelo.HorarioExcepcion;
import utilidad.ConexionBD;

public class HorarioExcepcionDao {
    
  /**
  * Metodo para ingresar un horario de excepcion a la base de datos
  * @param horarioExcepcion horario a guardar
  * @param identificador identificador de la sala
  */
  public void ingresarHorarioExcepcionSala(ArrayList<HorarioExcepcion> horarioExcepcion,String 
      identificador) throws ParseException{
    Connection conexion = ConexionBD.getConexion();
    for(HorarioExcepcion horario: horarioExcepcion){
      try{
        String query = "exec esquema.ingresarHorarioExcepcion "
            + "@idSala = ?,"
            + "@dia=?,"
            + "@horaInicio=?,"
            + "@horaFinal=?,"
            + "@detalle=?";
        CallableStatement consulta = conexion.prepareCall(query);
        consulta.setString(1,identificador);
        consulta.setDate(2,horario.getDia());
        
        String horaInicio = horario.getHoraInicio();
        String horaFinal = horario.getHoraFinal();
        
        if("cerrado".equals(horaInicio)){
          horaInicio = "00:00";
          horaFinal = "00:00";
        
        }

        consulta.setString(3,horaInicio);
        consulta.setString(4,horaFinal);
        consulta.setString(5,horario.getDetalle());
        consulta.execute();
        
      }catch(SQLException e){
        System.out.println(e);
      }
    }
  }
  
  /**
  * Metodo para consultar el horario de excepcion de una sala
  * @param pIdentificador id de la sala
  * @param pDetalle
  * @return horario de la sala
  */
  public ArrayList<HorarioExcepcion> consultarHorarioExcepcion(String pIdentificador,String 
      pDetalle){
    ArrayList<HorarioExcepcion> horariosExcepcion = new ArrayList<HorarioExcepcion>();
    try{
      Connection conexion = ConexionBD.getConexion();
      //horatios de apertura
      PreparedStatement consulta = conexion.prepareStatement("select idSala,dia,left(horaInicio,5)"
          + " as horaInicio,left(horaFinal,5) as horaFinal,detalle "
          + "from esquema.consultarHorarioExcepcion(?,?)");
      consulta.setString(1,pIdentificador);
      consulta.setString(2,pDetalle);
      ResultSet respuesta = consulta.executeQuery();
      while(respuesta.next()){
        HorarioExcepcion horarioExcepcion = new HorarioExcepcion();
        String HoraInicio = (respuesta.getString("horaInicio"));
        String HoraFinal = (respuesta.getString("horaFinal"));
         
        horarioExcepcion.setHoraInicio(HoraInicio);
        horarioExcepcion.setHoraFinal(HoraFinal);
        horarioExcepcion.setDia(respuesta.getDate("dia"));
        horarioExcepcion.setDetalle(respuesta.getString("detalle"));
        
        horariosExcepcion.add(horarioExcepcion);
      }
    }catch(SQLException e){
      System.out.println(e);
    } 
    return horariosExcepcion;
  }
}
