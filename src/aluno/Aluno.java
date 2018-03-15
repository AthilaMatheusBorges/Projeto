package aluno;

import java.io.Serializable;

public class Aluno implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3847073061835887184L;
	private String matricula, nome, telefone, email;
	private int codigoCurso;
	private double nota;

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
		this.nota = 5;
	}
	
	/**
	 * HashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}
	
	/**
	 * Equals com base na matricula
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
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



}
