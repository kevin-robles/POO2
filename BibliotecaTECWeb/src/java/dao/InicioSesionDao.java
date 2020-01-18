package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utilidad.ConexionBD;

/**
 *
 * @author Accer
 */
public class InicioSesionDao {
    
  public String validarUsuario(String usuario, String contrasena){
    String tipoUsuario = "noExiste";
    try{
      Connection conexion = ConexionBD.getConexion();
      PreparedStatement consulta = conexion.prepareStatement("select tipo from esquema.logInApp "
          + "where usuario=? and contrasena=?");
      consulta.setString(1, usuario);
      consulta.setString(2, contrasena);
      ResultSet respuesta = consulta.executeQuery();
  
      while(respuesta.next()){
        tipoUsuario = respuesta.getString("tipo"); 
      }  
    }catch(SQLException e){
      return "error";
    }
    return tipoUsuario;
  }  
}
