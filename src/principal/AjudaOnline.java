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
		if (stringTeste.trim().equals("") || stringTeste == null)
			throw new IllegalArgumentException(
					"Erro no pedido de ajuda online: " + quemSouEu + " nao pode ser vazio ou em branco");
	}

	/**
	 * Recupera uma descricao do Tutor da ajuda.
	 */
	public String getDescricaoTutor() {
		return "Tutor - " + super.getTutorMatricula() + ", disciplina- " + super.getDisciplina();
	}

}