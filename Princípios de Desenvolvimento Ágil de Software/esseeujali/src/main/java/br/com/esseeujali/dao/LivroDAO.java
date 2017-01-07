package br.com.esseeujali.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import br.com.esseeujali.exceptions.LivroNaoEncontradoException;
import br.com.esseeujali.models.Livro;

public class LivroDAO {

	private EntityManagerFactory factory;

	public LivroDAO(EntityManagerFactory factory) {
		this.factory = factory;
	}
	
	public Livro salvar(Livro livro) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		
		manager.persist(livro);
		
		manager.getTransaction().commit();
		manager.close();
		
		return livro;
	}
	
	public List<Livro> buscaTodos() {
		EntityManager manager = factory.createEntityManager();
		
		TypedQuery<Livro> query = manager.createQuery("SELECT l FROM Livro l", Livro.class);
		List<Livro> resultList = query.getResultList();
		
		manager.close();
		
		return resultList;
	}

	public Livro buscaLivro(Long id) throws LivroNaoEncontradoException {
		EntityManager manager = factory.createEntityManager();
		
		Livro livro = manager.find(Livro.class, id);
		
		manager.close();
		
		if(livro == null)
			throw new LivroNaoEncontradoException("Id inexistente no banco de dados");
			
		return livro;
	}
	
}
