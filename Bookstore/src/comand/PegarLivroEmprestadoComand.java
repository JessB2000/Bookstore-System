package comand;

import outros.Biblioteca;

public class PegarLivroEmprestadoComand implements comand{
	
	public PegarLivroEmprestadoComand() {
		
	}
	
	public void execute(String[] args) {
		Biblioteca instancia = Biblioteca.getInstanciaBiblioteca();
		instancia.pegarLivroEmprestado(args[1], args[2]);
	}

	
	
}
