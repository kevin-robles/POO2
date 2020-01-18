/**
 * Clase para manipular objetos tipo Sala con la base de datos
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
import java.util.Calendar;
import modelo.Recurso;
import modelo.Sala;
import utilidad.ConexionBD;

public class SalaDao {
    
  /**
  * Metodo para registrar una sala en la base de datos
  * @param sala objeto a registrar
  * @return 
  * @throws java.text.ParseException
  */
  public boolean registrarSala(Sala sala) throws ParseException{
    try{
      Connection conexion = ConexionBD.getConexion(); 
      //ingresar sala
      String query = "exec esquema.ingresarSala @identificador = ?,@ubicacion=?,"
          + "@capacidadMaxima=?,@estado=?,@calificacion=?";
      CallableStatement consulta = conexion.prepareCall(query);
      consulta.setString(1, sala.getIdentificador());
      consulta.setString(2,sala.getUbicacion());
      consulta.setInt(3,sala.getCapacidadMaxima());
      consulta.setString(4,sala.getEstado());
      consulta.setInt(5,sala.getCalificacion());
      consulta.execute();
      
      //ingresar horario sala mediante dao correspondiente
      HorarioDao horarioDao = new HorarioDao();
      System.out.println(34);
      horarioDao.ingresarHorarioSala(sala.getHorariosServicios(),sala.getIdentificador());
      
      //ingresar recursos mediante dao correspondiente
      if(!(sala.getRecursos().get(0).getNombreRecurso().equals("vacio"))){
        RecursoDao recursoDao = new RecursoDao();
        recursoDao.ingresarRecursosSala(sala.getRecursos(),sala.getIdentificador());
      }

      return true;
      
    }catch(SQLException e){
      System.out.println("dfs");
      return false;
    }
  }  
    
  
  /**
  * Metodo para modificar datos de una sala
  * @param identificador identificador de la sala
  * @param ubicacion nueva ubicacion de la sala
  * @param estado nuevo estado de la sala
  * @param recursos nuevos recursos de la sala
  * @return si se realizó la función o no
  * @throws java.sql.SQLException
  */
  public boolean modificarDatosSala(String identificador,String ubicacion,String estado,
      ArrayList<Recurso> recursos) throws SQLException{
    Connection conexion = ConexionBD.getConexion();
    if(!ubicacion.equals("")){
      try{
        String query = "update esquema.sala set ubicacion = ? where identificador = ? ";
        CallableStatement consulta = conexion.prepareCall(query);
        consulta.setString(1, ubicacion);
        consulta.setString(2, identificador);
        consulta.execute();
      }catch(SQLException e){
        System.out.println(e);
      }  
    }
    
    if(!estado.equals("")){
      try{
        String query = "update esquema.sala set estado = ? where identificador = ? ";
        CallableStatement consulta = conexion.prepareCall(query);
        consulta.setString(1, estado);
        consulta.setString(2, identificador);
        consulta.execute();
      }catch(SQLException e){
        System.out.println(e);
      } 
    }
  
    if(!recursos.isEmpty()){
      RecursoDao recursoDao = new RecursoDao();
      int contadorRecursos = 0;
      int noEncontrados = 0;
      while(recursos.size() > contadorRecursos){
        if(!(recursoDao.validarRecursoExistente(recursos.get(contadorRecursos).getNombreRecurso()))){
          noEncontrados++;
        }
        contadorRecursos++;
      }
      if(noEncontrados > 0){
        return false;
      }
      try{
        //eliminar recursos anteriores de la sala
        String query = "delete from esquema.SalaRecurso where idSala = ?";
        CallableStatement consulta = conexion.prepareCall(query);
        consulta.setString(1, identificador);
        consulta.execute();
        //ingresar recursos mediante dao correspondiente
        recursoDao.ingresarRecursosSala(recursos,identificador); 
      }catch(SQLException e){
        System.out.println(e);
      } 
    }
    return true;
      
  }
  
  /**
   * Metodo para registrar las calificaciones en la base de datos
   * @param calificaciones lista de calificaciones
   * @param identificador identificador de la sala
   */
  private void registrarCalificaciones(ArrayList<Integer> calificaciones, String identificador){
    if(!calificaciones.isEmpty()){
      for (Integer calificacion : calificaciones) {
        try{
          Connection conexion = ConexionBD.getConexion();
          String query = "exec esquema.registrarCalificaciones ? ,?";
          CallableStatement consulta = conexion.prepareCall(query);
          consulta.setInt(1, calificacion);
          consulta.setString(2,identificador);
          consulta.execute();
        }catch(SQLException e){
          System.out.println(e);
        }
      }
    }
  }
  
  /**
  * Metodo para registrar la nueva calificacion
  * @param calificacion calificacion a registrar
  * @param identificador id de la sala
  * @return nueva calificacion
  */
  public int calificar(int calificacion,String identificador){
    //registrar calificacion
    ArrayList<Integer> calificaciones = new ArrayList<Integer>();
    calificaciones.add(calificacion);
    registrarCalificaciones(calificaciones,identificador);
    
    //obtener nueva calificacion
    int nuevaCalificacion = obtenerCalificacion(identificador);
    return nuevaCalificacion;
  }
  
  /**
  * Metodo para obtener el promedio de calificaciones de la sala
  * @param identificador id de la sala
  * @return promedio de calificaciones de la sala
  */
  private int obtenerCalificacion(String identificador){
    try{
      Connection conexion = ConexionBD.getConexion();
      PreparedStatement consulta = conexion.prepareStatement("select esquema.obtenerCalificacion(?)");
      consulta.setString(1,identificador);
      ResultSet respuesta = consulta.executeQuery();
      int calificacion = 0;
      while(respuesta.next()){
        calificacion = respuesta.getInt(1);
      }
      return calificacion;
    }catch(SQLException e){
      System.out.println(e);
      return 0;
    } 
  }
  
  /**
  * Metodo para consultar los datos de una sala
  * @param identificador id de la sala
  * @return objeto Sala con los los datos y horario normal y de excepciones
  */
  public Sala consultarSala(String identificador){
    Sala sala = new Sala();
    try{
      Connection conexion = ConexionBD.getConexion();
      PreparedStatement consulta = conexion.prepareStatement("select * from esquema.consultarSalaValidar"
          + "(?)");
      consulta.setString(1,identificador);
      ResultSet respuesta = consulta.executeQuery();
      while(respuesta.next()){
        sala.setIdentificador(identificador);
        sala.setUbicacion(respuesta.getString("ubicacion"));
        sala.setCapacidadMaxima(respuesta.getInt("capacidadMaxima"));
        sala.setEstado(respuesta.getString("estado"));
        sala.setCalificacion(respuesta.getInt("calificacion"));  
      }
      
    }catch(SQLException e){
      System.out.println("sdsdsd");
      return null;
    } 
    return sala;
  }
  
  /**
   * Metod para consultar las salas disponibles 
   * @param fechaSolicitud fecha a consultar
   * @param horaInicio hora de inicio a consultar
   * @param horaFinal hora final a consultar
   * @param capacidadMinima capacidad minima a consultar
   * @param recursos lista de recursos necesarios
   * @return lista de identificador de las salas disponibles
   * @throws ParseException 
   */
  public ArrayList<String> obtenerSalasDisponibles(java.sql.Date fechaSolicitud,String horaInicio,
      String horaFinal,int capacidadMinima,ArrayList<Recurso> recursos) throws ParseException{
    ArrayList<String> salasDisponibles = new ArrayList<String>();
    try{
        ///limpiar datos anteriores
      Connection conexion = ConexionBD.getConexion(); 
      String queryLimpiar = "exec esquema.limpiarSalasDisponibles";
      CallableStatement consultaLimpiar = conexion.prepareCall(queryLimpiar);
      consultaLimpiar.execute();
      
      //obtener nombre del dia
      String[] dias={"Domingo","Lunes","Martes", "Miercoles","Jueves","Viernes","Sabado"};
      int numeroDia=0;
      Calendar cal= Calendar.getInstance();
      cal.setTime(fechaSolicitud);
      numeroDia=cal.get(Calendar.DAY_OF_WEEK);
      String nombreDia = dias[numeroDia - 1];
      
      //busqueda sin filtro de recursos
      String queryPrevio = "exec esquema.ingresarSalasDisponibles "
          + "@nombreDia=?,@fechaSolicitud=?,@horaInicio=?,@horaFinal=?,@capacidadMinima=?";
      CallableStatement consultaPrevio = conexion.prepareCall(queryPrevio);
      consultaPrevio.setString(1, nombreDia);
      consultaPrevio.setDate(2, fechaSolicitud);
      consultaPrevio.setString(3, horaInicio);
      consultaPrevio.setString(4, horaFinal);
      consultaPrevio.setInt(5, capacidadMinima);
      consultaPrevio.execute();
      
      //filtro de recursos
      if(recursos.isEmpty()){//si no hay filtro de recursos
        String querySinRecurso = "exec esquema.cuandoNoHayRecursos ";
        CallableStatement consultaSinRecurso = conexion.prepareCall(querySinRecurso);
        consultaSinRecurso.execute();
      }else{//si se debe hacer fltro de recursos
        String querySinFiltro = "select * from esquema.salasDisponibles";  
        CallableStatement consultaSinFiltro = conexion.prepareCall(querySinFiltro);
        ResultSet respuestaSinFiltro = consultaSinFiltro.executeQuery();
        ArrayList<String> salasDisponilesSinFiltro = new ArrayList<String>();
        while(respuestaSinFiltro.next()){
          salasDisponilesSinFiltro.add(respuestaSinFiltro.getString("identificador"));
        }
        int contador = 0;
        while (contador < salasDisponilesSinFiltro.size()){
          String identificador=salasDisponilesSinFiltro.get(contador);
          int indicador = 0;
          for(Recurso recurso:recursos){
            String queryRecurso = "select esquema.filtrarRecursos(?,?)";
            CallableStatement consultaRecurso = conexion.prepareCall(queryRecurso);
            consultaRecurso.setString(1, recurso.getNombreRecurso());
            consultaRecurso.setString(2,identificador);
            ResultSet respuesta = consultaRecurso.executeQuery();
            int bandera = 0;
            while(respuesta.next()){
              bandera = respuesta.getInt(1);
            }
            if(bandera == 0){
              indicador++;
            }
          }
          
          if(indicador==0){
            String cqueryInsert = "insert into esquema.salasDisponiblesUltimo values (?)";
            CallableStatement consultaInsert = conexion.prepareCall(cqueryInsert);
            consultaInsert.setString(1, identificador);
            consultaInsert.execute();
          }
          contador++;
        
        }
      }
      
      //obtener resultado final
      PreparedStatement consultaSalas = conexion.prepareStatement("select * from esquema."
          + "obtenerSalasDisponibles()");
      ResultSet respuestaSalas = consultaSalas.executeQuery();
      while(respuestaSalas.next()){
        salasDisponibles.add(respuestaSalas.getString(1));
      }
      
    }catch(SQLException e){
      System.out.println(e);
    }

    return salasDisponibles;
  }
  
  /**
  * Metodo para validar la existencia de una sala
  * @param idSala
  * @return true si existe, false de lo contrario
  * @throws SQLException
  */
  public boolean existeSala(String idSala) throws SQLException{
    Connection conexion = ConexionBD.getConexion();
    String query = "select identificador from esquema.sala "
        + "where identificador=?";
    PreparedStatement validar = conexion.prepareStatement(query);
    validar.setString(1, idSala);
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
  * Metodo para obtener el proximo id para las salas
  * @return proximo id sala
  */
  public int obtenerProximoIdSala(){
    int proximoIdSala = 0;
    try{
      Connection conexion = ConexionBD.getConexion();
      PreparedStatement consulta = conexion.prepareStatement("select esquema.obtenerProximoIdSala()");
      ResultSet respuesta = consulta.executeQuery();
      
      while(respuesta.next()){
        proximoIdSala = respuesta.getInt(1);
      }
      return proximoIdSala;
    
    }catch(SQLException e){
      System.out.println(e);
    }
    return proximoIdSala;
  }
  
  /**
   * Metodo para validar validez de horario de exepcion con motivo
   * @param pIdentificador identificador de la sala
   * @param pMotivo motivo a verificar
   * @return true de ser verdad, false de lo contrario
   * @throws SQLException 
   */
  public boolean validarSalaMotivo(String pIdentificador, String pMotivo) throws SQLException{
    Connection conexion = ConexionBD.getConexion();
    PreparedStatement consulta = conexion.prepareStatement(""
        +"select * from esquema.horarioExcepcion where idSala=? and detalle=?");
    consulta.setString(1, pIdentificador);
    consulta.setString(2, pMotivo);
    ResultSet respuesta = consulta.executeQuery();
    
    return respuesta.next();
  }
  
  /**
   * Metodo para consultar el top 5 de salas usadas
   * @return lista de salas 
   */
  public String[][] consultarTopSalasUsadas(){
    String[][] salasUsadas = new String[5][2];
    try{
      Connection conexion = ConexionBD.getConexion();
      PreparedStatement consulta = conexion.prepareStatement("select * from esquema."
          + "consultarTopSalasUsadas()");
      ResultSet respuesta = consulta.executeQuery();
      int i =0;
      while(respuesta.next()){
        salasUsadas[i][0] = respuesta.getString("identificador");
        salasUsadas[i][1] = respuesta.getString("porcentaje");
        i++;
      }
      
      }catch(SQLException e){
        System.out.println(e);
      }
    return salasUsadas;
  }
  
  /**
   * Metodo para consultar las puntuaciones de las salas
   * @return lista de salas
   */
  public String[][] consultarSalasPuntuacion(){
    String[][] salasPuntuacion = new String[5][2];
    try{
      Connection conexion = ConexionBD.getConexion();
      PreparedStatement consulta = conexion.prepareStatement("select * from esquema."
          + "consultarTopSalasPuntuacion()");
      ResultSet respuesta = consulta.executeQuery();
      
      int i =0;
      while(respuesta.next()){
        salasPuntuacion[i][0] = respuesta.getString("identificador");
        salasPuntuacion[i][1] = respuesta.getString("calificacion");
        i++;
      }
      
    }catch(SQLException e){
       System.out.println(e);
      }
    return salasPuntuacion;
  }
  
  /**
   * Metodo para consultar top salas usadas segun las carreras
   * @return lista de salas
   */
  public String[][] consultarTopSalasCarreras(){
    String[][] salasUsadasCarreras = new String[5][2];
    try{
      Connection conexion = ConexionBD.getConexion();
      PreparedStatement consulta = conexion.prepareStatement("select * from esquema."
          + "consultarTopSalasCarreras()");
      ResultSet respuesta = consulta.executeQuery();

      int i =0;
      while(respuesta.next()){
        salasUsadasCarreras[i][0] = respuesta.getString("carrera");
        salasUsadasCarreras[i][1] = respuesta.getString("porcentaje");
        i++;
      }
             
      }catch(SQLException e){
        System.out.println(e);
      }
    return salasUsadasCarreras;
  }
 
}
