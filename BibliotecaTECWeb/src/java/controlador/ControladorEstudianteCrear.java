/**
 * Clase para administrar la creación del estudiante.
 * 
 * @author Daniel Quiros, Kevin Robles, Oscar Trejos
 * @version 1.0
 */
package controlador;
import dao.EstudianteDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Estudiante;
import utilidad.MostrarMensaje;

public class ControladorEstudianteCrear extends HttpServlet {
  Estudiante modelo = new Estudiante();
  EstudianteDao dao = new EstudianteDao();
  MostrarMensaje mensaje = new MostrarMensaje();
    
  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
           
      if(dao.existeEstudiante(request.getParameter("txtCarnet"))){
        mensaje.showMessage(response, "El estudiante ya ha sido ingresad", "IngresarEstudiante.xhtml");
      }else{
        modelo.setCarnet(request.getParameter("txtCarnet"));
        modelo.setNombre(request.getParameter("txtNombre"));
        modelo.setApellido(request.getParameter("txtApellido"));
        modelo.setCarrera(request.getParameter("txtCarrera"));
        modelo.setCorreo(request.getParameter("txtCorreo"));
        modelo.setTelefono(request.getParameter("txtTelefono"));

        dao.registrarEstudiante(modelo);
        
        mensaje.showMessage(response, "El estudiante se agregó con éxito", "Administrativo.xhtml");
      }
    }
  }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
