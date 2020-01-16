/**
 * Clase para manipular objetos tipo Reserva con la base de datos
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
import modelo.Estudiante;
import modelo.Participante;
import modelo.Reserva;
import utilidad.ConexionBD;

public class ReservaDao {
  /**
  * Metodo para consultar las reservas de un estudiante particular
  * 
  * @param carnet identificador del estudiante
  * @return Lista de reservas del estudiante
  */
  public ArrayList consultarReservasEstudiante(String carnet){
    ArrayList<Reserva> misReservas = new ArrayList<Reserva>();
    try{   
      Connection conexion = ConexionBD.getConexion();
      //obtener reservas del estudiante
      PreparedStatement consultaReservas = conexion.prepareStatement("select * from esquema."
          + "consultarReservasEstudiante(?)");
      consultaReservas.setString(1, carnet);
      ResultSet respuestaReservas = consultaReservas.executeQuery();
      while(respuestaReservas.next()){
        Reserva reserva = new Reserva();
        reserva.setIdReserva(respuestaReservas.getInt("idReserva"));
        reserva.setEstado(respuestaReservas.getString("estado"));
        reserva.setAsunto(respuestaReservas.getString("asunto"));
        reserva.setFechaSolicitud(respuestaReservas.getDate("fechaSolicitud"));
        reserva.setHoraInicio(respuestaReservas.getString("horaInicio"));
        reserva.setHoraFinal(respuestaReservas.getString("horaFinal"));
        reserva.setIdSala(respuestaReservas.getString("salaAsignada"));
        
        //consulta incidentes de la reserva con su respectivo dao
        IncidenteDao incidenteDao = new IncidenteDao();
        reserva.setIncidentes(incidenteDao.consultarIncidentesReserva(respuestaReservas.
            getInt("idReserva"))); 
        misReservas.add(reserva);//agregar una reserva
      }
    }catch(SQLException e){
      System.out.println(e);
    }
    return misReservas;
  }
  
  /**
  * Metodo para obtener las reservas de los proximos 7 dias para una sala
  * @param identificador id de la sala
  * @return lista de reservas
  */
  public ArrayList consultarReservas7Dias(String identificador){
    ArrayList<Reserva> misReservas = new ArrayList<Reserva>();
    try{   
      Connection conexion = ConexionBD.getConexion();
      //obtener reservas del estudiante
      PreparedStatement consultaReservas = conexion.prepareStatement("select * from esquema."
          + "consultarReservas7Dias(?)");
      consultaReservas.setString(1, identificador);
      ResultSet respuestaReservas = consultaReservas.executeQuery();
      while(respuestaReservas.next()){
        Reserva reserva = new Reserva();
        reserva.setIdReserva(respuestaReservas.getInt("idReserva"));
        reserva.setEstado(respuestaReservas.getString("estado"));
        reserva.setAsunto(respuestaReservas.getString("asunto"));
        reserva.setFechaSolicitud(respuestaReservas.getDate("fechaSolicitud"));
        reserva.setHoraInicio(respuestaReservas.getString("horaInicio"));
        reserva.setHoraFinal(respuestaReservas.getString("horaFinal"));
        reserva.setIdSala(respuestaReservas.getString("salaAsignada"));
        misReservas.add(reserva);//agregar una reserva
      }
    }catch(SQLException e){
      System.out.println(e);
    }
    return misReservas;
  }
  
  /**
   * Metodo para ingresar una reserva a la base de datos
   * @param reserva objeto con los datos correspondientes
   */
  public void ingresarReserva(Reserva reserva){
    try{
      Connection conexion = ConexionBD.getConexion(); 
      //ingresar reserva
      String query = "exec esquema.ingresarReserva "
          + "@idReserva=?,"
          + "@estado=?,"
          + "@asunto=?,"
          + "@fechaSolicitud=?,"
          + "@horaInicio=?,"
          + "@horaFinal=?,"
          + "@organizador=?,"
          + "@salaAsignada=?";
      CallableStatement consulta = conexion.prepareCall(query);
      consulta.setInt(1,reserva.getIdReserva());
      consulta.setString(2,reserva.getEstado());
      consulta.setString(3,reserva.getAsunto());
      java.sql.Date fechaSolicitudSQL = new java.sql.Date(reserva.getFechaSolicitud().getTime());
      consulta.setDate(4,fechaSolicitudSQL);
      consulta.setString(5,reserva.getHoraInicio());
      consulta.setString(6,reserva.getHoraFinal());
      consulta.setString(7,reserva.getCarnetOrganizador());
      consulta.setString(8,reserva.getIdSala());
      consulta.execute();
      
      
      //ingresar participantes
      ParticipanteDao participanteDao = new ParticipanteDao();
      participanteDao.ingresarParticipantes(reserva.getIdReserva(),reserva.getParticipantes());
      
    }catch(SQLException e){
      System.out.println(e);
    }
  
  }
  
  /**
   * metodo para cancelar una reserva en la base de datos
   * @param idReserva identificador de la reserva
   * @return true de haber podido cancelarse, false de lo contrario
   */
  public boolean cancelarReserva(int idReserva){
    try{
      Connection conexion = ConexionBD.getConexion(); 
      String query = "exec esquema.cancelarReserva ? ";
      CallableStatement consulta = conexion.prepareCall(query);
      consulta.setInt(1, idReserva);
      consulta.execute();
      return true;
      
    }catch(SQLException e){
      System.out.println(e);
      return false;
    }
  }
  
  
  /**
  * Metodo para obtener el proximo id para las reservas
  * @return proximo id sala
  */
  public int obtenerProximoIdReserva(){
    int proximoIdReserva = 0;
    try{
      Connection conexion = ConexionBD.getConexion();
      PreparedStatement consulta = conexion.prepareStatement("select esquema.obtenerProximoIdReserva()");
      ResultSet respuesta = consulta.executeQuery();
      
      while(respuesta.next()){
        proximoIdReserva = respuesta.getInt(1);
      }
      return proximoIdReserva;
    
    }catch(SQLException e){
      System.out.println(e);
    }
    return proximoIdReserva;
  }
  
  /**
   * Metodo para validar existencia de una reserva
   * @param idReserva identificador
   * @return true de existir, false de lo contrario
   * @throws SQLException 
   */
  public boolean validarReserva(String idReserva) throws SQLException{
    Connection conexion = ConexionBD.getConexion();
    PreparedStatement consulta = conexion.prepareStatement("select * from esquema.reserva where "
        + "idReserva=?;");
    consulta.setString(1, idReserva);
    ResultSet respuesta = consulta.executeQuery();

    return respuesta.next();
  }
  
  /**
   * Metodo para obtener la sala asiganda de una reserva
   * @param idReserva identificador de la reserva
   * @return Objeto reserva
   * @throws SQLException 
   */
  public Reserva obtenerSalaReserva(String idReserva) throws SQLException{
    Connection conexion = ConexionBD.getConexion();
    PreparedStatement consulta = conexion.prepareStatement("select organizador, fechaSolicitud,"
        + "left(horaInicio,5) as horaInicio,left(horaFinal,5) as horaFinal,salaAsignada from "
        + "esquema.reserva where idReserva=?");
    consulta.setString(1, idReserva);
    ResultSet respuesta = consulta.executeQuery();
    Reserva nuevaReserva = new Reserva();

    if(respuesta.next()){
      nuevaReserva.setFechaSolicitud(new java.util.Date(respuesta.getDate("fechaSolicitud").
          getTime()));
      nuevaReserva.setHoraInicio(respuesta.getString("horaInicio"));
      nuevaReserva.setIdSala(respuesta.getString("salaAsignada"));
      nuevaReserva.setHoraFinal(respuesta.getString("horaFinal"));
      Estudiante organizador = new Estudiante();
      organizador.setCarnet(respuesta.getString("organizador"));
      nuevaReserva.setOrganizador(organizador);
      return nuevaReserva;
    }
    return null;
  }
  
  /**
   * Metodo para validar existencia de reserva con numero de sala
   * @param pReserva numero de reserva
   * @param pSala numero de sala
   * @return true de existir, false de lo contrario
   * @throws SQLException 
   */
  public boolean validarSalaReserva(String pReserva , String pSala) throws SQLException{
    Connection conexion = ConexionBD.getConexion();
    PreparedStatement consulta = conexion.prepareStatement
        ("select * from esquema.reserva where idReserva=? and salaAsignada=?;");
    consulta.setString(1, pReserva);
    consulta.setString(2, pSala);
    ResultSet respuesta = consulta.executeQuery();

    return respuesta.next();
  }
  
  /**
   * Metodo para obtener los participantes de una reserva
   * @param pReserva identificador de reserva
   * @return lista de participantes
   * @throws SQLException 
   */
  public ArrayList<Participante> obtenerParticipantes(String pReserva) throws SQLException{
    Connection conexion = ConexionBD.getConexion();
    PreparedStatement consulta = conexion.prepareStatement
        ("select * from esquema.participante where idReserva=?;");
    consulta.setString(1, pReserva);
    ResultSet respuesta = consulta.executeQuery();
    
    ArrayList<Participante> arrayFinal = new ArrayList<Participante>();
    while(respuesta.next()){
      Participante nuevo = new Participante();
      nuevo.setCorreo(respuesta.getString("correo"));
      arrayFinal.add(nuevo);
    }
    return arrayFinal;
  }
  
  /**
   * Metodo para verificar si una reserva esta cancelada
   * @param pReserva identificador
   * @return true de ser verdad, false de lo contrario
   * @throws SQLException 
   */
  public boolean reservaCancelada(String pReserva) throws SQLException{
    Connection conexion = ConexionBD.getConexion();
    PreparedStatement consulta = conexion.prepareStatement
        ("select estado from esquema.reserva where idReserva=?;");
    consulta.setString(1, pReserva);
    ResultSet respuesta = consulta.executeQuery();
    String estado = "";
    while(respuesta.next()){
      estado = respuesta.getString("estado");
    }
    
    if("Cancelada".equals(estado)){
      return true;
    }
    return false;
  }
  
  /**
   * Metodo para consultar la cantidad de incidentes de una sala
   * @param pSala identificador de la sala
   * @return cantidad de incidentes
   * @throws SQLException 
   */
  public int validarIncidentes(String pSala) throws SQLException{
    Connection conexion = ConexionBD.getConexion();
    PreparedStatement consulta = conexion.prepareStatement
        ("select esquema.cantidadIncidentes(?)");
    consulta.setString(1, pSala);
    ResultSet respuesta = consulta.executeQuery();
    int bandera =0;
    while(respuesta.next()){
      bandera = respuesta.getInt(1);
    }
    
    return bandera;
  }
  
  /**
   * Metodo para determinar la cantidad de reservas de un estudiante en la semana actual
   * @param pEstudiante identificador del estudiante
   * @return cantidad de reservas en la semana actual
   * @throws SQLException 
   */
  public int reservasSemanales(String pEstudiante) throws SQLException{
    Connection conexion = ConexionBD.getConexion();
    PreparedStatement consulta = conexion.prepareStatement
        ("select esquema.numeroReservasSemana(?)");
    consulta.setString(1, pEstudiante);
    ResultSet respuesta = consulta.executeQuery();
    int bandera =0;
    while(respuesta.next()){
      bandera = respuesta.getInt(1);
    }
    return bandera;
  }
  
}
