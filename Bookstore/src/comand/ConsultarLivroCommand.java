package comand;

import java.util.List;

import interfaces.ILivro;
import outros.Biblioteca;

public class ConsultarLivroCommand implements Command {

	@Override
	public void execute(String[] args) throws Exception {
		try {
		List<ILivro> livros = Biblioteca.getInstanciaBiblioteca().getListaLivros().stream()
			.filter(livro -> livro.getCodigoLivro().equals(args[1])).toList();
		
		if(livros.size()<=0) {
			throw new Exception ("NÃO FOI POSSÍVEL OBTER O LIVRO -> Livro não existe ");
		}else {
			livros.forEach(livro -> System.out.println(livro.toString()));
		}
		
		} catch (Exception e) {
			throw new Exception ("NÃO FOI POSSÍVEL OBTER O LIVRO -> " + e.getMessage()); 
		}
	}

}
