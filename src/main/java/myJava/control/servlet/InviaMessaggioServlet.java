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

@WebServlet(name = "InviaMessaggioServlet")
public class InviaMessaggioServlet extends HttpServlet {
    DataManager dm = new DataManager();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String testo = request.getParameter("inviaMess");
        int id = Integer.parseInt(request.getParameter("idStu"));
        int idProf = Integer.parseInt(request.getParameter("idPro"));
        String lato = request.getParameter("lato");
        DataManager dm = new DataManager();
        dm.inviaMessaggio(id,idProf,testo,lato);

        if(lato.equals("studente")) {
            ArrayList<Messaggio> messaggi = dm.getArrayListMessaggio(id, idProf);
            request.getSession().setAttribute("messaggi", messaggi);
            request.getSession().setAttribute("idProf", idProf);
            request.getSession().setAttribute("idStud",id);
            request.getServletContext().getRequestDispatcher("/View/General/MessaggiStudente.jsp").forward(request, response);
        }
        if(lato.equals("professore")) {
            ArrayList<Messaggio> messaggi = dm.getArrayListMessaggio(id, idProf);
            request.getSession().setAttribute("messaggi", messaggi);
            request.getSession().setAttribute("idProf", idProf);
            request.getSession().setAttribute("idStud",id);
            request.getServletContext().getRequestDispatcher("/View/General/MessaggiProfessore.jsp").forward(request, response);
        }
    }
}
