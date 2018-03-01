package principal;

public interface PedidoDeAjuda {

	/**
	 * Retorna a disciplina deste pedido de ajuda.
	 * 
	 * @return retorna a disciplina do pedido.
	 */
	public String getDisciplina();

	/**
	 * Retorna a matricula do tutor responsavel pelo pedido.
	 * 
	 * @return retorna a matricula do tudor.
	 */
	public String getTutorMatricula();

	/**
	 * Retorna o identificador do pedido de ajuda.
	 * 
	 * @return retorna o id do pedido de ajuda.
	 */
	public int getIdAjuda();

	/**
	 * Retorna se ja foi feita a avaliacao do tutor pela ajuda
	 * 
	 * @return retorna um booleano true caso ainda nao tenha sido avaliada a ajuda e
	 *         false caso ja tenha
	 */
	public boolean avaliarTutor();

}