package sistema;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import comparadores.OrdenaPorEmailAlunos;
import comparadores.OrdenaPorMatriculaAlunos;
import comparadores.OrdenaPorNomeAlunos;
import controladores.ControllerAjuda;
import controladores.ControllerAluno;
import controladores.ControllerCaixa;
import controladores.ControllerTutor;
import principal.AjudaOnline;
import principal.AjudaPresencial;
import principal.Aluno;
import principal.EntradaESaida;
import principal.PedidoDeAjuda;
import principal.Tutor;

/**
 * Responsavel por controlar todo o sistema. Usa os outros controladores para
 * gerenciar o sistema.
 * 
 * @author Roundhouse Kick Group
 *
 */
public class Sistema {

	private ControllerAluno cAluno;
	private ControllerTutor cTutor;
	private ControllerAjuda cAjuda;
	private ControllerCaixa cCaixa;
	private Comparator ordenacao;
	private EntradaESaida io;

	/**
	 * Inicia um novo sistema com os controladores.
	 */
	public Sistema() {
		cAluno = new ControllerAluno();
		cTutor = new ControllerTutor();
		cAjuda = new ControllerAjuda();
		cCaixa = new ControllerCaixa();
		ordenacao = new OrdenaPorNomeAlunos();
		io = new EntradaESaida();
	}

