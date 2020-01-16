/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ReservaDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oscar Andres
 */
public class ControladorReservaCancelar extends HttpServlet {
    ReservaDao dao = new ReservaDao();

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
              System.out.println(1);
              return;
            }
            int idReserva = 0;
            try{
              idReserva = Integer.parseInt(request.getParameter("txtidreserva"));
            }catch(NumberFormatException e){
              //no es numero
              System.out.println(2);
              return;
            }
            //Si existe o no reserva
            if(dao.validarReserva(idReserva)){
            
                //si reserva ya esta cancelada
                if(!dao.reservaCancelada(idReserva)){
                  
                  //validar si se puede cancelar por la hora
                  if(dao.validarCancelarReserva(idReserva)){

                    //si se completo la cancelacion
                    if(dao.cancelarReserva(idReserva)){
                      //se cancelo la reserva
                      System.out.println(3);
                    }else{
                      //hubo un error 
                      System.out.println(3);
                    }    

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

                    response.getWriter().println("<form class=\"form-sign\" action=\"CancelarReserva.xhtml\" method=\"POST\">");
                    response.getWriter().println("<h2 class=\"card-body\">La reserva se debe cancelar minimo con una hora de antiicipacion</h2>");
                    response.getWriter().println("<input type=\"submit\" value=\"Volver\" class=\"btn btn-primary btn-block\" > </input>");

                    response.getWriter().println("</div>");
                    response.getWriter().println("</div>");
                    response.getWriter().println("</div>");

                    response.getWriter().println("</h:body>");
                    response.getWriter().println("</html>");
                  }

                }else{
                  response.getWriter().println("<!DOCTYPE html>");
                  response.getWriter().println("<html xmlns=\"http://www.w3.org/1999/xhtml\" \n" +
    "                 xmlns:h=\"http://xmlns.jcp.org/jsf/html\">");
                  response.getWriter().println("<h:head>");
                  response.getWriter().println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> </meta>");
                  response.getWriter().println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\" \n" +
    "                 integrity=\"sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh\" crossorigin=\"anonymous\"> </link>");
                  response.getWriter().println("<title>BibliotecaTec</title>");            
                  response.getWriter().println("</h:head>");
                  response.getWriter().println("<h:body>");
                  response.getWriter().println("<div class=\"container mt-4 col-lg-7\">");
                  response.getWriter().println("<div class=\"card col-sm-7\" >");
                  response.getWriter().println("<div class=\"card-body\" class=\"card-body text-center\">");

                  response.getWriter().println("<form class=\"form-sign\" action=\"ReservaCancelar.xhtml\" method=\"POST\">");
                  response.getWriter().println("<h2 class=\"card-body\">La reserva ya ha sido cancelada</h2>");
                  response.getWriter().println("<input type=\"submit\" value=\"Volver\" class=\"btn btn-primary btn-block\" > </input>");

                  response.getWriter().println("</div>");
                  response.getWriter().println("</div>");
                  response.getWriter().println("</div>");

                  response.getWriter().println("</h:body>");
                  response.getWriter().println("</html>");
                }
            }else{
              response.getWriter().println("<!DOCTYPE html>");
              response.getWriter().println("<html xmlns=\"http://www.w3.org/1999/xhtml\" \n" +
    "             xmlns:h=\"http://xmlns.jcp.org/jsf/html\">");
              response.getWriter().println("<h:head>");
              response.getWriter().println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> </meta>");
              response.getWriter().println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\" \n" +
    "             integrity=\"sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh\" crossorigin=\"anonymous\"> </link>");
              response.getWriter().println("<title>BibliotecaTec</title>");            
              response.getWriter().println("</h:head>");
              response.getWriter().println("<h:body>");
              response.getWriter().println("<div class=\"container mt-4 col-lg-7\">");
              response.getWriter().println("<div class=\"card col-sm-7\" >");
              response.getWriter().println("<div class=\"card-body\" class=\"card-body text-center\">");

              response.getWriter().println("<form class=\"form-sign\" action=\"ReservaCancelar.xhtml\" method=\"POST\">");
              response.getWriter().println("<h2 class=\"card-body\">La reserva no existe</h2>");
              response.getWriter().println("<input type=\"submit\" value=\"Volver\" class=\"btn btn-primary btn-block\" > </input>");                response.getWriter().println("</div>");
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
