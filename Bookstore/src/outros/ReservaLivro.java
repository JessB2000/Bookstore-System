package outros;

import java.time.LocalDate;

import interfaces.ILivro;
import interfaces.IUsuario;

public class ReservaLivro {
	private IUsuario usuario; 
	private static int geradorReserva = 17;
	private int codigoReserva;
	private ILivro livro;
	private LocalDate data; 
	private boolean isAtivo; 
	public ReservaLivro(IUsuario usuario,ILivro livro) {
			this.usuario = usuario;
			this.livro = livro;
			this.data = LocalDate.now();
			this.codigoReserva = ++geradorReserva;
			this.isAtivo=true;
	}
	

	public IUsuario getUsuario() {
		return usuario; 
	}
	
	public LocalDate getData() {
		return data; 
	}
	public boolean getIsAtivo() {
		return isAtivo; 
	}
	
	public int getCodigoReserva() {
		return this.codigoReserva;
	}
	
	public void setIsAtivo(boolean value) {
		this.isAtivo = value;
	}
	
	public ILivro getLivro() {
		return livro;
	}
}
