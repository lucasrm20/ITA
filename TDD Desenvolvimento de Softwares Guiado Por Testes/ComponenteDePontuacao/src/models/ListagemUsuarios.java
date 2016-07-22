package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.UsuarioInexistenteException;
import pontuacoes.Pontuacao;

public class ListagemUsuarios {

	private Map<String, Usuario> usuarios = new HashMap<>();
	
	public void inserir(String nomeUsuario, Pontuacao pontuacao){
		Usuario usuario;
		if(isUsuarioExistente(nomeUsuario))
			usuario = usuarios.get(nomeUsuario);
		else
			usuario = new Usuario(nomeUsuario);
		
		usuario.adicionaUmaPontuacao(pontuacao);
		usuarios.put(nomeUsuario, usuario);
	}
	
	public Usuario buscarUsuario(String nomeUsuario) throws UsuarioInexistenteException{
		if(isUsuarioExistente(nomeUsuario))
			return usuarios.get(nomeUsuario);
		
		throw new UsuarioInexistenteException("O usuário " + nomeUsuario + " não existe.");
	}
	
	public boolean isUsuarioExistente(String nomeUsuario){
		return usuarios.containsKey(nomeUsuario);
	}
	
	public List<Usuario> getUsuariosComPontuacao() {
		return new ArrayList<Usuario>(usuarios.values());
	}
	
	public int size(){
		return usuarios.size();
	}
	
	public Map<String, Usuario> getUsuarios() {
		return Collections.unmodifiableMap(usuarios);
	}
	
}
