package aluno;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import aluno.Aluno;

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
	 * Testa se o metodo esta retornando a matricula corretamente.
	 */
	@Test
	public void testGetMatricula() {
		assertEquals("123", a1.getMatricula());
	}

	/**
	 * Testa se o metodo esta retornando o nome corretamente.
	 */
	@Test
	public void testGetNome() {
		assertEquals("Marcelo", a1.getNome());
	}

	/**
	 * Testa se o metodo esta retornando o numero de telefone corretamente.
	 */
	@Test
	public void testGetTelefone() {
		assertEquals("99999999", a1.getTelefone());
	}

	/**
	 * Testa se o metodo esta retornando uma String vazia caso o aluno nao tenha
	 * telefone.
	 */
	@Test
	public void testGetTelefoneVazio() {
		assertEquals("", a2.getTelefone());
	}

	/**
	 * Testa se o metodo esta retornando o email do aluno corretamente.
	 */
	@Test
	public void testGetEmail() {
		assertEquals("marcelobonito@bol.com", a1.getEmail());
	}

	/**
	 * Testa se o metodo esta retornando o codigo do curso de forma correta.
	 */
	@Test
	public void testGetCodigoCurso() {
		assertEquals(10, a1.getCodigoCurso());
	}

	/**
	 * Testa se o metodo esta retornando a nota corretamente.
	 */
	@Test
	public void testGetNota() {
		assertEquals(5, 0, a1.getNota());
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
	 * Testa se a representacao textual com numero esta sendo retornada
	 * corretamente.
	 */
	@Test
	public void testToStringComTelefone() {
		assertEquals("123 - Marcelo - 10 - 99999999 - marcelobonito@bol.com", a1.toString());
		assertEquals("321 - Flavio - 10 - flavinho@yahoo.com", a2.toString());

	}

	/**
	 * Testa se a representacao textual sem numero esta sendo retornada
	 * corretamente.
	 */
	@Test
	public void testToStringSemTelefone() {
		assertEquals("321 - Flavio - 10 - flavinho@yahoo.com", a2.toString());

	}

}
