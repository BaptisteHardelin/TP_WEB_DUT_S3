package vide.java;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import java.sql.*;
import java.io.FileInputStream;
import java.util.Properties;

@WebServlet("/servlet-ListeJoueurs")
public class ListeJoueurs extends HttpServlet
{
  public void service( HttpServletRequest req, HttpServletResponse res )
  throws ServletException, IOException
  {
    res.setContentType("text/html;");
    PrintWriter out = res.getWriter();
    out.println( "<head><title>liste joueurs</title>" );
    out.println( "</head><body><center>" );
    out.println( "<h1>Liste Joueurs</h1>" );
	
	connectToDB(out);
	
    out.println( "<h2>C'est beau !</h2>" );
    out.println( "</center> </body>" );
  }
  
  private static void connectToDB(PrintWriter out) {
	  Connection con = null;
        try {
            DS bdd = new DS();

            con = bdd.getConnection();
        
            // execution de la requete
            String query = "select NOM,PRENOM,AGE from CLIENTS";
            PreparedStatement ps = con.prepareStatement( query );
            ResultSet rs = ps.executeQuery();
      
            System.out.println("Liste des clients:");
            while (rs.next()) {
                String n = rs.getString("nom");
                String p = rs.getString("prenom");
                int a = rs.getInt("age");
                out.println("<p>" + n + "</p>");
            }

            System.out.println("Ok.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // fermeture des espaces
                con.close();
            } catch (Exception e2) {}
        }
  }
}