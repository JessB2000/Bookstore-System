package comand;

import outros.Biblioteca;

public class PegarLivroEmprestadoCommand implements Command {

	public void execute(String[] args) {
		Biblioteca.getInstanciaBiblioteca().pegarLivroEmprestado(args[1], args[2]);
	}

}
