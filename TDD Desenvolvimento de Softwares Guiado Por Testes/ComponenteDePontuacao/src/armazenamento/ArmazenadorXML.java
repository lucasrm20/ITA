package armazenamento;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import exceptions.ArmazenamentoException;
import models.ListagemUsuarios;
import models.Usuario;
import pontuacoes.EstrelaPoints;
import pontuacoes.MoedaPoints;
import pontuacoes.Pontuacao;
import pontuacoes.TipoPontuacao;

public class ArmazenadorXML implements Armazenador {
	
	private XStream xStream;

	public ArmazenadorXML() {
		xStream = new XStream(new DomDriver());
		
		xStream.alias("listagemUsuario", ListagemUsuarios.class);
		xStream.alias("usuario", Usuario.class);
		xStream.alias("pontuacao", Pontuacao.class);
		xStream.alias("estrelaPoints", EstrelaPoints.class);
		xStream.alias("moedaPoints", MoedaPoints.class);
		xStream.alias("tipoPontuacao", TipoPontuacao.class);
	}
	
	public void escreverArquivo(ListagemUsuarios listagemUsuarios, String path) throws ArmazenamentoException {
		try {
			String xml = xStream.toXML(listagemUsuarios);

			FileOutputStream fos = new FileOutputStream(path);
			fos.write("<?xml version=\"1.0\"?>\n".getBytes("UTF-8"));
			fos.write(xml.getBytes("UTF-8"));
			fos.close();
		} catch (IOException e) {
			throw new ArmazenamentoException("Erro na criação do arquivo" + e.getMessage());
		}
	}
	
	public ListagemUsuarios lerArquivo(String path){
		ListagemUsuarios listagemUsuarios = new ListagemUsuarios();
		
		if(isArquivoExistente(path))
			listagemUsuarios = (ListagemUsuarios) xStream.fromXML(new File(path));
		
		return listagemUsuarios;
	}
	
	public void deletarArquivo(String path){
		if(isArquivoExistente(path))
			new File(path).delete();
	}
	
	private boolean isArquivoExistente(String path){
		return new File(path).exists();
	}
	
}
