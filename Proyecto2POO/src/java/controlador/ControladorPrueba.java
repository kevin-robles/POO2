package controlador;

import dao.EstudianteDao;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Estudiante;
/**
 *
 * @author Accer
 */
public class ControladorPrueba extends HttpServlet { 
    
    Estudiante estudiante = new Estudiante();
    EstudianteDao dao = new EstudianteDao();
    
    
  public void doPost(HttpServletRequest request, HttpServletResponse response){
      
    String accion = request.getParameter("accion");
    
    if(accion.equalsIgnoreCase("Ingresar")){
      String carnet = request.getParameter("carnet");
      String nombre = request.getParameter("nombre");
      System.out.println(nombre);
      System.out.println(carnet);
      estudiante =dao.consultarDatosEstudiante(carnet);
    }else{
      System.out.println("picha mama");
      request.getRequestDispatcher("index.xhtml");
    }
      
  } 
    
}
