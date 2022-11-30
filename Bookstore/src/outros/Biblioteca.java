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
	public List <IUsuario> getListaUsuarios(){
		return this.listaUsuarios; 
	}
	public List<ILivro> getListaLivros() {
		return this.listaLivros;
	}

}
