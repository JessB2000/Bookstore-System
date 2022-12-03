package outros;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import comand.AdicionarObservadorCommand;
import comand.Command;
import comand.ConsultarLivroCommand;
import comand.ConsultarUsuarioCommand;
import comand.DevolverLivroCommand;
import comand.NotificacoesCommand;
import comand.PegarLivroEmprestadoCommand;
import comand.ReservarLivroCommand;
import interfaces.ILivro;
import interfaces.IUsuario;
import interfaces.Observer;

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
		this.comandos.put("ntf", new NotificacoesCommand());
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
			System.out.println(obterUsuario(codigoUsuario).toString());
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
				System.out.println("\n ---------------------------------------- \nQt. Reservas: "+livros.stream()
				.filter(liv ->liv.getStatus().equals(StatusEmprestimoLivro.Reservado)).toList().size());
			}

		} catch (Exception e) {
			System.out.println("NÃO FOI POSSÍVEL OBTER O LIVRO -> " + e.getMessage());
		}

	}

	public void pegarLivroEmprestado(String codigoUsuario, String codigoLivro) {
		try {
			obterUsuario(codigoUsuario).pegarLivroEmprestado(codigoLivro);
			System.out.println("EMPRESTIMO REALIZADO COM SUCESSO!");
		} catch (Exception e) {
			System.out.println("NÃO FOI POSSÍVEL PEGAR O LIVRO -> " + e.getMessage());
		}
	}

	public void reservarLivro(String codigoUsuario, String codigoLivro) {
		try {
			obterUsuario(codigoUsuario).reservarLivro(codigoLivro);
			System.out.println("RESERVA REALIZADA COM SUCESSO!");
		} catch (Exception e) {
			System.out.println("NÃO FOI POSSÍVEL PEGAR O LIVRO -> " + e.getMessage());
		}
	}

	public void devovlerLivro(String codigoUsuario, String codigoLivro) {
		try {
			obterUsuario(codigoUsuario).devolverLivro(codigoLivro);
			System.out.println("LIVRO DEVOLVIDO COM SUCESSO!");
		} catch (Exception e) {
			System.out.println("NÃO FOI POSSÍVEL DEVOLVER O LIVRO -> " + e.getMessage());
		}
	}
   public void consultarNotificacoes(String codigoUsuario) {
	   Observer observador = (Observer) obterUsuario(codigoUsuario);
	   System.out.printf("QUANTIDADE DE NOTIFICAÇÕES: %d", observador.getQtNotificacoes()); 
   }
	private IUsuario obterUsuario(String codigoUsuario) {
		return this.listaUsuarios.stream().filter(user -> user.getCodigo().equals(codigoUsuario)).toList().get(0);
	}

	public void addObserver(String codigoUsuario, String codigoLivro) {
		
		Observer observador = (Observer) obterUsuario(codigoUsuario);
		
			this.listaLivros.forEach(liv -> {
				Subject livro = (Subject) liv;
				livro.addObserver(observador);
			});
	}
}
