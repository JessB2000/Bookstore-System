package ufba;

import java.util.ArrayList;
import java.util.List;

public class AlunoGraduacao implements IUsuario {
private String nome; 
private String codigo; 
private int LIMITE_EMPRESTIMO = 3;
List<Reserva> listaReserva; 
List <Reserva> listaReservaAtiva; 
List<Emprestimo> listaEmprestimo; 
List<Emprestimo> listaEmprestimoAtivo; 

public AlunoGraduacao(String nome, String codigo) {
	this.nome = nome; 
	this.codigo=codigo; 
	this.listaReserva = new ArrayList<>();
	this.listaReservaAtiva = new ArrayList<>();
	this.listaEmprestimo = new ArrayList<>(); 
	this.listaEmprestimoAtivo  = new ArrayList<>(); 
	
}
	@Override
	public void pegarLivroEmprestado(ILivro livro) throws Exception {
		if (listaEmprestimoAtivo.size()>= LIMITE_EMPRESTIMO) {
			throw new Exception("erro"); 
		}
		else {
			livro.emprestarItem(this);
		}
	}

	@Override
	public void reservarLivro(ILivro livro) {
		
	}

	@Override
	public void devolverLivro(ILivro livro) {
		
	}

	@Override
	public List<Reserva> listarReservas() {
		return null;
	}

	@Override
	public List<Emprestimo> listarEmprestimo() {
		return null;
	}

	@Override
	public String getCodigo() {
		return codigo;
	}

	@Override
	public String getNome() {
		return nome;
	}

}
