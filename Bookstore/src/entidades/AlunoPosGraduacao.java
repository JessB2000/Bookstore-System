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
	private int LIMITE_EMPRESTIMOPG = 4;
	private int LIMITE_RESERVAPG = 3; 
	private int PRAZO_DEVOLUCAOPG = 4;
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
	@Override
	public void pegarLivroEmprestado(ILivro livro) throws Exception {
		
		if (listaEmprestimoAtivo.size() >= LIMITE_EMPRESTIMOPG) {
			throw new Exception("LIMITE DE EMPRESTIMO EXECIDO");
		}
		if (this.isDevedor()) {
			throw new Exception("USUARIO ESTÁ EM DIVIDA COM A BIBLIOTECA");
		}
		
		if (this.listaEmprestimoAtivo.stream().anyMatch(emp -> emp.getLivro().getCodigoLivro().equals(livro.getCodigoLivro())))
		{
			throw new Exception ("USUARIO JÁ POSSUI EMPRÉSTIMO DESSE LIVRO");
		}
		Biblioteca biblioteca = Biblioteca.getInstanciaBiblioteca();
		
		List<ILivro> livros_livres_and_reservados = biblioteca.getListaLivros().stream()
				.filter(liv -> (liv.getCodigoLivro().equals(livro.getCodigoLivro()))).toList();
		
		Predicate<ILivro> Status_Livre = (l) -> l.getStatus().equals(StatusEmprestimoLivro.Livre);
	
		ReservaLivro reserva = obterReserva(livro);
	
		if (reserva == SEM_RESERVA) { 
			ILivro livroEmprestar = livros_livres_and_reservados.stream().filter(Status_Livre).toList().get(0);
			EmprestimoLivro emprestimo  = new EmprestimoLivro(this, livro, LocalDate.now(), LocalDate.now().plusDays(PRAZO_DEVOLUCAOPG));
			livroEmprestar.emprestarItem(this, emprestimo);
			listaEmprestimoAtivo.add(emprestimo);
		} else {
			EmprestimoLivro emprestimo  = new EmprestimoLivro(this, livro, LocalDate.now(), LocalDate.now().plusDays(PRAZO_DEVOLUCAOPG));
			reserva.getLivro().emprestarItem(this, emprestimo);
			listaEmprestimoAtivo.add(emprestimo);		}
		
	}
	
	public void removerReservaAtiva(ILivro livro) {
		listaReservaAtiva.removeIf(reserva -> reserva.getLivro().equals(livro));
		}
	
	public void adicionarReservaHistorico(ReservaLivro reserva) {
		reserva.setIsAtivo(false);
		listaReservaHistorico.add(reserva);
	}

	public ReservaLivro obterReserva (ILivro livro) {
		
		List<ReservaLivro> reservas = listaReservaAtiva.stream().filter(reserva -> reserva.getLivro().equals(livro)).toList();
			if(reservas.size()>0) {
				return reservas.get(0);
			}
			return null;	
		} 
	
    public EmprestimoLivro obterEmprestimoAtivo(ILivro livro) {
		
		List<EmprestimoLivro> emprestimos = listaEmprestimoAtivo.stream().filter(emprestimo -> emprestimo.getLivro().equals(livro)).toList();
		if(emprestimos.size()>0) {
			return emprestimos.get(0);
		}
		return null;
	}

	@Override
	public void reservarLivro(ILivro livro) throws Exception {
		if (listaReservaAtiva.size() >= LIMITE_RESERVAPG) {
			throw new Exception("LIMITE DE RESERVA EXECIDO");
		}
		if (this.isDevedor()) {
			throw new Exception("USUARIO ESTÁ EM DIVIDA COM A BIBLIOTECA");
		}
		
		if (this.listaReservaAtiva.stream().anyMatch(res -> res.getLivro().getCodigoLivro().equals(livro.getCodigoLivro())))
		{
			throw new Exception ("USUARIO JÁ POSSUI RESERVA DESSE LIVRO");
		}
		List<ILivro> livros_livres = Biblioteca.getInstanciaBiblioteca().
				getListaLivros().stream().filter(liv-> liv.getStatus().
				equals(StatusEmprestimoLivro.Livre)&& liv.getCodigoLivro().equals(livro.getCodigoLivro())).toList();
		if(livros_livres.size()>0) {
			ILivro exemplar = livros_livres.get(0); 
			ReservaLivro reserva = new ReservaLivro(this, exemplar);
			exemplar.reservarItem(this,reserva);
			this.listaReservaAtiva.add(reserva); 
		}else {
			throw new Exception("NÃO HÁ EXEMPLARES DISPONÍVEIS PARA RESERVA!");
		}
		
	}

	@Override
	public void devolverLivro(ILivro livro) {
		EmprestimoLivro emprestimo = obterEmprestimoAtivo(livro);
		emprestimo.setDataDevolucaoReal(LocalDate.now());
		listaEmprestimo.add(emprestimo);
		listaEmprestimoAtivo.remove(emprestimo);
		livro.devolverItem(this,livro, emprestimo);
		
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
	private boolean isDevedor() {
		return this.listaEmprestimoAtivo.stream()
				.anyMatch(emprestimo -> emprestimo.getDataDevolucaoPrevista().isBefore(LocalDate.now()));
	}
	@Override
	public String getCodigo() {
		return codigo;
	}
	@Override
	public String getNome() {
		return nome;
	}
	
}
