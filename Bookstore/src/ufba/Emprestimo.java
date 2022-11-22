package ufba;

import java.util.Date;

public class Emprestimo {
IUsuario usuario; 
IItemBiblioteca item; 
Date data; 

public IUsuario getUsuario() {
	return usuario; 
}
public IItemBiblioteca getItem() {
	return item; 
}
public Date getData() {
	return data; 
}
}
