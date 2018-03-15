package pedidoAjuda;

public class AjudaOnline extends PedidoDeAjuda {

	private static final long serialVersionUID = -2665583070988638093L;

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
	public AjudaOnline(String matrAluno, String disciplina, String tutorMatricula, int idAjuda) {
		super(matrAluno, disciplina, tutorMatricula, idAjuda);
		verificaString("matricula de aluno", matrAluno);
		verificaString("disciplina", disciplina);
	}

	/**
	 * Verifica se a String dos parametros passados para Ajuda Online eh valida.
	 * 
	 * @param quemSouEu
	 *            eh quem esta sendo verficado.
	 * @param stringTeste
	 *            eh a String a ser verificada.
	 */
	private void verificaString(String quemSouEu, String stringTeste) {
		if (stringTeste == null || stringTeste.trim().equals("") )
			throw new IllegalArgumentException(
					"Erro no pedido de ajuda online: " + quemSouEu + " nao pode ser vazio ou em branco");
	}

	/**
	 * Recupera uma descricao do Tutor da ajuda.
	 */
	public String getDescricaoTutor() {
		return "Tutor - " + super.getTutorMatricula() + ", disciplina - " + super.getDisciplina();
	}

	/**
	 * Retorna uma informacao baseada no atributo que pode ser "tutor" ou
	 * "disciplina". Caso nao seja nenhum desses, uma excecao eh lancada.
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
		default:
			throw new IllegalArgumentException("Erro ao tentar recuperar info da ajuda : atributo invalido");
		}
	}

}