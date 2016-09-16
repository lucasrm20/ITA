package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import model.Usuario;

@WebServlet("/CadastroController")
public class CadastroController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO usuarioDAO;
       
    public CadastroController() {
        super();
        usuarioDAO = new UsuarioDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("cadastro.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Usuario usuario = new Usuario();
		
		usuario.setLogin(request.getParameter("login"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setNome(request.getParameter("nome"));
		usuario.setSenha(request.getParameter("senha"));
		
		usuarioDAO.inserir(usuario);
		
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
	}

}
