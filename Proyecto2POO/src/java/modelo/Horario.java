/**
 * Clase para objetos tipo Horario
 * 
 * @author Daniel Quirós, Kevin Robles, Óscar Trejos
 * @version 1.0
 */
package modelo;
import java.util.Arrays;

public class Horario {
    
  private String[] horaApertura = new String[7];
  private String[] horaCierre = new String[7];
  
  /**
  * Constructor vacío
  */
  public Horario(){
  }
  
  /**
   * Metodo constructor con parametros
  * @param pLunes
  * @param pMartes
  * @param pMiercoles
  * @param pJueves
  * @param pViernes
  * @param pSabado
  * @param pDomingo
  * recibe cada uno de los horarios, apertura y cierre [0] es apertura [1] es cierre
  */
  public Horario(String[] pLunes,String[] pMartes,String[] pMiercoles,String[] pJueves,String[] 
      pViernes,String[] pSabado,String[] pDomingo){
    this.horaApertura[0] = "Lunes" + pLunes[0];
    this.horaApertura[1] = "Martes" + pMartes[0];
    this.horaApertura[2] = "Miércoles" + pMiercoles[0];
    this.horaApertura[3] = "Jueves" + pJueves[0];
    this.horaApertura[4] = "Viernes" + pViernes[0];
    this.horaApertura[5] = "Sábado" + pSabado[0];
    this.horaApertura[6] = "Domingo" + pDomingo[0];
    
    this.horaCierre[0] = "Lunes" + pLunes[1];
    this.horaCierre[1] = "Martes" + pMartes[1];
    this.horaCierre[2] = "Miércoles" + pMiercoles[1];
    this.horaCierre[3] = "Jueves" + pJueves[1];
    this.horaCierre[4] = "Viernes" + pViernes[1];
    this.horaCierre[5] = "Sábado" + pSabado[1];
    this.horaCierre[6] = "Domingo" + pDomingo[1];
  }

  /**
   * Metodo para objeter el horario de apertura de un dia especifico de la semana
   * @param pDia nombre del dia a buscar
   * @return horario del dia solicitado
   */
  public String getHoraApertura(String pDia) {
    String infoTxt = "";
    switch(pDia){
      case "Lunes":
        infoTxt += this.horaApertura[0];
        break;
      case "Martes":
        infoTxt += this.horaApertura[1];
        break;
      case "Miércoles":
        infoTxt += this.horaApertura[2];
        break;
      case "Jueves":
        infoTxt += this.horaApertura[3];
        break;
      case "Viernes":
        infoTxt += this.horaApertura[4];
        break;
      case "Sábado":
        infoTxt += this.horaApertura[5];
        break;
      case "Domingo":
        infoTxt += this.horaApertura[6];
        break;
    }
    return infoTxt;
  }
  
  /**
   * Metodo para modificar el horario de apertura de un dia especidifo
   * @param pDia nombre del dia a modificar
   * @param pHoraApertura nuevo horario
   */
  public void setHoraApertura(String pDia, String pHoraApertura) {
    switch(pDia){
      case "Lunes":
        this.horaApertura[0] = pHoraApertura;
        break;
      case "Martes":
        this.horaApertura[1] = pHoraApertura;
        break;
      case "Miércoles":
        this.horaApertura[2] = pHoraApertura;
        break;
      case "Jueves":
        this.horaApertura[3] = pHoraApertura;
        break;
      case "Viernes":
        this.horaApertura[4] = pHoraApertura;
        break;
      case "Sábado":
        this.horaApertura[5] = pHoraApertura;
        break;
      case "Domingo":
        this.horaApertura[6] = pHoraApertura;
        break;
    }
  }

  /**
   * Metodo para objeter el horario de cierre de un dia especifico de la semana
   * @param pDia nombre del dia a buscar
   * @return horario del dia solicitado
   */
  public String getHoraCierre(String pDia) {
    String infoTxt = "";
    switch(pDia){
      case "Lunes":
        infoTxt += this.horaCierre[0];
        break;
      case "Martes":
        infoTxt += this.horaCierre[1];
        break;
      case "Miércoles":
        infoTxt += this.horaCierre[2];
        break;
      case "Jueves":
        infoTxt += this.horaCierre[3];
        break;
      case "Viernes":
        infoTxt += this.horaCierre[4];
        break;
      case "Sábado":
        infoTxt += this.horaCierre[5];
        break;
      case "Domingo":
        infoTxt += this.horaCierre[6];
        break;
    }
    return infoTxt;
  }
  
  /**
   * Metodo para modificar el horario de cierre de un dia especidifo
   * @param pDia nombre del dia a modificar
   * @param pHoraCierre nuevo horario
   */
  public void setHoraCierre(String pDia, String pHoraCierre) {
    switch(pDia){
      case "Lunes":
        this.horaCierre[0] = pHoraCierre;
        break;
      case "Martes":
        this.horaCierre[1] = pHoraCierre;
        break;
      case "Miércoles":
        this.horaCierre[2] = pHoraCierre;
        break;
      case "Jueves":
        this.horaCierre[3] = pHoraCierre ;
        break;
      case "Viernes":
        this.horaCierre[4] = pHoraCierre  ;
        break;
      case "Sábado":
        this.horaCierre[5] = pHoraCierre ;
        break;
      case "Domingo":
        this.horaCierre[6] = pHoraCierre;
        break;
    }
  }
  
  /**
   * Representacion del objeto en cadena de caracteres
   * @return objeto en caracteres
   */
  public String toString(){
    String msg="";
    msg += "Lunes\n Apertura: "+getHoraApertura("Lunes")+" Cierre: "+ getHoraCierre("Lunes") +"\n";
    msg += "Martes\n Apertura: "+getHoraApertura("Martes")+" Cierre: "+getHoraCierre("Martes")+
        "\n";
    msg += "Mipercoles\n Apertura: " + getHoraApertura("Miércoles") + " Cierre: "+ getHoraCierre
        ("Miércoles") +"\n";
    msg += "Jueves\n Apertura: " + getHoraApertura("Jueves") + " Cierre: "+ getHoraCierre("Jueves") 
        +"\n";
    msg += "Viernes\n Apertura: "+getHoraApertura("Viernes")+" Cierre: "+getHoraCierre("Viernes")
        +"\n";
    msg += "Sábado\n Apertura: " + getHoraApertura("Sábado") + " Cierre: "+ getHoraCierre("Sábado")
        +"\n";
    msg += "Domingo\n Apertura: "+getHoraApertura("Domingo")+" Cierre: "+getHoraCierre("Domingo") 
        +"\n\n";
    return msg;
  }
    
  /**
  * @param obj recibe el objeto para la comparación
  * @return valor de verdad de la comparacion entre objetos
  */
  public boolean equals(Object obj){
    if(this ==  obj){
      return true;
    }  
    if(obj == null){
      return false;
    }

    Horario horario = (Horario) obj;
    return Arrays.equals(horaCierre, horario.horaCierre) && Arrays.equals(horaApertura, 
        horario.horaApertura);
  }
}
