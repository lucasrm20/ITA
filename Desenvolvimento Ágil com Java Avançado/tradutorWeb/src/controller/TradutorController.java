package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Tradutor;


@WebServlet("/TradutorController")
public class TradutorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Tradutor tradutor;
       
    
    public TradutorController() {
        super();
        tradutor = new Tradutor();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String palavra = request.getParameter("palavra");
		
		request.setAttribute("traducao", tradutor.traduzir(palavra.toLowerCase()));
		request.getRequestDispatcher("traducao.jsp").forward(request, response);
	}

}
