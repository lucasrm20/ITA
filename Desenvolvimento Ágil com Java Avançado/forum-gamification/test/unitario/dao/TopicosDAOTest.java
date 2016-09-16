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

import dao.TopicoDAO;
import model.Topico;
import model.Usuario;

public class TopicosDAOTest {

	private String driver = "com.mysql.jdbc.Driver";
    private String connection = "jdbc:mysql://localhost:3306/coursera";
    
    private String user = "root";
    private String password = "1234";
    
    private TopicoDAO dao;
	private JdbcDatabaseTester jdt;

	@Before
	public void before() throws Exception{
		dao = new TopicoDAO();
		
		jdt = new JdbcDatabaseTester(driver, connection, user, password);
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		jdt.setDataSet(loader.load("/inicio.xml"));
		jdt.onSetup();
	}
	
	@Test
	public void buscaTodosOsTopicos(){
		List<Topico> topicos = dao.todosTopicos();
		
		assertEquals(4, topicos.size());
		
		assertEquals("Tópico 01", topicos.get(0).getTitulo());
		assertEquals("lucas", topicos.get(0).getUsuario().getLogin());
		
		assertEquals("Tópico 02", topicos.get(1).getTitulo());
		assertEquals("joao", topicos.get(1).getUsuario().getLogin());
		
		assertEquals("Tópico 03", topicos.get(2).getTitulo());
		assertEquals("lucas", topicos.get(2).getUsuario().getLogin());
		
		assertEquals("Tópico 04", topicos.get(3).getTitulo());
		assertEquals("fulano", topicos.get(3).getUsuario().getLogin());
	}
	
	@Test
	public void buscarTopicoEspecifico(){
		Topico topico = dao.buscarTopico(1);
		
		assertEquals(1, topico.getId_topico());
		assertEquals("Tópico 01", topico.getTitulo());
		assertEquals("Este é o conteudo do tópico 01", topico.getConteudo());
		assertEquals("lucas", topico.getUsuario().getLogin());
	}
	
	@Test
	public void insereNovoTopico() throws SQLException, Exception{
		Topico topico = new Topico();
		topico.setTitulo("Novo Topico");
		topico.setConteudo("Conteudo do Novo Topico");
		
		Usuario usuario = new Usuario();
		usuario.setLogin("lucas");
		topico.setUsuario(usuario);
		
		dao.inserirNovo(topico);
		
		IDataSet currentDataSet = jdt.getConnection().createDataSet();
		ITable currentTable = currentDataSet.getTable("topico");
		
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet expectedDataSet = loader.load("/novoTopico.xml");
		ITable expectedTable = expectedDataSet.getTable("topico");
		
		Assertion.assertEquals(expectedTable, currentTable);
	}

}
