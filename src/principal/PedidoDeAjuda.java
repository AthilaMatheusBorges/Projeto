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

}