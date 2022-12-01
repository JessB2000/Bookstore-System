package entidades;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
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
	private List<ReservaLivro> listaReserva;
	private ReservaLivro reservaAtiva;
	private List<EmprestimoLivro> listaEmprestimo;
	private EmprestimoLivro EmprestimoAtivo;
	private StatusEmprestimoLivro status;
	private IUsuario locatario;
	private static int gerarCodigoExemplar =1;

	public Livro(String codigo, String titulo, String autor, String edicao, String anopublicacao) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.autor = autor;
		this.edicao = edicao;
		this.anopublicacao = anopublicacao;
		this.gerarCodigoExemplar = this.gerarCodigoExemplar++;
		this.codigoExemplar = String.valueOf(gerarCodigoExemplar);
		this.status = StatusEmprestimoLivro.Livre; 
		this.listaReserva = new ArrayList<>();
		this.listaEmprestimo = new ArrayList<>();
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
		
		if(reservaAtiva!= null && reservaAtiva.getUsuario().equals(usuario)) {
		
			if(this.reservaAtiva!= null) {
				usuario.adicionarReservaHistorico(this.reservaAtiva);
			}
		
			usuario.removerReservaAtiva(this);
			this.locatario = usuario; 
			this.EmprestimoAtivo = emprestimo; 
		}else if(reservaAtiva == null || !reservaAtiva.getUsuario().equals(usuario)) {
			
		
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

	
	
	@Override
	public String toString() {
		
		String reservaView = "";
		String emprestimoView = "";
		if(this.listaReserva.size()<=0) {
			reservaView = "SEM RESERVA";
		}else {
			for(int i=0; i< this.listaReserva.size();i++) {
				reservaView.concat("\n"+this.listaReserva.get(i).getUsuario().getNome());
			}
		}
		
		if(this.reservaAtiva!=null) {
			reservaView.concat("\n"+this.reservaAtiva.getUsuario().getNome());
		}
		
		if(this.EmprestimoAtivo!=null) {
			emprestimoView.concat("\nEmprestimo Ativo:  || Usuario: "+this.EmprestimoAtivo.getUsuario().getNome()+
					"   || Data Emprestimo: "+this.EmprestimoAtivo.getDataEmprestimo()+
					"   || Data Devolução: "+this.EmprestimoAtivo.getDataDevolucaoPrevista());
		}
		
		
		
		String usuarioView = 
				"\n------------------------------------------------------"
				+ " \nCodigo Exemplar: "+this.codigoExemplar+
				"\nTitulo: "+this.titulo+
				"\nQt. Reservas: "+reservaView+
				"\nStatus: "+this.status+
				emprestimoView;
		
		return usuarioView;
	}
	

}
