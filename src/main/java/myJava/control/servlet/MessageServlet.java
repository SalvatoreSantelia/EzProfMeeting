package myJava.control.servlet;

import myJava.model.beans.Messaggio;
import myJava.model.general.DataManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "MessageServlet")
public class MessageServlet extends HttpServlet {
    DataManager dm = new DataManager();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idStudente = Integer.parseInt(request.getParameter("idStudente"));
        int idProfessore = Integer.parseInt(request.getParameter("idProfessore"));
        String lato = request.getParameter("lato");
        System.out.println(idProfessore+"aa "+idStudente+lato);
        ArrayList<Messaggio> messaggi = dm.getArrayListMessaggio(idStudente,idProfessore);
        request.getSession().setAttribute("messaggi",messaggi);
        request.getSession().setAttribute("idProf",idProfessore);
        request.getSession().setAttribute("idStud",idStudente);
        if(lato.equals("studente")) {
            request.getServletContext().getRequestDispatcher("/View/General/MessaggiStudente.jsp").forward(request, response);
        }
        if(lato.equals("professore")){
            request.getServletContext().getRequestDispatcher("/View/General/MessaggiProfessore.jsp").forward(request, response);
        }
    }
}
