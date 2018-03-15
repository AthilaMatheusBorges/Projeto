package comparadores;

import java.util.Comparator;

import tutoria.Tutor;

public class ComparatorMaiorProficiecia implements Comparator<Tutor> {

	/**
	 * Compara tutores pela nota. Se as notas forem iguais, usa a ordem de cadastro.
	 */
	@Override
	public int compare(Tutor tutor, Tutor outroTutor) {
		if (tutor.getNota() > outroTutor.getNota())
			return -1;
		else if (tutor.getNota() == outroTutor.getNota()) {
			if (tutor.getId() < outroTutor.getId())
				return -1;
			return 1;
		}
		return 1;
	}

}
