package controlador;
import dao.EstudianteDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Estudiante;

public class ControladorEstudianteCrear extends HttpServlet {
  Estudiante modelo = new Estudiante();
  EstudianteDao dao = new EstudianteDao();
    
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
 
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html xmlns=\"http://www.w3.org/1999/xhtml\" \n" +
            "xmlns:h=\"http://xmlns.jcp.org/jsf/html\">");
        response.getWriter().println("<h:head>");
        response.getWriter().println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> </meta>");
        response.getWriter().println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\" \n" +
            "integrity=\"sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh\" crossorigin=\"anonymous\"> </link>");
        response.getWriter().println("<title>BibliotecaTec</title>");            
        response.getWriter().println("</h:head>");
        response.getWriter().println("<h:body>");
        response.getWriter().println("<div class=\"container mt-4 col-lg-7\">");
        response.getWriter().println("<div class=\"card col-sm-7\" >");
        response.getWriter().println("<div class=\"card-body\" class=\"card-body text-center\">");

        response.getWriter().println("<form class=\"form-sign\" action=\"IngresarEstudiante.xhtml\" method=\"POST\">");
        response.getWriter().println("<h2 class=\"card-body\">El estudiante ya ha sido ingresado</h2>");
        response.getWriter().println("<input type=\"submit\" value=\"Volver\" class=\"btn btn-primary btn-block\" > </input>");

        response.getWriter().println("</div>");
        response.getWriter().println("</div>");
        response.getWriter().println("</div>");

        response.getWriter().println("</h:body>");
        response.getWriter().println("</html>");

      }else{
        modelo.setCarnet(request.getParameter("txtCarnet"));
        modelo.setNombre(request.getParameter("txtNombre"));
        modelo.setApellido(request.getParameter("txtApellido"));
        modelo.setCarrera(request.getParameter("txtCarrera"));
        modelo.setCorreo(request.getParameter("txtCorreo"));
        modelo.setTelefono(request.getParameter("txtTelefono"));

        dao.registrarEstudiante(modelo);
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html xmlns=\"http://www.w3.org/1999/xhtml\" \n" +
            "xmlns:h=\"http://xmlns.jcp.org/jsf/html\">");
        response.getWriter().println("<h:head>");
        response.getWriter().println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> </meta>");
        response.getWriter().println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\" \n" +
           "integrity=\"sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh\" crossorigin=\"anonymous\"> </link>");
        response.getWriter().println("<title>BibliotecaTec</title>");            
        response.getWriter().println("</h:head>");
        response.getWriter().println("<h:body>");
        response.getWriter().println("<div class=\"container mt-4 col-lg-7\">");
        response.getWriter().println("<div class=\"card col-sm-7\" >");
        response.getWriter().println("<div class=\"card-body\" class=\"card-body text-center\">");

        response.getWriter().println("<form class=\"form-sign\" action=\"Administrativo.xhtml\" method=\"POST\">");
        response.getWriter().println("<h2 class=\"card-body\">El estudiante se agregó con éxito</h2>");
        response.getWriter().println("<input type=\"submit\" value=\"Ok\" class=\"btn btn-primary btn-block\" > </input>");

        response.getWriter().println("</div>");
        response.getWriter().println("</div>");
        response.getWriter().println("</div>");

        response.getWriter().println("</h:body>");
        response.getWriter().println("</html>");
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
