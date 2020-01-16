/**
 * Clase para objetos tipo Sala
 * 
 * @author Daniel Quirós, Kevon Robles, Óscar Trejos
 * @version 1.0
 */
package modelo;
import dao.SalaDao;
import java.util.ArrayList;

public class Sala {
  private String identificador;
  private String ubicacion;
  private int capacidadMaxima;
  private String estado = "Inactiva";
  private int calificacion = 100;
  private static int cantidadSalas = 0;
  private ArrayList<Horario> horariosServicios;
  private ArrayList<HorarioExcepcion> horariosExcepcion;
  private ArrayList<Recurso> recursos;
  
  /**
  * Contructor vacío para crear la clase por defecto
  */
  public Sala(){
    horariosServicios = new ArrayList<Horario>();
    horariosExcepcion = new ArrayList<HorarioExcepcion>();
    SalaDao salaDao = new SalaDao();
    cantidadSalas = salaDao.obtenerProximoIdSala();
  }
  
  /**
   * Metodo constructor con parametros
   * 
  * @param pUbicacion muestra la ubicación de la sala
  * @param pCapacidadMaxima muestra la capacidad maxima de la sala
  */
  public Sala(String pUbicacion, int pCapacidadMaxima){
    this.ubicacion = pUbicacion;
    this.capacidadMaxima = pCapacidadMaxima;
    this.recursos = new ArrayList<Recurso>();
    
  }

  public String getIdentificador() {
    return identificador;
  }

  public void setIdentificador(String pIdentificador) {
    this.identificador = pIdentificador;
  }
  
  
  public String getUbicacion() {
    return ubicacion;
  }
  
  public void setUbicacion(String pUbicacion) {
    this.ubicacion = pUbicacion;
  }

  public String getEstado() {
    return estado;
  }
  
  public void setEstado(String pEstado) {
    this.estado = pEstado;
  }

  public int getCapacidadMaxima() {
    return capacidadMaxima;
  }
  
  public void setCapacidadMaxima(int pCapacidadMaxima) {
    this.capacidadMaxima = pCapacidadMaxima;
  }
  
  public int getCalificacion() {
    return calificacion;
  }
  
  public void setCalificacion(int pCalificacion) {
    this.calificacion = pCalificacion;
  }
  
  public ArrayList<Horario> getHorariosServicios() {
    return horariosServicios;
  }
  
  public void setHorariosServicios(ArrayList<Horario> pHorariosServicios) {
    this.horariosServicios = pHorariosServicios;
  }
  
  public ArrayList<Recurso> getRecursos() {
    return recursos;
  }
  public void setRecursos(ArrayList<Recurso> pRecursos) {
    this.recursos = pRecursos;
  }

  public ArrayList<HorarioExcepcion> getHorariosExcepcion() {
    return horariosExcepcion;
  }

  public void setHorariosExcepcion(ArrayList<HorarioExcepcion> pHorariosExcepcion) {
    this.horariosExcepcion = pHorariosExcepcion;
  }
    
  public static int getCantidadSalas() {
    return cantidadSalas;
  }
  
  public static void setCantidadSalas(int pCantidad){
    Sala.cantidadSalas = pCantidad;
  }
    
  /**
  * Representacion en cadena de caracteres
  * @return objeto en caracteres
  */
  public String toString(){
    String msg="";
    msg += "Identificador: " + getIdentificador() + "\n";
    msg += "Ubicación: " + getUbicacion() + "\n";
    msg += "Capacidad: " + getCapacidadMaxima() + "\n";
    msg += "Estado: " + getEstado() + "\n";
    msg += "Calificación: " + getCalificacion() + "\n\n";
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

    Sala cuenta = (Sala) obj;
    return identificador.equals(cuenta.identificador);

  }
}
