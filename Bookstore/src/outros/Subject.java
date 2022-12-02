package outros;

import interfaces.ILivro;
import interfaces.Observer;

public interface Subject {
	public void addObserver(Observer observador);

	public void removeObserver();

	public void notifyObserver(ILivro livro);
}
