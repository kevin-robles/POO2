/**
 * Clase para manipular objetos tipo Horario con la base de datos
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
import modelo.Horario;
import utilidad.ConexionBD;

public class HorarioDao {
 
  /**
  * Metodo para registrar el horario de una sala a la base de datos
  * @param horarios horario a registrar
  * @param identificador identificador de la sala
     * @throws java.text.ParseException
  */
  public void ingresarHorarioSala(ArrayList<Horario> horarios,String identificador) throws 
      ParseException{
    Connection conexion = ConexionBD.getConexion();
    for (Horario horario : horarios) {
      try{      
        //ingresar horario
        String query = "exec esquema.ingresarHorarioSala "
            + "@lunesApertura = ?,"
            + "@martesApertura=?,"
            + "@miercolesApertura=?,"
            + "@juevesApertura=?,"
            + "@viernesApertura=?,"
            + "@sabadoApertura=?,"
            + "@domingoApertura=?,"
            + "@lunesCierre=?,"
            + "@martesCierre=?,"
            + "@miercolesCierre=?,"
            + "@juevesCierre=?,"
            + "@viernesCierre=?,"
            + "@sabadoCierre=?,"
            + "@domingoCierre=?,"
            + "@idSala=?";
        
        String horaAperturaLunes = horario.getHoraApertura("Lunes");
        String horaCierreLunes = horario.getHoraCierre("Lunes");
        if("cerrado".equals(horaAperturaLunes)){
          horaAperturaLunes = "00:00";
          horaCierreLunes = "00:00";
        }
        
        String horaAperturaMartes = horario.getHoraApertura("Martes");
        String horaCierreMartes = horario.getHoraCierre("Martes");
        if("cerrado".equals(horaAperturaMartes)){
          horaAperturaMartes = "00:00";
          horaCierreMartes = "00:00";
        }
        
        String horaAperturaMiercoles = horario.getHoraApertura("Miércoles");
        String horaCierreMiercoles = horario.getHoraCierre("Miércoles");
        if("cerrado".equals(horaAperturaMiercoles)){
          horaAperturaMiercoles = "00:00";
          horaCierreMiercoles = "00:00";
        }
        
        String horaAperturaJueves = horario.getHoraApertura("Jueves");
        String horaCierreJueves = horario.getHoraCierre("Jueves");
        if("cerrado".equals(horaAperturaJueves)){
          horaAperturaJueves = "00:00";
          horaCierreJueves = "00:00";
        }
        
        String horaAperturaViernes = horario.getHoraApertura("Viernes");
        String horaCierreViernes = horario.getHoraCierre("Viernes");
        if("cerrado".equals(horaAperturaViernes)){
          horaAperturaViernes = "00:00";
          horaCierreViernes = "00:00";
        }
        
        String horaAperturaSabado = horario.getHoraApertura("Sábado");
        String horaCierreSabado = horario.getHoraCierre("Sábado");
        if("cerrado".equals(horaAperturaSabado)){
          horaAperturaSabado = "00:00";
          horaCierreSabado = "00:00";
        }
        String horaAperturaDomingo = horario.getHoraApertura("Domingo");
        String horaCierreDomingo = horario.getHoraCierre("Domingo");
        if("cerrado".equals(horaAperturaDomingo)){
          horaAperturaDomingo = "00:00";
          horaCierreDomingo = "00:00";
        }

        CallableStatement consulta = conexion.prepareCall(query);
        consulta.setString(1, horaAperturaLunes);
        consulta.setString(2, horaAperturaMartes);
        consulta.setString(3, horaAperturaMiercoles);
        consulta.setString(4, horaAperturaJueves);
        consulta.setString(5, horaAperturaViernes);
        consulta.setString(6, horaAperturaSabado);
        consulta.setString(7, horaAperturaDomingo);
        consulta.setString(8, horaCierreLunes);
        consulta.setString(9, horaCierreMartes);
        consulta.setString(10, horaCierreMiercoles);
        consulta.setString(11, horaCierreJueves);
        consulta.setString(12, horaCierreViernes);
        consulta.setString(13, horaCierreSabado);
        consulta.setString(14, horaCierreDomingo);
        consulta.setString(15, identificador);
        consulta.execute();
      }catch(SQLException e){
        System.out.println(e);
      }
    }
  }
  
  /**
  * Metodo para consultar el horario de una sala
  * @param identificador id de la sala
  * @return horario de la sala
  */
  public Horario consultarHorario(String identificador){
    Horario horario = new Horario();
    try{
      Connection conexion = ConexionBD.getConexion();
      //horatios de apertura
      PreparedStatement consultaApertura = conexion.prepareStatement("select left(lunes,5) as lunes"
          + ",left(martes,5) as martes,left(miercoles,5) as miercoles,left(jueves,5) as jueves,"
          + "left(viernes,5) as viernes,left(sabado,5) as sabado,left(domingo,5) as domingo "
          + "from esquema.consultarHorarioApertura(?)");
      consultaApertura.setString(1,identificador);
      ResultSet respuestaApertura = consultaApertura.executeQuery();
      while(respuestaApertura.next()){
        horario.setHoraApertura("Lunes", respuestaApertura.getString("Lunes"));
        horario.setHoraApertura("Martes", respuestaApertura.getString("Martes"));
        horario.setHoraApertura("Miércoles", respuestaApertura.getString("Miercoles"));
        horario.setHoraApertura("Jueves", respuestaApertura.getString("Jueves"));
        horario.setHoraApertura("Viernes", respuestaApertura.getString("Viernes"));
        horario.setHoraApertura("Sábado", respuestaApertura.getString("Sabado"));
        horario.setHoraApertura("Domingo", respuestaApertura.getString("Domingo"));
      }
      
      //horarios de cierre
      PreparedStatement consultaCierre = conexion.prepareStatement("select left(lunes,5) as lunes,"
          + "left(martes,5) as martes,left(miercoles,5) as miercoles,left(jueves,5) as jueves,"
          + "left(viernes,5) as viernes,left(sabado,5) as sabado,left(domingo,5) as domingo "
          + "from esquema.consultarHorarioCierre(?)");
      consultaCierre.setString(1,identificador);
      ResultSet respuestaCierre = consultaCierre.executeQuery();
      while(respuestaCierre.next()){
        horario.setHoraCierre("Lunes", (respuestaCierre.getString("Lunes")));
        horario.setHoraCierre("Martes", (respuestaCierre.getString("Martes")));
        horario.setHoraCierre("Miércoles", (respuestaCierre.getString("Miercoles")));
        horario.setHoraCierre("Jueves", (respuestaCierre.getString("Jueves")));
        horario.setHoraCierre("Viernes", (respuestaCierre.getString("Viernes")));
        horario.setHoraCierre("Sábado", (respuestaCierre.getString("Sabado")));
        horario.setHoraCierre("Domingo", (respuestaCierre.getString("Domingo")));
      }
    }catch(SQLException e){
      System.out.println(e);
    } 
    return horario;
  }
  
  /**
   * Metodo para consultar el top 5 de horarios apertura utilizados
   * @return lista de horarios
   */
  public String[][] consultarTopHorarioApertura(){
    String[][] horarioAperturaSala = new String[5][2];
    try{
      Connection conexion = ConexionBD.getConexion();
      PreparedStatement consulta = conexion.prepareStatement("select * from esquema."
          + "consultarTopHorarioApertura()");
      ResultSet respuesta = consulta.executeQuery();
      
      int i =0;
      while(respuesta.next()){
        horarioAperturaSala[i][0] = respuesta.getString("hora");
        horarioAperturaSala[i][1] = respuesta.getString("porcentaje");
        i++;
      }
      
      }catch(SQLException e){
        System.out.println(e);
      } 
    return horarioAperturaSala;
  }
  
    /**
   * Metodo para consultar el top 5 de horarios cierre utilizados
   * @return lista de horarios
   */
  public String[][] consultarTopHorarioCierre(){
    String[][] horarioCierreSala = new String[5][2];
    try{
      Connection conexion = ConexionBD.getConexion();
      PreparedStatement consulta = conexion.prepareStatement("select * from esquema."
          + "consultarTopHorarioCierre()");
      ResultSet respuesta = consulta.executeQuery();
      
      int i =0;
      while(respuesta.next()){
        horarioCierreSala[i][0] = respuesta.getString("hora");
        horarioCierreSala[i][1] = respuesta.getString("porcentaje");
        i++;
      }
      
      }catch(SQLException e){
        System.out.println(e);
      }
    return horarioCierreSala;
  }
  
  
}
