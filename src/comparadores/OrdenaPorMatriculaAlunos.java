
package comparadores;

import java.util.Comparator;

import aluno.Aluno;

public class OrdenaPorMatriculaAlunos implements Comparator<Aluno> {

	/**
	 * Metodo que compara por matricula
	 */
	@Override
	public int compare(Aluno umAluno, Aluno outroAluno) {

		return umAluno.getMatricula().compareTo(outroAluno.getMatricula());
	}

}