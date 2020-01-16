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
                    //no se puede cancelar la reserva por la hora
                    System.out.println(4);
                  }

                }else{
                  //reserva ya esta cancelada
                  System.out.println(5);
                }
            }else{
              //no existe reserva
              System.out.println(6);
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
