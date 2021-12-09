package vide.java;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet-Fibo")
public class Fibonacci extends HttpServlet
{
  public void service( HttpServletRequest req, HttpServletResponse res )
  throws ServletException, IOException
  {
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    out.println( "<head><title>servlet fibonacci</title>" );
    out.println( "<META content=\"charset=UTF-8\"></head><body><center>" );
    out.println( "<h1>Servlet Fibonacci</h1>" );
	
	for (int i = 1 ; i <= 30 ; ++i) {
		out.println("<p>" + fibo(i) + "</p>");
	}
	
    out.println( "<h2>Super ! ça marche</h2>" );
    out.println( "</center> </body>" );
  }
  
  private static int fibo(int n) {
	  if (n <= 1) return n;
	  else return fibo(n-1) + fibo(n-2);
  }
}