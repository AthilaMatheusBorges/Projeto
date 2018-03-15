package controladores;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controladores.ControllerAjuda;

public class ControllerAjudaTest {

	private ControllerAjuda ajuda;

	/**
	 * Inicia atributos que serao utilizados para os testes.
	 */
	@Before
	public void inicia() {
		this.ajuda = new ControllerAjuda();
		ajuda.adicionaAjuda("117", "P1", "116");
		ajuda.adicionaAjuda("116", "LED", "114", "15:00", "ter", "CAA");
	}

	@Test
	public void testGetPedidoDeAjuda() {
		ajuda.getPedidoDeAjuda(1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetPedidoDeAjudaNaoCadastrado() {
		ajuda.getPedidoDeAjuda(5000);
	}

	@Test
	public void testAdicionaAjudaPresencial() {
		assertEquals(3, ajuda.adicionaAjuda("117", "P1", "116", "10:00", "seg", "CAA"));
	}

	@Test
	public void testAdicionaAjudaOnline() {
		assertEquals(3, ajuda.adicionaAjuda("117", "P1", "116"));
	}

	@Test
	public void testGetQntdPedidosAjuda() {
		assertEquals(2, ajuda.getQntdPedidosAjuda());
	}

	@Test
	public void testGetMatriculaTutorAjuda() {
		assertEquals("114", ajuda.getMatriculaTutorAjuda(2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidaIdAjuda() {
		ajuda.validaIdAjuda("ola", 50);
	}

	@Test
	public void testAvaliarAjuda() {
		ajuda.avaliarAjuda(2);
	}

	@Test
	public void testGetDescricaoTutor() {
		assertEquals("Tutor - 114, horario - 15:00, dia - ter, local - CAA, disciplina - LED",
				ajuda.getDescricaoTutor(2));
	}

	@Test
	public void testGetInfoAjudaTutor() {
		assertEquals("114", ajuda.getInfoAjuda(2, "tutor"));
	}

	@Test
	public void testGetInfoAjudaDisciplina() {
		assertEquals("LED", ajuda.getInfoAjuda(2, "disciplina"));
	}

	@Test
	public void testGetInfoAjudaDia() {
		assertEquals("ter", ajuda.getInfoAjuda(2, "dia"));
	}

	@Test
	public void testGetInfoAjudaHorario() {
		assertEquals("15:00", ajuda.getInfoAjuda(2, "horario"));
	}

	@Test
	public void testGetInfoAjudaLocal() {
		assertEquals("CAA", ajuda.getInfoAjuda(2, "localinteresse"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetInfoAjudaExcecao() {
		ajuda.getInfoAjuda(2, "qualquer coisa");
	}

}
