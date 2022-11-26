package entidades;

import java.util.ArrayList;
import java.util.List;

import interfaces.ILivro;
import interfaces.IUsuario;
import outros.EmprestimoLivro;
import outros.Observer;
import outros.ReservaLivro;

public class Professor implements IUsuario, Observer {
	private String nome; 
	private String codigo; 
	List<ReservaLivro> listaReservaHistorico; 
	List <ReservaLivro> listaReservaAtiva;
	List<EmprestimoLivro> listaEmprestimo; 
	List<EmprestimoLivro> listaEmprestimoAtivo; 
	public Professor(String nome, String codigo) {
		this.nome = nome; 
		this.codigo=codigo; 
		this.listaReservaHistorico = new ArrayList<>();
		this.listaReservaAtiva = new ArrayList<>();
		this.listaEmprestimo = new ArrayList<>(); 
		this.listaEmprestimoAtivo  = new ArrayList<>(); 
		
	}
	@Override
	public void pegarLivroEmprestado(ILivro livro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reservarLivro(ILivro livro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void devolverLivro(ILivro livro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ReservaLivro> listarReservas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmprestimoLivro> listarEmprestimo() {
		// TODO Auto-generated method stub
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

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	public void removerReservaAtiva(ILivro livro) {
		listaReservaAtiva.removeIf(reserva -> reserva.getLivro().equals(livro));
	}
	
	public void adicionarReservaHistorico(ReservaLivro reserva) {
		listaReservaAtiva.add(reserva);
	}
	

}
