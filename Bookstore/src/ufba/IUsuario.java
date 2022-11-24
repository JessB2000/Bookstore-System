package ufba;

import java.util.List;

public interface IUsuario {
public void pegarLivroEmprestado(ILivro livro);
public void reservarLivro(ILivro livro); 
public void devolverLivro(ILivro livro); 
public List<Reserva> listarReservas();
public List<Emprestimo> listarEmprestimo();  
public String getCodigo();
public String getNome(); 
}
