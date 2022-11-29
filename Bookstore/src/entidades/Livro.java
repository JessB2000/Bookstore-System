package entidades;


import java.time.LocalDate;
import java.util.List;

import interfaces.ILivro;
import interfaces.IUsuario;
import outros.EmprestimoLivro;
import outros.ReservaLivro;
import outros.StatusEmprestimoLivro;
import outros.Subject;

public class Livro implements ILivro, Subject {
	private String codigo;
	private String codigoExemplar;
	private String titulo;
	private String autor;
	private String edicao;
	private String anopublicacao;
	List<ReservaLivro> listaReserva;
	ReservaLivro reservaAtiva;
	List<EmprestimoLivro> listaEmprestimo;
	EmprestimoLivro EmprestimoAtivo;
	StatusEmprestimoLivro status;
	IUsuario locatario;

	public Livro(String codigo, String titulo, String autor, String edicao, String anopublicacao,
		String codigoExemplar) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.autor = autor;
		this.edicao = edicao;
		this.anopublicacao = anopublicacao;
		this.codigoExemplar = codigoExemplar;
		this.status = StatusEmprestimoLivro.Livre; 
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
	public List <EmprestimoLivro> getHistoricoEmprestimo() {
		return listaEmprestimo;
	}

	@Override
	public IUsuario getLocatario(IUsuario usuario) {
		return locatario;
	}

	@Override
	public String getCodigoLivro() {
		return this.codigo;

	}
	
	
	@Override
	public void devolverItem(IUsuario usuario, ILivro livro, EmprestimoLivro emprestimo) {
		this.locatario = null; 
		this.status = StatusEmprestimoLivro.Livre; 
	    this.listaEmprestimo.add(emprestimo); 
        this.EmprestimoAtivo = null; 
        
	}

	public String getCodigoExemplar() {
		return codigoExemplar;
	}

	@Override
	public void reservarItem(IUsuario usuario, ReservaLivro reserva) {
		this.reservaAtiva = reserva; 
		this.status = StatusEmprestimoLivro.Reservado; 
	}

	@Override
	public void emprestarItem(IUsuario usuario, EmprestimoLivro emprestimo) {
		this.status = StatusEmprestimoLivro.Emprestado;		
		if(reservaAtiva.getUsuario().equals(usuario)) {
			usuario.adicionarReservaHistorico(this.reservaAtiva);
			usuario.removerReservaAtiva(this);
			this.locatario = usuario; 
			this.EmprestimoAtivo = emprestimo; 
		}else if(reservaAtiva == null || !reservaAtiva.getUsuario().equals(usuario)) {
			reservaAtiva.getUsuario().removerReservaAtiva(this);
			this.locatario = usuario; 
			this.EmprestimoAtivo = emprestimo;
		}
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}

		Livro livro = (Livro) obj;

		return ((livro.getCodigoExemplar() == this.codigoExemplar) && (livro.getCodigoLivro().equals(this.codigo)));
	}

	@Override
	public StatusEmprestimoLivro getStatus() {
		return this.status;
	}

	@Override
	public ReservaLivro getReservaAtiva() {
		return reservaAtiva;
	}

	

}
