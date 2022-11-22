package ufba;

public class Livro implements ILivro, Subject{
public String titulo; 
public String autor;
public String edicao; 
public String anopublicacao;
//public Enum status; 
IUsuario locatario;
	@Override
	public String getTitulo() {
		return titulo;
	}

	@Override
	public String getAutor() {
		return autor;
	}

	@Override
	public String getEdicao() {
		return edicao;
	}

	@Override
	public String getAnoPublicacao() {
		return anopublicacao;
	}

	@Override
	public void addObserver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeObserver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub
		
	}

}
