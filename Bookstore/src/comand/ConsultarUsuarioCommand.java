package comand;

import outros.Biblioteca;

public class ConsultarUsuarioCommand implements Command {

	@Override
	public void execute(String[] args) throws Exception {
		try {
			
			Biblioteca.getInstanciaBiblioteca().getListaUsuarios().stream()
					.filter(user -> user.getCodigo().equals(args[1]))
					.toList().get(0).toString();
		} catch (Exception e) {
			throw new Exception ("NÃO FOI POSSÍVEL OBTER O USUARIO -> " + e.getMessage()); 
		}

	}

}
