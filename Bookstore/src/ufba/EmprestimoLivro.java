package ufba;

import java.time.LocalDate;

public class EmprestimoLivro {
	private static int geradorCodigoEmprestimo = 11;
	private int codigoEmprestimo;
	IUsuario usuario; 
	ILivro livro; 
	private boolean isAtivo; 
	LocalDate dataEmprestimo; 
	LocalDate dataDevolucaoPrevista;
	LocalDate dataDevolucaoReal;
	
public EmprestimoLivro(IUsuario usuario, ILivro livro, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista) {
		super();
		this.usuario = usuario;
		this.livro = livro;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucaoPrevista = dataDevolucaoPrevista;
		this.isAtivo = true; 
		this.codigoEmprestimo = ++geradorCodigoEmprestimo;
	}
public IUsuario getUsuario() {
	return usuario; 
}
public ILivro getLivro() {
	return livro; 
}
public LocalDate getDataEmprestimo() {
	return dataEmprestimo; 
}
public LocalDate getDataDevolucaoPrevista() {
	return dataDevolucaoPrevista;
}
public LocalDate getDataDevolucaoReal() {
	return dataDevolucaoReal; 
}
public boolean getIsAtivo() {
	return isAtivo; 
}

public int getCodigoEmprestimo() {
	return this.codigoEmprestimo;
}

@Override
public boolean equals(Object obj) {

	if (this == obj) {
		return true;
	}

	if (obj == null || obj.getClass() != this.getClass()) {
		return false;
	}

	EmprestimoLivro emprestimo = (EmprestimoLivro) obj;

	return ((emprestimo.getCodigoEmprestimo() == this.codigoEmprestimo));
}
}
