package vide.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/voler")
public class Voler extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<head><title>servlet voler</title>");
		out.println("</head><body><center>");
		String p = req.getParameter("p");

		Connection con = null;
		try {

			// enregistrement du driver
			Class.forName("org.h2.Driver");

			// connexion a la base
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/~/test", "sa", "");

			// execution de la requete
			String query = "insert into NUMSESSIONS values(?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, p);
			
			res.sendRedirect("../tp309/servlet/Menu");
			int rs = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
			}
		}

		out.println("</center> </body>");
	}
}
