package principal;

public class AjudaOnline extends PedidoDeAjuda {

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
		super(disciplina, tutorMatricula, idAjuda);
		verificaDisciplina(disciplina);
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

}