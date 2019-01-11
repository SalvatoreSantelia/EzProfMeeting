package myJava.model.general;

import myJava.control.connection.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccessManager {
    public boolean login(String mail,String password)throws SQLException {

        Connection connection=null;

        connection= DriverManagerConnectionPool.getConnection();

        PreparedStatement preparedStatement= connection.prepareStatement("select * from login where email=? and password=?");

        preparedStatement.setString(1,mail);
        preparedStatement.setString(2,password);

        ResultSet rs=preparedStatement.executeQuery();

        if (rs!=null) return true;
        else return false;


    }

}
