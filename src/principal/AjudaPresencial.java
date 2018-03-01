package principal;

public class AjudaPresencial implements PedidoDeAjuda {

	private String disciplina, tutorMatricula, dia, horario, localInteresse;
	private int idAjuda;
	private boolean avaliado;

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
	public AjudaPresencial(String disciplina, String tutorMatricula, String dia, String horario, String localInteresse,
			int idAjuda) {
		verificaString("Disciplina", disciplina);
		verificaString("Dia", dia);
		verificaString("Horario", horario);
		verificaString("Local", localInteresse);
		this.disciplina = disciplina;
		this.tutorMatricula = tutorMatricula;
		this.dia = dia;
		this.horario = horario;
		this.localInteresse = localInteresse;
		this.idAjuda = idAjuda;
		this.avaliado = false;
	}

	/**
	 * Retorna a disciplina deste pedido de ajuda.
	 * 
	 * @return retorna a disciplina do pedido.
	 */
	@Override
	public String getDisciplina() {
		return disciplina;
	}

	/**
	 * Retorna a matricula do tutor responsavel pelo pedido.
	 * 
	 * @return retorna a matricula do tudor.
	 */
	@Override
	public String getTutorMatricula() {
		return tutorMatricula;
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
	 * Retorna o identificador do pedido de ajuda.
	 * 
	 * @return retorna o id do pedido de ajuda.
	 */
	@Override
	public int getIdAjuda() {
		return idAjuda;
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
		if (stringTeste.trim().equals("") || stringTeste == null)
			throw new IllegalArgumentException(
					"Erro no pedido de ajuda online: " + quemSouEu + " nao pode ser vazio ou nulo");
	}

	/**
	 * Retorna se ja foi feita a avaliacao do tutor pela ajuda
	 * 
	 * @return retorna um booleano true caso ainda nao tenha sido avaliada a ajuda e
	 *         false caso ja tenha
	 */
	@Override
	public boolean avaliarTutor() {
		if (avaliado == true) {
			return false;
		}
		setAvaliado(true);
		return true;
	}

	/**
	 * Altera o valor booleano de avaliado
	 * 
	 * @param avaliacao
	 *            um booleano informando se ja foi alterado
	 */
	private void setAvaliado(boolean avaliacao) {
		this.avaliado = avaliacao;
	}
}