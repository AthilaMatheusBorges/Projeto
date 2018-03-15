package tutoria;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Disciplina implements Serializable{

	private static final long serialVersionUID = -4727746398973325902L;
	private Map<String, Integer> disciplinas;

	public Disciplina() {
		this.disciplinas = new HashMap<>();
	}

	/**
	 * Adiciona uma disciplina e a proficiencia ao mapa.
	 * @param disciplina eh a disciplina.
	 * @param proficiencia eh a proficiencia.
	 */
	public void adicionarDisciplina(String disciplina, int proficiencia) {
		verificaProficiencia(proficiencia);
		verificaDisciplina(disciplina);
		this.disciplinas.put(disciplina, proficiencia);
	}
	
	/**
	 * Retorna um set com as disciplinas.
	 * @return retorna um set com as disciplinas.
	 */
	public Set<String> getDisciplinas(){
		return this.disciplinas.keySet();
	}
	
	/**
	 * Verifica se a lista de disciplinas tem determinada disciplina.
	 * @param disciplina eh a disciplina ser verificada.
	 * @return retorna true se a disciplina estiver na lista, false caso contrario.
	 */
	public boolean temDisciplina(String disciplina) {
		return this.disciplinas.containsKey(disciplina);
	}
	
	/**
	 * Valida a disciplina nova.
	 * @param disciplina eh a disciplina a ser validada.
	 * @return retorna uma excecao se a disciplina estiver na lista, false caso contrairo.
	 */
	private boolean verificaDisciplina(String disciplina) {
		if(this.disciplinas.containsKey(disciplina))
			throw new IllegalArgumentException("Erro na definicao de papel: Ja eh tutor dessa disciplina");
		return false;
	}
	
	/**
	 * Valida a proficiencia.
	 * @param proficiencia eh a proficiencia a ser validada.
	 */
	private void verificaProficiencia(int proficiencia) {
		if (proficiencia < 1 || proficiencia > 5)
			throw new IllegalArgumentException("Erro na definicao de papel: Proficiencia invalida");
	}
	
	/**
	 * Recupera a proficiencia.
	 * @param disciplina eh a disciplina.
	 * @return retorna a proficiencia associada a disciplina.
	 */
	public int recuperaProficiencia(String disciplina) {
		if(temDisciplina(disciplina))
			return this.disciplinas.get(disciplina);
		throw new IllegalArgumentException("Erro ao tentar recuperar proficiencia: Disciplina nao encontrada");
	}
	

}
