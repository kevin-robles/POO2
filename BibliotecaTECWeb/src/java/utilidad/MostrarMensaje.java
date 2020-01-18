package utilidad;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

public class MostrarMensaje {
  public void showMessage(HttpServletResponse response, String mensaje,String to) throws IOException{
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

    response.getWriter().println("<form class=\"form-sign\" action=\""+to+"\" method=\"POST\">");
    response.getWriter().println("<h2 class=\"card-body\">"+mensaje+"</h2>");
    response.getWriter().println("<input type=\"submit\" value=\"Volver\" class=\"btn btn-primary btn-block\" > </input>");

    response.getWriter().println("</div>");
    response.getWriter().println("</div>");
    response.getWriter().println("</div>");

    response.getWriter().println("</h:body>");
    response.getWriter().println("</html>");
  }   
}
