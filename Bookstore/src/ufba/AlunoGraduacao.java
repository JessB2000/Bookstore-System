package ufba;

import java.util.List;

public class AlunoGraduacao implements IUsuario {
public String nome; 
public String codigo; 
List<String> listaReserva; 
List<String> listaEmprestimo; 

	@Override
	public void pegarItemEmprestado(IItemBiblioteca item) {
		
	}

	@Override
	public void reservarLivro(IItemBiblioteca item) {
		
	}

	@Override
	public void devolverLivro(IItemBiblioteca item) {
		
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
