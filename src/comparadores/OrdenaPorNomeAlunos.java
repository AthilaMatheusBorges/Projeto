package comparadores;

import java.util.Comparator;

import aluno.Aluno;

public class OrdenaPorNomeAlunos implements Comparator<Aluno> {

	/**
	 * Metodo que compara por nome, mas se o nome for igual comparara por matricula
	 */
	@Override
	public int compare(Aluno umAluno, Aluno outroAluno) {
		if (umAluno.getNome().equalsIgnoreCase(outroAluno.getNome())) {
			return umAluno.getMatricula().compareTo(outroAluno.getMatricula());
		}
		return umAluno.getNome().compareTo(outroAluno.getNome());
	}

}