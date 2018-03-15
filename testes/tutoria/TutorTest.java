package tutoria;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TutorTest {
	private Tutor tutor;

	@Before
	public void inicializa() {
		tutor = new Tutor("167943", 5);
		tutor.adicionarDisciplina("anemonalogia", 3);
		tutor.cadastrarHorario("19:00", "quinta");
		tutor.cadastrarLocalDeAtendimento("lcc2");
	}

	@Test
	public void testGetTaxaTutor() {
		assertEquals(tutor.getTaxaTutor(), 0.8, 0.0001);
	}
	
	@Test
	public void testTemDisciplina() {
		assertTrue(tutor.temDisciplina("anemonalogia"));
	}
	
	@Test
	public void testNaoTemDisciplina() {
		assertFalse(tutor.temDisciplina("meianacanelogia"));
	}
	
	@Test
	public void testAdicionarDisciplina() {
		tutor.adicionarDisciplina("ciencias", 4);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAdicionarDisciplinaProficienciaZero() {
		tutor.adicionarDisciplina("ciencias", 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAdicionarDisciplinaProficienciaNegativa() {
		tutor.adicionarDisciplina("ciencias", -6);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAdicionarDisciplinaProficienciaAcimaLimite() {
		tutor.adicionarDisciplina("ciencias", 1649);
	}
	
	@Test
	public void testGetTutorMatricula() {
		assertEquals(tutor.getTutorMatricula(), "167943");
	}
	
	@Test
	public void testCadastrarHorario() {
		tutor.cadastrarHorario("15:00", "sexta");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCadastrarHorarioVazio() {
		tutor.cadastrarHorario("", "sexta");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCadastrarHorarioNulo() {
		tutor.cadastrarHorario(null, "sexta");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCadastrarHorarioDiaVazio() {
		tutor.cadastrarHorario("13:00", "        ");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCadastrarHorarioDiaNulo() {
		tutor.cadastrarHorario("13:00", null);
	}
	
	@Test
	public void testCadastrarLocalAtendimento() {
		tutor.cadastrarLocalDeAtendimento("casa");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCadastrarLocalAtendimentoVazio() {
		tutor.cadastrarLocalDeAtendimento("");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCadastrarLocalAtendimentoNulo() {
		tutor.cadastrarLocalDeAtendimento(null);
	}
	
	@Test
	public void testConsultaHorario() {
		assertTrue(tutor.consultaHorario("19:00", "quinta"));
	}
	
	@Test 
	public void testConsultaHorarioVazio() {
		assertFalse(tutor.consultaHorario("", "quinta"));
	}
	
	@Test 
	public void testConsultaHorarioNulo() {
		assertFalse(tutor.consultaHorario(null, "quinta"));
	}
	
	@Test 
	public void testConsultaHorarioDiaVazio() {
		assertFalse(tutor.consultaHorario("19:00", ""));
	}
	
	@Test 
	public void testConsultaHorarioDiaNulo() {
		assertFalse(tutor.consultaHorario("19:00", null));
	}
	
	@Test
	public void testConsultaLocal() {
		tutor.consultaLocal("lcc2");
	}
	
	@Test 
	public void testConsultaLocalVazio() {
		assertFalse(tutor.consultaLocal(""));
	}
	
	@Test
	public void testConsultaLocalNulo() {
		assertFalse(tutor.consultaLocal(null));
	}

}
