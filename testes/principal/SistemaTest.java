package principal;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SistemaTest {

	Sistema sistema;
	Aluno a1;
	
	/**
	 * Inicializa alguns recursos que serao utilizados mais a frente.
	 */
	@Before
	public void inicializa() {
		sistema = new Sistema();
		sistema.cadastrarAluno("Davi", "1234", 20, "", "davi@daqui.com");
		sistema.cadastrarAluno("Yuri", "2323", 23, "40028922", "somdojapones@sbt.com");
		sistema.tornarTutor("1234", "TV", 2);
		sistema.cadastrarHorario("davi@daqui.com", "10:00", "seg");
		sistema.cadastrarLocalDeAtendimento("davi@daqui.com", "UFCG");
	}

	/**
	 * Testa se o recuperaAluno retorna a informacao correta.
	 */
	@Test
	public void testRecuperaAluno() {
		assertEquals(sistema.recuperaAluno("1234"), "1234 - Davi - 20 - davi@daqui.com");
	}
	
	/**
	 * Testa recuperaAluno com matricula invalida. Espera uma excecao.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRecuperaAlunoInvalido() {
		sistema.recuperaAluno("134");
	}

	/**
	 * Testa recuperaAluno com mtricula null. Espera uma excecao.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRecuperaAlunoNull() {
		sistema.recuperaAluno(null);
	}
	
	/**
	 * Testa se listarAluno retorna a informacao correta.
	 */
	@Test
	public void testListarAlunos() {
		assertEquals(sistema.listarAlunos(), "1234 - Davi - 20 - davi@daqui.com, 2323 - Yuri - 23 - 40028922 - somdojapones@sbt.com");
	}

	/**
	 * Testa obter a informacao do aluno.
	 */
	@Test
	public void testGetInfoAlunoNomeValido() {
		assertEquals(sistema.getInfoAluno("1234", "nome"), "Davi");
	}
	

	/**
	 * Testa obter a informacao do aluno. Espera uma excecao.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testGetInfoAlunoMatriculaVazia() {
		sistema.getInfoAluno("", "nome");
	}
	

	/**
	 * Testa obter a informacao do aluno. Espera uma excecao.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testGetInfoAlunoMatriculaNull() {
		sistema.getInfoAluno(null, "nome");
	}
	

	/**
	 * Testa obter a informacao do aluno. Espera uma excecao.
	 */
	@Test (expected = NullPointerException.class)
	public void testGetInfoAlunoNomeNull() {
		sistema.getInfoAluno("1234", null);
	}
	

	/**
	 * Testa obter a informacao do aluno.
	 */
	@Test
	public void testGetInfoAlunoTelefoneValido() {
		assertEquals(sistema.getInfoAluno("2323", "telefone"), "40028922");
	}
	

	/**
	 * Testa obter a informacao do aluno. Espera uma excecao.
	 */
	@Test (expected = NullPointerException.class)
	public void testGetInfoAlunoTelefoneNull() {
		sistema.getInfoAluno("1234", null);
	}
	

	/**
	 * Testa obter a informacao do aluno.
	 */
	@Test 
	public void testGetInfoAlunoEmail() {
		assertEquals(sistema.getInfoAluno("2323", "email"), "somdojapones@sbt.com");
	}

	/**
	 * Testa tornar tutor.
	 */
	@Test
	public void testTornarTutorValido() {
		sistema.tornarTutor("2323", "LP2", 5);
	}
	
	/**
	 * Testa tornar tutor com proficiencia menor que o limite.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTornarTutorProficienciaMenor() {
		sistema.tornarTutor("2323", "LP2", 0);
	}
	
	/**
	 * Testa tornar tutor com proficiencia maior que o limite.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTornarTutorProficienciaMaior() {
		sistema.tornarTutor("2323", "LP2", 6);
	}

	/**
	 * Testa tornar tutor de uma mesma disciplina.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTornarTutorMesmaDisciplina() {
		sistema.tornarTutor("2323", "LP2", 5);
		sistema.tornarTutor("2323", "LP2", 4);
	}
	
	/**
	 * Testa recupera tutor.
	 */
	@Test
	public void testRecuperaTutor() {
		assertEquals(sistema.recuperaTutor("1234"), "1234 - Davi - 20 - davi@daqui.com");
	}
	
	/**
	 * Testa recupera tutor com matricula nao cadastrada.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testRecuperaTutorInvalido() {
		sistema.recuperaTutor("134");
	}
	
	/**
	 * Testa recupera tutor com matricula null.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testRecuperaTutorNull() {
		sistema.recuperaTutor(null);
	}

	/**
	 * Testa listar tutores.
	 */
	@Test
	public void testListarTutores() {
		assertEquals(sistema.listarTutores(), "1234 - Davi - 20 - davi@daqui.com");
	}

	/**
	 * Testa recupera tutor por email.
	 */
	@Test
	public void testRecuperaTutorPorEmail() {
		assertEquals(sistema.recuperaTutorPorEmail("davi@daqui.com").toString(), "1234 - Davi - 20 - davi@daqui.com");
	}
	
	/**
	 * Testa cadastrar horario de atendimento invalido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastrarHorarioInvalido() {
		sistema.cadastrarHorario("somdojapones@sbt.com", "", "");
	}

	/**
	 * Testa cadastrar local de atendimento invalido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCadastrarLocalDeAtendimentoInvalido() {
		sistema.cadastrarLocalDeAtendimento("davi@daqui.com", "");
	}

	/**
	 * Testa consulta horario de atendimento para horario valido.
	 */
	@Test
	public void testConsultaHorario() {
		assertEquals(sistema.consultaHorario("davi@daqui.com", "10:00", "seg"), true);
	}

	/**
	 * Testa consulta local de atendimento para local valido.
	 */
	@Test
	public void testConsultaLocal() {
		assertEquals(sistema.consultaLocal("davi@daqui.com", "UFCG"), true);
	}
	
	/**
	 * Testa consulta horario de atendimento para horario nao cadastrado.
	 */
	@Test
	public void testConsultaHorarioInvalido() {
		assertEquals(sistema.consultaHorario("davi@daqui.com", "", "seg"), false);
	}
	
	/**
	 * testa consulta local de atendimento para local invalido.
	 */
	@Test
	public void testConsultaLocalInvalido() {
		assertEquals(sistema.consultaLocal("davi@daqui.com", ""), false);
	}


}