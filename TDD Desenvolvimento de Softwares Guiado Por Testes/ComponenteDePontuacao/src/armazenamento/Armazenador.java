package armazenamento;

import exceptions.ArmazenamentoException;
import models.ListagemUsuarios;

public interface Armazenador {

	public void escreverArquivo(ListagemUsuarios listagemUsuarios, String path) throws ArmazenamentoException;
	
	public ListagemUsuarios lerArquivo(String path);
	
	public void deletarArquivo(String path);
	
}
