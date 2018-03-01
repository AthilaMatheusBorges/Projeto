package principal;

public class AjudaPresencial extends PedidoDeAjuda {

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
	public AjudaPresencial(String disciplina, String tutorMatricula, String dia, String horario, String localInteresse,
			int idAjuda) {
		super(disciplina, tutorMatricula, idAjuda);
		verificaString("Disciplina", disciplina);
		verificaString("Dia", dia);
		verificaString("Horario", horario);
		verificaString("Local", localInteresse);
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
		if (stringTeste.trim().equals("") || stringTeste == null)
			throw new IllegalArgumentException(
					"Erro no pedido de ajuda presencial: " + quemSouEu + " nao pode ser vazio ou nulo");
	}
}