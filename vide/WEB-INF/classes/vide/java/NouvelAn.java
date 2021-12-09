package vide.java;
import java.io.*;
import java.time.*;
import java.time.temporal.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet-NouvelAn")
public class NouvelAn extends HttpServlet
{
  public void service( HttpServletRequest req, HttpServletResponse res )
  throws ServletException, IOException
  {
    res.setContentType("text/html;");
    PrintWriter out = res.getWriter();
    out.println( "<head><title>Seconds to new year</title>" );
    out.println( "</head><body><center>" );
    out.println( "<h1>Seconds to new year</h1>" );
	
	LocalDateTime today = LocalDateTime.now();
	LocalDateTime newYear = LocalDateTime.of(2022, Month.JANUARY, 1, 0, 0);
	Duration delay = Duration.between(today, newYear);
	long seconds = delay.get(ChronoUnit.SECONDS);
	
	out.println("<p>" + seconds + "</p>");
	
    out.println( "<h2>C'est beau !</h2>" );
    out.println( "</center> </body>" );
  }
}