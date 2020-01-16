/**
 *Clase para objetos tipo Recurso
 * 
 * @author Daniel Quirós, Kevin Robles y Óscar Trejos
 * @version 1.0
 */
package modelo;

public class Recurso {
    
  private String nombreRecurso;

  /**
  * Constructor vacío para darle valores por defecto
  */
  public Recurso(){
  }
  
  /**
  * Metodo constructor con parametros
  * @param pNombreRecurso nombre del recurso
  */
  public Recurso(String pNombreRecurso){
   this.nombreRecurso = pNombreRecurso;
  }

  public String getNombreRecurso() {
    return nombreRecurso;
  }
  
  
  
  /**
  * Representacion en cadena de caracteres
  */
  public String toString(){
    String msg="";
    msg += "Nombre: " + getNombreRecurso() + "\n";
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
    Recurso recurso = (Recurso) obj;
    return nombreRecurso.equals(recurso.nombreRecurso);
  }
}
