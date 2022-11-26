package comand;

import outros.Biblioteca;

public class PegarLivroEmprestadoComand implements comand{

	private String CodigoLivro;
	private String CodigoUsuario;
	
	public PegarLivroEmprestadoComand(String CodigoLivro,String CodigoUsuario) {
		
	}
	
	public void execute() {
		Biblioteca instancia = Biblioteca.getInstanciaBiblioteca();
		instancia.PegarLivroEmprestado(this.CodigoUsuario, CodigoLivro);
	}

	
	
}
