package comand;

import outros.Biblioteca;

public class ConsultarUsuarioCommand implements Command {

	@Override
	public void execute(String[] args)  {
		Biblioteca.getInstanciaBiblioteca().consultarUsuario(args[1]);

	}

}
