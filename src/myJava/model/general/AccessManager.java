package myJava.model.general;

import myJava.control.connection.DriverManagerConnectionPool;
import myJava.model.beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccessManager {
    public User doLogin(String mail, String password) throws SQLException {

        Connection connection = null;

        User utente = new User();
        try {
            connection = DriverManagerConnectionPool.getConnection();
            //creating prepared statement for our required query
            PreparedStatement statement = connection.prepareStatement("SELECT * from login WHERE email = ? AND password=?");
            //setting the parameters

            statement.setString(1, mail);
            statement.setString(2, password);
            //executing the prepared statement, which returns a ResultSet
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                utente.setEmail(rs.getString("email"));
                utente.setPassword(rs.getString("password"));
                utente.setTipo(rs.getString("ruolo"));


            }
            System.out.println(utente.getEmail());

        } catch (Exception e) {

            e.printStackTrace();
        }
        return utente;

    }
}