package ufba;

public class FuncionamentoSistema {

	public static void main(String[] args) {
		Biblioteca biblioteca = Biblioteca.getInstanciaBiblioteca();
        IUsuario aluno = new AlunoPosGraduacao("Joao Pedro", "452"); 
        biblioteca.listaUsuarios.add(aluno); 
        ILivro livro = new Livro("888", "O dia em que a terra parou", "Albert Einstein", "2", "2020","320"); 
        biblioteca.listaLivros.add(livro); 
        biblioteca.PegarLivroEmprestado("452", "888");   
        
	}

}
