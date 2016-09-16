package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ComentarioDAO;
import dao.TopicoDAO;
import model.Comentario;
import model.Topico;

@WebServlet("/TopicoController")
public class TopicoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TopicoDAO topicoDAO;
	private ComentarioDAO comentarioDAO;
       
    public TopicoController() {
        super();
        topicoDAO = new TopicoDAO();
        comentarioDAO = new ComentarioDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int topicoId = Integer.parseInt(request.getParameter("topicoId"));
		
		Topico topico = topicoDAO.buscarTopico(topicoId);
		request.setAttribute("topico", topico);
		
		List<Comentario> comentarios = comentarioDAO.comentariosPorTopico(topicoId);
		request.setAttribute("comentarios", comentarios);
		
		request.getRequestDispatcher("topico.jsp").forward(request, response);
	}

}
