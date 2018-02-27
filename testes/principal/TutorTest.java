package principal;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TutorTest {
	private Sistema sistema;
	private Aluno aluno;

	@Before
	public void inicializa() {
		sistema = new Sistema();
		aluno = new Aluno("Athila", "524896632", 12, "40028922", "athila@famail.com");
		sistema.cadastrarAluno("Athila", "524896632", 12, "40028922", "athila@famail.com");
	}

	@Test
	public void testTornarTutor() {
		sistema.tornarTutor("524896632", "LP2", 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTornarTutorMatriculaErrada() {
		sistema.tornarTutor("6546835252", "Matemática", 4);
	}

	
}
