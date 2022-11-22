package ufba;

import java.util.List;

public class Professor implements IUsuario, Observer {
	public String nome; 
	public String codigo; 
	List<String> listaReserva; 
	List<String> listaEmprestimo; 
	@Override
	public void pegarItemEmprestado(IItemBiblioteca item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reservarLivro(IItemBiblioteca item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void devolverLivro(IItemBiblioteca item) {
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
