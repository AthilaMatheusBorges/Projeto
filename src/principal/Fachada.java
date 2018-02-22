package principal;

import easyaccept.EasyAccept;

public class Fachada {
	
	Sistema sistema = new Sistema();
	
	public static void main(String[] args) {
		args = new String[] {"principal.Fachada","acceptance_test/us1_test.txt","acceptance_test/us2_test.txt"};
		EasyAccept.main(args);
	}
	
    public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
    	sistema.cadastrarAluno(nome, matricula, codigoCurso, telefone, email);
    }
    public String recuperaAluno(String matricula) {
    	return sistema.recuperaAluno(matricula);
    }
    public String listarAlunos() {
    	return sistema.listarAlunos();
    }
    public String getInfoAluno(String matricula, String atributo) {
    	return sistema.getInfoAluno(matricula, atributo);
    }
}
