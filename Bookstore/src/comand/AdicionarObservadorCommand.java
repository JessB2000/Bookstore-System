package comand;

import outros.Biblioteca;

public class AdicionarObservadorCommand implements Command{

	@Override
	public void execute(String[] args) {
		Biblioteca.getInstanciaBiblioteca().addObserver(args[1], args[2]);;
	}

}
