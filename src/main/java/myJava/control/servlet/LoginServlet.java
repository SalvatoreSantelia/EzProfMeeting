 package myJava.control.servlet;

import myJava.model.beans.User;
import myJava.model.general.DataManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;




 @WebServlet(name = "LoginServlet")

public class LoginServlet extends HttpServlet {
        

	private DataManager DM=new DataManager();
     

     
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");


       User utente=new User();
        try {

            utente=DM.doLogin(username, password);
        }
        catch(Exception e){}
     if( utente ==null || utente.getEmail()==null){
           System.out.println("utente non caricato");
       
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