package outros;

import java.util.ArrayList;
import java.util.List;

import interfaces.ILivro;
import interfaces.IUsuario;

public class Biblioteca {
	private List<IUsuario> listaUsuarios;
	private List<ILivro> listaLivros;
	private static Biblioteca instanciaBiblioteca;

	private Biblioteca() {
		this.listaUsuarios = new ArrayList<IUsuario>();
		this.listaLivros = new ArrayList<ILivro>();
	}

	public static Biblioteca getInstanciaBiblioteca() {
		if (instanciaBiblioteca == null) {
			instanciaBiblioteca = new Biblioteca();
		}
		return instanciaBiblioteca;
	}

	public void pegarLivroEmprestado(String codigoUsuario, String codigoLivro) {

	}

	public void reservarLivro(String codigoUsuario, String codigoLivro) {

	}

	public void devolverLivro(String codigoUsuario, String codigoLivro) {

	}

	public void consultarLivro(String codigoUsuario, String codigoLivro) {

	}

	public void consultarUsuario(String usuario) {

	}

	public List<ILivro> getListaLivros() {
		return this.listaLivros;
	}

}
