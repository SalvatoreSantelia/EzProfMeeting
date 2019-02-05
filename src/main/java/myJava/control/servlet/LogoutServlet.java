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


@WebServlet(name = "LoginServlet")

public class LogoutServlet extends HttpServlet {


  private DataManager DM = new DataManager();


  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    session.invalidate();
    response.sendRedirect(request.getContextPath()+ "/View/General/Login.jsp");
  }


  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {


    doGet(request, response);


  }
}