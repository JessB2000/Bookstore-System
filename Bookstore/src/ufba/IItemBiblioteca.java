package ufba;

public interface IItemBiblioteca {
public int getCodigoItem(); 
public String getHistoricoEmprestimo();
public String getLocatario(IUsuario usuario);
public void devolverItem();
public void reservarItem();
public void emprestarItem();
}
