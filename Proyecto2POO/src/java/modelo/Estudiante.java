/**
 * Clase para objetos tipo Estudiante
 * 
 * @author Daniel Quiros, Kevin Robles, Oscar Trejos
 * @version 1.0
 */
package modelo;
import java.util.ArrayList;
import java.util.Objects;

public class Estudiante {
  private String carnet;
  private String nombre;
  private String apellido;
  private String carrera;
  private String correo;
  private int calificacion = 100;
  private String telefono;
  private ArrayList<Reserva> misReservas;

  /**
   * Metodo constructor vacio
   */
  public Estudiante() {
  }
  
  /**
   * Metodo constructor de estudiante con parametros
   * @param pCarnet
   * @param pNombre
   * @param pApellido
   * @param pCarrera
   * @param pCorreo
   * @param pTelefono 
   */
  public Estudiante(String pCarnet, String pNombre, String pApellido, String pCarrera, String 
      pCorreo, String pTelefono) {
    this.carnet = pCarnet;
    this.nombre = pNombre;
    this.apellido = pApellido;
    this.carrera = pCarrera;
    this.correo = pCorreo;
    this.telefono = pTelefono;
    this.misReservas = new ArrayList<Reserva>();
  }

  public String getCarnet() {
    return carnet;
  }

  public void setCarnet(String pCarnet) {
    this.carnet = pCarnet;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String pNombre) {
    this.nombre = pNombre;
  }
  
  public String getApellido() {
    return apellido;
  }

  public void setApellido(String pApellido) {
    this.apellido = pApellido;
  }

  public String getCarrera() {
    return carrera;
  }
  
  public void setCarrera(String pCarrera) {
    this.carrera = pCarrera;
  }

  public String getCorreo() {
    return correo;
  }
  
  public void setCorreo(String pCorreo) {
    this.correo = pCorreo;
  }

  public int getCalificacion() {
    return calificacion;
  }
  
  public void setCalificacion(int pCalificacion) {
    this.calificacion = pCalificacion;
  }

  public String getTelefono() {
    return telefono;
  }
  
  public void setTelefono(String pTelefono) {
    this.telefono = pTelefono;
  }
  
  public void setMisReservas(ArrayList<Reserva> pMisReservas) {
    this.misReservas = pMisReservas;
  }

  /**
   * Metodo para obtener objeto en cadena de caracteres
   * @return objeto en caracteres
   */
  public String toString() {
    return "carnet=\t" + carnet + "\n nombre=\t" + nombre + "\n apellido=\t" + apellido + 
            "\n carrera=\t" + carrera + "\n correo=\t" + correo + "\n calificacion=\t" + 
            calificacion + "\n telefono=\t" + telefono + '\n';
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
    final Estudiante other = (Estudiante) obj;
    if (!Objects.equals(this.carnet, other.carnet)) {
      return false;
    }
    return true;
  }
  
}
