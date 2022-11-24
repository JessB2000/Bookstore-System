package ufba;

import java.util.Date;

public class Reserva {
	IUsuario usuario; 
	IItemBiblioteca item; 
	Date data; 
	private boolean isAtivo; 

	public IUsuario getUsuario() {
		return usuario; 
	}
	public IItemBiblioteca getItem() {
		return item; 
	}
	public Date getData() {
		return data; 
	}
	public boolean getIsAtivo() {
		return isAtivo; 
	}
}
