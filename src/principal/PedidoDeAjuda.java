package principal;

public abstract class PedidoDeAjuda {

	private String disciplina, tutorMatricula, matrAluno;
	private int idAjuda;
	private boolean avaliado;

	/**
	 * Constroi o Pedido de Ajuda com os parametros passados. Inicialmente define o
	 * atributo 'avaliado' como false, indicando que a ajuda ainda nao foi avaliada.
	 * 
	 * @param disciplina
	 *            eh a disciplina da ajuda.
	 * @param tutorMatricula
	 *            eh a matricula do tutor responsavel pelo pedido de ajuda.
	 * @param idAjuda
	 *            eh o identificador do pedido de ajuda.
	 */
	public PedidoDeAjuda(String matrAluno, String disciplina, String tutorMatricula, int idAjuda) {
		this.matrAluno = matrAluno;
		this.disciplina = disciplina;
		this.idAjuda = idAjuda;
		this.tutorMatricula = tutorMatricula;
		this.avaliado = false;
	}

	public void avaliar() {
		this.avaliado = true;
	}
	
	/**
	 * Retorna a disciplina deste pedido de ajuda.
	 * 
	 * @return retorna a disciplina do pedido.
	 */
	public String getDisciplina() {
		return this.disciplina;
	}

	/**
	 * Retorna a matricula do tutor responsavel pelo pedido.
	 * 
	 * @return retorna a matricula do tudor.
	 */
	public String getTutorMatricula() {
		return this.tutorMatricula;
	}

	/**
	 * Retorna o identificador do pedido de ajuda.
	 * 
	 * @return retorna o id do pedido de ajuda.
	 */
	public int getIdAjuda() {
		return this.idAjuda;
	}

	
	public boolean tutorAvaliado() {
		return this.avaliado;
	}
	
	/**
	 * Descricao do tutor da ajuda.
	 * @return retorna uma descricao do tutor da ajuda.
	 */
	public abstract String getDescricaoTutor();

}