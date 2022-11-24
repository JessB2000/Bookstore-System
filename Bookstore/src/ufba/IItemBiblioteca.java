package ufba;

public interface IItemBiblioteca {
public String getHistoricoEmprestimo();
public String getLocatario(IUsuario usuario);
public void devolverItem();
public void reservarItem(IUsuario usuario);
public void emprestarItem(IUsuario usuario);
}
