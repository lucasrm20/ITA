package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	/**
	 * USEI MYSQL PARA REALIZAR ESTA TAREFA, CASO ESTEJA UTILIZANDO OUTRO BANCO DE DADOS
	 * E DESEJA RODAR A APLICAÇÃO, FAVOR ALTERAR O DRIVER E A URL DE CONEXÃO PARA UMA COMPATIVEL
	 * COM O BANCO DE DADOS QUE ESTEJA UTILIZANDO E TAMBEM ADICIONE O .JAR DO DRIVER APROPRIADO
	 */
	private static String driver = "com.mysql.jdbc.Driver";
    private static String connection = "jdbc:mysql://localhost:3306/coursera";
    
    private static String user = "root";
    private static String password = "1234";
    
    static {
    	try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(connection, user, password);
	}
	
}
