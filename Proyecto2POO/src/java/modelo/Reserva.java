/**
 * Clase para objetos tipo Reserva
 * 
 * @author Daniel Quiros, Kevin Robles, Oscar Trejos
 * @version 1.0
 */
package modelo;
import dao.ReservaDao;
import java.util.ArrayList;
import java.util.Date;

public class Reserva {
  private int idReserva;
  private String estado;
  private String asunto;
  private Date fechaSolicitud;
  private String horaInicio;
  private String horaFinal;
  private static int numeroReservas = 0;
  private Estudiante organizador;
  private Sala salaAsignada;
  private ArrayList<Participante> participantes;
  private ArrayList<Incidente> incidentes;

  /**
   * Metodo constructor vacio
   */
  public Reserva() {
    salaAsignada = new Sala();
    participantes = new ArrayList<Participante>();
    incidentes = new ArrayList<Incidente>();
    ReservaDao reservaDao = new ReservaDao();
  }

  /**
   * MEtodo constructor con parametros
   * @param pEstado
   * @param pAsunto
   * @param pFechaSolicitud
   * @param pHoraInicio
   * @param pHoraFinal
   * @param pOrganizador
   * @param pSalaAsignada 
   */
  public Reserva(String pEstado, String pAsunto, Date pFechaSolicitud, String pHoraInicio, 
      String pHoraFinal, Estudiante pOrganizador, Sala pSalaAsignada) {
    this.estado = pEstado;
    this.asunto = pAsunto;
    this.fechaSolicitud = pFechaSolicitud;
    this.horaInicio = pHoraInicio;
    this.horaFinal = pHoraFinal;
    this.organizador = pOrganizador;
    this.salaAsignada = pSalaAsignada;
    this.idReserva = numeroReservas;
    participantes = new ArrayList<Participante>();
    incidentes = new ArrayList<Incidente>();
  }

  public int getIdReserva() {
    return idReserva;
  }

  public void setIdReserva(int pIdReserva) {
    this.idReserva = pIdReserva;
  }
  
  public String getEstado() {
    return estado;
  }

  public void setEstado(String pEstado) {
    this.estado = pEstado;
  }

  public String getAsunto() {
    return asunto;
  }

  public void setAsunto(String pAsunto) {
    this.asunto = pAsunto;
  }

  public Date getFechaSolicitud() {
    return fechaSolicitud;
  }

  public void setFechaSolicitud(Date pFechaSolicitud) {
    this.fechaSolicitud = pFechaSolicitud;
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
  
  public String getIdSala(){
    return salaAsignada.getIdentificador();
  } 
  
  public void setIdSala(String pIdSala) {
    this.salaAsignada.setIdentificador(pIdSala);
  }
  
  public String getCarnetOrganizador(){
    return organizador.getCarnet();
  }

  public void setIncidentes(ArrayList<Incidente> pIncidentes) {
    this.incidentes = pIncidentes;
  }

  public ArrayList<Participante> getParticipantes() {
    return participantes;
  }
    
 public void setOrganizador(Estudiante pOrganizador){
   this.organizador = pOrganizador;
 }

  /**
   * Metodo para obtener objeto en cadena de caracteres
   * @return objeto en caracteres
   */
  public String toString() {
    return "idReserva=\t" + idReserva + "\n estado=\t" + estado + "\n asunto=\t" + asunto +
        "\n fecha Solicitud=\t" + fechaSolicitud + "\n Hora inicio=\t" + horaInicio +
        "\n Hora fin=\t"+horaFinal +"organizador=\t"+organizador.toString()+ "\n salaAsignada=\t" + 
        salaAsignada.toString() + "\n participantes=\t" + participantes.toString() + 
        "\n incidentes=\t" + incidentes.toString() + '\n';
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
    final Reserva other = (Reserva) obj;
    if (this.idReserva != other.idReserva) {
      return false;
    }
      return true;
  }
     
}
