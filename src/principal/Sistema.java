package principal;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Sistema {

	private Map<String, Aluno> listaDeAlunos;
	private Auxiliar auxiliarController;

	public Sistema() {
		this.listaDeAlunos = new HashMap<>();
		this.auxiliarController = new Auxiliar();
		
	}

	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		auxiliarController.verificaCadastroAluno(this.listaDeAlunos, nome, matricula, email);
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

//	private void verificaCadastroAluno(String nome, String matricula, String email) {
//		String erro = "";
//		if (!verificaEmail(email)) {
//			erro = "Email invalido";
//		} else if (this.listaDeAlunos.containsKey(matricula)) {
//			erro = "Aluno de mesma matricula ja cadastrado";
//		} else if (nome.trim().equals("") || nome == null) {
//			erro = "Nome nao pode ser vazio ou nulo";
//
//		}
//		if (!erro.equals("")) {
//			throw new IllegalArgumentException("Erro no cadastro de aluno: " + erro);
//		}
//	}

//	private boolean verificaEmail(String email) {
//		int posicao = email.indexOf("@");
//		if (posicao == -1) {
//			return false;
//		} else if (email.length() < 3) {
//			return false;
//		} else if (posicao == 0 || posicao == email.length() - 1) {
//			return false;
//		}
//		return true;
//
//	}

//	public void verificaMatricula(String metodo, String matricula) {
//		if (metodo.equals("getInfoAluno") && !this.listaDeAlunos.containsKey(matricula)) {
//			throw new IllegalArgumentException("Erro na obtencao de informacao de aluno: Aluno nao encontrado");
//		} else if (metodo.equals("recuperaAluno") && !this.listaDeAlunos.containsKey(matricula)) {
//			throw new IllegalArgumentException("Erro na busca por aluno: Aluno nao encontrado");
//		}
//	}

	public void tornarTutor(String matricula, String disciplina, int proficiencia) {
		if(auxiliarController.verificaDadosParaTornarTutor(listaDeAlunos, matricula, disciplina, proficiencia)) {
			Aluno aluno = this.listaDeAlunos.get(matricula);
			Tutor tutor = new Tutor(aluno, disciplina, proficiencia);
			this.listaDeAlunos.replace(matricula, tutor);
		}
		else {
			String email = listaDeAlunos.get(matricula).getEmail();
			auxiliarController.recuperaTutorPorEmail(listaDeAlunos, email).adicionaDisciplina(disciplina);
		}
	}

//	private boolean verificaDadosParaTornarTutor(String matricula, String disciplina, int proficiencia) {
//		if(!listaDeAlunos.containsKey(matricula)) {
//			throw new IllegalArgumentException("Erro na definicao de papel: Tutor nao encontrado");
//		}
//		else if(proficiencia < 1 || proficiencia > 5) {
//			throw new IllegalArgumentException("Erro na definicao de papel: Proficiencia invalida");
//		}
//		else if(listaDeAlunos.get(matricula).getTipo().equals("tutor")) {
//			String email = listaDeAlunos.get(matricula).getEmail();
//			if(auxiliarController.recuperaTutorPorEmail(listaDeAlunos, email).confereSeJaEtutorDaDisciplina(disciplina)) {
//				throw new IllegalArgumentException("Erro na definicao de papel: Ja eh tutor dessa disciplina");
//			}
//			else {
//				return false;
//			}
//		}
//		return true;
//	}

	public String recuperaTutor(String matricula) {
		if(!this.listaDeAlunos.containsKey(matricula)) {
			throw new IllegalArgumentException("Erro na busca por tutor: Tutor nao encontrado");
		}
		return recuperaAluno(matricula);
	}

	public String listarTutores() {
		String saida = "";
		for (String matricula : listaDeAlunos.keySet()) {
			if (auxiliarController.verificaTutor(this.listaDeAlunos, matricula)) {
				saida += listaDeAlunos.get(matricula).toString() + ", ";
			}
		}
		return saida.substring(0, saida.length() - 2);

	}

//	public boolean verificaTutor(String matricula) {
//		if (listaDeAlunos.get(matricula).getTipo().equals("tutor")) {
//			return true;
//		}
//		return false;
//	}

//	public Tutor recuperaTutorPorEmail(String email){
//		for (Aluno aluno : listaDeAlunos.values()) {
//			if (aluno.getEmail().equalsIgnoreCase(email)) {
//				if (verificaTutor(aluno.getMatricula())) {
//					Tutor tutor = (Tutor) aluno;
//					return tutor;
//				}
//			}
//		}
//		return null;
//	}
	
	public void cadastrarHorario(String email, String horario, String dia) {
		auxiliarController.confereCadastrarHorario(this.listaDeAlunos, email, horario, dia);
		auxiliarController.recuperaTutorPorEmail(this.listaDeAlunos, email).cadastrarHorario(horario, dia);
	}

	public void cadastrarLocalDeAtendimento(String email, String local) {
		auxiliarController.confereCadastrarLocalDeAtendimento(this.listaDeAlunos, email, local);
		auxiliarController.recuperaTutorPorEmail(this.listaDeAlunos, email).cadastrarLocalDeAtendimento(local);
		
	}

//	public boolean consultaHorario(String email, String horario, String dia) {
//		auxiliarController.confereConsultaHorario
//		
//	}
	
	
}
