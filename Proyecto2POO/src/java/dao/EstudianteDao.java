/**
 * Clase para manipular objetos tipo Estudiante con la base de datos
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
import modelo.Incidente;
import utilidad.ConexionBD;

public class EstudianteDao {
    
  /**
  *  Metodo para registrar un nuevo estudiante en la base de datos
  * @param estudiante objeto a registrar
  * @return 0 si hubo error, 1 si hubo exito, 2 si el estudiante ya esta registrado
  */
  public int registrarEstudiante(Estudiante estudiante){
    try{
      Connection conexion = ConexionBD.getConexion(); 
      int bandera = validarEstudiante(estudiante.getCarnet());
      if(bandera == 1){
        String query = "exec esquema.ingresarEstudiante @pCarnet = ?,@pNombre=?,@pApellido=?,"
            + "@pCarrera=?,@pCorreo=?,@pCalificacion = ?,@pTelefono = ?";
        CallableStatement consulta = conexion.prepareCall(query);
        consulta.setString(1, estudiante.getCarnet());
        consulta.setString(2,estudiante.getNombre());
        consulta.setString(3,estudiante.getApellido());
        consulta.setString(4,estudiante.getCarrera());
        consulta.setString(5,estudiante.getCorreo());
        consulta.setInt(6,estudiante.getCalificacion());
        consulta.setString(7,estudiante.getTelefono());
        consulta.execute();
        return bandera;
      }
      return bandera;
    }catch(SQLException e){
      System.out.println(e);
      return 0;
    }
  }
  
  /**
  *  Metodo para registrar un nuevo estudiante en la base de datos
  * @param carnet identificador del estudiante
  * @return 0 si hubo error, 1 si hubo exito, 2 si el estudiante ya esta registrado
  */
  public int validarEstudiante(String carnet){
    try{
      Connection conexion = ConexionBD.getConexion();
      //validar estudiante
      System.out.println(conexion);
      PreparedStatement validar = conexion.prepareStatement("select esquema.validarEstudiante(?)");
      validar.setString(1, carnet);
      ResultSet respuestaValidar = validar.executeQuery();
      int bandera = 0;
      while(respuestaValidar.next()){
        bandera = respuestaValidar.getInt(1);
      }
      return bandera;
    }catch(SQLException e){
      System.out.println(e);
      return 0;
    }
  }
  
  public boolean existeEstudiante(String carnet){
    try{
      Connection conexion = ConexionBD.getConexion();
      //validar estudiante
      PreparedStatement validar = conexion.prepareStatement("select * from esquema.estudiante where"
          + " carnet=?");
      validar.setString(1, carnet);
      ResultSet respuestaValidar = validar.executeQuery();

      if(respuestaValidar.next()){
        return true;
      }

    }catch(SQLException e){
      System.out.println(e);
      return false;
    }
    return false;
  }
          
  public Estudiante obtenerEstudiante(String carnet){
    Estudiante encontrado = new Estudiante();
    try{
      Connection conexion = ConexionBD.getConexion();
      //validar estudiante
      System.out.println(conexion);
      PreparedStatement validar = conexion.prepareStatement("select * from esquema.estudiante where"
          + " carnet=?");
      validar.setString(1, carnet);
      ResultSet respuestaValidar = validar.executeQuery();

      if(respuestaValidar.next()){
        encontrado.setApellido(respuestaValidar.getString("apellido"));
        encontrado.setNombre(respuestaValidar.getString("nombre"));
        encontrado.setTelefono(respuestaValidar.getString("telefono"));
        encontrado.setCarrera(respuestaValidar.getString("carrera"));
        encontrado.setCorreo(respuestaValidar.getString("correo"));
        encontrado.setCalificacion(respuestaValidar.getInt("calificacion"));
        encontrado.setCarnet(carnet);
      }
      
      return encontrado;
    }catch(SQLException e){
      System.out.println(e);
      return encontrado;
    }
  }
  
  
  /**
  * Metodo para obtener los datos de un estudiante, sus reservas con sus respectivos incidentes
  * @param carnet identificador del estudiante
  * @return objeto tipo Estudiante con los datos encontrados
  */  
  public Estudiante consultarDatosEstudiante(String carnet){
    Estudiante estudiante = new Estudiante();
    try{
      Connection conexion = ConexionBD.getConexion();
      //obtener datos del estudiante
      PreparedStatement consultaEstudiante = conexion.prepareStatement("select * from esquema."
         + "consultarEstudiante(?)");
      consultaEstudiante.setString(1, carnet);
      ResultSet respuestaEstudiante = consultaEstudiante.executeQuery();
      while(respuestaEstudiante.next()){
        estudiante.setCarnet(respuestaEstudiante.getString("carnet"));
        estudiante.setNombre(respuestaEstudiante.getString("nombre"));
        estudiante.setApellido(respuestaEstudiante.getString("apellido"));
        estudiante.setCarrera(respuestaEstudiante.getString("carrera"));
        estudiante.setCorreo(respuestaEstudiante.getString("correo"));
        estudiante.setCalificacion(respuestaEstudiante.getInt("calificacion"));
        estudiante.setTelefono(respuestaEstudiante.getString("telefono"));
        
        //consultar reservas del estudiante mediante su dao respectivo
        ReservaDao reservaDao = new ReservaDao();
        estudiante.setMisReservas(reservaDao.consultarReservasEstudiante(carnet));
      }      
    }catch(SQLException e){
      System.out.println(e);
    }
    return estudiante;
  }
  
  /**
   * Metodo para obtener datos de un estudiante segun un numero de reserva
   * @param idReserva
   * @return Estudiante
   * @throws SQLException 
   */
  public Estudiante datosEstudianteReserva(String idReserva) throws SQLException{
    Connection conexion = ConexionBD.getConexion();
    String query = "select estudiante.nombre,estudiante.apellido from " +
        "esquema.estudiante, esquema.reserva where estudiante.carnet = reserva.organizador "+
        "and reserva.idReserva=?";
    PreparedStatement validar = conexion.prepareStatement(query);
    validar.setString(1, idReserva);
    ResultSet executeQuery = validar.executeQuery();
    Estudiante nuevoEstudiante = new Estudiante();
    
    if(executeQuery.next()){
      nuevoEstudiante.setNombre(executeQuery.getString("nombre"));
      nuevoEstudiante.setApellido(executeQuery.getString("apellido"));
      return nuevoEstudiante;
    }else{
      return null;
    }
  }
  
  public ArrayList<Incidente> consultarIncidentesEstudiante(String pCarnet) throws SQLException{
    ArrayList<Incidente> incidentes = new ArrayList<Incidente>();
    Connection conexion = ConexionBD.getConexion();
    String query = "select incidente.fecha,incidente.idSala,incidente.comentario,tipoIncidente.tipo\n" +
        "from esquema.estudiante,esquema.reserva,esquema.incidente, esquema.tipoIncidente\n" +
            "where reserva.idReserva=incidente.idReserva and estudiante.carnet=reserva.organizador\n" +
                 "and tipoIncidente.idTipoIncidente=incidente.idTipoIncidente and estudiante.carnet=?;";
    PreparedStatement validar = conexion.prepareStatement(query);
    validar.setString(1, pCarnet);
    ResultSet executeQuery = validar.executeQuery();
    
    while(executeQuery.next()){
      Incidente incidente = new Incidente();
      java.util.Date fecha = new java.util.Date(executeQuery.getDate("fecha").getTime());
      incidente.setFecha(fecha);
      incidente.setSala(executeQuery.getString("idSala"));
      incidente.setComentario(executeQuery.getString("comentario"));
      incidente.setTipo(executeQuery.getString("tipo"));
      incidentes.add(incidente);
    }
    return incidentes;
  }
  
}
