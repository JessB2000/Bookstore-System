package entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.lang.model.type.NullType;

import interfaces.ILivro;
import interfaces.IUsuario;
import outros.Biblioteca;
import outros.EmprestimoLivro;
import outros.ReservaLivro;
import outros.StatusEmprestimoLivro;

public class AlunoPosGraduacao implements IUsuario {
	
	private String nome;
	private String codigo;
	private int LIMITE_EMPRESTIMO = 4;
	private int LIMITE_RESERVA = 3; 
	private int PRAZO_DEVOLUCAO = 4;
	private List<ReservaLivro> listaReservaHistorico;
	private List<ReservaLivro> listaReservaAtiva;
	private List<EmprestimoLivro> listaEmprestimo;
	private List<EmprestimoLivro> listaEmprestimoAtivo;
	private NullType SEM_RESERVA = null;
	
	public AlunoPosGraduacao(String nome, String codigo) {
		this.nome = nome;
		this.codigo = codigo;
		this.listaReservaHistorico = new ArrayList<>();
		this.listaReservaAtiva = new ArrayList<>();
		this.listaEmprestimo = new ArrayList<>();
		this.listaEmprestimoAtivo = new ArrayList<>();
	}
	
	//// ----------- GETTES E SETTERS
	
	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public String getCodigo() {
		return codigo;
	}
	
	@Override
	public List<EmprestimoLivro> getEmprestimosHistorico() {
		
		return this.listaEmprestimo;
	}


	@Override
	public List<ReservaLivro> getReservasHistorico() {
	
		return this.listaReservaHistorico;
	}


	@Override
	public List<EmprestimoLivro> getEmprestimosAtivos() {
		
		return this.listaEmprestimoAtivo;
	}


	@Override
	public List<ReservaLivro> getReservasAtivas() {
		
		return this.listaReservaAtiva;
	}

	
	///// FUNÇÕES PRINCIPAIS

	@Override
	public void pegarLivroEmprestado(String codigoLivro) throws Exception {

		if (listaEmprestimoAtivo.size() >= LIMITE_EMPRESTIMO) {
			throw new Exception("LIMITE DE EMPRESTIMO EXECIDO");
		}
		if (this.isDevedor()) {
			throw new Exception("USUARIO ESTÁ EM DIVIDA COM A BIBLIOTECA");
		}
		if (this.listaEmprestimoAtivo.stream().anyMatch(emp -> emp.getLivro().getCodigoLivro().equals(codigoLivro))) {
			throw new Exception("USUARIO JÁ POSSUI EMPRÉSTIMO DESSE LIVRO");
		}

		ReservaLivro reserva = obterReserva(codigoLivro);

		if (reserva == SEM_RESERVA) {

			List<ILivro> livros_livres = getLivrosLivresAndCodigo(codigoLivro);

			if (livros_livres.size() <= 0) {
				throw new Exception("NÃO HÁ LIVROS DISPONÍVEIS");
			}

			addEmprestimo(livros_livres.get(0));

		} else {
			addEmprestimo(reserva.getLivro());
		}

	}

	@Override
	public void reservarLivro(String Codigolivro) throws Exception {

		if (listaReservaAtiva.size() >= LIMITE_RESERVA) {
			throw new Exception("LIMITE DE RESERVA EXECIDO");
		}
		if (this.isDevedor()) {
			throw new Exception("USUARIO ESTÁ EM DIVIDA COM A BIBLIOTECA");
		}
		if (this.listaReservaAtiva.stream().anyMatch(res -> res.getLivro().getCodigoLivro().equals(Codigolivro))) {
			throw new Exception("USUARIO JÁ POSSUI RESERVA DESSE LIVRO");
		}

		List<ILivro> livros_livres = getLivrosLivresAndCodigo(Codigolivro);

		if (livros_livres.size() > 0) {
			addReserva(livros_livres.get(0));
		} else {
			throw new Exception("NÃO HÁ EXEMPLARES DISPONÍVEIS PARA RESERVA!");
		}

	}

	@Override
	public void devolverLivro(String codigoLivro) {
		EmprestimoLivro emprestimo = obterEmprestimoAtivo(codigoLivro);
		emprestimo.setDataDevolucaoReal(LocalDate.now());
		listaEmprestimo.add(emprestimo);
		listaEmprestimoAtivo.remove(emprestimo);
		emprestimo.getLivro().devolverItem(this, emprestimo.getLivro(), emprestimo);
	}
	
	public void removerReservaAtiva(ILivro livro) {
		listaReservaAtiva.removeIf(reserva -> reserva.getLivro().equals(livro));
	}
	
	public void adicionarReservaHistorico(ReservaLivro reserva) {
		reserva.setIsAtivo(false);
		listaReservaHistorico.add(reserva);
	}

	/// METODOS ACESSORIOS--------------------------------

	private void addEmprestimo(ILivro livro) {
		EmprestimoLivro emprestimo = new EmprestimoLivro(this, livro, LocalDate.now(),
				LocalDate.now().plusDays(PRAZO_DEVOLUCAO));
		livro.emprestarItem(this, emprestimo);
		listaEmprestimoAtivo.add(emprestimo);
	}

	private void addReserva(ILivro exemplar) {
		ReservaLivro reserva = new ReservaLivro(this, exemplar);
		exemplar.reservarItem(this, reserva);
		this.listaReservaAtiva.add(reserva);
	}

	private ReservaLivro obterReserva(String codigoLivro) {

		List<ReservaLivro> reservas = listaReservaAtiva.stream()
				.filter(reserva -> reserva.getLivro().getCodigoLivro().equals(codigoLivro)).toList();
		if (reservas.size() > 0) {
			return reservas.get(0);
		}
		return null;
	}

	private EmprestimoLivro obterEmprestimoAtivo(String codigoLivro) {

		List<EmprestimoLivro> emprestimos = listaEmprestimoAtivo.stream()
				.filter(emprestimo -> emprestimo.getLivro().getCodigoLivro().equals(codigoLivro)).toList();
		if (emprestimos.size() > 0) {
			return emprestimos.get(0);
		}
		return null;
	}

	private List<ILivro> getLivrosLivresAndCodigo(String codigo) {

		return Biblioteca.getInstanciaBiblioteca().getListaLivros().stream().filter(
				livro -> livro.getStatus().equals(StatusEmprestimoLivro.Livre) && livro.getCodigoLivro().equals(codigo))
				.toList();

	}

	private boolean isDevedor() {
		return this.listaEmprestimoAtivo.stream()
				.anyMatch(emprestimo -> emprestimo.getDataDevolucaoPrevista().isBefore(LocalDate.now()));
	}


	

}