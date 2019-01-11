package myJava.model.beans;

import java.io.Serializable;
import java.sql.Date;

public class Messaggio implements Serializable {

    private int idMessaggio;
    private Date dataMessaggio;
    private String testoMessaggio;
    private int idProfessore;
    private int idStudente;

    public Messaggio() {
    }

    public Messaggio(int idMessaggio, Date dataMessaggio, String testoMessaggio, int idProfessore, int idStudente) {
        this.idMessaggio = idMessaggio;
        this.dataMessaggio = dataMessaggio;
        this.testoMessaggio = testoMessaggio;
        this.idProfessore = idProfessore;
        this.idStudente = idStudente;
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
