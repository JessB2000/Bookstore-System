package ufba;

import java.util.List;

public class Livro implements ILivro, Subject{
private String codigo; 
private String codigoExemplar; 
private String titulo; 
private String autor;
private String edicao; 
private String anopublicacao;
List<Reserva> listaReserva; 
List <Reserva> listaReservaAtiva;
List<Emprestimo> listaEmprestimo; 
List<Emprestimo> listaEmprestimoAtivo; 
StatusEmprestimoLivro status = StatusEmprestimoLivro.Emprestado; 
IUsuario locatario;
public Livro(String codigo, String titulo, String autor, String edicao, String anopublicacao, String codigoExemplar) { 
	this.codigo=codigo; 
	this.titulo = titulo; 
	this.autor = autor;
	this.edicao = edicao; 
	this.anopublicacao=anopublicacao; 
	this.codigoExemplar=codigoExemplar; 
}
	@Override
	public String getTitulo() {
		return titulo;
	}

	@Override
	public String getAutor() {
		return autor;
	}

	@Override
	public String getEdicao() {
		return edicao;
	}

	@Override
	public String getAnoPublicacao() {
		return anopublicacao;
	}

	@Override
	public void addObserver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeObserver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCodigo() {
		// TODO Auto-generated method stub
		return codigo;
	}
	@Override
	public int getCodigoItem() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getHistoricoEmprestimo() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getLocatario(IUsuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void devolverItem() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void reservarItem() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void emprestarItem() {
		// TODO Auto-generated method stub
		
	}
	public String getCodigoExemplar() {
		return codigoExemplar;
	}

}
