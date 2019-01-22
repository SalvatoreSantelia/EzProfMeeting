package myJava.model.beans;

import java.io.Serializable;

public class Studente extends User implements Serializable {

    private int idStudente;
    private String nomeStudente;
    private String cognomeStudente;
    private String matricola;
    private String emailStudente;
    private String telefonoStudente;
    private int numAssenza;

    public Studente() {
    }

    public Studente(int idStudente, String nomeStudente, String cognomeStudente, String matricola, String emailStudente, String telefonoStudente, int numAssenza) {
        this.idStudente = idStudente;
        this.nomeStudente = nomeStudente;
        this.cognomeStudente = cognomeStudente;
        this.matricola = matricola;
        this.emailStudente = emailStudente;
        this.telefonoStudente = telefonoStudente;
        this.numAssenza = numAssenza;
    }

    public int getIdStudente() {
        return idStudente;
    }

    public void setIdStudente(int idStudente) {
        this.idStudente = idStudente;
    }

    public String getNomeStudente() {
        return nomeStudente;
    }

    public void setNomeStudente(String nomeStudente) {
        this.nomeStudente = nomeStudente;
    }

    public String getCognomeStudente() {
        return cognomeStudente;
    }

    public void setCognomeStudente(String cognomeStudente) {
        this.cognomeStudente = cognomeStudente;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public String getEmailStudente() {
        return emailStudente;
    }

    public void setEmailStudente(String emailStudente) {
        this.emailStudente = emailStudente;
    }

    public String getTelefonoStudente() {
        return telefonoStudente;
    }

    public void setTelefonoStudente(String telefonoStudente) {
        this.telefonoStudente = telefonoStudente;
    }

    public int getNumAssenza() {
        return numAssenza;
    }

    public void setNumAssenza(int numAssenza) {
        this.numAssenza = numAssenza;
    }

    @Override
    public String toString() {
        return "Studente{" +
                "idStudente=" + idStudente +
                ", nomeStudente='" + nomeStudente + '\'' +
                ", cognomeStudente='" + cognomeStudente + '\'' +
                ", matricola='" + matricola + '\'' +
                ", emailStudente='" + emailStudente + '\'' +
                ", telefonoStudente='" + telefonoStudente + '\'' +
                ", numAssenza=" + numAssenza +
                '}';
    }
}
