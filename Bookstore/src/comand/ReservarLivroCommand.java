package comand;

import outros.Biblioteca;

public class ReservarLivroCommand implements Command {

	@Override
	public void execute(String[] args) {
		Biblioteca.getInstanciaBiblioteca().reservarLivro(args[1], args[2]);
	}

}
