package principal;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author athilambb
 *
 */
public class Sistema {

	private Map<String, Aluno> listaDeAlunos;
	private ExcecoesController auxiliarController;

	/**
	 * Constroi um novo sistema.
	 */
	public Sistema() {
		this.listaDeAlunos = new HashMap<>();
		this.auxiliarController = new ExcecoesController();

	}

	/**
	 * Cadastra um novo aluno no sistema.
	 * 
	 * @param nome,
	 *            nome do aluno
	 * @param matricula,
	 *            matricula  do aluno.
	 * @param codigoCurso,
	 *            codigo do curso do aluno.
	 * @param telefone,
	 *            telefone do aluno.
	 * @param email,
	 *            email do aluno.
	 */
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		auxiliarController.verificaCadastroAluno(this.listaDeAlunos.containsKey(matricula), nome, matricula, email);
		Aluno aluno = new Aluno(nome, matricula, codigoCurso, telefone, email);
		this.listaDeAlunos.put(matricula, aluno);
	}

	/**
	 * Retorna a representacao textual de um aluno, a partir de uma matricula.
	 * 
	 * @param matricula,
	 *            matricula do aluno.
	 * @return
	 */
	public String recuperaAluno(String matricula) {
		auxiliarController.verificaMatricula(listaDeAlunos.containsKey(matricula), "recuperaAluno", matricula);
		return this.listaDeAlunos.get(matricula).toString();
	}

	/**
	 * Lista os alunos do sistema.
	 * 
	 * @return
	 */
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

	/**
	 * Retorna uma informacao do aluno a partir de uma matricula e a informacao que
	 * o usuario quer.
	 * 
	 * @param matricula,
	 *            matricula do aluno
	 * @param atributo,
	 *            informacao a ser retornada.
	 * @return
	 */
	public String getInfoAluno(String matricula, String atributo) {
		auxiliarController.verificaMatricula(this.listaDeAlunos.containsKey(matricula), "getInfoAluno", matricula);
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

	/**
	 * Transforma determinado aluno num tutor.
	 * 
	 * @param matricula,
	 *            matricula do aluno.
	 * @param disciplina,
	 *            disciplina na qual o mesmo sera tutor
	 * @param proficiencia,
	 *            nivel de proficiencia do mesmo na disciplina
	 */
	public void tornarTutor(String matricula, String disciplina, int proficiencia) {
		if (!listaDeAlunos.containsKey(matricula)) {
			throw new IllegalArgumentException("Erro na definicao de papel: Tutor nao encontrado");
		}

		if (auxiliarController.verificaDadosParaTornarTutor(listaDeAlunos.containsKey(matricula),
				listaDeAlunos.get(matricula).getTipo().equals("tutor"), recuperaTutorPelaMatricula(matricula),
				disciplina, proficiencia)) {
			Aluno aluno = this.listaDeAlunos.get(matricula);
			Tutor tutor = new Tutor(aluno, disciplina, proficiencia);
			this.listaDeAlunos.replace(matricula, tutor);
		} else {
			recuperaTutorPelaMatricula(matricula).adicionaDisciplina(disciplina);
		}
	}

	/**
	 * Retorna a representacao textual de um tutor.
	 * 
	 * @param matricula,
	 *            matricula do aluno
	 * @return
	 */
	public String recuperaTutor(String matricula) {
		if (!this.listaDeAlunos.containsKey(matricula)) {
			throw new IllegalArgumentException("Erro na busca por tutor: Tutor nao encontrado");
		}
		return recuperaAluno(matricula);
	}

	/**
	 * Lista todos os tutores do sistema.
	 * 
	 * @return
	 */
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
	 * 
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
	 * 
	 * @param email
	 *            email do aluno em String
	 * @return o Tutor procurado
	 */

	public Tutor recuperaTutorPorEmail(String email) {
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

	/**
	 * Retorna um Tutor a partir de uma matricula.
	 * 
	 * @param matricula,
	 *            suposta matricula do tutor.
	 * @return
	 */
	public Tutor recuperaTutorPelaMatricula(String matricula) {
		if (listaDeAlunos.containsKey(matricula) && listaDeAlunos.get(matricula).getTipo().equals("tutor")) {
			Tutor tutor = (Tutor) listaDeAlunos.get(matricula);
			return tutor;
		}
		return null;
	}

	/**
	 * Cadastra um horario de atendimento do tutor.
	 * 
	 * @param email,
	 *            email do tutor
	 * @param horario,
	 *            horario que ficara disponivel
	 * @param dia,
	 *            dia do atendimento
	 */
	public void cadastrarHorario(String email, String horario, String dia) {

		auxiliarController.confereCadastrarHorario(recuperaTutorPorEmail(email), email, horario, dia);
		recuperaTutorPorEmail(email).cadastrarHorario(horario, dia);
	}

	/**
	 * Cadastra um local de atendimento para o tutor.
	 * 
	 * @param email,
	 *            email do tutor.
	 * @param local,
	 *            local do atendimento.
	 */
	public void cadastrarLocalDeAtendimento(String email, String local) {
		auxiliarController.confereCadastrarLocalDeAtendimento(recuperaTutorPorEmail(email), email, local);
		recuperaTutorPorEmail(email).cadastrarLocalDeAtendimento(local);

	}

	/**
	 * Retorna se o horario passado esta cadastrado para atendimento.
	 * 
	 * @param email,
	 *            email do tutor.
	 * @param horario,
	 *            que esta sendo consultado.
	 * @param dia,
	 *            dia que esta sendo consultado.
	 * @return
	 */
	public boolean consultaHorario(String email, String horario, String dia) {
		if (recuperaTutorPorEmail(email) == null)
			return false;
		return recuperaTutorPorEmail(email).consultaHorario(horario, dia);
	}

	/**
	 * Consulta o local de atendimento de um tutor.
	 * 
	 * @param email,
	 *            email do tutor.
	 * @param local,
	 *            local a ser consultado.
	 * @return
	 */
	public boolean consultaLocal(String email, String local) {
		if (recuperaTutorPorEmail(email) == null)
			return false;
		return recuperaTutorPorEmail(email).consultaLocal(local);
	}

}
