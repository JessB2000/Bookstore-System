package entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.lang.model.type.NullType;

import interfaces.ILivro;
import interfaces.IUsuario;
import interfaces.Observer;
import outros.Biblioteca;
import outros.EmprestimoLivro;
import outros.ReservaLivro;
import outros.StatusEmprestimoLivro;

public class Professor implements IUsuario, Observer {

	private String nome;
	private String codigo;
	private int LIMITE_RESERVA = 3;
	private int PRAZO_DEVOLUCAO = 7;
	private List<ReservaLivro> listaReservaHistorico;
	private List<ReservaLivro> listaReservaAtiva;
	private List<EmprestimoLivro> listaEmprestimo;
	private List<EmprestimoLivro> listaEmprestimoAtivo;
	private NullType SEM_RESERVA = null;
    private int qt_notificacoes = 0; 
	public Professor(String nome, String codigo) {
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

		if (this.isDevedor()) {
			throw new Exception("USUARIO ESTÁ EM DIVIDA COM A BIBLIOTECA");
		}
		if (this.listaEmprestimoAtivo.stream().anyMatch(emp -> emp.getLivro().getCodigoLivro().equals(codigoLivro))) {
			throw new Exception("USUARIO JÁ POSSUI EMPRÉSTIMO DESSE LIVRO");
		}

		ReservaLivro reserva = obterReserva(codigoLivro);

		if (reserva == SEM_RESERVA) {

			List<ILivro> livros_livres = getLivrosLivresAndCodigo(codigoLivro);
			if (livros_livres.size() > 0) {
				addEmprestimo(livros_livres.get(0));
				return;
			}

			List<ILivro> livros_reservados = getLivrosReservadosAndCogido(codigoLivro);
			if (livros_reservados.size() > 0) {
				addEmprestimo(livros_reservados.get(0));
			}

			throw new Exception("NÃO HÁ LIVROS DISPONÍVEIS");

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
			return;
		}

		throw new Exception("NÃO HÁ EXEMPLARES DISPONÍVEIS!");

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

		return Biblioteca.getInstanciaBiblioteca().getListaLivros().stream()
				.filter(l -> l.getStatus().equals(StatusEmprestimoLivro.Livre))
				.filter(livro -> livro.getCodigoLivro().equals(codigo)).toList();

	}

	private List<ILivro> getLivrosReservadosAndCogido(String codigo) {

		return Biblioteca.getInstanciaBiblioteca().getListaLivros().stream()
				.filter(l -> l.getStatus().equals(StatusEmprestimoLivro.Reservado))
				.filter(livro -> livro.getCodigoLivro().equals(codigo)).toList();

	}

	private boolean isDevedor() {
		return this.listaEmprestimoAtivo.stream()
				.anyMatch(emprestimo -> emprestimo.getDataDevolucaoPrevista().isBefore(LocalDate.now()));
	}

	@Override
	public void update(ILivro livro) {
		System.out.println("HÁ MAIS DE DOIS EXEMPLARES RESERVADOS DO LIVRO: || NOME: " + livro.getTitulo()
				+ "|| CÓDIGO: " + livro.getCodigoLivro());
		this.qt_notificacoes++;
	}
	public String toString() {
		String listaEmprestimosCorrentes = "\nEMPRÉSTIMOS CORRENTES: \n----------------------------------\n";
		String listaEmprestimosHistorico = "\nHISTÓRICO DE EMPRÉSTIMOS: \n----------------------------------\n";
		String listaReservas = "\nRESERVAS: \n----------------------------------\n";
		int i = 0;
		for (i = 0; i < this.listaEmprestimoAtivo.size(); i++) {
			listaEmprestimosCorrentes = listaEmprestimosCorrentes
					.concat("\n || TÍTULO DO LIVRO: " + this.listaEmprestimoAtivo.get(i).getLivro().getTitulo()
							+ " || DATA DO EMPRÉSTIMO: " + this.listaEmprestimoAtivo.get(i).getDataEmprestimo()
							+ " || STATUS DO EMPRÉSTIMO: Em curso" + " || DATA DA DEVOLUÇÃO PREVISTA: "
							+ this.listaEmprestimoAtivo.get(i).getDataDevolucaoPrevista().toString());
		}
		
		for (i = 0; i < this.listaEmprestimo.size(); i++) {
			listaEmprestimosHistorico = listaEmprestimosHistorico
					.concat("\n || TÍTULO DO LIVRO: " + this.listaEmprestimo.get(i).getLivro().getTitulo()
							+ " || DATA DO EMPRÉSTIMO: " + this.listaEmprestimo.get(i).getDataEmprestimo()
							+ " || STATUS DO EMPRÉSTIMO: Finalizado" + " || DATA DA DEVOLUÇÃO: "
							+ this.listaEmprestimo.get(i).getDataDevolucaoReal().toString());
		}
		for (i = 0; i < this.listaReservaAtiva.size(); i++) {
			listaReservas = listaReservas
					.concat("\n || TÍTULO DO LIVRO: " + this.listaReservaAtiva.get(i).getLivro().getTitulo()
							+ " || DATA DA SOLICITAÇÃO DA RESERVA: " + this.listaReservaAtiva.get(i).getData());
		}
		for (i = 0; i < this.listaReservaHistorico.size(); i++) {
			listaReservas = listaReservas
					.concat("\n || TÍTULO DO LIVRO: " + this.listaReservaHistorico.get(i).getLivro().getTitulo()
							+ " || DATA DA SOLICITAÇÃO DA RESERVA: " + this.listaReservaHistorico.get(i).getData());
		}
		return listaEmprestimosCorrentes + listaEmprestimosHistorico + listaReservas; 
		
	}

	@Override
	public int getQtNotificacoes() {
		return this.qt_notificacoes; 
	}

}
