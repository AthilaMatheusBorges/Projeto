package principal;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Disciplina {

	private Map<String, Integer> disciplinas;

	public Disciplina() {
		this.disciplinas = new HashMap<>();
	}

	public void adicionarDisciplina(String disciplina, int proficiencia) {
		verificaProficiencia(proficiencia);
		verificaDisciplina(disciplina);
		this.disciplinas.put(disciplina, proficiencia);
	}
	
	public Set<String> getDisciplinas(){
		return this.disciplinas.keySet();
	}
	
	public boolean temDisciplina(String disciplina) {
		return this.disciplinas.containsKey(disciplina);
	}
	
	private boolean verificaDisciplina(String disciplina) {
		if(this.disciplinas.containsKey(disciplina))
			throw new IllegalArgumentException("Erro na definicao de papel: Ja eh tutor dessa disciplina");
		return false;
	}
	
	private void verificaProficiencia(int proficiencia) {
		if (proficiencia < 1 || proficiencia > 5)
			throw new IllegalArgumentException("Erro na definicao de papel: Proficiencia invalida");
	}
	
	public int recuperaProficiencia(String disciplina) {
		if(temDisciplina(disciplina))
			return this.disciplinas.get(disciplina);
		return -1;
	}
	

}
