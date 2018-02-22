package principal;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
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
		ArrayList<Aluno> alunos = new ArrayList<Aluno>(this.listaDeAlunos.values());
		Collections.sort(alunos);
		String lista = "";
		for (Aluno aluno : alunos) {
			lista += aluno.toString() + ", ";
		}
		String saida = lista.substring(0, lista.length() - 2);
		return saida;

	}

	public String getInfoAluno(String matricula, String atributo) {
		switch (atributo.toLowerCase()) {
		case "nome":
			return this.listaDeAlunos.get(matricula).getNome();
		case "telefone":
			return this.listaDeAlunos.get(matricula).getTelefone();
		case "email":
			return this.listaDeAlunos.get(matricula).getEmail();
		default:

		}
	}

}
