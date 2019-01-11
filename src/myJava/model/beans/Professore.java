package myJava.model.beans;

import java.io.Serializable;

public class Professore implements Serializable {

    private int idProfessore;
    private String nomeProfessore;
    private String cognomeProfessore;
    private String emailProfessore;
    private String telefonoProfessore;
    private String ufficioProfessore;

    public Professore() {
    }

    public Professore(int idProfessore, String nomeProfessore, String cognomeProfessore, String emailProfessore, String telefonoProfessore, String ufficioProfessore) {
        this.idProfessore = idProfessore;
        this.nomeProfessore = nomeProfessore;
        this.cognomeProfessore = cognomeProfessore;
        this.emailProfessore = emailProfessore;
        this.telefonoProfessore = telefonoProfessore;
        this.ufficioProfessore = ufficioProfessore;
    }

    public int getIdProfessore() {
        return idProfessore;
    }

    public void setIdProfessore(int idProfessore) {
        this.idProfessore = idProfessore;
    }

    public String getNomeProfessore() {
        return nomeProfessore;
    }

    public void setNomeProfessore(String nomeProfessore) {
        this.nomeProfessore = nomeProfessore;
    }

    public String getCognomeProfessore() {
        return cognomeProfessore;
    }

    public void setCognomeProfessore(String cognomeProfessore) {
        this.cognomeProfessore = cognomeProfessore;
    }

    public String getEmailProfessore() {
        return emailProfessore;
    }

    public void setEmailProfessore(String emailProfessore) {
        this.emailProfessore = emailProfessore;
    }

    public String getTelefonoProfessore() {
        return telefonoProfessore;
    }

    public void setTelefonoProfessore(String telefonoProfessore) {
        this.telefonoProfessore = telefonoProfessore;
    }

    public String getUfficioProfessore() {
        return ufficioProfessore;
    }

    public void setUfficioProfessore(String ufficioProfessore) {
        this.ufficioProfessore = ufficioProfessore;
    }
}
