package ufba;

import java.util.Date;

public class Reserva {
	private IUsuario usuario; 
	private String codigoItem; 
	private String codigoReserva;
	private Date data; 
	private boolean isAtivo; 

	public IUsuario getUsuario() {
		return usuario; 
	}
	
	public Date getData() {
		return data; 
	}
	public boolean getIsAtivo() {
		return isAtivo; 
	}
	
	public String getCodigoItem() {
		return this.codigoItem;
	}
	
	public void setIsAtivo(boolean value) {
		this.isAtivo = value;
	}
	
	public String getCodigoReserva() {
		return this.codigoReserva;
	}
}
