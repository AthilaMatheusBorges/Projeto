package principal;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AtendimentoTest {

	Atendimento atendimento;

	/**
	 * Inicializa o objeto atendimento, que sera usado nos testes.
	 * Sao tambem cadastrados um horario e um local de atendimento.
	 */
	@Before
	public void inicializa() {
		atendimento = new Atendimento();
		atendimento.cadastrarHorario("10:00", "seg");
		atendimento.cadastrarLocalDeAtendimento("LCC2");
	}

	/**
	 * Testa se a consulta de horarios esta retornando o Boolean correto para
	 * cada caso, incluindo casos nos quais a consulta utiliza letras maiusculas,
	 * minusculas ou ambas.
	 */
	@Test
	public void testConsultaHorario() {
		assertTrue(atendimento.consultaHorario("10:00", "seg"));
		assertTrue(atendimento.consultaHorario("10:00", "SEG"));
		assertTrue(atendimento.consultaHorario("10:00", "Seg"));
		assertFalse(atendimento.consultaHorario("12:00", "qua"));
		assertFalse(atendimento.consultaHorario("08:00", "ter"));
	}

	/**
	 * Testa se a consulta de local esta retornando o Boolean correto para
	 * cada caso, incluindo casos nos quais a consulta utiliza letras maiusculas,
	 * minusculas ou ambas.
	 */
	@Test
	public void testConsultaLocal() {
		assertTrue(atendimento.consultaLocal("LCC2"));
		assertTrue(atendimento.consultaLocal("lcc2"));
		assertTrue(atendimento.consultaLocal("Lcc2"));
		assertFalse(atendimento.consultaLocal("LCC1"));
		assertFalse(atendimento.consultaLocal("Praca de Alimentacao"));
	}

}
