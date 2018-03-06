package principal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Disciplina {

	String disciplina;
	Map<String, Tutor> listaDeTutores;

	public Disciplina(String disciplina) {
		this.disciplina = disciplina;
		this.listaDeTutores = new HashMap<>();
	}

	public Map<String, Tutor> getListaDeTutores() {
		return this.listaDeTutores;
	}

	public Tutor getTutor(String matricula) {
		return this.listaDeTutores.get(matricula);
	}

	public void adicionarTutor(Tutor tutor) {
		this.listaDeTutores.put(tutor.getTutorMatricula(), tutor);
	}

	public String getDisciplina() {
		return this.disciplina;
	}

	public boolean temTutor(String matricula) {
		return this.listaDeTutores.containsKey(matricula);
	}

	public Tutor maiorProficiencia(String horario, String dia, String localInteresse) {
		Tutor possivelTutor = null;
		for (Tutor tutor : this.listaDeTutores.values()) {
			if (tutor.consultaLocal(localInteresse) && tutor.consultaHorario(horario, dia)) {
				if (possivelTutor == null)
					possivelTutor = tutor;
				else {
					if (tutor.getNota() > possivelTutor.getNota())
						possivelTutor = tutor;
					else if (tutor.getNota() == possivelTutor.getNota())
						if (tutor.getId() < possivelTutor.getId())
							possivelTutor = tutor;
				}
			}
		}
		return possivelTutor;
	}

	public Tutor maiorProficiencia() {
		Tutor possivelTutor = null;
		for (Tutor tutor : this.listaDeTutores.values()) {
			if (possivelTutor == null)
				possivelTutor = tutor;
			else {
				if (tutor.getNota() > possivelTutor.getNota())
					possivelTutor = tutor;
				else if (tutor.getNota() == possivelTutor.getNota())
					if (tutor.getId() < possivelTutor.getId())
						possivelTutor = tutor;
			}
		}
		return possivelTutor;
	}

	public String getDescricaoTutor(String matricula) {
		return "Tutor - " + matricula + ", disciplina- " + this.disciplina;
	}

}
