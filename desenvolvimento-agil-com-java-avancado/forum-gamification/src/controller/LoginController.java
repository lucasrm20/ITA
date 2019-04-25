package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import exception.AutenticacaoException;
import model.Usuario;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO usuarioDAO;
       
    public LoginController() {
        super();
        usuarioDAO = new UsuarioDAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		try {
			Usuario usuario = usuarioDAO.autenticar(login, senha);
			request.getSession().setAttribute("usuario", usuario);
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/TopicosController"));
			
		} catch (AutenticacaoException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
