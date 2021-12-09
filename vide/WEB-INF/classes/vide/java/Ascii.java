package vide.java;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet-Ascii")
public class Ascii extends HttpServlet
{
  public void service( HttpServletRequest req, HttpServletResponse res )
  throws ServletException, IOException
  {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    out.println( "<head><title>servlet ascii</title>" );
    out.println( "</head><body><center>" );
    out.println("<h1>Table ASCII</h1>");
    String nbColStr = req.getParameter("nbCol");
    out.println("<p>Nombre de colonnes : " + nbColStr + "</p>");

    out.println("<form action=servlet-Ascii method=post>");

    out.println("<select id=\"nbCol\" name=\"nbCol\">");

    for(int i=1;i<=14;i++) {
      out.println("<option value=\"" + i + "\">" + i + "</option>");
    }

    out.println("</select>");
    out.println("<table>");

    int nbCol = 1;

    if(nbColStr !=null && !nbColStr.equals("")) {
      int nbColInt = Integer.parseInt(nbColStr);
      if(nbColInt>=1 && nbColInt<=14) {
        nbCol = nbColInt;
      }
    }

    for(int asciiNumber=32;asciiNumber<=255;asciiNumber++) {
      out.println("<tr>");
      for(int col=1;col<=nbCol;col++,asciiNumber++) {
        out.println("<td>" + asciiNumber + "</td>");
        out.println("<br >");
        out.println("<td>" + ((char) asciiNumber) + "</td>");
      }
      out.println("</tr>");
    }

    out.println("</table>");
    out.println("<h2>C'est beau !</h2>");
    out.println( "</center> </body>" );
  }
}