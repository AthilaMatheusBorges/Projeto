package principal;

public class Aluno implements Comparable<Aluno> {
	private String matricula, nome, telefone, email;
	private int codigoCurso;
	private double nota;
	private String tipo;

	/**
	 * Constroi o aluno baseado nos seguintes parametros:
	 * 
	 * @param nome
	 *            Uma String representando o nome do aluno.
	 * @param matricula
	 *            A matricula, representada por uma String.
	 * @param codigoCurso
	 *            O codigo do curso tambem representado por um inteiro.
	 * @param telefone
	 *            Uma String representando o telefone do aluno.
	 * @param email
	 *            Uma String representando o email do aluno.
	 */
	public Aluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		this.nome = nome;
		this.matricula = matricula;
		this.codigoCurso = codigoCurso;
		this.telefone = telefone.trim();
		this.email = email;
		this.tipo = "aluno";
		this.nota = 5;
	}

	/**
	 * Retorna a String que representa o tipo do aluno (Se ele eh tutor ou somente
	 * aluno).
	 * 
	 * @return A String representante do tipo.
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Atualiza a String que representa o tipo do aluno, caso ele se torne tutor.
	 * 
	 * @param tipo
	 *            A nova String representando o tipo.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Retorna a String que representa a matricula do aluno.
	 * 
	 * @return A matricula do aluno.
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Retorna a String que representa o nome do aluno.
	 * 
	 * @return O nome do aluno.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Retorna a String que representa o telefone do aluno.
	 * 
	 * @return O telefone.
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * Retorna a String que representa o email do aluno.
	 * 
	 * @return O email do aluno.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Retorna o inteiro que representa o codigo do curso do aluno.
	 * 
	 * @return O codigo do curso.
	 */
	public int getCodigoCurso() {
		return codigoCurso;
	}

	/**
	 * Retorna um double que representa a nota de qualificacao do aluno.
	 * 
	 * @return A nota.
	 */
	public double getNota() {
		return nota;
	}

	/**
	 * Atualiza a nota do aluno.
	 * 
	 * @param nota
	 *            A nova nota.
	 */
	public void setNota(double nota) {
		this.nota = nota;
	}

	/**
	 * Retorna uma representacao textual do aluno, contendo sua matricula, seu nome,
	 * o codigo do seu curso, o email e caso o aluno tenha, o numero de telefone.
	 * 
	 * @return A representacao textual em formato de String.
	 */
	public String toString() {
		if (getTelefone().equals("")) {
			return getMatricula() + " - " + getNome() + " - " + getCodigoCurso() + " - " + getEmail();
		}
		return getMatricula() + " - " + getNome() + " - " + getCodigoCurso() + " - " + getTelefone() + " - "
				+ getEmail();
	}

	/**
	 * Metodo responsavel por comparar dois alunos.
	 * 
	 * @param outroAluno
	 *            O outro aluno que vai ser comparado.
	 */
	@Override
	public int compareTo(Aluno outroAluno) {
		return getNome().compareTo(outroAluno.getNome());
	}

}
