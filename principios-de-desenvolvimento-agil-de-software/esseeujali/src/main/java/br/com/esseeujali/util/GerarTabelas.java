package br.com.esseeujali.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.esseeujali.models.Categoria;
import br.com.esseeujali.models.Livro;
import br.com.esseeujali.models.Usuario;

public class GerarTabelas {

	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("gerarTabelas");
		EntityManager manager = factory.createEntityManager();
		
		gerarUsuarios(manager);
		gerarLivros(manager);
		
		manager.close();
		factory.close();
	}

	private static void gerarUsuarios(EntityManager manager) {
		manager.getTransaction().begin();
		
		Usuario usuario01 = new Usuario("usuario01", "usuario01", 0);
		manager.persist(usuario01);
		
		Usuario usuario02 = new Usuario("usuario02", "usuario02", 0);
		manager.persist(usuario02);
		
		Usuario usuario03 = new Usuario("usuario03", "usuario03", 0);
		manager.persist(usuario03);
		
		Usuario usuario04 = new Usuario("usuario04", "usuario04", 0);
		manager.persist(usuario04);
		
		Usuario usuario05 = new Usuario("usuario05", "usuario05", 0);
		manager.persist(usuario05);
		
		manager.getTransaction().commit();
	}
	
	private static void gerarLivros(EntityManager manager){
		manager.getTransaction().begin();
		
		Livro livro01 = new Livro("Clube da Luta", "Chuck Palahniuk", 99, Categoria.ROMANCE, "https://images-submarino.b2w.io/produtos/01/03/item/110764/1/110764191_1GG.jpg");
		manager.persist(livro01);
		
		Livro livro02 = new Livro("O Senhor Dos Anéis", "J. R. R. Tolkien", 100, Categoria.FANTASIA, "https://images-submarino.b2w.io/produtos/01/00/item/198/2/198255_1GG.jpg");
		manager.persist(livro02);
		
		Livro livro03 = new Livro("Crime e Castigo", "Fiódor Dostoiévski", 199, Categoria.ROMANCE, "https://resenhasdefilosofia.files.wordpress.com/2013/06/crime-e-castigo.jpg");
		manager.persist(livro03);
		
		Livro livro04 = new Livro("As Duas Torres", "J. R. R. Tolkien", 200, Categoria.FANTASIA, "http://www.emartinsfontes.com.br/fotos/zoom/16169fz1/senhor-dos-aneis-o-vol-2-as-duas-torres-capa-nova.jpg");
		manager.persist(livro04);
		
		Livro livro05 = new Livro("O Idiota", "Fiódor Dostoiévski", 299, Categoria.ROMANCE, "https://catracalivre.com.br/wp-content/uploads/2010/01/cover-145434-600.jpg");
		manager.persist(livro05);
		
		Livro livro06 = new Livro("O Retorno Do Rei", "J. R. R. Tolkien", 300, Categoria.FANTASIA, "https://grapegum.files.wordpress.com/2012/10/retorno_do_rei.jpg");
		manager.persist(livro06);
		
		Livro livro07 = new Livro("Os Irmãos Karamazov", "Fiódor Dostoiévski", 399, Categoria.ROMANCE, "http://statics.livrariacultura.net.br/products/capas_lg/049/13003049.jpg");
		manager.persist(livro07);
		
		Livro livro08 = new Livro("O Hobbit", "J. R. R. Tolkien", 400, Categoria.FANTASIA, "http://www.reidaverdade.net/wp-content/uploads/2010/10/O-Hobbit-500x500.jpg");
		manager.persist(livro08);
		
		Livro livro09 = new Livro("Os Demônios", "Fiódor Dostoiévski", 499, Categoria.ROMANCE, "http://statics.livrariacultura.net.br/products/capas_lg/372/3150372.jpg");
		manager.persist(livro09);
		
		Livro livro10 = new Livro("O Silmarillion", "J. R. R. Tolkien", 500, Categoria.FANTASIA, "https://images-submarino.b2w.io/produtos/01/00/item/6919/3/6919381_1GG.jpg");
		manager.persist(livro10);
		
		manager.getTransaction().commit();
	}
	
}
