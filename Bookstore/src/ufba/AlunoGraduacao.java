package ufba;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.lang.model.type.NullType;

public class AlunoGraduacao implements IUsuario {
	private String nome;
	private String codigo;
	private int LIMITE_EMPRESTIMO = 3;
	private int PRAZO_DEVOLUCAO = 3;
	private List<ReservaLivro> listaReservaHistorico;
	private List<ReservaLivro> listaReservaAtiva;
	private List<EmprestimoLivro> listaEmprestimo;
	private List<EmprestimoLivro> listaEmprestimoAtivo;
	private NullType SEM_RESERVA = null;
	
	public AlunoGraduacao(String nome, String codigo) {
		this.nome = nome;
		this.codigo = codigo;
		this.listaReservaHistorico = new ArrayList<>();
		this.listaReservaAtiva = new ArrayList<>();
		this.listaEmprestimo = new ArrayList<>();
		this.listaEmprestimoAtivo = new ArrayList<>();

	}

	@Override
	public void pegarLivroEmprestado(ILivro livro) throws Exception {
		
		if (listaEmprestimoAtivo.size() >= LIMITE_EMPRESTIMO) {
			throw new Exception("LIMITE DE EMPRESTIMO EXECIDO");
		}
		if (this.isDevedor()) {
			throw new Exception("USUARIO ESTÁ EM DIVIDA COM A BIBLIOTECA");
		}

		Biblioteca biblioteca = Biblioteca.getInstanciaBiblioteca();
		
		List<ILivro> livros_livres_and_reservados = biblioteca.getListaLivros().stream()
				.filter(liv -> (liv.getCodigoLivro().equals(livro.getCodigoLivro()))).toList();
		
		Predicate<ILivro> Status_Livre = (l) -> l.getStatus().equals(StatusEmprestimoLivro.Livre);
	
		ReservaLivro reserva = obterReserva(livro);
	
		if (reserva == SEM_RESERVA) { 
			ILivro livroEmprestar = livros_livres_and_reservados.stream().filter(Status_Livre).toList().get(0);
			livroEmprestar.emprestarItem(this);
			listaEmprestimoAtivo.add(new EmprestimoLivro(this, livro, LocalDate.now(), LocalDate.now().plusDays(PRAZO_DEVOLUCAO)));
		} else {
			reserva.getLivro().emprestarItem(this);
			listaEmprestimoAtivo.add(new EmprestimoLivro(this, livro, LocalDate.now(), LocalDate.now().plusDays(PRAZO_DEVOLUCAO)));
		}
	}

	public void removerReservaAtiva(ILivro livro) {
		listaReservaAtiva.removeIf(reserva -> reserva.getLivro().equals(livro));
	}
	
	public void adicionarReservaHistorico(ReservaLivro reserva) {
		listaReservaAtiva.add(reserva);
	}
	
	
	public ReservaLivro obterReserva (ILivro livro) {
		
	List<ReservaLivro> reservas = listaReservaAtiva.stream().filter(reserva -> reserva.getLivro().equals(livro)).toList();
		if(reservas.size()>0) {
			return reservas.get(0);
		}
		return null;	
	}
	
	public EmprestimoLivro obterEmprestimoAtivo(ILivro livro) {
		
		List<EmprestimoLivro> emprestimos = listaEmprestimoAtivo.stream().filter(emprestimo -> emprestimo.livro.equals(livro)).toList();
		if(emprestimos.size()>0) {
			return emprestimos.get(0);
		}
		return null;
	}
	
	@Override
	public void reservarLivro(ILivro livro) throws Exception {
		List<ILivro> livros_livres = Biblioteca.getInstanciaBiblioteca().
				listaLivros.stream().filter(liv-> liv.getStatus().
				equals(StatusEmprestimoLivro.Livre)&& liv.getCodigoLivro().equals(livro.getCodigoLivro())).toList();
		
		if(livros_livres.size()>0) {
			livros_livres.get(0).reservarItem(this);
		}else {
			throw new Exception("NÃO HÁ EXEMPLARES DISPONÍVEIS PARA RESERVA!");
		}
	
	}

	@Override
	public void devolverLivro(ILivro livro) {
		livro.devolverItem();
		EmprestimoLivro emprestimo = obterEmprestimoAtivo(livro);
		emprestimo.dataDevolucaoReal = LocalDate.now();
		listaEmprestimo.add(emprestimo);
		listaEmprestimoAtivo.remove(emprestimo);
	}

	@Override
	public List<ReservaLivro> listarReservas() {
		
		List<ReservaLivro> list = new ArrayList<>();
		list.addAll(listaReservaHistorico);
		list.addAll(listaReservaAtiva);
		return list;
	}

	@Override
	public List<EmprestimoLivro> listarEmprestimo() {
		List<EmprestimoLivro> list = new ArrayList<>();
		list.addAll(listaEmprestimo);
		list.addAll(listaEmprestimoAtivo);
		return list;
	}

	@Override
	public String getCodigo() {
		return codigo;
	}

	private boolean isDevedor() {
		return this.listaEmprestimoAtivo.stream()
				.anyMatch(emprestimo -> emprestimo.dataDevolucaoPrevista.isBefore(LocalDate.now()));
	}

	@Override
	public String getNome() {
		return nome;
	}

}
