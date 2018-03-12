
package comparadores;

import principal.Aluno;

public class OrdenaPorMatriculaAlunos implements EstrategiaOrdenacaoAlunos {

	@Override
	public int compare(Aluno umAluno, Aluno outroAluno) {
		
		return umAluno.getMatricula().compareTo(outroAluno.getMatricula());
	}

}