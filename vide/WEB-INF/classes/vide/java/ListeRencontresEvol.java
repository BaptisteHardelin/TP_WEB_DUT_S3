package vide.java;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/ListeRencontresEvol")
public class ListeRencontresEvol extends HttpServlet
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
    String query = "select * from RENCONTRES ORDER BY "+req.getParameter("type")+" "+req.getParameter("sens");
    
    PreparedStatement ps = con.prepareStatement(query);
    ResultSet rs = ps.executeQuery();


    out.println("<table>");
    out.println("<tr>");
    out.println("<th> <a href=\""+concateArgs("num_match",inverse(req.getParameter("sens")))+"\">num_match</a></th>");
    out.println("<th> <a href=\""+concateArgs("eq1",inverse(req.getParameter("sens")))+"\">eq1</a></th>");
    out.println("<th> <a href=\""+concateArgs("eq2",inverse(req.getParameter("sens")))+"\">eq2</a></th>");
    out.println("<th> <a href=\""+concateArgs("jour",inverse(req.getParameter("sens")))+"\">jour</a></th>");
    out.println("<th> <a href=\""+concateArgs("sc1",inverse(req.getParameter("sens")))+"\">sc1</a></th>");
    out.println("<th> <a href=\""+concateArgs("sc2",inverse(req.getParameter("sens")))+"\">sc2</a></th>");
    out.println("</tr>");

    out.println("<form action=ListeRencontresEvol method=post>");
    
    out.println("<select name=\"type\">");
      out.println("<option value=\"num_match\">num_match</option>");
      out.println("<option value=\"eq1\">eq1</option>");
      out.println("<option value=\"eq2\">eq2</option>");
      out.println("<option value=\"jour\">jour</option>");
      out.println("<option value=\"sc1\">sc1</option>");
      out.println("<option value=\"sc2\">sc2</option>");
      out.println("</select>");
      out.println("<input type=\"submit\" value=\"Choix\">");
  
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
        out.println("<td>.." + sc1 + "</td>");
        out.println("<td>" + sc2 + "</td>");
        out.println("</tr>");
        
    }

    out.println("</form>");
  
    out.println("</table>");
    // fermeture de la connexion
    con.close();
    } catch (Exception e) {
      //TODO: handle exception
    }

    out.println( "</center> </body>" );
  }

  public String concateArgs(String type,String sens){
    return "http://localhost:8080/vide/ListeRencontresEvol?type="+type+"&sens="+sens;
  }

  public String inverse(String s){
    if(s == null) return "asc";
    if(s.toLowerCase().equals("asc")){
      return "desc";
    }else{
      return "asc";
    }
  }
}