package controlador;
import dao.EstudianteDao;
import dao.IncidenteDao;
import dao.ReservaDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Estudiante;
import modelo.Incidente;
import modelo.Reserva;

public class ControladorEstudianteConsultar extends HttpServlet {

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
              
        System.out.println("si existe");
        modelo = dao.obtenerEstudiante(request.getParameter("txtCarnet"));
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
        response.getWriter().println("<h1 class=\"card-body\">Información de las reservas del estudiante</h1>");
        response.getWriter().println("<div class=\"card-body\">");
            
        //Empiezan los datos
         response.getWriter().println("<div class=\"form-group\">");
         response.getWriter().println("<label>Carnet: "+ modelo.getCarnet() +" </label>");
         response.getWriter().println("</div>");
            
        response.getWriter().println("<div class=\"form-group\">");
        response.getWriter().println("<label>Nombre: "+ modelo.getNombre() +" </label>");
        response.getWriter().println("</div>");
            
        response.getWriter().println("<div class=\"form-group\">");
        response.getWriter().println("<label>Apellido: "+ modelo.getApellido() +" </label>");
        response.getWriter().println("</div>");
            
        response.getWriter().println("<div class=\"form-group\">");
        response.getWriter().println("<label>Carrera: "+ modelo.getCarrera() +" </label>");
        response.getWriter().println("</div>");

        response.getWriter().println("<div class=\"form-group\">");
        response.getWriter().println("<label>Correo: "+ modelo.getCorreo() +" </label>");
        response.getWriter().println("</div>");

        response.getWriter().println("<div class=\"form-group\">");
        response.getWriter().println("<label>Teléfono: "+ modelo.getTelefono() +" </label>");
        response.getWriter().println("</div>");
        response.getWriter().println("<br></br>");
        response.getWriter().println("<br>");

        //reservas
        response.getWriter().println("<div class=\"form-group\">");
        ReservaDao reservaDao = new ReservaDao();
        ArrayList<Reserva> reservasTotales= reservaDao.consultarReservasEstudiante(modelo.getCarnet());

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


        //incidentes
        response.getWriter().println("<div class=\"form-group\">");
        IncidenteDao incidentesDao = new IncidenteDao();
        ArrayList<Incidente> incidentesTotales = new ArrayList();
        try {
          incidentesTotales = dao.consultarIncidentesEstudiante(modelo.getCarnet());
        }catch (SQLException ex) {
          Logger.getLogger(ControladorEstudianteConsultar.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.getWriter().println("<h4>Incidentes </h4>");
        int contadorIncidentes = 0;
        while(incidentesTotales.size() > contadorIncidentes){
          response.getWriter().println("<div class=\"form-group\">");
          response.getWriter().println("<label>Sala: "+ incidentesTotales.get(contadorIncidentes).getSala()+" </label>");
          response.getWriter().println("<br>");
          response.getWriter().println("<label>Comentario: "+ incidentesTotales.get(contadorIncidentes).getComentario()+" </label>");
          response.getWriter().println("<br>");
          response.getWriter().println("<label>Tipo: "+ incidentesTotales.get(contadorIncidentes).getTipo()+" </label>");
          response.getWriter().println("<br>");
          response.getWriter().println("<label>Fecha: "+ incidentesTotales.get(contadorIncidentes).getFecha()+" </label>");
          response.getWriter().println("<br>");
          response.getWriter().println("<label>Reserva: "+ incidentesTotales.get(contadorIncidentes).getReserva()+" </label>");
          response.getWriter().println("<br></br>");
          response.getWriter().println("</div>");
          contadorIncidentes++;
        }
        response.getWriter().println("</div>");

        //terminan los datos
        response.getWriter().println("</div>");
        response.getWriter().println("</div>");
        response.getWriter().println("</div>");


        response.getWriter().println("</h:body>");
        response.getWriter().println("</html>");

      }else{
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html xmlns=\"http://www.w3.org/1999/xhtml\" \n" +
"               xmlns:h=\"http://xmlns.jcp.org/jsf/html\">");
        response.getWriter().println("<h:head>");
        response.getWriter().println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> </meta>");
        response.getWriter().println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\" \n" +
"               integrity=\"sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh\" crossorigin=\"anonymous\"> </link>");
        response.getWriter().println("<title>BibliotecaTec</title>");            
        response.getWriter().println("</h:head>");
        response.getWriter().println("<h:body>");
        response.getWriter().println("<div class=\"container mt-4 col-lg-7\">");
        response.getWriter().println("<div class=\"card col-sm-7\" >");
        response.getWriter().println("<div class=\"card-body\" class=\"card-body text-center\">");

        response.getWriter().println("<form class=\"form-sign\" action=\"ConsultarEstudiante.xhtml\" method=\"POST\">");
        response.getWriter().println("<h2 class=\"card-body\">El estudiante no existe</h2>");
        response.getWriter().println("<input type=\"submit\" value=\"Volver\" class=\"btn btn-primary btn-block\" > </input>");

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
