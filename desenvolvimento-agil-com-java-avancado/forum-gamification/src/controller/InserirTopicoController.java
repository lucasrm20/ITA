package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TopicoDAO;
import dao.UsuarioDAO;
import model.Topico;
import model.Usuario;

@WebServlet("/InserirTopicoController")
public class InserirTopicoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TopicoDAO topicoDAO;
	private UsuarioDAO usuarioDAO;
       
    public InserirTopicoController() {
        super();
        topicoDAO = new TopicoDAO();
        usuarioDAO = new UsuarioDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("inserirTopico.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Topico topico = new Topico();
		topico.setTitulo(request.getParameter("titulo"));
		topico.setConteudo(request.getParameter("conteudo"));
		
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		topico.setUsuario(usuario);
		
		topicoDAO.inserirNovo(topico);
		usuarioDAO.adicionarPontos(usuario.getLogin(), 10);
		
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/TopicosController"));
	}

}
