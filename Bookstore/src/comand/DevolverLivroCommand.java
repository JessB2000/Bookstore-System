package comand;

import interfaces.ILivro;
import interfaces.IUsuario;
import outros.Biblioteca;

public class DevolverLivroCommand implements Command {
	
	public void execute(String[] args) throws Exception {
		Biblioteca.getInstanciaBiblioteca().devovlerLivro(args[1], args[2]);
	}
}
