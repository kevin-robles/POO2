/**
 *Clase para objetos tipo HorarioExcepcion
 * 
 * @author Daniel Quirós, Kevin Robles y Óscar Trejos
 * @version 1.0
 */
package modelo;
import java.sql.Date;

public class HorarioExcepcion {
  private String detalle;
  private java.sql.Date dia;
  private String horaInicio;
  private String horaFinal;
  
  /**
  *
  * Constructor vacío para darle valores por defecto
  */
  public HorarioExcepcion(){
  }

  /**
   * Metodo constructor con parametros 
   * @param pDetalle
   * @param pDia
   * @param pHoraInicio
   * @param pHoraFinal 
   */
  public HorarioExcepcion(String pDetalle, Date pDia, String pHoraInicio, String pHoraFinal) {
    this.detalle = pDetalle;
    this.dia = pDia;
    this.horaInicio = pHoraInicio;
    this.horaFinal = pHoraFinal;
  }
  

  public String getDetalle() {
    return detalle;
  }
  
  public void setDetalle(String pDetalle) {
    this.detalle = pDetalle;
  }

  public java.sql.Date getDia() {
    return dia;
  }
  
  public void setDia(java.sql.Date pDia) {
    this.dia = pDia;
  }

  public String getHoraInicio() {
    return horaInicio;
  }
  
  public void setHoraInicio(String pHoraInicio) {
    this.horaInicio = pHoraInicio;
  }

  public String getHoraFinal() {
    return horaFinal;
  } 

  public void setHoraFinal(String pHoraFinal) {
    this.horaFinal = pHoraFinal;
  }
 
  /**
  * Representacion en cadena de caracteres
  */
  public String toString(){
    String msg="";
    msg += "Detalle: " + getDetalle() + "\n";
    msg += "Dia: " + getDia()+"\n";
    msg += "Hora inicio: " + getHoraInicio()+"\n";
    msg += "Hora Final: " + getHoraFinal()+"\n";
    return msg;
  }
  
  /**
   * Metodo para determinar si otro objeto es igual
   * @param obj
   * @return 
   */
  public boolean equals(Object obj){
    if(this ==  obj){
      return true;
    }  
    if(obj == null){
      return false;
    }
    HorarioExcepcion horario = (HorarioExcepcion) obj;
    return horario.equals(horario.detalle);
  }

}
