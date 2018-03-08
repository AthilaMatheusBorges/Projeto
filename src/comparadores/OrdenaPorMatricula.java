package comparadores;

import principal.Aluno;

public class OrdenaPorMatricula implements EstrategiaOrdenacao {

	@Override
	public int compare(Aluno umAluno, Aluno outroAluno) {
		
		return umAluno.getMatricula().compareTo(outroAluno.getMatricula());
	}

}
