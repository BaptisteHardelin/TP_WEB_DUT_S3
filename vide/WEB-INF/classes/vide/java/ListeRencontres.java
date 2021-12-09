package vide.java;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/ListeRencontres")
public class ListeRencontres extends HttpServlet
{
  public void service( HttpServletRequest req, HttpServletResponse res )
  throws ServletException, IOException
  {
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    out.println( "<head><title>servlet ListeRencontres</title>" );
    out.println( "<style>td{height:25px;width:50px;}</style>" );
    out.println( "<META content=\"charset=UTF-8\"></head><body><center>" );

    try {

    // enregistrement du driver
    Class.forName("org.h2.Driver");
    // connexion à la base
    String url = "jdbc:h2:tcp://localhost:9092/~/test";
    String nom = "sa";
    String mdp = "";
    Connection con = DriverManager.getConnection(url,nom,mdp);
    String query = "select * from RENCONTRES";
    PreparedStatement ps = con.prepareStatement(query);
    ResultSet rs = ps.executeQuery();
    System.out.println("Table des rencontres");

    out.println("<table>");
    out.println("<tr>");
    out.println("<th num_match</th>");
    out.println("<th> eq1 </th>");
    out.println("<th> eq2 </th>");
    out.println("<th> jour </th>");
    out.println("<th> sc1 </th>");
    out.println("<th> sc2 </th>");
    out.println("</tr>");

    out.println("<form action=ListeRencontres method=post>");

    out.println("<select id=\"num_match\" name=\"num_match\">");
    int n = 0; 
    while (rs.next())
    {
        n = rs.getInt("num_match");
        int eq1 = rs.getInt("eq1");
        int eq2 = rs.getInt("eq2");
        Date d = rs.getDate("jour");
        int sc1 = rs.getInt("sc1");
        int sc2 = rs.getInt("sc2");

        
        out.println("<tr>");
        out.println("<td>" + n + "</td>");
        out.println("<td>" + eq1 + "</td>");
        out.println("<td>" + eq2 + "</td>");
        out.println("<td>" + d + "</td>");
        out.println("<td>" + sc1 + "</td>");
        out.println("<td>" + sc2 + "</td>");
        out.println("</tr>");
        
    }

    
    out.println("</select>");
    out.println("<input type=\"submit\" value=\"Choisir\"/>");
    out.println("</form>");

    out.println("</table>");
    // fermeture de la connexion
    con.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    out.println( "</center> </body>" );
  }

}