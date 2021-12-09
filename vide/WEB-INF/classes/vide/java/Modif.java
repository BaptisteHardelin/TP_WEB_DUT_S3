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


@WebServlet("/Modif")
public class Modif extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		res.sendRedirect(req.getParameter("failConnection.html"));
		out.println("<head><title>Modif</title>");
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
			String queryEquipe = "select * from PERSONNE";
			PreparedStatement ps = con.prepareStatement(queryEquipe);
			ResultSet rs = ps.executeQuery();
			
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		out.println("</center> </body>");
	}
}
