package principal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Sistema {

	private ControllerAluno cAluno;
	private ControllerTutor cTutor;
	private ControllerAjuda cAjuda;
	private ControllerCaixa cCaixa;

	/**
	 * Constroi um novo sistema.
	 */
	public Sistema() {
		cAluno = new ControllerAluno();
		cTutor = new ControllerTutor();
		cAjuda = new ControllerAjuda();
		cCaixa = new ControllerCaixa();
	}

	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		cAluno.adicionaAluno(nome, matricula, codigoCurso, telefone, email);
	}

	public String recuperaAluno(String matricula) {
		return cAluno.recuperaAluno(matricula);
	}

	public String listarAlunos() {
		return cAluno.listarAlunos();

	}

	public String getInfoAluno(String matricula, String atributo) {
		return cAluno.getInfoAluno(matricula, atributo);
	}

	public void tornarTutor(String matricula, String disciplina, int proficiencia) {
		if (!matriculaCadastrada(matricula))
			throw new IllegalArgumentException("Erro na definicao de papel: Tutor nao encontrado");
		cTutor.tornarTutor(matricula, disciplina, proficiencia);
	}

	public boolean matriculaCadastrada(String matricula) {
		return cAluno.matriculaCadastrada(matricula);
	}

	public String recuperaTutor(String matricula) {
		cTutor.verificaTutor(matricula);
		return recuperaAluno(matricula);
	}

	public String listarTutores() {
		String saida = "";
		for (String matricula : cTutor.listarTutores()) {
			saida += cAluno.recuperaAluno(matricula) + ", ";
		}
		if (saida.equals(""))
			return saida;
		return saida.substring(0, saida.length() - 2);

	}

	public Tutor recuperaTutorPorEmail(String email) {
		Aluno aluno = cAluno.getAlunoPorEmail(email);
		if (aluno==null) return null;
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

	public PedidoDeAjuda getPedidoDeAjuda(int idAjuda) {
		return cAjuda.getPedidoDeAjuda(idAjuda);
	}

	public int pedirAjudaPresencial(String matrAluno, String disciplina, String horario, String dia,
			String localInteresse) {
		String tutorMatricula = escolheTutorAjudaPresencial(disciplina, horario, dia, localInteresse);
		return cAjuda.adicionaAjuda(matrAluno, disciplina, tutorMatricula, horario, dia, localInteresse);
	}

	public int pedirAjudaOnline(String matrAluno, String disciplina) {
		String tutorMatricula = escolheTutorAjudaOnline(disciplina);
		return cAjuda.adicionaAjuda(matrAluno, disciplina, tutorMatricula);
	}

	private String escolheTutorAjudaPresencial(String disciplina, String horario, String dia, String localInteresse) {
		if (stringNullVazio(disciplina))
			return null;
		if (stringNullVazio(horario))
			return null;
		if (stringNullVazio(dia))
			return null;
		if (stringNullVazio(localInteresse))
			return null;
		return cTutor.maiorProficiencia(disciplina, horario, dia, localInteresse).getTutorMatricula();
	}

	private String escolheTutorAjudaOnline(String disciplina) {
		if (stringNullVazio(disciplina))
			return null;
		return cTutor.maiorProficiencia(disciplina).getTutorMatricula();
	}

	public String getMatriculaTutorAjuda(int idAjuda) {
		return cAjuda.getMatriculaTutorAjuda(idAjuda);
	}

	public String pegarTutor(int idAjuda) {
		cAjuda.validaIdAjuda("tutor", idAjuda);
		return cAjuda.getDescricaoTutor(idAjuda);
	}

	public PedidoDeAjuda getAjuda(int idAjuda) {
		cAjuda.validaIdAjuda("recuperar ajuda", idAjuda);
		return cAjuda.getPedidoDeAjuda(idAjuda);
	}

	public String getInfoAjuda(int idAjuda, String atributo) {
		cAjuda.validaIdAjuda("info da ajuda", idAjuda);
		switch (atributo.toLowerCase()) {
		case "tutor":
			return getAjuda(idAjuda).getTutorMatricula();
		case "disciplina":
			return getAjuda(idAjuda).getDisciplina();
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
		case "localinteresse":
			if (getAjuda(idAjuda) instanceof AjudaOnline)
				throw new IllegalArgumentException(
						"Erro na obtencao de informacao de ajuda: Ajuda Online nao tem local");
			AjudaPresencial ajudaLocal = (AjudaPresencial) getAjuda(idAjuda);
			return ajudaLocal.getLocalInteresse();
		default:
			if (atributo.trim().equals("") || atributo == null)
				throw new IllegalArgumentException(
						"Erro ao tentar recuperar info da ajuda : atributo nao pode ser vazio ou em branco");
			throw new IllegalArgumentException("Erro ao tentar recuperar info da ajuda : atributo nao encontrado");
		}
	}


	public String avaliarTutor(int idAjuda, int nota) {
		if (nota > 5)
			throw new IllegalArgumentException("Erro na avaliacao de tutor: nota nao pode ser maior que 5");
		else if (nota < 0) 
			throw new IllegalArgumentException("Erro na avaliacao de tutor: nota nao pode ser menor que 0");
		
		cAjuda.avaliarAjuda(idAjuda);
		return cTutor.avaliarTutor(cAjuda.getMatriculaTutorAjuda(idAjuda), nota);
	}

	
	public String pegarNota(String matriculaTutor) {
		return cTutor.getTutor(matriculaTutor).pegarNota();
	}

	
	public String pegarNivel(String matriculaTutor) {
		return cTutor.getTutor(matriculaTutor).pegarNivel();
	}

	
	public void doar(String matriculaTutor, int totalCentavos) {
		if(totalCentavos < 0) {
			throw new IllegalArgumentException("Erro na doacao para tutor: totalCentavos nao pode ser menor que zero");
		}else if(!cTutor.temTutor(matriculaTutor)) {
			throw new IllegalArgumentException("Erro na doacao para tutor: Tutor nao encontrado");
		}
		
		double valorSistema = 0;
		double taxa = cTutor.getTaxaTutor(matriculaTutor);
		valorSistema = Math.ceil((1 - taxa) * totalCentavos);
		cCaixa.adicionaAoCaixa((int) valorSistema);
		cTutor.receberDoacao(matriculaTutor, (totalCentavos - valorSistema));
	}

	public int totalDinheiroTutor(String emailTutor) {
		if (emailTutor.trim().equals("") || emailTutor == null) {
			throw new IllegalArgumentException("Erro na consulta de total de dinheiro do tutor: emailTutor nao pode ser vazio ou nulo");
		}else if(recuperaTutorPorEmail(emailTutor)==null) {
			throw new NullPointerException("Erro na consulta de total de dinheiro do tutor: Tutor nao encontrado");
		}
		
		return recuperaTutorPorEmail(emailTutor).getSaldo();
	}

	
	public int totalDinheiroSistema() {
		return cCaixa.getSaldo();
	}

	public boolean stringNullVazio(String stringTeste) {
		if (stringTeste.trim().equals("") || stringTeste == null)
			return true;
		return false;
	}

}