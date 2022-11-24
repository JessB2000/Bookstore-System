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
		List <ILivro> listaLivro = listaLivros.stream().filter(liv->(liv.getCodigoLivro().equals(codigoLivro))).toList();
		List <ILivro> listaLivrosReservados = listaLivros.stream().filter(liv->liv.getStatus().equals(StatusEmprestimoLivro.Reservado)).toList();
		List <ILivro> listaLivrosLivres = listaLivros.stream().filter(liv->liv.getStatus().equals(StatusEmprestimoLivro.Livre)).toList();
	    
		listaLivrosReservados.forEach(livro -> {
			if(livro.getReservaAtiva().getUsuario().equals(usuario)) {
			}
		});
		usuario.pegarLivroEmprestado(listalivro);
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
}



}
