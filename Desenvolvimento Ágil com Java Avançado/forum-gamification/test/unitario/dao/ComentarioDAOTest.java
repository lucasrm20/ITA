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

import dao.ComentarioDAO;
import model.Comentario;
import model.Topico;
import model.Usuario;

public class ComentarioDAOTest {

	private String driver = "com.mysql.jdbc.Driver";
    private String connection = "jdbc:mysql://localhost:3306/coursera";
    
    private String user = "root";
    private String password = "1234";
    
    private ComentarioDAO dao;
	private JdbcDatabaseTester jdt;

	@Before
	public void before() throws Exception{
		dao = new ComentarioDAO();
		
		jdt = new JdbcDatabaseTester(driver, connection, user, password);
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		jdt.setDataSet(loader.load("/inicio.xml"));
		jdt.onSetup();
	}
	
	@Test
	public void buscaTodosOsComentariosDeUmTopico(){
		List<Comentario> comentarios = dao.comentariosPorTopico(1);
		
		assertEquals(2, comentarios.size());
		
		assertEquals(1, comentarios.get(0).getId_comentario());
		assertEquals("fulano", comentarios.get(0).getUsuario().getLogin());
		
		assertEquals(2, comentarios.get(1).getId_comentario());
		assertEquals("joao", comentarios.get(1).getUsuario().getLogin());
	}
	
	@Test
	public void insereNovoComentario() throws SQLException, Exception{
		Comentario comentario = new Comentario();
		comentario.setComentario("Novo Comentario");
		
		Topico topico = new Topico();
		topico.setId_topico(1);
		comentario.setTopico(topico);
		
		Usuario usuario = new Usuario();
		usuario.setLogin("joao");
		comentario.setUsuario(usuario);
		
		dao.inserirNovo(comentario);
		
		IDataSet currentDataSet = jdt.getConnection().createDataSet();
		ITable currentTable = currentDataSet.getTable("comentario");
		
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet expectedDataSet = loader.load("/novoComentario.xml");
		ITable expectedTable = expectedDataSet.getTable("comentario");
		
		Assertion.assertEquals(expectedTable, currentTable);
	}

}
