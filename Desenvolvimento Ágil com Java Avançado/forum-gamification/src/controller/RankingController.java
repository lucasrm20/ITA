package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import model.Usuario;

@WebServlet("/RankingController")
public class RankingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO usuarioDAO;
       
    public RankingController() {
        super();
        usuarioDAO = new UsuarioDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Usuario> ranking = usuarioDAO.ranking();
		
		request.setAttribute("ranking", ranking);
		request.getRequestDispatcher("ranking.jsp").forward(request, response);
	}

}
