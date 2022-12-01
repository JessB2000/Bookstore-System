package outros;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

import javax.lang.model.type.NullType;

import entidades.AlunoGraduacao;
import entidades.Livro;
import entidades.Professor;
import interfaces.ILivro;
import interfaces.IUsuario;

public class FuncionamentoSistema {
	
	
	private static void run() {
		Scanner sc = new Scanner(System.in);
		try {
			String input = sc.nextLine();
			
			String[] arguments = input.split(" ");
			Biblioteca.getInstanciaBiblioteca().executar(arguments);
		} catch (Exception e) {
			e.getClass();
			System.out.println(e.getMessage());
		}
	}
	

	public static void main(String[] args) {
		
		scriptInitial();
		System.out.println("Bem Vindo - Sistema Iniciado:");
		while(true) {
			run();
		}
	
	}
	
			


	
	private static void scriptInitial(){
		
		Biblioteca.getInstanciaBiblioteca().getListaLivros()
		.add(new Livro("11","Harry Potter 1","JK","2010","2020"));
		Biblioteca.getInstanciaBiblioteca().getListaUsuarios()
		.add(new Professor("ÍCARO", "245"));
	}
	
	
	
	
	
}
