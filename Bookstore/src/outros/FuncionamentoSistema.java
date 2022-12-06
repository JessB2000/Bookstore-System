package outros;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

import javax.lang.model.type.NullType;

import entidades.AlunoGraduacao;
import entidades.AlunoPosGraduacao;
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
		while (true) {
			run();
		}

	}

	private static void scriptInitial() {
		Biblioteca.getInstanciaBiblioteca().getListaUsuarios().add(new AlunoGraduacao("João da Silva", "123"));
		Biblioteca.getInstanciaBiblioteca().getListaUsuarios()
				.add(new AlunoPosGraduacao("Luiz Fernando Rodrigues", "456"));
		Biblioteca.getInstanciaBiblioteca().getListaUsuarios().add(new AlunoGraduacao("Pedro Paulo", "789"));
		Biblioteca.getInstanciaBiblioteca().getListaUsuarios().add(new Professor("Carlos Lucena", "100"));
		Biblioteca.getInstanciaBiblioteca().getListaLivros()
				.add(new Livro("100", "Engenharia de Software", "Ian Sommervile", "6º", "2000"));
		Biblioteca.getInstanciaBiblioteca().getListaLivros().add(
				new Livro("101", "UML-Guia do Usuário", "Grady Booch, James Rumbaugh, Ivar Jacobson", "7ª", "2000"));
		Biblioteca.getInstanciaBiblioteca().getListaLivros()
				.add(new Livro("200", "Code Complete", "Steve McConnell", "2ª", "2014"));
		Biblioteca.getInstanciaBiblioteca().getListaLivros().add(new Livro("201",
				"Agile Software, Development,Principles, Patterns,and Practices", "Robert Martin", "1ª", "2002"));
		Biblioteca.getInstanciaBiblioteca().getListaLivros().add(
				new Livro("300", "Refactoring:Improving the Design of Existing Code", "Martin Fowler", "1ª", "1999"));
		Biblioteca.getInstanciaBiblioteca().getListaLivros().add(
				new Livro("300", "Refactoring:Improving the Design of Existing Code", "Martin Fowler", "1ª", "1999"));
		Biblioteca.getInstanciaBiblioteca().getListaLivros().add(new Livro("301",
				"Software Metrics: A Rigorous and Practical Approach", "Norman Fenton,James Bieman", "3ª", "2014"));
		Biblioteca.getInstanciaBiblioteca().getListaLivros()
				.add(new Livro("400", "Design Patterns: Elements of Reusable Object-Oriented Software",
						"Erich Gamma,Richard Helm, Ralph Johnson, John Vlissides", "1ª", "1994"));
		Biblioteca.getInstanciaBiblioteca().getListaLivros()
		.add(new Livro("400", "Design Patterns: Elements of Reusable Object-Oriented Software",
				"Erich Gamma,Richard Helm, Ralph Johnson, John Vlissides", "1ª", "1994"));
		Biblioteca.getInstanciaBiblioteca().getListaLivros()
				.add(new Livro("401", "UML Distilled: A Brief Guide to the Standard Object Modeling Language",
						"Martin Fowler", "3ª", "2003"));
	}

}
