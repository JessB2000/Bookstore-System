package ufba;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
List<IUsuario> listaUsuarios;
List<ILivro> listaLivros; 
private static Biblioteca instanciaBiblioteca;
private Biblioteca() {
	this.listaUsuarios = new ArrayList<IUsuario>();
	this.listaLivros = new ArrayList<ILivro>();
}

public static Biblioteca getInstanciaBiblioteca() {
	if(instanciaBiblioteca==null) {
		instanciaBiblioteca = new Biblioteca(); 
	}
	return instanciaBiblioteca; 
}

public void PegarLivroEmprestado(String codigoUsuario, String codigoLivro) {
	try {
		IUsuario usuario = listaUsuarios.stream().filter(user->user.getCodigo().equals(codigoUsuario)).toList().get(0);
		ILivro livro = listaLivros.stream().filter(liv->liv.getCodigo().equals(codigoLivro)).toList().get(0);
		usuario.pegarLivroEmprestado(livro);
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
}



}
