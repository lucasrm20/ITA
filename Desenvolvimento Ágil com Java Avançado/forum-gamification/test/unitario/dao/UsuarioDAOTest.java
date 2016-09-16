package unitario.dao;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Before;
import org.junit.Test;

import dao.UsuarioDAO;
import exception.AutenticacaoException;
import model.Usuario;

public class UsuarioDAOTest {
	
	/**
	 * USEI MYSQL PARA REALIZAR ESTA TAREFA, CASO ESTEJA UTILIZANDO OUTRO BANCO DE DADOS
	 * E DESEJA RODAR A APLICAÇÃO, FAVOR ALTERAR O DRIVER E A URL DE CONEXÃO PARA UMA COMPATIVEL
	 * COM O BANCO DE DADOS QUE ESTEJA UTILIZANDO E TAMBEM ADICIONE O .JAR DO DRIVER APROPRIADO
	 */
	private String driver = "com.mysql.jdbc.Driver";
    private String connection = "jdbc:mysql://localhost:3306/coursera";
    
    private String user = "root";
    private String password = "1234";
    
    private UsuarioDAO dao;
	private JdbcDatabaseTester jdt;

	@Before
	public void before() throws Exception{
		dao = new UsuarioDAO();
		
		jdt = new JdbcDatabaseTester(driver, connection, user, password);
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		jdt.setDataSet(loader.load("/inicio.xml"));
		jdt.onSetup();
	}
	
	@Test
	public void insereUmNovoUsuario() throws SQLException, Exception{
		Usuario usuario = new Usuario();
		usuario.setLogin("usuarioNovo");
		usuario.setEmail("usuarionovo@usuarionovo.com");
		usuario.setNome("Usuario Novo");
		usuario.setSenha("12345");
		usuario.setPontos(15);
		
		dao.inserir(usuario);
		
		IDataSet currentDataSet = jdt.getConnection().createDataSet();
		ITable currentTable = currentDataSet.getTable("usuario");
		
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet expectedDataSet = loader.load("/novoUsuario.xml");
		ITable expectedTable = expectedDataSet.getTable("usuario");
		
		Assertion.assertEquals(expectedTable, currentTable);
	}
	
	@Test
	public void recuperaUsuarioExistente(){
		Usuario usuario = dao.recuperar("lucas");
		
		assertEquals("lucas", usuario.getLogin());
		assertEquals("lucas@lucas.com", usuario.getEmail());
		assertEquals("Lucas Rodrigues", usuario.getNome());
		assertEquals("12345", usuario.getSenha());
		assertEquals(20, usuario.getPontos());
	}
	
	@Test
	public void adicionaPontosAUmUsuario(){
		dao.adicionarPontos("lucas", 25);
		
		Usuario usuario = dao.recuperar("lucas");
		
		assertEquals(45, usuario.getPontos());
	}
	
	@Test
	public void obtemORanking(){
		List<Usuario> ranking = dao.ranking();
		
		assertEquals(3, ranking.size());
		assertEquals("fulano", ranking.get(0).getLogin());
		assertEquals("joao", ranking.get(1).getLogin());
		assertEquals("lucas", ranking.get(2).getLogin());
	}
	
	@Test
	public void autenticaUsuario() throws AutenticacaoException{
		Usuario usuario = dao.autenticar("lucas", "12345");
		
		assertEquals("Lucas Rodrigues", usuario.getNome());
	}
	
	@Test(expected=AutenticacaoException.class)
	public void usuarioOuSenhaInvalido() throws AutenticacaoException{
		Usuario usuario = dao.autenticar("pedro", "pedro");
	}
	
}
