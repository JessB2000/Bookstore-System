package ufba;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import javax.lang.model.type.NullType;

public class FuncionamentoSistema {

	public static void main(String[] args) {
		Biblioteca biblioteca = Biblioteca.getInstanciaBiblioteca();
        IUsuario aluno = new AlunoGraduacao("Joao Pedro", "452"); 
        biblioteca.listaUsuarios.add(aluno); 
        ILivro livro = new Livro("888", "O dia em que a terra parou", "Albert Einstein", "2", "2020","320"); 
        biblioteca.listaLivros.add(livro); 
        biblioteca.PegarLivroEmprestado("452", "888");  
        List<String> list = new ArrayList<>();
        NullType SEM_RESERVA = null;
    
        
      
	}

}
