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
		verificaCadastroAluno(nome, matricula, email);
		Aluno aluno = new Aluno(nome, matricula, codigoCurso, telefone, email);
		this.listaDeAlunos.put(matricula, aluno);
	}

	public String recuperaAluno(String matricula) {
		verificaMatricula("recuperaAluno", matricula);
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
		verificaMatricula("getInfoAluno", matricula);
		switch (atributo.toLowerCase()) {
		case "nome":
			return this.listaDeAlunos.get(matricula).getNome();
		case "telefone":
			return this.listaDeAlunos.get(matricula).getTelefone();
		case "email":
			return this.listaDeAlunos.get(matricula).getEmail();
		default:
			return "";
		}
	}

	private void verificaCadastroAluno(String nome, String matricula, String email) {
		String erro = "";
		if (!verificaEmail(email)) {
			erro = "Email invalido";
		} else if (this.listaDeAlunos.containsKey(matricula)) {
			erro = "Aluno de mesma matricula ja cadastrado";
		} else if (nome.trim().equals("") || nome == null) {
			erro = "Nome nao pode ser vazio ou nulo";

		}
		if (!erro.equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de aluno: " + erro);
		}
	}

	private boolean verificaEmail(String email) {
		int posicao = email.indexOf("@");
		if (posicao == -1) {
			return false;
		} else if (email.length() < 3) {
			return false;
		} else if (posicao == 0 || posicao == email.length() - 1) {
			return false;
		}
		return true;

	}

	public void verificaMatricula(String metodo, String matricula) {
		if (metodo.equals("getInfoAluno") && !this.listaDeAlunos.containsKey(matricula)) {
			throw new IllegalArgumentException("Erro na obtencao de informacao de aluno: Aluno nao encontrado");
		} else if (metodo.equals("recuperaAluno") && !this.listaDeAlunos.containsKey(matricula)) {
			throw new IllegalArgumentException("Erro na busca por aluno: Aluno nao encontrado");
		}
	}

	public void tornarTutor(String matricula, String disciplina, int proficiencia) {
		Aluno aluno = this.listaDeAlunos.get(matricula);
		Tutor tutor = new Tutor(aluno, disciplina, proficiencia);
		this.listaDeAlunos.replace(matricula, tutor);
	}

	public String recuperaTutor(String matricula) {
		return recuperaAluno(matricula);
	}

	public String listarTutores() {
		String saida = "";
		for (String matricula : listaDeAlunos.keySet()) {
			if (verificaTutor(matricula)) {
				saida += listaDeAlunos.get(matricula).toString() + ", ";
			}
		}
		return saida.substring(0, saida.length() - 2);

	}

	public boolean verificaTutor(String matricula) {
		if (listaDeAlunos.get(matricula).getTipo().equals("tutor")) {
			return true;
		}
		return false;
	}

	public Tutor recuperaTutorPorEmail(String email){
		for (Aluno aluno : listaDeAlunos.values()) {
			if (aluno.getEmail().equalsIgnoreCase(email)) {
				if (verificaTutor(aluno.getMatricula())) {
					Tutor tutor = (Tutor) aluno;
					return tutor;
				}
			}
		}
		return null;
	}
	
	public void cadastrarHorario(String email, String horario, String dia) {
		recuperaTutorPorEmail(email).cadastrarHorario(horario, dia);
	}

	public void cadastrarLocalDeAtendimento(String email, String local) {
		recuperaTutorPorEmail(email).cadastrarLocalDeAtendimento(local);
		
	}
}
