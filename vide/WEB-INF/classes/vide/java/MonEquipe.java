package vide.java;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/MonEquipe")
public class MonEquipe extends HttpServlet
{
	private final static int JOUR = 3600 * 24;
	
  public void service( HttpServletRequest req, HttpServletResponse res )
  throws ServletException, IOException
  {
    if(req.getParameter("equipe") != null){
      Cookie monCookie = new Cookie("mon_equipe", req.getParameter("equipe"));
      monCookie.setMaxAge( 10 * JOUR );
      res.addCookie(monCookie);
      System.out.println(monCookie.toString());
    }

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
    String query = "SELECT * FROM EQUIPES ;";
    PreparedStatement ps = con.prepareStatement(query);
    ResultSet rs = ps.executeQuery();

    out.println("<form action=MonEquipe method=get>");
    out.println("<select name=\"equipe\">");

    String equipe = "";
    int number = 0;
    while(rs.next()) {
      equipe = rs.getString("NOM_EQUIPE");
      number = rs.getInt("NUM_EQUIPE");
      out.println("<option value=\""+ number +"\">" + equipe + "</option>");
        
    }
    out.println("</select>");

   
    out.println("<input type=\"submit\" value=\"Send Cookie\">");
    out.println("</form>");


    // fermeture de la connexion
    con.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    out.println( "</center> </body>" );
  }
}