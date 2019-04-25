package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ComentarioDAO;
import dao.UsuarioDAO;
import model.Comentario;
import model.Topico;
import model.Usuario;

@WebServlet("/ComentarioController")
public class ComentarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ComentarioDAO comentarioDAO;
	private UsuarioDAO usuarioDAO;
       
    public ComentarioController() {
        super();
        comentarioDAO = new ComentarioDAO();
        usuarioDAO = new UsuarioDAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		int topicoId = Integer.parseInt(request.getParameter("topicoId"));
		String textoComentario = request.getParameter("comentario");
		
		Comentario comentario = new Comentario();
		comentario.setComentario(textoComentario);
		
		Topico topico = new Topico();
		topico.setId_topico(topicoId);
		comentario.setTopico(topico);
		
		comentario.setUsuario(usuario);
		
		comentarioDAO.inserirNovo(comentario);
		usuarioDAO.adicionarPontos(usuario.getLogin(), 3);
		
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/TopicoController?topicoId=" + topicoId));
	}

}
