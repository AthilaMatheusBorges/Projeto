package comparadores;

import java.util.Comparator;

import aluno.Aluno;

public interface EstrategiaOrdenacaoAlunos extends Comparator<Aluno> {

	/**
	 * Metodo abstrato de compare
	 */
	public int compare(Aluno umAluno, Aluno outroAluno);

}