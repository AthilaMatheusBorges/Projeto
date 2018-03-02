package principal;

import java.util.Comparator;

public class ComparatorDisciplina implements Comparator<Tutor> {

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