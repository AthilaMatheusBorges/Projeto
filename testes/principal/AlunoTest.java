package principal;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AlunoTest {

	Aluno a1;
	Aluno a2;

	/**
	 * Inicializa os objetos que serao testados.
	 */
	@Before
	public void inicializa() {
		a1 = new Aluno("Marcelo", "123", 10, "99999999", "marcelobonito@bol.com");
		a2 = new Aluno("Flavio", "321", 10, " ", "flavinho@yahoo.com");
	}

	/**
	 * Testa se o metodo esta retornando a String correta, que representa o tipo.
	 */
	@Test
	public void testGetTipo() {
		assertEquals("aluno", a1.getTipo());
		assertEquals("aluno", a2.getTipo());
	}

	/**
	 * Testa se o metodo esta alterando a String corretamente.
	 */
	@Test
	public void testSetTipo() {
		a1.setTipo("tutor");
		assertEquals("tutor", a1.getTipo());
	}

	/**
	 * Testa se o metodo esta retornando a matricula corretamente.
	 */
	@Test
	public void testGetMatricula() {
		assertEquals("123", a1.getMatricula());
		assertEquals("321", a2.getMatricula());
	}

	/**
	 * Testa se o metodo esta retornando o nome corretamente.
	 */
	@Test
	public void testGetNome() {
		assertEquals("Marcelo", a1.getNome());
		assertEquals("Flavio", a2.getNome());
	}

	/**
	 * Testa se o metodo esta retornando o numero de telefone corretamente, mesmo
	 * quando estiver vazio.
	 */
	@Test
	public void testGetTelefone() {
		assertEquals("99999999", a1.getTelefone());
		assertEquals("", a2.getTelefone());
	}

	/**
	 * Testa se o metodo esta retornando o email do aluno corretamente.
	 */
	@Test
	public void testGetEmail() {
		assertEquals("marcelobonito@bol.com", a1.getEmail());
		assertEquals("flavinho@yahoo.com", a2.getEmail());
	}

	/**
	 * Testa se o metodo esta retornando o codigo do curso de forma correta.
	 */
	@Test
	public void testGetCodigoCurso() {
		assertEquals(10, a1.getCodigoCurso());
		assertEquals(10, a2.getCodigoCurso());
	}

	/**
	 * Testa se o metodo esta retornando a nota corretamente.
	 */
	@Test
	public void testGetNota() {
		assertEquals(5, 0, a1.getNota());
		assertEquals(5, 0, a2.getNota());
	}

	/**
	 * Testa se o metodo esta realmente alterando a nota.
	 */
	@Test
	public void testSetNota() {
		a1.setNota(4.3);
		assertEquals(4, 3, a1.getNota());
	}

	/**
	 * Testa se a representacao textual esta sendo retornada corretamente, mesmo
	 * quando o aluno nao possuir um numero de telefone.
	 */
	@Test
	public void testToString() {
		assertEquals("123 - Marcelo - 10 - 99999999 - marcelobonito@bol.com", a1.toString());
		assertEquals("321 - Flavio - 10 - flavinho@yahoo.com", a2.toString());

	}

}
