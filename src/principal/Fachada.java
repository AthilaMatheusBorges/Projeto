package principal;

import easyaccept.EasyAccept;

/**
 * 
 * @authores Jonathan Allisson de Lima Silva -- 117110926
 * 			 Marcelo Fagner da Silva Andrade -- 117110910
 * 			 Flavio Roberto Pires Quirino Farias -- 117111444
 * 			 Athila Matheus Barros Borges -- 117110913
 * 			 ****UFCG - COMPUTACAO 2017.2****
 */
public class Fachada {

	Sistema sistema = new Sistema();

	public static void main(String[] args) {
		args = new String[] { "principal.Fachada", "acceptance_test/us1_test.txt", "acceptance_test/us2_test.txt",
				"acceptance_test/us3_test.txt" };
		EasyAccept.main(args);
	}

	/**
	 * Cadastra um novo aluno no sistema.
	 * 
	 * @param nome,
	 *            nome do aluno
	 * @param matricula,
	 *            matricula do aluno.
	 * @param codigoCurso,
	 *            codigo do curso do aluno.
	 * @param telefone,
	 *            telefone do aluno.
	 * @param email,
	 *            email do aluno.
	 */
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		sistema.cadastrarAluno(nome, matricula, codigoCurso, telefone, email);
	}

	/**
	 * Retorna a representacao textual de um aluno, a partir de uma matricula.
	 * 
	 * @param matricula,
	 *            matricula do aluno.
	 * @return
	 */
	public String recuperaAluno(String matricula) {
		return sistema.recuperaAluno(matricula);
	}

	/**
	 * Lista os alunos do sistema.
	 * 
	 * @return uma String com a lista de alunos
	 */
	public String listarAlunos() {
		return sistema.listarAlunos();
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
		return sistema.getInfoAluno(matricula, atributo);
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
		sistema.tornarTutor(matricula, disciplina, proficiencia);
	}

	/**
	 * Retorna a representacao textual de um tutor.
	 * 
	 * @param matricula,
	 *            matricula do aluno
	 * @return retorna o toString do aluno procurado
	 */
	public String recuperaTutor(String matricula) {
		return sistema.recuperaTutor(matricula);
	}

	/**
	 * Lista todos os tutores do sistema.
	 * 
	 * @return uma String com todos os tutores
	 */
	public String listarTutores() {
		return sistema.listarTutores();
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
		sistema.cadastrarHorario(email, horario, dia);
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
		sistema.cadastrarLocalDeAtendimento(email, local);
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
	 * @return um booleano se o tutor tiver tal horario live
	 */
	public boolean consultaHorario(String email, String horario, String dia) {
		return sistema.consultaHorario(email, horario, dia);
	}

	/**
	 * Consulta o local de atendimento de um tutor.
	 * 
	 * @param email,
	 *            email do tutor.
	 * @param local,
	 *            local a ser consultado.
	 * @return retorna um booleano se o tutor tiver tal horario livre
	 */
	public boolean consultaLocal(String email, String local) {
		return sistema.consultaLocal(email, local);
	}
}
