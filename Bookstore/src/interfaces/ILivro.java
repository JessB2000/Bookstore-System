package interfaces;

import outros.EmprestimoLivro;
import outros.ReservaLivro;
import outros.StatusEmprestimoLivro;

public interface ILivro  {
	public String getHistoricoEmprestimo();
	public String getLocatario(IUsuario usuario);
	public void devolverItem(IUsuario usuario, ILivro livro, EmprestimoLivro emprestimo);
	public void reservarItem(IUsuario usuario);
	public void emprestarItem(IUsuario usuario, EmprestimoLivro emprestimo);
	public String getCodigoLivro();
	public String getTitulo();
	public String getAutor();
	public String getCodigoExemplar(); 
	public String getEdicao();
	public String getAnoPublicacao();
	public StatusEmprestimoLivro getStatus(); 
	public ReservaLivro getReservaAtiva(); 
}
