package comand;

import outros.Biblioteca;

public class ConsultarLivroCommand implements Command {

	@Override
	public void execute(String[] args) throws Exception {
		try {
			Biblioteca.getInstanciaBiblioteca().getListaLivros().stream()
			.filter(livro -> livro.getCodigoLivro().equals(args[1]))
			.toList().forEach(liv->liv.toString());
		} catch (Exception e) {
			throw new Exception ("NÃO FOI POSSÍVEL OBTER O LIVRO -> " + e.getMessage()); 
		}
	}

}
