package outros;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import comand.AdicionarObservadorCommand;
import comand.Command;
import comand.ConsultarLivroCommand;
import comand.ConsultarUsuarioCommand;
import comand.DevolverLivroCommand;
import comand.PegarLivroEmprestadoCommand;
import comand.ReservarLivroCommand;
import interfaces.ILivro;
import interfaces.IUsuario;

public class Biblioteca {
	private List<IUsuario> listaUsuarios;
	private List<ILivro> listaLivros;
	private static Biblioteca instanciaBiblioteca;
	private Map<String, Command> comandos;

	private Biblioteca() {
		this.listaUsuarios = new ArrayList<IUsuario>();
		this.listaLivros = new ArrayList<ILivro>();
		this.comandos = new HashMap<>();
		this.comandos.put("dev", new DevolverLivroCommand());
		this.comandos.put("res", new ReservarLivroCommand());
		this.comandos.put("liv", new ConsultarLivroCommand());
		this.comandos.put("emp", new PegarLivroEmprestadoCommand());
		this.comandos.put("usu", new ConsultarUsuarioCommand());
		this.comandos.put("obs", new AdicionarObservadorCommand());
	}

	public static Biblioteca getInstanciaBiblioteca() {
		if (instanciaBiblioteca == null) {
			instanciaBiblioteca = new Biblioteca();
		}
		return instanciaBiblioteca;
	}

	public void executar(String[] args) throws Exception {
		this.comandos.get(args[0]).execute(args);
	}

	public List<IUsuario> getListaUsuarios() {
		return this.listaUsuarios;
	}

	public List<ILivro> getListaLivros() {
		return this.listaLivros;
	}

	public void consultarUsuario(String codigoUsuario) {

		try {
			this.getListaUsuarios().stream().filter(user -> user.getCodigo().equals(codigoUsuario)).toList().get(0)
					.toString();
		} catch (Exception e) {
			System.out.println("NÃO FOI POSSÍVEL OBTER O USUARIO -> " + e.getMessage());
		}

	}

	public void consultarLivro(String codigoLivro) {
		try {
			List<ILivro> livros = this.getListaLivros().stream()
					.filter(livro -> livro.getCodigoLivro().equals(codigoLivro)).toList();
			if (livros.size() <= 0) {
				throw new Exception("NÃO FOI POSSÍVEL OBTER O LIVRO -> Livro não existe ");
			} else {
				livros.forEach(livro -> System.out.println(livro.toString()));
			}

		} catch (Exception e) {
			System.out.println("NÃO FOI POSSÍVEL OBTER O LIVRO -> " + e.getMessage());
		}

	}

	public void pegarLivroEmprestado(String codigoUsuario, String codigoLivro) {
		try {
			this.getListaUsuarios().stream().filter(user -> user.getCodigo().equals(codigoUsuario)).toList().get(0)
					.pegarLivroEmprestado(codigoLivro);
			System.out.println("EMPRESTIMO REALIZADO COM SUCESSO!");
		} catch (Exception e) {
			System.out.println("NÃO FOI POSSÍVEL PEGAR O LIVRO -> " + e.getMessage());
		}
	}

	public void reservarLivro(String codigoUsuario, String codigoLivro) {
		try {
			this.getListaUsuarios().stream().filter(user -> user.getCodigo().equals(codigoUsuario)).toList().get(0)
					.reservarLivro(null);
			System.out.println("RESERVA REALIZADA COM SUCESSO!");
		} catch (Exception e) {
			System.out.println("NÃO FOI POSSÍVEL PEGAR O LIVRO -> " + e.getMessage());
		}
	}

	public void devovlerLivro(String codigoUsuario, String codigoLivro) {
		try {
		this.listaUsuarios
			 .stream().filter(user -> user.getCodigo()
				.equals(codigoUsuario)).toList().get(0)
				.devolverLivro(codigoLivro);
		System.out.println("LIVRO DEVOLVIDO COM SUCESSO!");
		} catch (Exception e) {
			System.out.println("NÃO FOI POSSÍVEL DEVOLVER O LIVRO -> " + e.getMessage()); 
		} 
	}

}
