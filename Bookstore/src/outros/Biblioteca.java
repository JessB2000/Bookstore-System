package outros;

import java.util.ArrayList;
import java.util.List;

import interfaces.ILivro;
import interfaces.IUsuario;

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
		
		
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
}

public List<ILivro> getListaLivros(){
	return this.listaLivros;
}


}
