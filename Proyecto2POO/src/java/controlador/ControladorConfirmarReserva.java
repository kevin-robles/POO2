package controlador;
import dao.EstudianteDao;
import dao.ReservaDao;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Estudiante;
import modelo.Reserva;
import utilidad.MostrarMensaje;

@WebServlet(name = "ControladorConfirmarReserva", urlPatterns = {"/ControladorConfirmarReserva"})
public class ControladorConfirmarReserva extends HttpServlet {
  ReservaDao reservaDao = new ReservaDao();
  EstudianteDao estudianteDao = new EstudianteDao();
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
      throws ServletException, IOException, SQLException, ParseException {
    String fechaSolicitudtxt = request.getParameter("txtfechasolicitud");
    String horaInicio = request.getParameter("txthorainicio");
    String horaFinal = request.getParameter("txthorafinal");
    String idSala = request.getParameter("txtidsala");
    String carnetEstudiante = request.getParameter("txtcarnet");
    String asunto = request.getParameter("txtasunto");
    String estado = "Activa";
    int idReserva = reservaDao.obtenerProximoIdReserva();
    System.out.println(fechaSolicitudtxt);
    System.out.println(horaInicio);
    System.out.println(horaFinal);
    System.out.println(idSala);
    System.out.println(carnetEstudiante);
    System.out.println(asunto);
    System.out.println(estado);
    System.out.println(idReserva);
          
    if(!estudianteDao.existeEstudiante(carnetEstudiante)){
      mensaje.showMessage(response, "Estudiante no existe", "CrearReserva.xhtml");
      return;
    }
 
    if(reservaDao.reservasSemanales(carnetEstudiante) >= 3){
      mensaje.showMessage(response, "Estudiante posee más de al menos 3 reservas en esta semana", "CrearReserva.xhtml");
      return;
    }
    
    Estudiante estudiante = estudianteDao.obtenerEstudiante(carnetEstudiante);
          
    Reserva reserva = new Reserva();
    reserva.setAsunto(asunto);
    reserva.setEstado(estado);
    reserva.setFechaSolicitud(new SimpleDateFormat("yyyy-MM-dd").parse(fechaSolicitudtxt));
    reserva.setHoraFinal(horaFinal);
    reserva.setHoraInicio(horaInicio);
    reserva.setIdReserva(idReserva);
    reserva.setIdSala(idSala);
    reserva.setOrganizador(estudiante);
          
    reservaDao.ingresarReserva(reserva);
  
    //se ingreso la reserva
    mensaje.showMessage(response, "Reserva creada", "CrearReserva.xhtml");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorConfirmarReserva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ControladorConfirmarReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorConfirmarReserva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ControladorConfirmarReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
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
