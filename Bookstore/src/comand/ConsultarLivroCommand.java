package comand;

import outros.Biblioteca;

public class ConsultarLivroCommand implements Command {

	@Override
	public void execute(String[] args) throws Exception {
		Biblioteca.getInstanciaBiblioteca().consultarLivro(args[1]);
	}

}
