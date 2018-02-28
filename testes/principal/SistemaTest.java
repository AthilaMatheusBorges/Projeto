package principal;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SistemaTest {

	Sistema sistema;
	Aluno a1;

	/**
	 * Inicializa alguns recursos que serao utilizados para os testes seguintes.
	 */
	@Before
	public void inicializa() {
		sistema = new Sistema();
		sistema.cadastrarAluno("Davi", "1234", 20, "", "davi@daqui.com");
		sistema.cadastrarAluno("Yuri", "2323", 23, "40028922", "somdojapones@sbt.com");
		sistema.tornarTutor("1234", "TV", 2);
		sistema.tornarTutor("2323", "P3", 3);
		sistema.cadastrarHorario("davi@daqui.com", "10:00", "seg");
		sistema.cadastrarHorario("somdojapones@sbt.com", "14:00", "qua");
		sistema.cadastrarLocalDeAtendimento("davi@daqui.com", "UFCG");
		sistema.cadastrarLocalDeAtendimento("somdojapones@sbt.com", "CAA");
		sistema.pedirAjudaPresencial("P3", "14:00", "qua", "CAA");
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
		assertEquals(sistema.listarAlunos(),
				"1234 - Davi - 20 - davi@daqui.com, 2323 - Yuri - 23 - 40028922 - somdojapones@sbt.com");
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
	@Test(expected = IllegalArgumentException.class)
	public void testGetInfoAlunoMatriculaVazia() {
		sistema.getInfoAluno("", "nome");
	}

	/**
	 * Testa obter a informacao do aluno. Espera uma excecao.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetInfoAlunoMatriculaNull() {
		sistema.getInfoAluno(null, "nome");
	}

	/**
	 * Testa obter a informacao do aluno. Espera uma excecao.
	 */
	@Test(expected = NullPointerException.class)
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
	@Test(expected = NullPointerException.class)
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
	@Test(expected = IllegalArgumentException.class)
	public void testRecuperaTutorInvalido() {
		sistema.recuperaTutor("134");
	}

	/**
	 * Testa recupera tutor com matricula null.
	 */
	@Test(expected = IllegalArgumentException.class)
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
	 * Testa consulta local de atendimento para local invalido.
	 */
	@Test
	public void testConsultaLocalInvalido() {
		assertEquals(sistema.consultaLocal("davi@daqui.com", ""), false);
	}

	/**
	 * Testa se o cadastro do pedido de ajuda esta retornando o ID corretamente.
	 */
	@Test
	public void testPedirAjudaPresencialTudoValido() {
		assertEquals(2, sistema.pedirAjudaPresencial("TV", "10:00", "seg", "UFCG"));
	}

	/**
	 * Testa se uma excecao eh lancada caso o parametro "disciplina" esteja vazio.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaPresencialDisciplinaVazia() {
		sistema.pedirAjudaPresencial("", "10:00", "seg", "UFCG");
	}

	/**
	 * Testa se uma excecao eh lancada caso o parametro "disciplina" esteja nulo.
	 */
	@Test(expected = NullPointerException.class)
	public void testPedirAjudaPresencialDisciplinaNula() {
		sistema.pedirAjudaPresencial(null, "10:00", "seg", "UFCG");
	}

	/**
	 * Testa se uma excecao eh lancada caso o parametro "disciplina" passe uma
	 * disciplina invalida.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaPresencialDisciplinaInvalida() {
		sistema.pedirAjudaPresencial("OAC", "10:00", "seg", "UFCG");
	}

	/**
	 * Testa se uma excecao eh lancada caso o parametro "horario" esteja vazio.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaPresencialHorarioVazio() {
		sistema.pedirAjudaPresencial("TV", "", "seg", "UFCG");
	}

	/**
	 * Testa se uma excecao eh lancada caso o parametro "horario" esteja nulo.
	 */
	@Test(expected = NullPointerException.class)
	public void testPedirAjudaPresencialHorarioNulo() {
		sistema.pedirAjudaPresencial("TV", null, "seg", "UFCG");
	}

	/**
	 * Testa se uma excecao eh lancada caso o parametro "horario" passe um horario
	 * invalido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaPresencialHorarioInvalido() {
		sistema.pedirAjudaPresencial("TV", "12:00", "seg", "UFCG");
	}

	/**
	 * Testa se uma excecao eh lancada caso o parametro "dia" esteja vazio.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaPresencialDiaVazio() {
		sistema.pedirAjudaPresencial("TV", "10:00", "", "UFCG");
	}

	/**
	 * Testa se uma excecao eh lancada caso o parametro "dia" esteja nulo.
	 */
	@Test(expected = NullPointerException.class)
	public void testPedirAjudaPresencialDiaNulo() {
		sistema.pedirAjudaPresencial("TV", "10:00", null, "UFCG");
	}

	/**
	 * Testa se uma excecao eh lancada caso o parametro "dia" passe um dia invalido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaPresencialDiaInvalido() {
		sistema.pedirAjudaPresencial("TV", "10:00", "ter", "UFCG");
	}

	/**
	 * Testa se uma excecao eh lancada caso o parametro "localInteresse" esteja
	 * vazio.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaPresencialLocalVazio() {
		sistema.pedirAjudaPresencial("TV", "10:00", "seg", "");
	}

	/**
	 * Testa se uma excecao eh lancada caso o parametro "localInteresse" esteja
	 * nulo.
	 */
	@Test(expected = NullPointerException.class)
	public void testPedirAjudaPresencialLocalNulo() {
		sistema.pedirAjudaPresencial("TV", "10:00", "seg", null);
	}

	/**
	 * Testa se uma excecao eh lancada caso o parametro "localInteresse" passe um
	 * local invalido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaPresencialLocalInvalido() {
		sistema.pedirAjudaPresencial("TV", "10:00", "seg", "UEPB");
	}

	/**
	 * Testa se o metodo esta retornando o ID da ajuda corretamente.
	 */
	@Test
	public void testPedirAjudaOnlineValida() {
		assertEquals(2, sistema.pedirAjudaOnline("P3"));
	}

	/**
	 * Testa se uma excecao eh lancada caso o parametro "disciplina" esteja vazio.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaOnlineDisciplinaVazia() {
		sistema.pedirAjudaOnline("");
	}

	/**
	 * Testa se uma excecao eh lancada caso o parametro "disciplina" esteja nulo.
	 */
	@Test(expected = NullPointerException.class)
	public void testPedirAjudaOnlineDisciplinaNula() {
		sistema.pedirAjudaOnline(null);
	}

	/**
	 * Testa se uma excecao eh lancada caso o parametro "disciplina" passe uma
	 * disciplina invalida.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPedirAjudaOnlineDisciplinaInvalida() {
		sistema.pedirAjudaOnline("OAC");
	}

	/**
	 * Testa se o metodo esta retornando a matricula do tutor corretamente.
	 */
	@Test
	public void testPegarTutor() {
		assertEquals("2323", sistema.pegarTutor(1));
	}

	/**
	 * Testa se uma excecao eh lancada caso o parametro "idAjuda" seja um numero
	 * invalido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPegarTutorAjudaInvalida() {
		sistema.pegarTutor(-1);
	}

	/**
	 * Testa se uma excecao eh lancada caso o parametro "idAjuda" passe o id de uma
	 * ajuda ainda nao cadastrada.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPegarTutorAjudaNaoCadastrada() {
		sistema.pegarTutor(5);
	}

	/**
	 * Testa se uma excecao eh lancada caso o parametro "idAjuda" esteja nulo.
	 */
	@Test(expected = NullPointerException.class)
	public void testPegarTutorAjudaNula() {
		sistema.pegarTutor(null);
	}
	
	/**
	 * Testa se o metodo esta retornando a disciplina corretamente.
	 */
	@Test
	public void testGetInfoAjudaDisciplina() {
		assertEquals("P3",sistema.getInfoAjuda(1,"disciplina"));
	}
	
	/**
	 * Testa se o metodo esta retornando o horario corretamente.
	 */
	@Test
	public void testGetInfoAjudaHorario() {
		assertEquals("14:00",sistema.getInfoAjuda(1,"horario"));
	}

	/**
	 * Testa se o metodo esta retornando o dia corretamente.
	 */
	@Test
	public void testGetInfoAjudaDia() {
		assertEquals("qua",sistema.getInfoAjuda(1,"dia"));
	}

	/**
	 * Testa se o metodo esta retornando o local corretamente.
	 */
	@Test
	public void testGetInfoAjudaLocal() {
		assertEquals("CAA",sistema.getInfoAjuda(1,"local"));
	}
	
	/**
	 * Testa se uma excecao eh lancada caso o parametro "idAjuda" passe um id invalido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetInfoAjudaInvalida() {
		sistema.getInfoAjuda(-1,"disciplina");
	}
	
	/**
	 * Testa se uma excecao eh lancada caso o parametro "idAjuda" passe um id de uma Ajuda nao cadastrada.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetInfoAjudaNaoCadastrada() {
		sistema.getInfoAjuda(5,"disciplina");
	}
	
	/**
	 * Testa se uma excecao eh lancada caso o parametro "idAjuda" seja nulo.
	 */
	@Test(expected = NullPointerException.class)
	public void testGetInfoAjudaNula() {
		sistema.getInfoAjuda(null,"disciplina");
	}
	
	/**
	 * Testa se uma excecao eh lancada caso o parametro "atributo" esteja vazio.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetInfoAjudaAtributoVazio() {
		sistema.getInfoAjuda(1,"");
	}
	
	/**
	 * Testa se uma excecao eh lancada caso o parametro "atributo" esteja nulo.
	 */
	@Test(expected = NullPointerException.class)
	public void testGetInfoAjudaAtributoNulo() {
		sistema.getInfoAjuda(1,null);
	}
	
	/**
	 * Testa se uma excecao eh lancada caso o parametro "atributo" seja invalido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetInfoAjudaAtributoInvalido() {
		sistema.getInfoAjuda(1,"comida favorita");
	}


}