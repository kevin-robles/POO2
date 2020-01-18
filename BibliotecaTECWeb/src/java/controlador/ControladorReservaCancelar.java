/**
 * Clase para administrar la cancelación de ùna reserva.
 * 
 * @author Daniel Quiros, Kevin Robles, Oscar Trejos
 * @version 1.0
 */
package controlador;
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
import modelo.Participante;
import utilidad.EnviarCorreo;
import utilidad.MostrarMensaje;

public class ControladorReservaCancelar extends HttpServlet {
  ReservaDao dao = new ReservaDao();
  MostrarMensaje mensaje = new MostrarMensaje();

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   * @throws java.sql.SQLException
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, SQLException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      if(request.getParameter("txtidreserva").equals("")){
        //no se ingreso nada
        mensaje.showMessage(response, "Debe ingresar el número de reserva", "CancelarReserva.xhtml");
        return;
      }
      int idReserva = Integer.parseInt(request.getParameter("txtidreserva"));

      //Si existe o no reserva
      if(!dao.validarReserva(idReserva)){
        mensaje.showMessage(response, "La reserva no existe", "CancelarReserva.xhtml");
        return;
      }

      //si reserva ya esta cancelada
      if(dao.reservaCancelada(idReserva)){
        mensaje.showMessage(response, "La reserva ya ha sido cancelada", "CancelarReserva.xhtml");
        return;
      }

      //validar si se puede cancelar por la hora
      if(!dao.validarCancelarReserva(idReserva)){
        ArrayList<Participante> nuevaLista = dao.obtenerParticipantes(String.valueOf(idReserva));
        
        if(nuevaLista.size() > 0){
          EnviarCorreo correo = new EnviarCorreo();
          int contadorParticipantes = 0;
          while(nuevaLista.size() > contadorParticipantes){
            correo.enviarCorreo(nuevaLista.get(contadorParticipantes).getCorreo(),"Reunión "
                + "Cancelada","La reunión en la sala " + String.valueOf(idReserva) + 
                " ha sido cancelada");
            contadorParticipantes++;
          }
        }
        mensaje.showMessage(response, "Se debe cancelar la reserva al menos una hora antes de su inicio", "CancelarReserva.xhtml");
        return;
      }

      //si se completo la cancelacion
      if(dao.cancelarReserva(idReserva)){
        //se cancelo la reserva
        mensaje.showMessage(response, "Reserva Cancelada", "CancelarReserva.xhtml");
      }else{
        //hubo un error 
        mensaje.showMessage(response, "Hubo un error al cancelar la reserva", "CancelarReserva.xhtml");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorReservaCancelar.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ControladorReservaCancelar.class.getName()).log(Level.SEVERE, null, ex);
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
