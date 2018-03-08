package comparadores;

import principal.Aluno;

public class OrdenacaoPorEmail implements EstrategiaOrdenacao {

	@Override
	public int compare(Aluno umAluno, Aluno outroAluno) {
		if(umAluno.getEmail().equalsIgnoreCase(outroAluno.getEmail())) {
			return umAluno.getMatricula().compareTo(outroAluno.getMatricula());
		}
		return umAluno.getEmail().compareTo(outroAluno.getEmail());
	}
	
	

}
