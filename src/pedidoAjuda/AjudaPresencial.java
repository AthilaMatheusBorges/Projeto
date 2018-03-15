package pedidoAjuda;

public class AjudaPresencial extends PedidoDeAjuda {

	private static final long serialVersionUID = 7127262900317145512L;
	private String dia, horario, localInteresse;

	/**
	 * Constroi o Pedido de Ajuda com os parametros passados.
	 * 
	 * @param disciplina
	 *            eh a disciplina da ajuda.
	 * @param tutor
	 *            eh a matricula do tutor responsavel pelo pedido.
	 * @param dia
	 *            eh o dia da ajuda presencial.
	 * @param horario
	 *            eh o horario da ajuda presencial.
	 * @param localInteresse
	 *            eh o local da ajuda presencial.
	 * @param idAjuda
	 *            eh o identificador do pedido de ajuda.
	 */
	public AjudaPresencial(String matrAluno, String disciplina, String tutorMatricula, String dia, String horario,
			String localInteresse, int idAjuda) {
		super(matrAluno, disciplina, tutorMatricula, idAjuda);
		verificaString("matricula de aluno", matrAluno);
		verificaString("disciplina", disciplina);
		verificaString("dia", dia);
		verificaString("horario", horario);
		verificaString("local de interesse", localInteresse);
		this.dia = dia;
		this.horario = horario;
		this.localInteresse = localInteresse;
	}

	/**
	 * Retorna o dia da ajuda.
	 * 
	 * @return retorna o dia da ajuda.
	 */
	public String getDia() {
		return dia;
	}

	/**
	 * Retorna o horario da ajuda.
	 * 
	 * @return retorna o horario da ajuda.
	 */
	public String getHorario() {
		return horario;
	}

	/**
	 * Retorna o local da ajuda.
	 * 
	 * @return retorna o local da ajuda.
	 */
	public String getLocalInteresse() {
		return localInteresse;
	}

	/**
	 * Verifica se a String dos parametros passados para Ajuda Presencial eh valida.
	 * 
	 * @param quemSouEu
	 *            eh quem esta sendo verficado.
	 * @param stringTeste
	 *            eh a String a ser verificada.
	 */
	private void verificaString(String quemSouEu, String stringTeste) {
		if (stringTeste == null || stringTeste.trim().equals(""))
			throw new IllegalArgumentException(
					"Erro no pedido de ajuda presencial: " + quemSouEu + " nao pode ser vazio ou em branco");
	}

	/**
	 * Recupera uma descricao do Tutor da ajuda.
	 */
	public String getDescricaoTutor() {
		return "Tutor - " + super.getTutorMatricula() + ", horario - " + this.horario + ", dia - " + this.dia
				+ ", local - " + this.localInteresse + ", disciplina - " + super.getDisciplina();
	}

	/**
	 * Retorna uma informacao baseada no atributo que pode ser "tutor",
	 * "disciplina", "dia", "horario" ou "localinteresse". Caso nao seja nenhum
	 * desses, uma excecao eh lancada.
	 * 
	 * @param atributo
	 *            O atributo que corresponde a informacao a ser retornada.
	 * @return A informacao pedida.
	 */
	@Override
	public String getInfoAjuda(String atributo) {
		if (atributo == null || atributo.trim().equals(""))
			throw new IllegalArgumentException(
					"Erro ao tentar recuperar info da ajuda : atributo nao pode ser vazio ou em branco");
		switch (atributo.toLowerCase()) {
		case "tutor":
			return getTutorMatricula();
		case "disciplina":
			return getDisciplina();
		case "dia":
			return getDia();
		case "horario":
			return getHorario();
		case "localinteresse":
			return getLocalInteresse();
		default:
			throw new IllegalArgumentException("Erro ao tentar recuperar info da ajuda : atributo nao encontrado");
		}
	}
}