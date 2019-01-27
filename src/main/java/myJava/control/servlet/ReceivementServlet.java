package myJava.control.servlet;

import myJava.model.general.DataManager;

import javax.imageio.IIOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet(name = "ReceivementServlet")
public class ReceivementServlet extends HttpServlet {
  DataManager dm = new DataManager();

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {

    if(request.getParameter("operazione").equals("inserimento"))
      inserisciRicevimento(request, response);


  }

  private void inserisciRicevimento(HttpServletRequest request, HttpServletResponse response){

    String startFirstReceivement, endLastReceivement, luogo, giorno;
    String oraInizio, oraFine;


    startFirstReceivement = request.getParameter("inizio");
    endLastReceivement = request.getParameter("fine");
    luogo = request.getParameter("luogo");
    giorno = startFirstReceivement.substring(0,10).trim();
    oraInizio = startFirstReceivement.substring(10).trim();
    oraFine = endLastReceivement.substring(10).trim();

    System.out.println(giorno + "\n" + oraInizio + "\n" + oraFine);
    try {
      response.getWriter().println("tutto bene");
    }
    catch (IOException ex)
    {

    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }
}
