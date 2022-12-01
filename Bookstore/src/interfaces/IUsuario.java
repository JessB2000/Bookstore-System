package interfaces;

import java.util.List;

import outros.EmprestimoLivro;
import outros.ReservaLivro;

public interface IUsuario {
	
	public void pegarLivroEmprestado(String codigoLivro) throws Exception;

	public void reservarLivro(String CodigoLivro) throws Exception;

	public void devolverLivro(String Codigolivro);

	public String getCodigo();

	public String getNome();
	
	public List<EmprestimoLivro> getEmprestimosHistorico();
	
	public List<ReservaLivro> getReservasHistorico();
	
	public List<EmprestimoLivro> getEmprestimosAtivos();
	
	public List<ReservaLivro> getReservasAtivas();
	
	public void removerReservaAtiva(ILivro livro);

	public void adicionarReservaHistorico(ReservaLivro reserva);

}
