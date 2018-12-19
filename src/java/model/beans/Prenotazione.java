package java.model.beans;

import java.sql.Time;

public class Prenotazione {

    private int idPrenotazione;
    private String listaStudenti;
    private String motivazione;
    private Time orario;
    private int idRicevimento;
    private int idStudente;
    private boolean presenza;

    public Prenotazione() {
    }

    public Prenotazione(int idPrenotazione, String listaStudenti, String motivazione, Time orario, int idRicevimento, int idStudente, boolean presenza) {
        this.idPrenotazione = idPrenotazione;
        this.listaStudenti = listaStudenti;
        this.motivazione = motivazione;
        this.orario = orario;
        this.idRicevimento = idRicevimento;
        this.idStudente = idStudente;
        this.presenza = presenza;
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

    public int getIdRicevimento() {
        return idRicevimento;
    }

    public void setIdRicevimento(int idRicevimento) {
        this.idRicevimento = idRicevimento;
    }

    public int getIdStudente() {
        return idStudente;
    }

    public void setIdStudente(int idStudente) {
        this.idStudente = idStudente;
    }

    public boolean isPresenza() {
        return presenza;
    }

    public void setPresenza(boolean presenza) {
        this.presenza = presenza;
    }
}
