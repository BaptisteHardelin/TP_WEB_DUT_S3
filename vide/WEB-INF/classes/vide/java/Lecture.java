package vide.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Lecture")
public class Lecture extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		
		// vérifier que je suis authentifié
		HttpSession session = req.getSession();
		if ( session.getAttribute("login") == null ) {
			System.out.println("Non authentifié, redirection vers login");
			res.sendRedirect("login.html");
			return;
		}
		
		
		out.println("<head><title>Lecture</title>");
		out.println("<link rel='stylesheet' href='/vide/style_taches.css'>");
		out.println("<META content=\"charset=UTF-8\"></head><body><center>");

		try {

			// enregistrement du driver
			Class.forName("org.h2.Driver");
			// connexion à la base
			String url = "jdbc:h2:tcp://localhost:9092/~/test";
			String nom = "sa";
			String mdp = "";
			Connection con = DriverManager.getConnection(url, nom, mdp);
			String queryEquipe = "select LOGIN, MDP from PERSONNE";
			PreparedStatement ps = con.prepareStatement(queryEquipe);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String login = rs.getString("login");
				String password = rs.getString("mdp");
				
				out.println("<p>" + login + "</p>");
				out.println("<p>" + password + "</p>");
			}
			
			
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		out.println("</center> </body>");
	}
}
