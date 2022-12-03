package comand;

import outros.Biblioteca;

public class NotificacoesCommand implements Command{

	@Override
	public void execute(String[] args) throws Exception {
		Biblioteca.getInstanciaBiblioteca().consultarNotificacoes(args[1]);
		
	}

}
