package principal;

public class AjudaOnline implements PedidoDeAjuda {

	private String disciplina, tutorMatricula;
	private int idAjuda;
	private boolean avaliado;

	/**
	 * Constroi o pedido de Ajuda Online com o parametros passados.
	 * 
	 * @param disciplina
	 *            eh a disciplina da ajuda.
	 * @param tutorMatricula
	 *            eh a matricula do tutor responsavel pelo pedido de ajuda.
	 * @param idAjuda
	 *            eh o identificador do pedido de ajuda.
	 */
	public AjudaOnline(String disciplina, String tutorMatricula, int idAjuda) {
		verificaDisciplina(disciplina);
		this.disciplina = disciplina;
		this.idAjuda = idAjuda;
		this.tutorMatricula = tutorMatricula;
		this.avaliado = false;
	}

	/**
	 * Retorna a disciplina deste pedido de ajuda.
	 * 
	 * @return retorna a disciplina do pedido.
	 */
	@Override
	public String getDisciplina() {
		return this.disciplina;
	}

	/**
	 * Retorna a matricula do tutor responsavel pelo pedido.
	 * 
	 * @return retorna a matricula do tudor.
	 */
	@Override
	public String getTutorMatricula() {
		return this.tutorMatricula;
	}

	/**
	 * Retorna o identificador do pedido de ajuda.
	 * 
	 * @return retorna o id do pedido de ajuda.
	 */
	@Override
	public int getIdAjuda() {
		return this.idAjuda;
	}

	/**
	 * Verifica se a disciplina passada como parametro para Ajuda Online eh valida.
	 * 
	 * @param disciplina
	 *            eh a disciplina da ajuda.
	 */
	private void verificaDisciplina(String disciplina) {
		if (disciplina.trim().equals("") || disciplina == null)
			throw new IllegalArgumentException("Erro no pedido de ajuda online: Disciplina nao pode ser vazio ou nulo");
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
		;
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