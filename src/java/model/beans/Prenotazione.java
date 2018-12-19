package java.model.beans;

import java.sql.Time;

public class Prenotazione {

    private int idPrenotazione;
    private String listaStudenti;
    private String motivazione;
    private Time orario;
    private int idProfessore;
    private int idStudente;

    public Prenotazione() {
    }

    public Prenotazione(int idPrenotazione, String listaStudenti, String motivazione, Time orario, int idProfessore, int idStudente) {
        this.idPrenotazione = idPrenotazione;
        this.listaStudenti = listaStudenti;
        this.motivazione = motivazione;
        this.orario = orario;
        this.idProfessore = idProfessore;
        this.idStudente = idStudente;
    }

    public int getIdPrenotazione() {
        return idPrenotazione;
    }

    public void setIdPrenotazione(int idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
    }

    public String getListaStudenti() {
        return listaStudenti;
    }

    public void setListaStudenti(String listaStudenti) {
        this.listaStudenti = listaStudenti;
    }

    public String getMotivazione() {
        return motivazione;
    }

    public void setMotivazione(String motivazione) {
        this.motivazione = motivazione;
    }

    public Time getOrario() {
        return orario;
    }

    public void setOrario(Time orario) {
        this.orario = orario;
    }

    public int getIdProfessore() {
        return idProfessore;
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
}
