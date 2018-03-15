package tutoria;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tutoria.Disciplina;

public class DisciplinaTest {

	private Disciplina disciplina;
	
	@Before
	public void inicializa() {
		disciplina = new Disciplina();
		disciplina.adicionarDisciplina("AgroTecnoEcologia", 5);
	}

	@Test
	public void testAdicionarDisciplina() {
		disciplina.adicionarDisciplina("numerologia", 5);
	}
	
	@Test
	public void testAdicionarDisciplinaVazia() {
		disciplina.adicionarDisciplina("", 5);
	}
	
	@Test
	public void testAdicionarDisciplinaNula() {
		disciplina.adicionarDisciplina(null, 5);
	}

	@Test  (expected = IllegalArgumentException.class)
	public void testAdicionarDisciplinaProficienciaZero() {
		disciplina.adicionarDisciplina("numerologia", 0);
	}
	
	@Test  (expected = IllegalArgumentException.class)
	public void testAdicionarDisciplinaProficienciaNegativa() {
		disciplina.adicionarDisciplina("numerologia", -5);
	}
	
	@Test  (expected = IllegalArgumentException.class)
	public void testAdicionarDisciplinaProficienciaAcimaDoLimite() {
		disciplina.adicionarDisciplina("numerologia", 600);
	}

	@Test
	public void testTemDisciplinaCadastrada() {
		assertTrue(disciplina.temDisciplina("AgroTecnoEcologia"));
	}
	
	@Test 
	public void testTemDisciplinaNaoCadastrada() {
		assertFalse(disciplina.temDisciplina("AguasDoRioSanFrancisco"));
	}

	@Test
	public void testRecuperaProficienciaValida() {
		assertEquals(disciplina.recuperaProficiencia("AgroTecnoEcologia"), 5);
	}
	
	@Test  (expected = IllegalArgumentException.class)
	public void testRecuperaProficienciaDisciplinaNaoCadastrada() {
		disciplina.recuperaProficiencia("FazOGrau");
	}

}
