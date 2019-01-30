package myJava.model.beans;

public class User {
    private String email;
    private String password;
    private String tipo;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public User(String email, String password, String tipo) {
        this.email = email;
        this.password = password;
        this.tipo = tipo;
    }


    @Override
    public String toString() {
        return "User{" +
            "email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", tipo='" + tipo + '\'' +
            '}';
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }



}
