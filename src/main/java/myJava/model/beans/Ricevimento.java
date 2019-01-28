package myJava.model.beans;

import java.io.Serializable;

public class Ricevimento implements Serializable {

    private int idRicevimento;
    private String orarioInizio;
    private String orarioFine;
    private String luogo;
    private String data;
    private int idProfessore;

    public Ricevimento() {
    }

    public Ricevimento(int idRicevimento, String orarioInizio, String orarioFine, String luogo, String data, int idProfessore) {
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

    public String getOrarioInizio() {
        return orarioInizio;
    }

    public void setOrarioInizio(String orarioInizio) {
        this.orarioInizio = orarioInizio;
    }

    public String getOrarioFine() {
        return orarioFine;
    }

    public void setOrarioFine(String orarioFine) {
        this.orarioFine = orarioFine;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getIdProfessore() {
        return idProfessore;
    }

    public void setIdProfessore(int idProfessore) {
        this.idProfessore = idProfessore;


    }
}