	/**
	 * Cadastra um aluno.
	 * 
	 * @param nome
	 *            eh o nome do aluno.
	 * @param matricula
	 *            eh a matricula do aluno.
	 * @param codigoCurso
	 *            eh o codigo do curso.
	 * @param telefone
	 *            eh o telefone do aluno.
	 * @param email
	 *            eh o email do aluno.
	 */
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		cAluno.adicionaAluno(nome, matricula, codigoCurso, telefone, email);
	}

	/**
	 * Recupera o aluno a partir da matricula.
	 * 
	 * @param matricula
	 *            eh a matricula do aluno.
	 * @return retorna o toString() do aluno.
	 */
	public String recuperaAluno(String matricula) {
		return cAluno.recuperaAluno(matricula);
	}

	/**
	 * Lista os alunos.
	 * 
	 * @return retorna uma lista dos alunos ordenada por nome.
	 */
	public String listarAlunos() {
		ArrayList<Aluno> alunos = cAluno.getListaDeAlunos();
		Collections.sort(alunos, this.ordenacao);
		String saida = "";
		for (Aluno aluno1 : alunos) {
			saida += aluno1.toString() + ", ";
		}
		if (saida.equals(""))
			return saida;
		return saida.substring(0, saida.length() - 2);
	}

	/**
	 * Obtem uma informacao sobre o aluno.
	 * 
	 * @param matricula
	 *            eh a matricula do aluno.
	 * @param atributo
	 *            eh a informacao.
	 * @return retorna a informacao do aluno.
	 */
	public String getInfoAluno(String matricula, String atributo) {
		return cAluno.getInfoAluno(matricula, atributo);
	}

	/**
	 * Define o dono da matricula como tutor da disciplina passada.
	 * 
	 * @param matricula
	 *            eh a matricula.
	 * @param disciplina
	 *            eh a disciplina.
	 * @param proficiencia
	 *            eh a proficiencia sobre a disciplina.
	 */
	public void tornarTutor(String matricula, String disciplina, int proficiencia) {
		if (!matriculaCadastrada(matricula))
			throw new IllegalArgumentException("Erro na definicao de papel: Tutor nao encontrado");
		Aluno alunoTemporario = cAluno.getAlunoPorMatricula(matricula);
		cTutor.tornarTutor(matricula, disciplina, proficiencia);
	}

	/**
	 * Verifica se a matricula esta cadastrada.
	 * 
	 * @param matricula
	 *            eh a matricula a ser verificada.
	 * @return retorna true se a matricula estiver cadastrada, false caso contrario.
	 */
	public boolean matriculaCadastrada(String matricula) {
		return cAluno.matriculaCadastrada(matricula);
	}

	/**
	 * Recupera um tutor a partir da matricula.
	 * 
	 * @param matricula
	 *            eh a matricula do tutor.
	 * @return retorna uma descricao textual do tutor.
	 */
	public String recuperaTutor(String matricula) {
		cTutor.verificaTutor(matricula);
		return recuperaAluno(matricula);
	}

	/**
	 * A partir da lista dos tutores, lista os alunos que sao tutores.
	 * 
	 * @return retorna uma lista de tutores.
	 */
	public String listarTutores() {
		ArrayList<Aluno> tutores = new ArrayList<>();
		String saida = "";
		for (String matricula : cTutor.listarTutores()) {
			tutores.add(cAluno.getAlunoPorMatricula(matricula));
		}
		Collections.sort(tutores, this.ordenacao);
		for (Aluno aluno1 : tutores) {
			saida += aluno1.toString() + ", ";
		}
		if (saida.equals(""))
			return saida;
		return saida.substring(0, saida.length() - 2);

	}

	/**
	 * Recupera o tutor a partir do email.
	 * 
	 * @param email
	 *            eh o email do tutor.
	 * @return retorna o obejto tutor.
	 */
	public Tutor recuperaTutorPorEmail(String email) {
		Aluno aluno = cAluno.getAlunoPorEmail(email);
		if (aluno == null)
			return null;
		cTutor.verificaTutor(aluno.getMatricula());
		return cTutor.getTutor(aluno.getMatricula());
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
	 * Confere se o email eh de um tutor, se o email eh vazio ou em branco se o
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
	 * @return um booleano se o tutor tiver o horario.
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
	 * @return retorna um booleano se o tutor tiver o horario
	 */
	public boolean consultaLocal(String email, String local) {
		if (recuperaTutorPorEmail(email) == null)
			return false;
		return recuperaTutorPorEmail(email).consultaLocal(local);
	}

	/**
	 * Recupera um pedido de ajuda a partir do id.
	 * 
	 * @param idAjuda
	 *            eh o identificador da ajuda.
	 * @return retorna o pedido de ajuda.
	 */
	public PedidoDeAjuda getPedidoDeAjuda(int idAjuda) {
		return cAjuda.getPedidoDeAjuda(idAjuda);
	}

	/**
	 * Faz o pedido de ajuda.
	 * 
	 * @param matrAluno
	 *            eh a matricula do aluno.
	 * @param disciplina
	 *            eh a disciplina do pedido de ajuda.
	 * @param horario
	 *            eh o horario.
	 * @param dia
	 *            eh o dia.
	 * @param localInteresse
	 *            eh o local de interesse.
	 * @return retorna o identificador do pedido de ajuda realizado.
	 */
	public int pedirAjudaPresencial(String matrAluno, String disciplina, String horario, String dia,
			String localInteresse) {
		String tutorMatricula = escolheTutorAjudaPresencial(disciplina, horario, dia, localInteresse);
		return cAjuda.adicionaAjuda(matrAluno, disciplina, tutorMatricula, horario, dia, localInteresse);
	}

	/**
	 * Faz o pedido de ajuda.
	 * 
	 * @param matrAluno
	 *            eh a matricula do aluno.
	 * @param disciplina
	 *            e a disciplina do pedido do ajuda.
	 * @return retorna o identificador do pedido de ajuda realizado.
	 */
	public int pedirAjudaOnline(String matrAluno, String disciplina) {
		String tutorMatricula = escolheTutorAjudaOnline(disciplina);
		return cAjuda.adicionaAjuda(matrAluno, disciplina, tutorMatricula);
	}

	/**
	 * Escolhe o tutor para ajuda presencial com base nos requisitos.
	 * 
	 * @param disciplina
	 *            eh a disciplina.
	 * @param horario
	 *            eh o horario.
	 * @param dia
	 *            eh o dia.
	 * @param localInteresse
	 *            eh local de interesse.
	 * @return retorna a matricula do tutor selecionado.
	 */
	private String escolheTutorAjudaPresencial(String disciplina, String horario, String dia, String localInteresse) {
		if (stringNullVazio(disciplina) || stringNullVazio(horario) || stringNullVazio(dia)
				|| stringNullVazio(localInteresse))
			return null;
		return cTutor.maiorProficiencia(disciplina, horario, dia, localInteresse).getTutorMatricula();
	}

	/**
	 * Escolhe o tutor para ajuda online.
	 * 
	 * @param disciplina
	 *            eh a disciplina.
	 * @return retorna a matricula do tutor selecionado.
	 */
	private String escolheTutorAjudaOnline(String disciplina) {
		if (stringNullVazio(disciplina))
			return null;
		return cTutor.maiorProficiencia(disciplina).getTutorMatricula();
	}

	/**
	 * Recupera a matricula do tutor da ajuda.
	 * 
	 * @param idAjuda
	 *            eh o id da ajuda.
	 * @return retorna a matricula do tutor da ajuda.
	 */
	public String getMatriculaTutorAjuda(int idAjuda) {
		return cAjuda.getMatriculaTutorAjuda(idAjuda);
	}

	/**
	 * Recupera o tutor da ajuda.
	 * 
	 * @param idAjuda
	 *            eh o id da ajuda.
	 * @return retorna a descricao do tutor da ajuda.
	 */
	public String pegarTutor(int idAjuda) {
		cAjuda.validaIdAjuda("tutor", idAjuda);
		return cAjuda.getDescricaoTutor(idAjuda);
	}

	/**
	 * Recupera um pedido de ajuda.
	 * 
	 * @param idAjuda
	 *            eh o id da ajuda.
	 * @return retorna o objeto do pedido de ajuda.
	 */
	public PedidoDeAjuda getAjuda(int idAjuda) {
		cAjuda.validaIdAjuda("recuperar ajuda", idAjuda);
		return cAjuda.getPedidoDeAjuda(idAjuda);
	}

	/**
	 * Obtem informacao da ajuda.
	 * 
	 * @param idAjuda
	 *            eh o id da ajuda.
	 * @param atributo
	 *            eh a informacao.
	 * @return retorna a informacao da ajuda.
	 */
	public String getInfoAjuda(int idAjuda, String atributo) {
		return cAjuda.getInfoAjuda(idAjuda, atributo);
	}

	/**
	 * Avalida o tutor e a ajuda.
	 * 
	 * @param idAjuda
	 *            eh o id da ajuda.
	 * @param nota
	 *            eh a nota da avaliacao.
	 * @return retorna uma string confirmando a avaliacao.
	 */
	public String avaliarTutor(int idAjuda, int nota) {
		if (nota > 5)
			throw new IllegalArgumentException("Erro na avaliacao de tutor: nota nao pode ser maior que 5");
		else if (nota < 0)
			throw new IllegalArgumentException("Erro na avaliacao de tutor: nota nao pode ser menor que 0");

		cAjuda.avaliarAjuda(idAjuda);
		return cTutor.avaliarTutor(cAjuda.getMatriculaTutorAjuda(idAjuda), nota);
	}

	/**
	 * Pega a nota do tutor.
	 * 
	 * @param matriculaTutor
	 *            eh a matricula do tutor.
	 * @return
	 */
	public String pegarNota(String matriculaTutor) {
		return cTutor.getTutor(matriculaTutor).pegarNota();
	}

	/**
	 * Pega o nivel do tutor.
	 * 
	 * @param matriculaTutor
	 *            eh a matricula do tutor.
	 * @return
	 */
	public String pegarNivel(String matriculaTutor) {
		return cTutor.getTutor(matriculaTutor).pegarNivel();
	}

	/**
	 * Doa um valor ao tutor.
	 * 
	 * @param matriculaTutor
	 *            eh a matricula do tutor.
	 * @param totalCentavos
	 *            eh o valor a ser doado.
	 */
	public void doar(String matriculaTutor, int totalCentavos) {
		if (totalCentavos < 0) {
			throw new IllegalArgumentException("Erro na doacao para tutor: totalCentavos nao pode ser menor que zero");
		} else if (!cTutor.temTutor(matriculaTutor)) {
			throw new IllegalArgumentException("Erro na doacao para tutor: Tutor nao encontrado");
		}

		double valorSistema = 0;
		double taxa = cTutor.getTaxaTutor(matriculaTutor);
		valorSistema = Math.ceil((1 - taxa) * totalCentavos);
		cCaixa.adicionaAoCaixa((int) valorSistema);
		cTutor.receberDoacao(matriculaTutor, (totalCentavos - valorSistema));
	}

	/**
	 * Recupera o saldo do tutor.
	 * 
	 * @param emailTutor
	 *            eh o email do tutor.
	 * @return retorna o saldo do tutor.
	 */
	public int totalDinheiroTutor(String emailTutor) {
		if (emailTutor.trim().equals("") || emailTutor == null) {
			throw new IllegalArgumentException(
					"Erro na consulta de total de dinheiro do tutor: emailTutor nao pode ser vazio ou nulo");
		} else if (recuperaTutorPorEmail(emailTutor) == null) {
			throw new NullPointerException("Erro na consulta de total de dinheiro do tutor: Tutor nao encontrado");
		}

		return recuperaTutorPorEmail(emailTutor).getSaldo();
	}

	/**
	 * Recupera o saldo do sistema.
	 * 
	 * @return retorna o valor do caixa do sistema.
	 */
	public int totalDinheiroSistema() {
		return cCaixa.getSaldo();
	}

	/**
	 * Verifica se uma string eh valida.
	 * 
	 * @param stringTeste
	 *            e a string testada.
	 * @return retorna true se for valida, false caso contrario.
	 */
	public boolean stringNullVazio(String stringTeste) {
		if (stringTeste.trim().equals("") || stringTeste == null)
			return true;
		return false;
	}

	/**
	 * Metodo que decide o tipo de ordenacao usada no sistema
	 * 
	 * @param atributo
	 *            tipo de ordenacao desejada
	 */
	public void configuraOrdem(String atributo) {
		switch (atributo.toUpperCase()) {
		case "NOME":
			this.ordenacao = new OrdenaPorNomeAlunos();
			break;
		case "MATRICULA":
			this.ordenacao = new OrdenaPorMatriculaAlunos();
			break;
		case "EMAIL":
			this.ordenacao = new OrdenaPorEmailAlunos();
			break;
		default:
			throw new IllegalArgumentException("Ordenacao nao identificada");
		}

	}

	/**
	 * Salva o estado atual do sistema.
	 */
	public void salvar() {
		try {
			io.salvar(cAluno, "aluno-dados");
			io.salvar(cTutor, "tutor-dados");
			io.salvar(cAjuda, "ajuda-dados");
			io.salvar(cCaixa, "caixa-dados");
		} catch (IOException e) {
			System.out.println("Algo deu errado :/");
		}
	}

	/**
	 * Carrega o estado atual do sistema, salvo anteriormente.
	 */
	public void carregar() {
		try {
			this.cAluno = (ControllerAluno) io.carregar("aluno-dados");
			this.cTutor = (ControllerTutor) io.carregar("tutor-dados");
			this.cAjuda = (ControllerAjuda) io.carregar("ajuda-dados");
			this.cCaixa = (ControllerCaixa) io.carregar("caixa-dados");
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Algo deu errado");
		}
	}

	/**
	 * Limpa o estado atual do sistema.
	 */
	public void limpar() {
		File arquivo = new File("aluno-dados");
		File arquivo2 = new File("tutor-dados");
		File arquivo3 = new File("ajuda-dados");
		File arquivo4 = new File("caixa-dados");
		arquivo.delete();
		arquivo2.delete();
		arquivo3.delete();
		arquivo4.delete();

	}

}