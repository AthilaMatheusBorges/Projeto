package pedidoAjuda;

import java.io.Serializable;

public abstract class PedidoDeAjuda implements Serializable{


	private static final long serialVersionUID = -4260731781338538338L;
	private String disciplina, tutorMatricula, matrAluno;
	private int idAjuda;
	private boolean avaliado;

	/**
	 * Constroi o Pedido de Ajuda com os parametros passados. Inicialmente define o
	 * atributo 'avaliado' como false, indicando que a ajuda ainda nao foi avaliada.
	 * 
	 * @param martAluno
	 *            eh a matricula do aluno do pedido de ajuda.
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

	/**
	 * Avalida o pedido de ajuda.
	 */
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

	/**
	 * Verifica se o pedido de Ajuda ja foi avaliado.
	 * 
	 * @return retorna true se a ajuda ja foi avaliada, false caso contrario.
	 */
	public boolean ajudaAvaliada() {
		return this.avaliado;
	}

	/**
	 * Descricao do tutor da ajuda.
	 * 
	 * @return retorna uma descricao do tutor da ajuda.
	 */
	public abstract String getDescricaoTutor();

	/**
	 * Retorna uma informacao baseada no atributo pedido.
	 * 
	 * @param atributo
	 *            O atributo que corresponde a informacao a ser retornada.
	 * @return A informacao pedida.
	 */
	public abstract String getInfoAjuda(String atributo);

}