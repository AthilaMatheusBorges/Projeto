package controladores;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import aluno.Aluno;
import controladores.ControllerTutor;
import tutoria.Tutor;

public class ControllerTutorTest {

	private ControllerTutor controlleTutorTest;
	Tutor tutor1 = new Tutor("12254", 1);
	Aluno alunoAleatorio1 = new Aluno("Psy", "12200", 001, "", "psyNelas@gmail.com");
	Aluno alunoAleatorio2 = new Aluno("Skypinho", "10000", 002, "", "sk@gmail.com");
	Tutor tutor2 = new Tutor("12274", 2);

	@Before
	public void testControllerTutor() {
		controlleTutorTest = new ControllerTutor();
		Tutor tutor3 = new Tutor("12275", 2);
		Tutor tutor4 = new Tutor("12276", 5);
		controlleTutorTest.adicionarTutor(tutor2);
		controlleTutorTest.adicionarTutor(tutor4);
		controlleTutorTest.adicionarTutor(tutor3);
		controlleTutorTest.tornarTutor("10000", "Escapada", 4);
	}

	@Test
	public void testAdicionarTutor() {
		controlleTutorTest.adicionarTutor(tutor1);
	}

	@Test
	public void testTornarTutor() {
		controlleTutorTest.tornarTutor("12200", "rei delas", 4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTornarTutorJaEhTutor() {
		controlleTutorTest.tornarTutor("10000", "Escapada", 4);
	}

	@Test
	public void testGetTutor() {
		assertEquals(tutor2, controlleTutorTest.getTutor("12274"));
	}

	@Test
	public void testGetTutorInexistente() {
		assertEquals(null, controlleTutorTest.getTutor("125"));
	}

	@Test
	public void testTemTutor() {
		assertTrue(controlleTutorTest.temTutor("10000"));
	}

	@Test
	public void testNaoTemTutor() {
		assertFalse(controlleTutorTest.temTutor("10005"));
	}

	@Test
	public void testVerificaTutor() {
		controlleTutorTest.verificaTutor("10000");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testVerificaTutorInexistente() {
		controlleTutorTest.verificaTutor("14000");
	}

	@Test
	public void testAvaliarTutor() {
		assertEquals("Tutor avaliado", controlleTutorTest.avaliarTutor("10000", 7));
	}

	@Test
	public void testGetTaxaTutor() {
		assertEquals(0.8, controlleTutorTest.getTaxaTutor("10000"), 0.01);

	}

	@Test
	public void testReceberDoacao() {
		controlleTutorTest.receberDoacao("10000", 8000);
	}

}
