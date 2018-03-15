package pedidoAjuda;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pedidoAjuda.AjudaOnline;

public class AjudaOnlineTest {

	private AjudaOnline meuPedidoDeAjuda, outroPedidoDeAjuda;
	
	@Before
	public void inicializa() {
		meuPedidoDeAjuda = new AjudaOnline("444", "economia", "314", 1);
		outroPedidoDeAjuda = new AjudaOnline("1231141", "laguinhologia", "126", 2);
		outroPedidoDeAjuda.avaliar();
	}
	
	@Test
	public void testGetDescricaoTutor() {
		assertEquals(meuPedidoDeAjuda.getDescricaoTutor(), "Tutor - 314, disciplina - economia");
	}

	@Test
	public void testGetInfoAjudaTutor() {
		assertEquals(meuPedidoDeAjuda.getInfoAjuda("tutor"), "314");
	}
	
	@Test
	public void testGetInfoAjudaDisciplina() {
		assertEquals(meuPedidoDeAjuda.getInfoAjuda("disciplina"), "economia");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testGetInfoAjudaAtributoInvalido() {
		meuPedidoDeAjuda.getInfoAjuda("doar");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetInfoAjudaAtributoNulo() {
		meuPedidoDeAjuda.getInfoAjuda(null);
	}
	
	@Test
	public void testAvaliar() {
		meuPedidoDeAjuda.avaliar();
	}

	@Test
	public void testGetDisciplina() {
		assertEquals(meuPedidoDeAjuda.getDisciplina(), "economia");
	}

	@Test
	public void testGetTutorMatricula() {
		assertEquals(meuPedidoDeAjuda.getTutorMatricula(), "314");
	}

	@Test
	public void testGetIdAjuda() {
		assertEquals(meuPedidoDeAjuda.getIdAjuda(), 1);
	}

	@Test
	public void testAjudaAvaliada() {
		assertTrue(outroPedidoDeAjuda.ajudaAvaliada());
	}

	@Test
	public void testAjudaNaoAvaliada() {
		assertFalse(meuPedidoDeAjuda.ajudaAvaliada());
	}
	
	@Test 
	public void testAjudaOnlineValida() {
		meuPedidoDeAjuda = new AjudaOnline("5435", "numerologia", "123123", 2);
	}
	
	@Test  (expected = IllegalArgumentException.class)
	public void testAjudaOnlineMatrAlunoVazia() {
		meuPedidoDeAjuda = new AjudaOnline(" ", "numerologia", "123123", 2);
	}
	
	@Test  (expected = IllegalArgumentException.class)
	public void testAjudaOnlineMatrAlunoNula() {
		meuPedidoDeAjuda = new AjudaOnline(null, "numerologia", "123123", 2);
	}
	
	@Test  (expected = IllegalArgumentException.class)
	public void testAjudaOnlineDisciplinaVazia() {
		meuPedidoDeAjuda = new AjudaOnline("234234", "        ", "123123", 2);
	}
	
	@Test  (expected = IllegalArgumentException.class)
	public void testAjudaOnlineDisciplinaNula() {
		meuPedidoDeAjuda = new AjudaOnline("1341242", null, "123123", 2);
	}

}
