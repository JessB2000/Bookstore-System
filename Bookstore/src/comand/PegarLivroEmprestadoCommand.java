package comand;

import outros.Biblioteca;

public class PegarLivroEmprestadoCommand implements Command{
	
	public void execute(String[] args) throws Exception {
		try {
			Biblioteca.getInstanciaBiblioteca().getListaUsuarios()
			 .stream().filter(user -> user.getCodigo().equals(args[1])).toList().get(0).pegarLivroEmprestado(args[2]);
			 System.out.println("EMPRESTIMO REALIZADO COM SUCESSO!");
		} catch (Exception e) {
			throw new Exception ("NÃO FOI POSSÍVEL PEGAR O LIVRO -> " + e.getMessage()); 
		} 
		
	}

	
	
}
