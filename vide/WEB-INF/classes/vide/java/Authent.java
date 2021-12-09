package vide.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Authent")
public class Authent extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession(true);
		
		String login = req.getParameter("login");
		String password = req.getParameter("mdp");


		// enregistrement du driver
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e1) {
			out.println("Le driver H2 n'a pas été chargé.");
			e1.printStackTrace();
		}
		
		
		
		// connexion à la base
		String url = "jdbc:h2:tcp://localhost:9092/~/test";
		String nom = "sa";
		String mdp = "";
		
		try (Connection con = DriverManager.getConnection(url, nom, mdp)) {


			PreparedStatement ps = con.prepareStatement("SELECT * FROM personne where login = ? and mdp = ?");
			ps.setString(1, login);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			boolean userExist = rs.next();
			if(!userExist) {
				out.println("Vous n'existez pas !");
				return;
			} 

			out.println("Vous existez !");
			session.setAttribute("login", login);
		    out.println("<form action='Authent' method='post'>");
		    out.println("<a href='Lecture'");
		    out.println("</form>");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		



	}
}
