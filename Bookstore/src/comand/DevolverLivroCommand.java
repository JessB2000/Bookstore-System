package comand;

import interfaces.ILivro;
import interfaces.IUsuario;
import outros.Biblioteca;

public class DevolverLivroCommand implements Command {
	
	public void execute(String[] args) throws Exception {
		try {
			IUsuario usuario = Biblioteca.getInstanciaBiblioteca().getListaUsuarios()
			 .stream().filter(user -> user.getCodigo().equals(args[1])).toList().get(0);
			
			ILivro livro = usuario.listarEmprestimo().stream().filter(emp -> emp.getLivro().getCodigoLivro().equals(args[2]))
         .toList().get(0).getLivro();
			
			usuario.devolverLivro(livro);
			
		} catch (Exception e) {
			throw new Exception ("NÃO FOI POSSÍVEL DEVOLVER O LIVRO -> " + e.getMessage()); 
		} 
	}
}
