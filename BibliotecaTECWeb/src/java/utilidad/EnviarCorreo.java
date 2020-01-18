/**
 * Clase para enviar un correo electronico
 * 
 * @author Daniel Quiros, Kevin Robles, Oscar Trejos
 * @version 1.0
 */
package utilidad;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeMessage;

public class EnviarCorreo {
   /**
    * Metodo para enviar correo electronico
    * @param to destinatario
    * @param sub asunto del correo
    * @param msg mensaje del correo
    * @return booleano que indica si se envio el correo o no
    */
  public boolean enviarCorreo(String to, String sub,String msg){
    Properties props = new Properties();
       
    final String user = "proyectobasedatosati@gmail.com";
    final String pass = "progra123";     
        
    //acá se crea la conexion con el ap de java para correos
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");	
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
        
    //CREA UN AUTENTICADOR EN PARA PODER REALIZAAR LA VALIDACIÓN
    Session session = Session.getDefaultInstance(props,new Authenticator() 
    {
      @Override
      public PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(user,pass);
      }
    });

    try{
      Message message = new MimeMessage(session);
      message.setText(msg);
      message.setFrom(new InternetAddress(user));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
      message.setSubject(sub);
      Transport.send(message);
      return true;
    } 
    catch (MessagingException e){
      System.out.println(e);
      return false;
    }
  }
    
}
