package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TopicoDAO;
import model.Topico;

@WebServlet("/TopicosController")
public class TopicosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TopicoDAO topicoDAO;
       
    public TopicosController() {
        super();
        topicoDAO = new TopicoDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Topico> topicos = topicoDAO.todosTopicos();
		
		request.setAttribute("topicos", topicos);
		request.getRequestDispatcher("topicos.jsp").forward(request, response);
	}

}
