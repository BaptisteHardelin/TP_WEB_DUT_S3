package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dao;

@SuppressWarnings("serial")
@WebServlet("/control")
public class Control extends HttpServlet {

	String action = "";
	Dao dao = new Dao();

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		action = req.getParameter("action");
		String vue = "";
		switch (action) {
		case "vignette":
			int n = Integer.parseInt(req.getParameter("n"));
			if (n == 0)
				n = Integer.parseInt("0");
			req.setAttribute("n", dao.findById(n));
			vue = "WEB-INF/classes/vue/vignette.jsp";
			break;
		case "liste":
			req.setAttribute("liste", dao.findAll());
			vue = "WEB-INF/classes/vue/liste.jsp";
			break;
		default:
			System.out.println("pas possible !");
			break;
		}

		req.getRequestDispatcher(vue).forward(req, res);

	}
}