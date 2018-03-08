package comparadores;

import java.util.Comparator;

import principal.Aluno;

public interface EstrategiaOrdenacao extends Comparator<Aluno>{
	
	public int compare(Aluno umAluno, Aluno outroAluno);
	
}
