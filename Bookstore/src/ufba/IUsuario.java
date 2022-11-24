package ufba;

import java.util.List;

public interface IUsuario {
public void pegarLivroEmprestado(ILivro livro) throws Exception;
public void reservarLivro(ILivro livro); 
public void devolverLivro(ILivro livro); 
public List<ReservaLivro> listarReservas();
public List<EmprestimoLivro> listarEmprestimo();  
public String getCodigo();
public String getNome(); 
}
