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
	private Map<Integer, PedidoDeAjuda> pedidosDeAjuda;

	/**
	 * Constroi um novo sistema.
	 */
	public Sistema() {
		this.listaDeAlunos = new HashMap<>();
		this.pedidosDeAjuda = new HashMap<>();
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
		verificaCadastroAluno(this.listaDeAlunos.containsKey(matricula), nome, matricula, email);
		Aluno aluno = new Aluno(nome, matricula, codigoCurso, telefone, email);
		this.listaDeAlunos.put(matricula, aluno);
	}

	/**
	 * Verifica se o e-mail é valido, se a matrícula está cadastrada no sistema de
	 * alunos e se o nome é vazio ou null
	 * 
	 * @param matriculaValida
	 *            Um booleano true caso a matricula esteja no hashMap e false caso
	 *            nao
	 * @param nome
	 *            nome do aluno em String
	 * @param matricula
	 *            matricula do aluno em String
	 * @param email
	 *            email do aluno em String
	 */
	public void verificaCadastroAluno(boolean matriculaValida, String nome, String matricula, String email) {
		String erro = "";
		if (!verificaEmail(email)) {
			erro = "Email invalido";
		} else if (matriculaValida) {
			erro = "Aluno de mesma matricula ja cadastrado";
		} else if (nome.trim().equals("") || nome == null) {
			erro = "Nome nao pode ser vazio ou nulo";

		}
		if (!erro.equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de aluno: " + erro);
		}

	}

	/**
	 * Verifica se o email passado como parametro eh valido possui "@" e digitos
	 * antes e dps dele
	 * 
	 * @param email
	 *            email do a ser avaliado
	 * @return um booleano true caso seja email valido e false caso nao
	 */
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

	/**
	 * Retorna a representacao textual de um aluno, a partir de uma matricula.
	 * 
	 * @param matricula,
	 *            matricula do aluno.
	 * @return
	 */
	public String recuperaAluno(String matricula) {
		verificaMatricula(listaDeAlunos.containsKey(matricula), "recuperaAluno", matricula);
		return this.listaDeAlunos.get(matricula).toString();
	}

	/**
	 * Verifica se a matricula nos metodos de getInfoAluno e recuperaAluno sao
	 * validas e foram cadastradas no map de alunos
	 * 
	 * @param matriculaValida
	 *            um booleano true caso a matricula esteja no hashMap e false caso
	 *            nao
	 * @param metodo
	 *            nome do metodo que chama esse método em String
	 * @param matricula
	 *            do aluno em String
	 */

	public void verificaMatricula(boolean matriculaValida, String metodo, String matricula) {
		if (metodo.equals("getInfoAluno") && !matriculaValida) {
			throw new IllegalArgumentException("Erro na obtencao de informacao de aluno: Aluno nao encontrado");
		} else if (metodo.equals("recuperaAluno") && !matriculaValida) {
			throw new IllegalArgumentException("Erro na busca por aluno: Aluno nao encontrado");
		}

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
		verificaMatricula(this.listaDeAlunos.containsKey(matricula), "getInfoAluno", matricula);
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

		if (verificaDadosParaTornarTutor(listaDeAlunos.containsKey(matricula),
				listaDeAlunos.get(matricula).getTipo().equals("tutor"), recuperaTutorPelaMatricula(matricula),
				disciplina, proficiencia)) {
			Aluno aluno = this.listaDeAlunos.get(matricula);
			Tutor tutor = new Tutor(aluno, disciplina, proficiencia, getQntdAlunos());
			this.listaDeAlunos.replace(matricula, tutor);
		} else {
			recuperaTutorPelaMatricula(matricula).adicionaDisciplina(disciplina);
		}
	}

	/**
	 * Verifica se a matricula eh valida, so o valor de proficiencia eh valido, e se
	 * o possivel tutor ja eh tutor dessa disciplina
	 * 
	 * @param matriculaValida
	 *            Um booleano true caso a matricula esteja no hashMap e false caso
	 *            nao
	 * @param ehTutor
	 *            um booleano true caso seja tutor e false caso nao seja
	 * @param tutor
	 *            Um tutor da classe Tutor
	 * @param disciplina
	 *            uma String com o nome da disciplina
	 * @param proficiencia
	 *            valor de proficiencia em inteiro
	 * @return um booleano true se tiver tudo ok e false se nao
	 */
	public boolean verificaDadosParaTornarTutor(boolean matriculaValida, boolean ehTutor, Tutor tutor,
			String disciplina, int proficiencia) {

		if (!matriculaValida) {
			throw new IllegalArgumentException("Erro na definicao de papel: Tutor nao encontrado");
		} else if (proficiencia < 1 || proficiencia > 5) {
			throw new IllegalArgumentException("Erro na definicao de papel: Proficiencia invalida");
		} else if (ehTutor) {

			if (tutor.confereSeJaEtutorDaDisciplina(disciplina)) {
				throw new IllegalArgumentException("Erro na definicao de papel: Ja eh tutor dessa disciplina");
			} else {
				return false;
			}
		}
		return true;
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

		confereCadastrarHorario(recuperaTutorPorEmail(email), email, horario, dia);
		recuperaTutorPorEmail(email).cadastrarHorario(horario, dia);
	}

	/**
	 * Vai conferir se o email eh de um tutor, se o email eh vazio ou em branco se o
	 * horario eh vaziou ou em branco e se o dia eh vazio ou em branco
	 * 
	 * @param tutor
	 *            tutor da classe Tutor
	 * @param email
	 *            email do aluno em String
	 * @param horario
	 *            horario para encontro em String
	 * @param dia
	 *            dia do encontro em String
	 */

	public void confereCadastrarHorario(Tutor tutor, String email, String horario, String dia) {
		String erro = "";
		if (email.trim().equals("")) {
			erro = "email nao pode ser vazio ou em branco";
		} else if (tutor == null) {
			erro = "tutor nao cadastrado";
		} else if (horario.trim().equals("")) {
			erro = "horario nao pode ser vazio ou em branco";
		} else if (dia.trim().equals("")) {
			erro = "dia nao pode ser vazio ou em branco";
		}
		if (!erro.equals("")) {
			throw new IllegalArgumentException("Erro no cadastrar horario: " + erro);
		}

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
		confereCadastrarLocalDeAtendimento(recuperaTutorPorEmail(email), email, local);
		recuperaTutorPorEmail(email).cadastrarLocalDeAtendimento(local);

	}

	/**
	 * Confere se o email eh de um tutor, se o email eh vazio ou em branco e se o
	 * local eh vazio ou em branco
	 * 
	 * @param tutor
	 *            um tutor da classe Tutor
	 * @param email
	 *            email do aluno em String
	 * @param local
	 *            local para o encontro em String
	 */
	public void confereCadastrarLocalDeAtendimento(Tutor tutor, String email, String local) {
		String erro = "";
		if (email.trim().equals("")) {
			erro = "email nao pode ser vazio ou em branco";
		} else if (tutor == null) {
			erro = "tutor nao cadastrado";
		} else if (local.trim().equals("")) {
			erro = "local nao pode ser vazio ou em branco";
		}
		if (!erro.equals("")) {
			throw new IllegalArgumentException("Erro no cadastrar local de atendimento: " + erro);
		}

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

	/**
	 * Recupera um Pedido de Ajuda a partir do identificador da ajuda.
	 * 
	 * @param idAjuda
	 *            eh o identificador do pedido de ajuda.
	 * @return returna um Pedido de Ajuda.
	 */
	public PedidoDeAjuda getPedidoDeAjuda(int idAjuda) {
		return this.pedidosDeAjuda.get(idAjuda);
	}

	/**
	 * Cria e armazena um pedido de Ajuda Presencial com os parametros passados.
	 * 
	 * @param disciplina
	 *            eh a disciplina da ajuda.
	 * @param horario
	 *            eh o horario da ajuda.
	 * @param dia
	 *            eh o dia da ajuda.
	 * @param localInteresse
	 *            eh o local da ajuda.
	 * @return retorna o identificador do Pedido de Ajuda.
	 */
	public int pedirAjudaPresencial(String disciplina, String horario, String dia, String localInteresse) {
		String tutorMatricula = escolheTutorAjudaPresencial(disciplina, horario, dia, localInteresse).getMatricula();
		PedidoDeAjuda pedido = new AjudaPresencial(disciplina, tutorMatricula, dia, horario, localInteresse,
				getQntdPedidosAjuda() + 1);
		this.pedidosDeAjuda.put(pedido.getIdAjuda(), pedido);
		return pedido.getIdAjuda();
	}

	/**
	 * Cria e armazena um pedido de Ajuda Online.
	 * 
	 * @param disciplina
	 *            eh a disciplina da ajuda.
	 * @return retorna o identificador do Pedido de Ajuda.
	 */
	public int pedirAjudaOnline(String disciplina) {
		String tutorMatricula = escolheTutorAjudaOnline(disciplina).getMatricula();
		PedidoDeAjuda pedido = new AjudaOnline(disciplina, tutorMatricula, getQntdPedidosAjuda() + 1);
		this.pedidosDeAjuda.put(pedido.getIdAjuda(), pedido);
		return pedido.getIdAjuda();
	}

	/**
	 * Escolhe o tutor para associar a um pedido de Ajuda Presencial.
	 * 
	 * @param disciplina
	 *            eh a disciplina da ajuda.
	 * @param horario
	 *            eh o horario da ajuda.
	 * @param dia
	 *            eh o dia da ajuda.
	 * @param localInteresse
	 *            eh o local da ajuda.
	 * @return retorna o Tutor que melhor se encaixe nos requisitos.
	 */
	private Tutor escolheTutorAjudaPresencial(String disciplina, String horario, String dia, String localInteresse) {
		Tutor possivelTutor = null;
		Tutor tutorVerificado = null;
		for (Aluno aluno : this.listaDeAlunos.values()) {
			if (aluno.getTipo().equals("tutor")) {
				tutorVerificado = (Tutor) aluno;
				if (tutorVerificado.consultaLocal(localInteresse) && tutorVerificado.consultaHorario(horario, dia)) {
					if (possivelTutor == null)
						possivelTutor = tutorVerificado;
					else {
						if (tutorVerificado.getNota() > possivelTutor.getNota())
							possivelTutor = tutorVerificado;
						else if (tutorVerificado.getNota() == possivelTutor.getNota())
							if (tutorVerificado.getId() < possivelTutor.getId())
								possivelTutor = tutorVerificado;
					}
				}
			}
		}
		return possivelTutor;
	}

	/**
	 * Escolhe o tutor para associar a um pedido de Ajuda Online.
	 * 
	 * @param disciplina
	 *            eh a disciplina da ajuda.
	 * @return retorna o Tutor que melhor se encaixe nos requisitos.
	 */
	private Tutor escolheTutorAjudaOnline(String disciplina) {
		Tutor possivelTutor = null;
		Tutor tutorVerificado = null;
		for (Aluno aluno : this.listaDeAlunos.values()) {
			if (aluno.getTipo().equals("tutor")) {
				tutorVerificado = (Tutor) aluno;
				if (tutorVerificado.confereSeJaEtutorDaDisciplina(disciplina))
					if (possivelTutor == null)
						possivelTutor = tutorVerificado;
					else {
						if (tutorVerificado.getNota() > possivelTutor.getNota())
							possivelTutor = tutorVerificado;
						else if (tutorVerificado.getNota() == possivelTutor.getNota())
							if (tutorVerificado.getId() < possivelTutor.getId())
								possivelTutor = tutorVerificado;
					}
			}
		}
		return possivelTutor;
	}

	/**
	 * Recupera a matricula do tutor responsavel pelo pedido de ajuda a partir de
	 * identificador do pedido.
	 * 
	 * @param idAjuda
	 *            eh o identificador do pedido de ajuda.
	 * @return retorna a matricula do tutor responsavel pelo pedido de ajuda.
	 */
	public String pegarTutor(int idAjuda) {
		return this.pedidosDeAjuda.get(idAjuda).getTutorMatricula();
	}

	/**
	 * Recupera um Pedido de Ajuda a partir do identificador.
	 * 
	 * @param idAjuda
	 *            eh o identificador do Pedido de Ajuda.
	 * @return retorna um Pedido de Ajuda.
	 */
	public PedidoDeAjuda getAjuda(int idAjuda) {
		return this.pedidosDeAjuda.get(idAjuda);
	}

	/**
	 * Retorna uma informacao do Pedido de Ajuda a partir do identificador do pedido
	 * e da informacao passada como atrinuto.
	 * 
	 * @param idAjuda
	 *            eh o identificador do pedido de ajuda.
	 * @param atributo
	 *            eh a informacao que o usuario quer sobre o pedido de ajuda.
	 * @return retorna a informacao referente ao pedido de ajuda.
	 */
	public String getInfoAjuda(int idAjuda, String atributo) {
		switch (atributo.toLowerCase()) {
		case "tutor":
			return this.pedidosDeAjuda.get(idAjuda).getTutorMatricula();
		case "disciplina":
			return this.pedidosDeAjuda.get(idAjuda).getDisciplina();
		case "dia":
			if (getAjuda(idAjuda) instanceof AjudaOnline)
				throw new IllegalArgumentException("Erro na obtencao de informacao de ajuda: Ajuda Online nao tem dia");
			AjudaPresencial ajudaDia = (AjudaPresencial) getAjuda(idAjuda);
			return ajudaDia.getDia();
		case "horario":
			if (getAjuda(idAjuda) instanceof AjudaOnline)
				throw new IllegalArgumentException(
						"Erro na obtencao de informacao de ajuda: Ajuda Online nao tem horario");
			AjudaPresencial ajudaHorario = (AjudaPresencial) getAjuda(idAjuda);
			return ajudaHorario.getHorario();
		case "local":
			if (getAjuda(idAjuda) instanceof AjudaOnline)
				throw new IllegalArgumentException(
						"Erro na obtencao de informacao de ajuda: Ajuda Online nao tem local");
			AjudaPresencial ajudaLocal = (AjudaPresencial) getAjuda(idAjuda);
			return ajudaLocal.getLocalInteresse();
		default:
			throw new IllegalArgumentException("Erro na obtencao de informacao de ajuda: Atributo invalido");
		}
	}

	/**
	 * Retorna a quantidade de pedidos de ajuda cadastrados.
	 * 
	 * @return retorna a quantidade de pedidos de ajuda cadastrados.
	 */
	private int getQntdPedidosAjuda() {
		return this.pedidosDeAjuda.values().size();
	}

	/**
	 * Retorna a quantidade de alunos cadastrados.
	 * 
	 * @return retorna a quantidade de alunos cadastrados.
	 */
	private int getQntdAlunos() {
		return this.listaDeAlunos.values().size();
	}
}