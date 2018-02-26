package principal;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Sistema {

	private Map<String, Aluno> listaDeAlunos;
	private ExcecoesController auxiliarController;

	public Sistema() {
		this.listaDeAlunos = new HashMap<>();
		this.auxiliarController = new ExcecoesController();
		
	}

	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		auxiliarController.verificaCadastroAluno(this.listaDeAlunos.containsKey(matricula), nome, matricula, email);
		Aluno aluno = new Aluno(nome, matricula, codigoCurso, telefone, email);
		this.listaDeAlunos.put(matricula, aluno);
	}

	public String recuperaAluno(String matricula) {
		auxiliarController.verificaMatricula(listaDeAlunos, "recuperaAluno", matricula);
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
		auxiliarController.verificaMatricula(this.listaDeAlunos, "getInfoAluno", matricula);
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


	public void tornarTutor(String matricula, String disciplina, int proficiencia) {
		if(!listaDeAlunos.containsKey(matricula)) {
			throw new IllegalArgumentException("Erro na definicao de papel: Tutor nao encontrado");
		}
		String email = this.listaDeAlunos.get(matricula).getEmail();
		if(auxiliarController.verificaDadosParaTornarTutor(listaDeAlunos.containsKey(matricula), listaDeAlunos.get(matricula).getTipo().equals("tutor"), recuperaTutorPorEmail(email), disciplina, proficiencia)) {
			Aluno aluno = this.listaDeAlunos.get(matricula);
			Tutor tutor = new Tutor(aluno, disciplina, proficiencia);
			this.listaDeAlunos.replace(matricula, tutor);
		}
		else {
			recuperaTutorPorEmail(email).adicionaDisciplina(disciplina);
		}
	}

	public String recuperaTutor(String matricula) {
		if(!this.listaDeAlunos.containsKey(matricula)) {
			throw new IllegalArgumentException("Erro na busca por tutor: Tutor nao encontrado");
		}
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
	
	/**
	 * Verifica se o aluno é tutor ou nao
	 * @param matricula
	 *            matricula do aluno em String
	 * @return um booleano de sim para caso ja seja tuto e false caso nao
	 */
	
	public boolean verificaTutor(String matricula) {
		if (listaDeAlunos.get(matricula).getTipo().equals("tutor")) {
			return true;
		}
		return false;
	}
	
	/**
	 * recupera um tutor pelo email
	 * @param email
	 *            email do aluno em String
	 * @return o Tutor procurado
	 */
	
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
	
//	public Tutor recuperaTutorPelaMatricula(String matricula){
//		if(listaDeAlunos.containsKey(matricula)) {
//			Tutor tutor = (Tutor)listaDeAlunos.get(matricula);
//			return tutor;
//		}
//		return null;
//	}
	
	
	public void cadastrarHorario(String email, String horario, String dia) {
		
		auxiliarController.confereCadastrarHorario(recuperaTutorPorEmail(email), email, horario, dia);
		recuperaTutorPorEmail(email).cadastrarHorario(horario, dia);
	}

	public void cadastrarLocalDeAtendimento(String email, String local) {
		auxiliarController.confereCadastrarLocalDeAtendimento(recuperaTutorPorEmail(email), email, local);
		recuperaTutorPorEmail(email).cadastrarLocalDeAtendimento(local);
		
	}

	public boolean consultaHorario(String email, String horario, String dia) {
		if(recuperaTutorPorEmail(email) == null)
			return false;	
		return recuperaTutorPorEmail(email).consultaHorario(horario, dia);	
	}
	
	public boolean consultaLocal(String email, String local) {
		if(recuperaTutorPorEmail(email) == null)
			return false;	
		return recuperaTutorPorEmail(email).consultaLocal(local);	
	}
	
	
}
