/**
 * Clase para objetos tipo Participante
 * 
 * @author Daniel Quiros, Kevin Robles, Oscar Trejos
 * @version 1.0
 */
package modelo;
import java.util.Objects;

public class Participante {
  private String correo;
  private String primerNombre;
  private String primerApellido;
  private String segundoApellido;

  /**
   * Metodo constructor vacio
   */
  public Participante() {
  }

  /**
   * Metodo constructor con parametros para objetos tipo Participante
   * @param pCorreo correo electronico del participante
   * @param pPrimerNombre primer nombre del participante
   * @param pPrimerApellido primer apellido del participante
   * @param pPegundoApellido segundo apellido del participante
   */
  public Participante(String pCorreo, String pPrimerNombre, String pPrimerApellido, String 
      pPegundoApellido) {
    this.correo = pCorreo;
    this.primerNombre = pPrimerNombre;
    this.primerApellido = pPrimerApellido;
    this.segundoApellido = pPegundoApellido;
  }

  public String getCorreo() {
    return correo;
  }

  public String getPrimerNombre() {
    return primerNombre;
  }
  
  public void setPrimerNombre(String pPrimerNombre) {
    this.primerNombre = pPrimerNombre;
  }

  public String getPrimerApellido() {
    return primerApellido;
  }

  public void setPrimerApellido(String pPrimerApellido) {
    this.primerApellido = pPrimerApellido;
  }
  
  public String getSegundoApellido() {
    return segundoApellido;
  }

  public void setSegundoApellido(String pSegundoApellido) {
    this.segundoApellido = pSegundoApellido;
  }
  
  public void setCorreo(String pCorreo){
    this.correo = pCorreo;
  }
  /**
   * Metodo para obtener objeto en cadena de caracteres
   * @return objeto en caracteres
   */
  public String toString() {
    return "correo=\t" + correo + "\n primerNombre=\t" + primerNombre + "\n primerApellido=\t" + 
        primerApellido + "\n segundoApellido=\t" + segundoApellido + '}';
  }

  /**
   * Metodo para determinar si dos objetos son iguales
   * @param obj Objeto cualquiera
   * @return true si es el mismo objeto, false de lo contrario
   */
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Participante other = (Participante) obj;
    if (!Objects.equals(this.correo, other.correo)) {
      return false;
    }
    return true;
  }
  
  
  
  
  

  

  
  
  
  
  
  
}
