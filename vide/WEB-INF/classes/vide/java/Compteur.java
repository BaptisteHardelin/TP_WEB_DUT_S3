package vide.java;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/Compteur")
public class Compteur extends HttpServlet {
	static int cptServlet = 0;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		Integer cpt = (Integer) session.getAttribute("compteur");
		cpt = new Integer(cpt == null ? 1 : cpt.intValue() + 1);
		session.setAttribute("compteur", cpt);
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.println("<head> <title>Implémenter un compteur</title>");
		out.println(" </head> <body>");
		out.println("<h1> Toi : " + cpt + "</h1>");
		out.println("<h1> La Servlet : " + cptServlet++ + "</h1>");
		out.println("</body>");
	}

}