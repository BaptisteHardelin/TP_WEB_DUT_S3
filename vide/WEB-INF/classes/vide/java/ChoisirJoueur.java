package vide.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ChoisirJoueur")
public class ChoisirJoueur extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		String joueurChoisi = req.getParameter("joueurChoisi");
		String jChoisi =  joueurChoisi == null ? "" : joueurChoisi;
		@SuppressWarnings("unchecked")
		ArrayList<String> listeSession = (ArrayList<String>) session.getAttribute("joueurChoisi");
		
		if(listeSession == null) {
			listeSession = new ArrayList();
		}
		
		String clearParam = req.getParameter("clear");
		boolean clear = clearParam == null ? false : true;
		
		if(clear) {
			listeSession.clear();
		}
		
		listeSession.add(jChoisi);
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.println("<head><title>ChoisirJoueur</title>");
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
			String queryEquipe = "select nom_joueur,maillot,poste from JOUEURS";
			PreparedStatement ps = con.prepareStatement(queryEquipe);
			ResultSet rs = ps.executeQuery();
			session.setAttribute("joueurChoisi", listeSession);

			out.println("<table>");
			out.println("<tr>");
			out.println("<th>nom_joueur<th>");
			out.println("</tr>");
			
			
			for(int i = 0; i < listeSession.size(); i++) {
				out.println("<tr>");
				out.println("<td>" + listeSession.get(i) + "</td>");
				out.println("</tr>");
			}
			
			out.println("</table>");
			
			out.println("<a href='ChoisirJoueur?clear'>Effacer liste</a>");
			out.println("<a href='ListerJoueur'>ListerJoueur</a>");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		out.println("</center> </body>");
	}
}
