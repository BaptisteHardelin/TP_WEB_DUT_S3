package vide.java;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet-Palette")
public class Palette extends HttpServlet
{
  public void service( HttpServletRequest req, HttpServletResponse res )
  throws ServletException, IOException
  {
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    out.println( "<head><title>servlet palette</title>" );
    out.println( "<META content=\"charset=UTF-8\"></head><body><center>");

    out.println( "<h1>Test de ma Palette</h1>" );
    out.println("<form action=\"servlet-Palette\" method=\"get\"");
    out.println("<input type=\"text\" name=\"r\" value=\"8\">");
    out.println("<input type\"submit\" name=\"r\" value=\"\">");
    out.println("</form>");
    int r = Integer.parseInt(calculRed(req));
    out.println("<p> r : " + r + "</p>");

    for(int i = 0; i <= 15; i++) {

      out.print("<a href=http://localhost:8080/vide/servlet-Palette?r=" + i + ">(" + i + ")</a>");
    }
	
    out.println("<br>");
    out.println("<table>");
    
    for (int i = 0 ; i < 16 ; i++) {
      out.println("<tr>");
      
      for (int j = 0 ; j < 17 ; j++) {
        String couleur = "#" + Integer.toHexString(r) + Integer.toHexString(i) + "" + Integer.toHexString(j);
        out.println("<td style=\"background-color: " + couleur + "; width: 50px; heigth: 50px;\">&nbsp;</td>");
      }
      
      out.println("</tr>");
    }
    
    out.println("</table>");
    
    out.println( "<h2>C'est beau !</h2>" );
    out.println( "</center> </body>" );
  }

  String calculRed(HttpServletRequest req) {

    int r = Integer.parseInt(req.getParameter("r"));
    if(r >= 0 && r <= 15) {
      if(req.getParameter("r") != "") {
        return "" + req.getParameter("r");
      }
    }
    return "0";
  }

}
