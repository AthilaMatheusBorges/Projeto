package principal;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AlunoTest {

	Aluno a1;
	Aluno a2;
	Sistema sistema;

	@Before
	public void inicializa() {
		sistema = new Sistema();
		a1 = new Aluno("Marcelo", "123", 10, "99999999", "marcelobonito@bol.com");
		a2 = new Aluno("Flavio", "321", 10, " ", "flavinho@yahoo.com");
	}

	@Test
	public void testGetTipo() {
		assertEquals("aluno", a1.getTipo());
		assertEquals("aluno", a2.getTipo());
	}

	@Test
	public void testSetTipo() {
		a1.setTipo("tutor");
		assertEquals("tutor", a1.getTipo());
	}

	@Test
	public void testGetMatricula() {
		assertEquals("123", a1.getMatricula());
		assertEquals("321", a2.getMatricula());
	}

	@Test
	public void testGetNome() {
		assertEquals("Marcelo", a1.getNome());
		assertEquals("Flavio", a2.getNome());
	}

	@Test
	public void testGetTelefone() {
		assertEquals("99999999", a1.getTelefone());
		assertEquals("", a2.getTelefone());
	}

	@Test
	public void testGetEmail() {
		assertEquals("marcelobonito@bol.com", a1.getEmail());
		assertEquals("flavinho@yahoo.com", a2.getEmail());
	}

	@Test
	public void testGetCodigoCurso() {
		assertEquals(10, a1.getCodigoCurso());
		assertEquals(10, a2.getCodigoCurso());
	}

	@Test
	public void testGetNota() {
		assertEquals(5, 0, a1.getNota());
		assertEquals(5, 0, a2.getNota());
	}

	@Test
	public void testSetNota() {
		a1.setNota(4.3);
		assertEquals(4, 3, a1.getNota());
	}

	@Test
	public void testToString() {
		assertEquals("123 - Marcelo - 10 - 99999999 - marcelobonito@bol.com", a1.toString());
		assertEquals("321 - Flavio - 10 - flavinho@yahoo.com", a2.toString());

	}

}
