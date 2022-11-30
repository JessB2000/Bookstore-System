package interfaces;

import java.util.List;

import outros.EmprestimoLivro;
import outros.ReservaLivro;

public interface IUsuario {
public void pegarLivroEmprestado(String codigoLivro) throws Exception;
public void reservarLivro(ILivro livro) throws Exception; 
public void devolverLivro(ILivro livro); 
public List<ReservaLivro> listarReservas();
public List<EmprestimoLivro> listarEmprestimo();  
public String getCodigo();
public String getNome();
public void removerReservaAtiva(ILivro livro);
public void adicionarReservaHistorico(ReservaLivro reserva); 
public EmprestimoLivro obterEmprestimoAtivo(ILivro livro); 
}
