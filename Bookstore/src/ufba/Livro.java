package ufba;


import java.util.List;

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
	List<EmprestimoLivro> listaEmprestimoAtivo;
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
	public String getCodigoLivro() {
		return this.codigo;

	}
	
	
	@Override
	public void devolverItem() {
		// TODO Auto-generated method stub

	}

	public String getCodigoExemplar() {
		return codigoExemplar;
	}

	@Override
	public void reservarItem(IUsuario usuario) {
		ReservaLivro reserva = new ReservaLivro();
		

	}

	@Override
	public void emprestarItem(IUsuario usuario) {
		this.status = StatusEmprestimoLivro.Emprestado;		
		if(reservaAtiva!= null &&  reservaAtiva.getUsuario().equals(usuario)) {
			usuario.adicionarReservaHistorico(this.reservaAtiva);
			this.reservaAtiva.setIsAtivo(false);
			usuario.removerReservaAtiva(this);
		}else if(reservaAtiva == null || !reservaAtiva.getUsuario().equals(usuario)) {
			reservaAtiva.getUsuario().removerReservaAtiva(this);
			this.reservaAtiva.setIsAtivo(false);
		}
	}
	
	public void desativarReserva() {
		ReservaLivro reserva = new ReservaLivro();
		reserva = reservaAtiva;
		this.listaReserva.add(reserva);
		this.reservaAtiva=null;
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
		// TODO Auto-generated method stub
		return this.status;
	}

	@Override
	public ReservaLivro getReservaAtiva() {
		return reservaAtiva;
	}

	

}
