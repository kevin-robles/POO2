/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import dao.SalaDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Recurso;

/**
 *
 * @author Oscar Andres
 */
public class ControladorReservaCrear extends HttpServlet {
    SalaDao dao = new SalaDao();

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String fechaSolicitudtxt = request.getParameter("txtfechasolicitud");
            String horaInicio = request.getParameter("txthorainicio");
            String horaFinal = request.getParameter("txthorafinal");
            String CapacidadMinimatxtx = request.getParameter("txtcapacidadminima");
            String [] recursostxt = request.getParameterValues("recursos");
            Date fechaSolicitud = null;
            int capacidadMinima = 0;
            
            if(fechaSolicitudtxt.equals("")){
              System.out.println(1);
              return;
              //no ingreso fecha
            }else{
              fechaSolicitud = Date.valueOf(fechaSolicitudtxt);  
            }
            if(CapacidadMinimatxtx.equals("")){
              System.out.println(2);
              return;
              //no ingreso capacidad minima
            }else{
              capacidadMinima =  Integer.parseInt(CapacidadMinimatxtx);
            }
            ArrayList<Recurso> recursos =  new ArrayList<Recurso>();
            for (int i = 0; i < recursostxt.length; i++) {
              recursos.add(new Recurso(recursostxt[i]));
            }
            
            ArrayList<String> salasDisponibles = dao.obtenerSalasDisponibles(fechaSolicitud, horaInicio, horaFinal, capacidadMinima, recursos);
            System.out.println(salasDisponibles);
            if(salasDisponibles.isEmpty()){
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
              
              response.getWriter().println("<form class=\"form-sign\" action=\"CrearReserva.xhtml\" method=\"POST\">");
              response.getWriter().println("<h2 class=\"card-body\">No hay salas bajo los parámetros establecidos</h2>");
              response.getWriter().println("<input type=\"submit\" value=\"Volver\" class=\"btn btn-primary btn-block\" > </input>");
            
              response.getWriter().println("</div>");
              response.getWriter().println("</div>");
              response.getWriter().println("</div>");
            
              response.getWriter().println("</h:body>");
              response.getWriter().println("</html>");
              
              }else{
                response.sendRedirect("CrearReserva2.xhtml");
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
        } catch (ParseException ex) {
            Logger.getLogger(ControladorReservaCrear.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(ControladorReservaCrear.class.getName()).log(Level.SEVERE, null, ex);
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
