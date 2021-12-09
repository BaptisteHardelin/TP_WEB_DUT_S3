package vide.java;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/ListeTaches")
public class ListeTaches extends HttpServlet
{
  public void service( HttpServletRequest req, HttpServletResponse res )
  throws ServletException, IOException
  {
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    out.println( "<head><title>servlet ListeTaches</title>" );
    out.println( "<link rel='stylesheet' href='/vide/style_taches.css'>" );
    out.println( "<META content=\"charset=UTF-8\"></head><body><center>" );

    try {

    // enregistrement du driver
    Class.forName("org.h2.Driver");
    // connexion à la base
    String url = "jdbc:h2:tcp://localhost:9092/~/test";
    String nom = "sa";
    String mdp = "";
    Connection con = DriverManager.getConnection(url,nom,mdp);
      String query =  "select * from TACHES order by ORDRE";
      PreparedStatement ps = con.prepareStatement( query );
      ResultSet rs = ps.executeQuery();
	  out.println("<h1>Table Taches</h1>");
	  out.println("<p>" + query +"</p>");
	  
	  out.println("<a href='ListeTaches?statut=todo' name=statut>ToDo</a>");
	  out.println("<a href='ListeTaches?statut=inProgress' name=inProgress>InProgress</a>");
	  out.println("<a href='ListeTaches?statut=done' name=done>Done</a>");  
	  
	  out.println("<table>");
	  out.println("<th>TID</th>");
	  out.println("<th>ORDRE</th>");
	  out.println("<th>LIBELLE</th>");
	  out.println("<th>DUREE</th>");
	  out.println("<th>STATUT</th>");
	  out.println("<br />");
	  
		  while(rs.next()) {
			  int tid = rs.getInt("TID");
			  int ordre = rs.getInt("ORDRE");
			  String libelle = rs.getString("LIBELLE");
			  int duree = rs.getInt("DUREE");
			  String statut = rs.getString("STATUT");
			  
			  out.println("<tr>");
			  if(req.getParameter("statut") != null && req.getParameter("statut").equals("todo")) {
				  if(statut.equals("ToDo")) {
				  out.println("<td>" + tid + "</td>");
				  out.println("<td>" + ordre + "</td>");
				  out.println("<td>" + libelle + "</td>");
				  out.println("<td>" + duree + "</td>");
				  out.println("<td>" + statut + "</td>");
				}
			  }else if(req.getParameter("statut") != null && req.getParameter("statut").equals("inProgress")) {
				  if(statut.equals("InProgress")) {
				  out.println("<td>" + tid + "</td>");
				  out.println("<td>" + ordre + "</td>");
				  out.println("<td>" + libelle + "</td>");
				  out.println("<td>" + duree + "</td>");
				  out.println("<td>" + statut + "</td>");
				}
			  }else if(req.getParameter("statut") != null && req.getParameter("statut").equals("done")) {
				  if(statut.equals("Done")) {
				  out.println("<td>" + tid + "</td>");
				  out.println("<td>" + ordre + "</td>");
				  out.println("<td>" + libelle + "</td>");
				  out.println("<td>" + duree + "</td>");
				  out.println("<td>" + statut + "</td>");
				}
			  }	else {
				out.println("<td>" + tid + "</td>");
				out.println("<td>" + ordre + "</td>");
				out.println("<td>" + libelle + "</td>");
				out.println("<td>" + duree + "</td>");
				out.println("<td>" + statut + "</td>");
			  }

			  out.println("</tr>");
			  
		  }
 
	out.println("</table>");
    con.close();
    } catch (Exception e) {
		e.printStackTrace();
    }

    out.println( "</center> </body>" );
  }

}