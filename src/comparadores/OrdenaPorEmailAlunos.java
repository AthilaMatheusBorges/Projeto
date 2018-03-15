
package comparadores;

import aluno.Aluno;

public class OrdenaPorEmailAlunos implements EstrategiaOrdenacaoAlunos {

	/**
	 * Metodo que compara aluno por email e se a matricula for a mesma compara por
	 * matricula
	 */
	@Override
	public int compare(Aluno umAluno, Aluno outroAluno) {
		if (umAluno.getEmail().equalsIgnoreCase(outroAluno.getEmail())) {
			return umAluno.getMatricula().compareTo(outroAluno.getMatricula());
		}
		return umAluno.getEmail().compareTo(outroAluno.getEmail());
	}

}