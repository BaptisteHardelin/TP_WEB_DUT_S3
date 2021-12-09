package vide.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ListerJoueur")
public class ListerJoueur extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		ArrayList<String> listeSession = (ArrayList<String>) session.getAttribute("joueurChoisi");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.println("<head><title>ListerJoueur</title>");
		out.println("<link rel='stylesheet' href='/vide/style_taches.css'>");
		out.println("<META content=\"charset=UTF-8\"></head><body><center>");
		String str = req.getParameter("equipe");
		String cookie = str == null ? "" : str;

		try {

			// enregistrement du driver
			Class.forName("org.h2.Driver");
			// connexion à la base
			String url = "jdbc:h2:tcp://localhost:9092/~/test";
			String nom = "sa";
			String mdp = "";
			String poste = req.getParameter("poste");
			String nomPoste = poste == null ? "ATT" : poste;
			Connection con = DriverManager.getConnection(url, nom, mdp);
			String queryEquipe = "select nom_joueur,maillot,poste from JOUEURS WHERE poste=?";
			PreparedStatement ps = con.prepareStatement(queryEquipe);
			ps.setString(1, nomPoste);
			ResultSet rs = ps.executeQuery();

			out.println("<a href='ListerJoueur?poste=ATT'>ATT</a>");
			out.println("<a href='ListerJoueur?poste=DEF'>DEF</a>");
			out.println("<a href='ListerJoueur?poste=GAR'>GAR</a>");
			out.println("<a href='ListerJoueur?poste=MIL'>MIL</a>");

			out.println("<table>");
			out.println("<tr>");
			out.println("<th>nom_joueur<th>");
			out.println("<th>maillot<th>");
			out.println("<th>poste<th>");
			out.println("<th>ChoisirJoueur<th>");
			out.println("</tr>");

			while (rs.next()) {
				String nomJoueur = rs.getString("nom_joueur");
				String m = rs.getString("maillot");
				String p = rs.getString("poste");

				if (!listeSession.contains(nomJoueur)) {
					out.println("<tr>");
					out.println("<td> " + nomJoueur + "</td>");
					out.println("<td>" + m + "</td>");
					out.println("<td>" + p + "</td>");
					out.println(
							"<td> <a href=\"./ChoisirJoueur?joueurChoisi=" + nomJoueur + "\">ChoisirJoueur</a></td>");
					out.println("</tr>");
				}
			}

			out.println("</table>");

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		out.println("</center> </body>");
	}
}
