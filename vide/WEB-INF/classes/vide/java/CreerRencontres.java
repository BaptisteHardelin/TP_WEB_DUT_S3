package vide.java;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/CreerRencontres")
public class CreerRencontres extends HttpServlet
{
  public void service( HttpServletRequest req, HttpServletResponse res )
  throws ServletException, IOException
  {
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    out.println( "<head><title>servlet first</title>" );
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
    out.println("<form action=\"CreerRencontres\" method=\"get\">");
      out.println("<select id=\"eq1\" name=\"eq1\">");
      String queryEquipe =  "select * from EQUIPES";
      PreparedStatement ps1 = con.prepareStatement( queryEquipe );
      ResultSet rs1 = ps1.executeQuery();
      while (rs1.next()) {
        out.println("<option value=\"\" +  rs1.getInt(\"num_equipe\")>" +rs1.getString("nom_equipe") + "</option>");
      }
      out.println("</select>");
      out.println("</form>");

      PreparedStatement ps2 = con.prepareStatement( queryEquipe );
      ResultSet rs2 = ps2.executeQuery();
      while (rs2.next()) {
        out.println("<option value=\"\" +  rs2.getInt(\"num_equipe\")>" +rs2.getString("nom_equipe") + "</option>");
      }
      out.println("</select>");
      out.println("</form>");
    con.close();
    } catch (Exception e) {
      //TODO: handle exception
    }

    out.println( "</center> </body>" );
  }

}