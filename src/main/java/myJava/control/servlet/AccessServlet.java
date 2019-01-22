package myJava.control.servlet;

import myJava.model.beans.User;
import myJava.model.general.DataManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AccessServlet")
public class AccessServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataManager model=new DataManager();
        User utente= null;
        try {
            utente = model.doLogin(request.getParameter("email"),request.getParameter("pass"));
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        HttpSession session=request.getSession();
        session.setMaxInactiveInterval(-1);
        session.setAttribute("user", utente);
        request.getServletContext().getRequestDispatcher("/View/General/profiloStudente.jsp").forward(request, response);



    }
}
