package ufba;

import java.util.List;

public class Professor implements IUsuario, Observer {
	private String nome; 
	private String codigo; 
	List<Reserva> listaReserva; 
	List <Reserva> listaReservaAtiva;
	List<Emprestimo> listaEmprestimo; 
	List<Emprestimo> listaEmprestimoAtivo; 
	public Professor(String nome, String codigo) {
		this.nome = nome; 
		this.codigo=codigo; 
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
	public List<Reserva> listarReservas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Emprestimo> listarEmprestimo() {
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

}
