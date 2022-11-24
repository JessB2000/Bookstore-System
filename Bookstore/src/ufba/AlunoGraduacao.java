package ufba;

import java.util.List;

public class AlunoGraduacao implements IUsuario {
private String nome; 
private String codigo; 
List<Reserva> listaReserva; 
List <Reserva> listaReservaAtiva; 
List<Emprestimo> listaEmprestimo; 
List<Emprestimo> listaEmprestimoAtivo; 

public AlunoGraduacao(String nome, String codigo) {
	this.nome = nome; 
	this.codigo=codigo; 
}
	@Override
	public void pegarLivroEmprestado(ILivro livro) {
		
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
