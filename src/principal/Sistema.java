package principal;

import java.util.HashMap;
import java.util.Map;

public class Sistema {

	Map<String, Aluno> listaDeAlunos;

	public Sistema() {
		this.listaDeAlunos = new HashMap<>();
	}

	
	
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		Aluno aluno = new Aluno(nome, matricula, codigoCurso, telefone, email);
		this.listaDeAlunos.put(matricula, aluno);
	}


	public String recuperaAluno(String matricula) {
		return this.listaDeAlunos.get(matricula).toString();
	}

	public String listarAlunos() {
		String lista = "";
		for(Aluno aluno : this.listaDeAlunos.values()) {
			lista += aluno.toString() + ", ";
		}
		return lista.trim();
	}

	public String getInfoAluno(String matricula, String atributo) {
		switch(atributo.toLowerCase()) {
		case "nome":
			return this.listaDeAlunos.get(matricula).getNome();
		case "telefone":
			return this.listaDeAlunos.get(matricula).getTelefone();
		case "email":
			return this.listaDeAlunos.get(matricula).getEmail();
		default:
			return null;
		}
	}

}
