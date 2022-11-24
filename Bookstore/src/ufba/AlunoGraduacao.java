package ufba;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlunoGraduacao implements IUsuario {
private String nome; 
private String codigo; 
private int LIMITE_EMPRESTIMO = 3;
private boolean isDevedor;
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
			if(this.isDevedor()) {
				throw new Exception("USUARIO ESTÁ EM DIVIDA COM A BIBLIOTECA");
			}
			livro.emprestarItem(this);
			desativarReserva(livro.getCodigoLivro());
			this.listaEmprestimoAtivo.add(new Emprestimo());
		
		}
	}
	
	
	public void desativarReserva(String codigoLivro) {
		List<Reserva> reservasAtiva = this.listaReservaAtiva.stream().filter(reserva -> reserva.getCodigoItem().equals(codigoLivro)).toList();
		if(reservasAtiva.size()>0) {
			reservasAtiva.get(0).setIsAtivo(false);
			this.listaReserva.add(reservasAtiva.get(0));
			this.listaReservaAtiva.remove(reservasAtiva.get(0));
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
	
	
	private boolean isDevedor() {
		return this.listaEmprestimoAtivo.stream().anyMatch(emprestimo ->emprestimo.dataDevolucaoPrevista.after(new Date()));
	}

	@Override
	public String getNome() {
		return nome;
	}
	
	

}
