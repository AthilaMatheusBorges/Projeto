package principal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Disciplina {

	Map<String, Integer> disciplinas;

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
	
	public boolean verificaDisciplina(String disciplina) {
		if(this.disciplinas.containsKey(disciplina))
			throw new IllegalArgumentException("Erro na definicao de papel: Ja eh tutor dessa disciplina");
		return false;
	}
	
	public void verificaProficiencia(int proficiencia) {
		if (proficiencia < 1 || proficiencia > 5)
			throw new IllegalArgumentException("Erro na definicao de papel: Proficiencia invalida");
	}
	
	public int recuperaProficiencia(String disciplina) {
		if(temDisciplina(disciplina))
			return this.disciplinas.get(disciplina);
		return -1;
	}
	
	
	
	
//	public Tutor maiorProficiencia(String horario, String dia, String localInteresse) {
//		Tutor possivelTutor = null;
//		for (Tutor tutor : this.disciplinas.values()) {
//			if (tutor.consultaLocal(localInteresse) && tutor.consultaHorario(horario, dia)) {
//				if (possivelTutor == null)
//					possivelTutor = tutor;
//				else {
//					if (tutor.getNota() > possivelTutor.getNota())
//						possivelTutor = tutor;
//					else if (tutor.getNota() == possivelTutor.getNota())
//						if (tutor.getId() < possivelTutor.getId())
//							possivelTutor = tutor;
//				}
//			}
//		}
//		return possivelTutor;
//	}
//
//	public Tutor maiorProficiencia() {
//		Tutor possivelTutor = null;
//		for (Tutor tutor : this.disciplinas.values()) {
//			if (possivelTutor == null)
//				possivelTutor = tutor;
//			else {
//				if (tutor.getNota() > possivelTutor.getNota())
//					possivelTutor = tutor;
//				else if (tutor.getNota() == possivelTutor.getNota())
//					if (tutor.getId() < possivelTutor.getId())
//						possivelTutor = tutor;
//			}
//		}
//		return possivelTutor;
//	}
}
