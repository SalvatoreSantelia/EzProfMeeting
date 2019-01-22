 package myJava.control.servlet;

import myJava.model.beans.User;
import myJava.model.general.DataManager;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




 @WebServlet(name = "LoginServlet")

public class LoginServlet extends HttpServlet {
        

	private DataManager DM=new DataManager();
     

     
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

       User utente=new User();
        try {
            utente=DM.doLogin(username, password);
        }catch(Exception e){}
     if(utente.getEmail()==null){
           System.out.println("nullo");
       
       response.getWriter().write("FAILURE");
        }
      else{
        	 
             response.getWriter().write(utente.getEmail());
             
       }
   }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException{
		 
		 
		 doGet(request,response);
		 
		 
		 
	 }
}