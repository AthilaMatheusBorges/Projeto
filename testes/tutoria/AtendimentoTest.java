package tutoria;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tutoria.Atendimento;

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
	 * Testa se a consulta de horarios esta retornando True caso um horario existente
	 * for consultado.
	 */
	@Test
	public void testConsultaHorarioExistente() {
		assertTrue(atendimento.consultaHorario("10:00", "seg"));
	}
	
	/**
	 * Testa se a consulta de horarios esta retornando False caso um horario inexistente
	 * for consultado.
	 */
	@Test
	public void testConsultaHorarioInexistente() {
		assertFalse(atendimento.consultaHorario("12:00", "qua"));
	}

	/**
	 * Testa se a consulta de local retorna True caso um local existente for consultado
	 * utilizando letras maiusculas.
	 */
	@Test
	public void testConsultaLocalExistenteLetraMaiuscula() {
		assertTrue(atendimento.consultaLocal("LCC2"));
	}
	
	/**
	 * Testa se a consulta de local retorna True caso um local existente for consultado
	 * utilizando letras maiusculas.
	 */
	@Test
	public void testConsultaLocalExistenteLetraMinuscula() {
		assertTrue(atendimento.consultaLocal("lcc2"));
	}
	
	/**
	 * Testa se a consulta de local retorna True caso um local existente for consultado
	 * utilizando letras maiusculas e minusculas.
	 */
	@Test
	public void testConsultaLocalLetraMaiusculaEMinuscula() {
		assertTrue(atendimento.consultaLocal("Lcc2"));
	}
	
	/**
	 * Testa se a consulta de local retorna False caso um local inexistente for consultado.
	 */
	@Test
	public void testConsultaLocalInexistente() {
		assertFalse(atendimento.consultaLocal("LCC1"));
	}

}
