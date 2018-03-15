package sistema;

import easyaccept.EasyAccept;

/**
 * 
 * @author 	Jonathan Allisson de Lima Silva -- 117110926 
 * @author	Marcelo Fagner da Silva Andrade -- 117110910 
 * @author	Flavio Roberto Pires Quirino Farias -- 117111444 
 * @author  Athila Matheus Barros Borges -- 117110913 
 * 			       ****UFCG - COMPUTACAO 2017.2****
 */
public class Fachada {

	Sistema sistema = new Sistema();

	public static void main(String[] args) {
		args = new String[] { "sistema.Fachada", 
				"acceptance_test/us1_test.txt", 
				"acceptance_test/us2_test.txt",
				"acceptance_test/us3_test.txt", 
				"acceptance_test/us4_test.txt", 
				"acceptance_test/us5_test.txt", 
				"acceptance_test/us6_test.txt",
				"acceptance_test/us8_test.txt"};
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

	/**
	 * Cadastra um pedido de Ajuda Presencial.
	 * 
	 * @param disciplina
	 *            eh a disciplina da ajuda.
	 * @param horario
	 *            eh o horario da ajuda.
	 * @param dia
	 *            eh o dia da ajuda.
	 * @param localInteresse
	 *            eh o local da ajuda.
	 * @return retorna o identificador do pedido de ajuda.
	 */
	public int pedirAjudaPresencial(String matricula, String disciplina, String horario, String dia, String localInteresse) {
		return sistema.pedirAjudaPresencial(matricula, disciplina, horario, dia, localInteresse);
	}

	/**
	 * Cadastra um pedido de Ajuda Online.
	 * 
	 * @param disciplina
	 *            eh a disciplina da ajuda.
	 * @return retorna o identificador do pedido de ajuda.
	 */
	public int pedirAjudaOnline(String matricula, String disciplina) {
		return sistema.pedirAjudaOnline(matricula, disciplina);
	}

	/**
	 * Recupera a matricula do tutor responsavel pelo pedido de ajuda a partir do id
	 * do pedido.
	 * 
	 * @param idAjuda
	 *            eh o identificador do pedido de ajuda.
	 * @return retorna a matricula do tutor responsavel pelo pedido de ajuda.
	 */
	public String pegarTutor(int idAjuda) {
		return sistema.pegarTutor(idAjuda);
	}

	/**
	 * Recupera uma informacao sobre o pedido de ajuda.
	 * 
	 * @param idAjuda
	 *            eh o identificador do pedido de ajuda.
	 * @param atributo
	 *            eh a informacao que o usario quer sobre o pedido de ajudar.
	 * @return retorna a informacao pedida pelo usuario atraves do atributo.
	 */
	public String getInfoAjuda(int idAjuda, String atributo) {
		return sistema.getInfoAjuda(idAjuda, atributo);
	}

	/**
	 * Metodo que avalia o tutor apartir da nota de uma ajuda
	 * 
	 * @param idAjuda
	 *            identificacao da ajuda
	 * @param nota
	 *            nota da ajuda
	 * @return retorna uma String se tutor foi avaliado com sucesso ou nao
	 */
	public String avaliarTutor(int idAjuda, int nota) {
		return sistema.avaliarTutor(idAjuda, nota);
	}

	/**
	 * Metodo que pega a nota de avalicao de um tutor
	 * 
	 * @param matriculaTutor
	 *            matricula do tutor
	 * @return retorna um valor booleano da nota do tutor
	 */
	public String pegarNota(String matriculaTutor) {
		return sistema.pegarNota(matriculaTutor);
	}

	/**
	 * Metodo que recupera o nivel de um determinado tutor
	 * 
	 * @param matriculaTutor
	 *            matricula de um tutor
	 * @return retorna a avalicao do tutor em String
	 */
	public String pegarNivel(String matriculaTutor) {
		return sistema.pegarNivel(matriculaTutor);
	}

	/**
	 * Metodo que serve para fazer uma doacao ao algum tutor.
	 * 
	 * @param matriculaTutor
	 *            matricula de um tutor
	 * @param totalCentavos
	 *            valor a ser doado
	 */
	public void doar(String matriculaTutor, int totalCentavos) {
		sistema.doar(matriculaTutor, totalCentavos);
	}

	/**
	 * Retorna o total de dinheiro do tutor.
	 * 
	 * @param emailTutor
	 *            email do tutor
	 * @return dinheiro do tutor
	 */
	public int totalDinheiroTutor(String emailTutor) {
		return sistema.totalDinheiroTutor(emailTutor);
	}

	/**
	 * Retorna o dinheiro do sistema.
	 * 
	 * @return caixa do sistema
	 */
	public int totalDinheiroSistema() {
		return sistema.totalDinheiroSistema();
	}
	
	/**
	 * Metodo que decide o tipo de ordenacao usada na listagem
	 * @param atributo
	 */
	public void configurarOrdem(String atributo) {
		sistema.configuraOrdem(atributo);
	}
	
	/**
	 * Salva o estado atual do sistema, exceto o estado da ordenacao.
	 */
	public void salvar() {
		sistema.salvar();
	}
	
	/**
	 * Carrega o estado do sistema que foi salvo anteriormente
	 */
	public void carregar() {
		sistema.carregar();
	}
	
	/**
	 * Limpa o estado do sistema que foi salvo anteriormente.
	 */
	public void limpar() {
		sistema.limpar();
	}
}
