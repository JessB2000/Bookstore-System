package ufba;

import java.util.Date;

public class Emprestimo {
IUsuario usuario; 
IItemBiblioteca item; 
private boolean isAtivo; 
Date dataEmprestimo; 
Date dataDevolucaoPrevista;
Date dataDevolucaoReal;

public IUsuario getUsuario() {
	return usuario; 
}
public IItemBiblioteca getItem() {
	return item; 
}
public Date getDataEmprestimo() {
	return dataEmprestimo; 
}
public Date getDataDevolucaoPrevista() {
	return dataDevolucaoPrevista;
}
public Date getDataDevolucaoReal() {
	return dataDevolucaoReal; 
}
public boolean getIsAtivo() {
	return isAtivo; 
}
}
