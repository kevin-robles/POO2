 /**
 * Clase para objetos tipo Incidente
 * 
 * @author Daniel Quiros, Kevin Robles, Oscar Trejos
 * @version 1.0
 */
package modelo;
import java.util.Date;
import java.util.Objects;

public class Incidente {
  private String tipo;
  private String comentario;
  private Date fecha;
  private int reserva;
  private String sala;

  /**
   * Metodo constructor vacio
   */
  public Incidente() {
  }

  /**
   * Metodo constructor para objetos tipo Incidente
   * @param pTipo tipo de incidente ocurrido
   * @param pComentario comentario adicional
   * @param pFecha fecha del incidente
   */
  public Incidente(String pTipo, String pComentario, Date pFecha) {
    this.tipo = pTipo;
    this.comentario = pComentario;
    this.fecha = pFecha;
  }

  public String getTipo() {
    return tipo;
  }
  
  public void setTipo(String pTipo) {
    this.tipo = pTipo;
  }
  
  public String getComentario() {
    return comentario;
  }
  
  public void setComentario(String pComentario) {
    this.comentario = pComentario;
  }

  public Date getFecha() {
    return fecha;
  }
  
  public String getSala() {
    return sala;
  }
  
  public int getReserva(){
    return reserva;
  }

  public void setFecha(Date pFecha) {
    this.fecha = pFecha;
  }
  

  public void setReserva(int pReserva) {
    this.reserva = pReserva;
  }
  
  public void setSala(String pSala) {
    this.sala = pSala;
  }
  
  /**
   * Metodo para obtener objeto en cadena de caracteres
   * @return objeto en caracteres
   */
  public String toString() {
    return "tipo=\t" + tipo + "\n comentario=\t" + comentario + "\n fecha=\t" + fecha + '\n';
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
    final Incidente other = (Incidente) obj;
    if (!Objects.equals(this.tipo, other.tipo)) {
      return false;
    }
    if (!Objects.equals(this.comentario, other.comentario)) {
      return false;
    }
    if (!Objects.equals(this.fecha, other.fecha)) {
       return false;
    }
    return true;
  }
  
  
  
  

  

  
  
  
  
  
  
  
  
    
}
