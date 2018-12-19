package java.model.beans;

import java.sql.Date;
import java.sql.Time;

public class Ricevimento {

    private int idRicevimento;
    private Time orarioInizio;
    private Time orarioFine;
    private String luogo;
    private Date data;
    private int idProfessore;

    public Ricevimento() {
    }

    public Ricevimento(int idRicevimento, Time orarioInizio, Time orarioFine, String luogo, Date data, int idProfessore) {
        this.idRicevimento = idRicevimento;
        this.orarioInizio = orarioInizio;
        this.orarioFine = orarioFine;
        this.luogo = luogo;
        this.data = data;
        this.idProfessore = idProfessore;
    }

    public int getIdRicevimento() {
        return idRicevimento;
    }

    public void setIdRicevimento(int idRicevimento) {
        this.idRicevimento = idRicevimento;
    }

    public Time getOrarioInizio() {
        return orarioInizio;
    }

    public void setOrarioInizio(Time orarioInizio) {
        this.orarioInizio = orarioInizio;
    }

    public Time getOrarioFine() {
        return orarioFine;
    }

    public void setOrarioFine(Time orarioFine) {
        this.orarioFine = orarioFine;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getIdProfessore() {
        return idProfessore;
    }

    public void setIdProfessore(int idProfessore) {
        this.idProfessore = idProfessore;
    }
}
