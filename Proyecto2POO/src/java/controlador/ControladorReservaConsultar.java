/**
 * Clase para administrar la consulta de ùna reserva.
 * 
 * @author Daniel Quiros, Kevin Robles, Oscar Trejos
 * @version 1.0
 */
package controlador;
import dao.EstudianteDao;
import dao.ReservaDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Reserva;
import utilidad.MostrarMensaje;

public class ControladorReservaConsultar extends HttpServlet {
  ReservaDao dao = new ReservaDao();
  Reserva modelo = new Reserva();
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
      EstudianteDao nuevoEstudianteDao= new EstudianteDao();
      
      try (PrintWriter out = response.getWriter()) {
        if(nuevoEstudianteDao.validarEstudiante(request.getParameter("txtCarnet")) == 2){
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
        response.getWriter().println("<div class=\"container mt-4 col-lg-10\">");
        response.getWriter().println("<div class=\"card col-sm-10\" >");
        response.getWriter().println("<h1 class=\"card-body\">Información del estudiante</h1>");
        response.getWriter().println("<div class=\"card-body\">");
          
        //reservas
        response.getWriter().println("<div class=\"form-group\">");
        ReservaDao reservaDao = new ReservaDao();
        ArrayList<Reserva> reservasTotales= reservaDao.consultarReservasEstudiante(request.getParameter("txtCarnet"));
            
        response.getWriter().println("<h4>Reservas </h4>");
        int contador = 0;
        while(reservasTotales.size() > contador){
          response.getWriter().println("<div class=\"form-group\">");
          response.getWriter().println("<label>Asunto: "+ reservasTotales.get(contador).getAsunto() +" </label>");
          response.getWriter().println("<br>");
          response.getWriter().println("<label>Sala: "+ reservasTotales.get(contador).getIdSala()+" </label>");
          response.getWriter().println("<br>");
          response.getWriter().println("<label>Estado: "+ reservasTotales.get(contador).getEstado()+" </label>");
          response.getWriter().println("<br>");
          response.getWriter().println("<label>Hora Inicio: "+ reservasTotales.get(contador).getHoraInicio()+" </label>");
          response.getWriter().println("<br>");
          response.getWriter().println("<label>Hora Fin: "+ reservasTotales.get(contador).getHoraFinal()+" </label>");
          response.getWriter().println("<br>");
          response.getWriter().println("<label>Número reserva: "+ reservasTotales.get(contador).getIdReserva()+" </label>");
          response.getWriter().println("<br></br>");
          response.getWriter().println("</div>");
          contador++;
        }
        
        response.getWriter().println("</div>");
            
        //terminan los datos
        response.getWriter().println("</div>");
        response.getWriter().println("</div>");
        response.getWriter().println("</div>");
           
        response.getWriter().println("</h:body>");
        response.getWriter().println("</html>");
          
        }else{
          mensaje.showMessage(response, "El estudiante no existe", "ConsultarReservaEstudiante.xhtml");
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
