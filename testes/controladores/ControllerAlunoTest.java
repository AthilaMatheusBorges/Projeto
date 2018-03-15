package controladores;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controladores.ControllerAluno;

public class ControllerAlunoTest {

	private ControllerAluno aluno;

	/**
	 * Inicia atributos que serao utilizados para os testes.
	 */
	@Before
	public void inicia() {
		this.aluno = new ControllerAluno();
		aluno.adicionaAluno("marcelo", "117", 20, "44", "marcelo@bol.com");
		aluno.adicionaAluno("hawking", "2018", 20, "", "riphawking@bol.com");
	}

	/**
	 * Testa se eh possivel adicionar alunos.
	 */
	@Test
	public void testAdicionaAluno() {
		aluno.adicionaAluno("flavinho", "118", 20, "20", "flavinho@bol.com");
	}

	/**
	 * Testa se o metodo esta recuperando a descricao do aluno corretamente.
	 */
	@Test
	public void testRecuperaAluno() {
		assertEquals("117 - marcelo - 20 - 44 - marcelo@bol.com", aluno.recuperaAluno("117"));
	}

	/**
	 * Testa se o metodo esta recuperando a descricao por email.
	 */
	@Test
	public void testGetAlunoPorEmail() {
		assertEquals("117 - marcelo - 20 - 44 - marcelo@bol.com", aluno.getAlunoPorEmail("marcelo@bol.com").toString());
	}

	/**
	 * Testa se o metodo esta recuperando a descricao por matricula.
	 */
	@Test
	public void testGetAlunoPorMatricula() {
		assertEquals("117 - marcelo - 20 - 44 - marcelo@bol.com", aluno.getAlunoPorMatricula("117").toString());
	}

	/**
	 * Testa se o metodo esta retornando a lista de alunos corretamente.
	 */
	@Test
	public void testListarAlunos() {
		assertEquals("2018 - hawking - 20 - riphawking@bol.com, 117 - marcelo - 20 - 44 - marcelo@bol.com",
				aluno.listarAlunos());
	}

	/**
	 * Testa se o metodo esta retornando a informacao do aluno corretamente.
	 */
	@Test
	public void testGetInfoAlunoNome() {
		assertEquals("marcelo", aluno.getInfoAluno("117", "nome"));
	}

	/**
	 * Testa se o metodo esta retornando a informacao do aluno corretamente.
	 */
	@Test
	public void testGetInfoAlunoTelefone() {
		assertEquals("44", aluno.getInfoAluno("117", "telefone"));
	}

	/**
	 * Testa se o metodo esta retornando a informacao do aluno corretamente.
	 */
	@Test
	public void testGetInfoAlunoEmail() {
		assertEquals("marcelo@bol.com", aluno.getInfoAluno("117", "email"));
	}

	/**
	 * Testa se uma excecao eh lancada caso seja passado um atributo invalido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetInfoAlunoExcecao() {
		aluno.getInfoAluno("117", "nada");
	}

	/**
	 * Testa se o metodo esta retornando o boolean correto.
	 */
	@Test
	public void testMatriculaCadastrada() {
		assertTrue(aluno.matriculaCadastrada("117"));
	}

	/**
	 * Testa se o metodo esta retornando o boolean correto.
	 */
	@Test
	public void testMatriculaNaoCadastrada() {
		assertFalse(aluno.matriculaCadastrada("566660"));
	}

}
