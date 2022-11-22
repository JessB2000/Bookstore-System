package ufba;

import java.util.List;

public interface IUsuario {
public void pegarItemEmprestado(IItemBiblioteca item);
public void reservarLivro(IItemBiblioteca item); 
public void devolverLivro(IItemBiblioteca item); 
public List<Reserva> listarReservas();
public List<Emprestimo> listarEmprestimo();
public String getCodigo();
public String getNome(); 
}
