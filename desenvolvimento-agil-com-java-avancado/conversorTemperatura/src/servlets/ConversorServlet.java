package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Conversor")
public class ConversorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int tipoConversao = Integer.parseInt(request.getParameter("converterPara"));
		int temperaturaInicial = Integer.parseInt(request.getParameter("temperatura"));
		
		String temperaturaConvertida = converterTemperatura(tipoConversao, temperaturaInicial);
		
		response.getWriter().print(getTemplate(temperaturaConvertida));
	}
	
	private String converterTemperatura(int tipoConversao, int temperatura){
		if(tipoConversao == 1){
			return deCelciusParaFahrenheit(temperatura) + "°F";
		}
		return deFahrenheitParaCelcius(temperatura) + "°C";
	}
	
	private int deCelciusParaFahrenheit(int celcius){
		return ((celcius * 9) / 5) + 32;
	}
	
	private int deFahrenheitParaCelcius(int fahrenheit){
		return ((fahrenheit - 32) * 5) / 9;
	}
	
	private String getTemplate(String temperaturaConvertida){
		return "<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
					+ "<title>Conversor De Temperaturas</title>"
				+ "</head>"
				+ "<body>"
					+ "<h1>Conversor de Temperaturas</h1>"
					+ "<p>"
						+ "Resultado: <span id='resultado'>" + temperaturaConvertida + "</span>"
					+ "</p>"
					+ "<a href='index.html'>Voltar</a>"
				+ "</body>"
				+ "</html>";
	}

}
