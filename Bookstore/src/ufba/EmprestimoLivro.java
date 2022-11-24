package ufba;

import java.time.LocalDate;

public class EmprestimoLivro {
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
}
