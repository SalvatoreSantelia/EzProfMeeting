package myJava.model.beans;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class Messaggio implements Serializable {

  private int idMessaggio;
  private Date dataMessaggio;
  private Time orarioMessaggio;
  private String testoMessaggio;
  private int idProfessore;
  private int idStudente;
  private String lato;

  public Messaggio() {
  }

  public Messaggio(String lato, int idMessaggio, Time orarioMessaggio, Date dataMessaggio, String testoMessaggio, int idProfessore, int idStudente) {
    this.idMessaggio = idMessaggio;
    this.dataMessaggio = dataMessaggio;
    this.orarioMessaggio = orarioMessaggio;
    this.testoMessaggio = testoMessaggio;
    this.idProfessore = idProfessore;
    this.idStudente = idStudente;
    this.lato = lato;
  }

  public int getIdMessaggio() {
    return idMessaggio;
  }

  public void setIdMessaggio(int idMessaggio) {
    this.idMessaggio = idMessaggio;
  }

  public Date getDataMessaggio() {
    return dataMessaggio;
  }

  public void setDataMessaggio(Date dataMessaggio) {
    this.dataMessaggio = dataMessaggio;
  }

  public String getTestoMessaggio() {
    return testoMessaggio;
  }

  public void setTestoMessaggio(String testoMessaggio) {
    this.testoMessaggio = testoMessaggio;
  }

  public int getIdProfessore() {
    return idProfessore;
  }

  public Time getOrarioMessaggio() {
    return orarioMessaggio;
  }

  public void setOrarioMessaggio(Time orarioMessaggio) {
    this.orarioMessaggio = orarioMessaggio;
  }

  public void setIdProfessore(int idProfessore) {
    this.idProfessore = idProfessore;
  }

  public int getIdStudente() {
    return idStudente;
  }

  public void setIdStudente(int idStudente) {
    this.idStudente = idStudente;
  }

  public String getLato() {
    return lato;
  }

  public void setLato(String lato) {
    this.lato = lato;
  }
}
