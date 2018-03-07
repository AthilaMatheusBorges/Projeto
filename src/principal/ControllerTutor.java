package principal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerTutor {

private Map<String, Tutor> listaDeTutores;

	
	public ControllerTutor() {
		this.listaDeTutores= new HashMap<>();
	}
	
	private Tutor criarTutor(String matricula, int proficiencia) {
		return new Tutor(matricula, this.listaDeTutores.size());
	}
	
	public void adicionarTutor(Tutor tutor) {
		this.listaDeTutores.put(tutor.getTutorMatricula(), tutor);
	} 
	
	
	public void tornarTutor(String matricula, String disciplina, int proficiencia) {
		if(temTutor(matricula))
			getTutor(matricula).adicionarDisciplina(disciplina, proficiencia);
		else {
			Tutor tutor = criarTutor(matricula, this.listaDeTutores.size());
			tutor.adicionarDisciplina(disciplina, proficiencia);
			adicionarTutor(tutor);
		}
	}
	
	public Tutor getTutor(String matricula) {
		if(temTutor(matricula))
			return this.listaDeTutores.get(matricula);
		return null;
	}
	
	public boolean temTutor(String matricula) {
		if(this.listaDeTutores.containsKey(matricula))
			return true;
		return false;
	}
	
	public void verificaTutor(String matricula) {	
		if(!this.listaDeTutores.containsKey(matricula))
			throw new IllegalArgumentException("Erro na busca por tutor: Tutor nao encontrado");
	}
	
	public ArrayList<String> listarTutores() {
		ArrayList<String> saida = new ArrayList<>();
		for (String matricula : this.listaDeTutores.keySet()) {
			if (temTutor(matricula)) {
				saida.add(matricula);
			}
		}
		return saida;
	}
	
	public Tutor maiorProficiencia(String disciplina) {
		ArrayList<Tutor> tutoresDisponiveis = new ArrayList<>();
		for(Tutor tutor : this.listaDeTutores.values()) {
			if(tutor.temDisciplina(disciplina))
				tutoresDisponiveis.add(tutor);
		}
		Collections.sort(tutoresDisponiveis, new ComparatorMaiorProficiecia());
		return tutoresDisponiveis.get(0);
	}

	public Tutor maiorProficiencia(String disciplina, String horario, String dia, String localInteresse) {
		ArrayList<Tutor> tutoresDisponiveis = new ArrayList<>();
		for(Tutor tutor : this.listaDeTutores.values()) {
			if(tutor.temDisciplina(disciplina) && tutor.consultaHorario(horario, dia) && tutor.consultaLocal(localInteresse))
				tutoresDisponiveis.add(tutor);
		}
		Collections.sort(tutoresDisponiveis, new ComparatorMaiorProficiecia());
		return tutoresDisponiveis.get(0);
	}
	
	public String avaliarTutor(String matricula, int nota) {
		getTutor(matricula).avaliarTutor(nota);
		return "Tutor avaliado";
	}
	
	public void receberDoacao(String matricula, int valor) {
		getTutor(matricula).receberDoacao(valor);
	}
}
