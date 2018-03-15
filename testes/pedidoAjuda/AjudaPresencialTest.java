package pedidoAjuda;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pedidoAjuda.AjudaPresencial;

public class AjudaPresencialTest {

	private AjudaPresencial meuPedidoDeAjuda, outroPedidoDeAjuda;
	
	@Before
	public void inicializa() {
		meuPedidoDeAjuda = new AjudaPresencial("444", "economia", "346" ,"quarta", "13:00", "biblioteca", 1);
		outroPedidoDeAjuda = new AjudaPresencial("1231141", "laguinhologia", "126" ,"segunda", "7:00", "laguinho", 2);
		outroPedidoDeAjuda.avaliar();
	}
	
	@Test
	public void testGetDescricaoTutor() {
		assertEquals(meuPedidoDeAjuda.getDescricaoTutor(), "Tutor - 346, horario - 13:00, dia - quarta, local - biblioteca, disciplina - economia");
	}

	@Test
	public void testGetInfoAjudaTutor() {
		assertEquals(meuPedidoDeAjuda.getInfoAjuda("tutor"), "346");
	}
	
	@Test
	public void testGetInfoAjudaDisciplina() {
		assertEquals(meuPedidoDeAjuda.getInfoAjuda("disciplina"), "economia");
	}
	
	@Test
	public void testGetInfoAjudaDia() {
		assertEquals(meuPedidoDeAjuda.getInfoAjuda("dia"), "quarta");
	}
	
	@Test
	public void testGetInfoAjudaHorario() {
		assertEquals(meuPedidoDeAjuda.getInfoAjuda("horario"), "13:00");
	}
	
	@Test
	public void testGetInfoAjudaLocalInteresse() {
		assertEquals(meuPedidoDeAjuda.getInfoAjuda("localinteresse"), "biblioteca");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testGetInfoAjudaAtributoInvalido() {
		meuPedidoDeAjuda.getInfoAjuda("deficit");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetInfoAjudaAtributoVazio() {
		meuPedidoDeAjuda.getInfoAjuda(" ");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetInfoAjudaAtributoNulo() {
		meuPedidoDeAjuda.getInfoAjuda(null);
	}
	

	@Test
	public void testGetDia() {
		assertEquals(meuPedidoDeAjuda.getDia(), "quarta");
	}

	@Test
	public void testGetHorario() {
		assertEquals(meuPedidoDeAjuda.getHorario(), "13:00");
	}

	@Test
	public void testGetLocalInteresse() {
		assertEquals(meuPedidoDeAjuda.getLocalInteresse(), "biblioteca");
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
		assertEquals(meuPedidoDeAjuda.getTutorMatricula(), "346");
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
	public void testAjudaPresencialValida() {
		meuPedidoDeAjuda = new AjudaPresencial("444", "economia", "346" ,"quarta", "13:00", "biblioteca", 1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAjudaPresencialMatrAlunoVazia() {
		meuPedidoDeAjuda = new AjudaPresencial("", "economia", "346" ,"quarta", "13:00", "biblioteca", 1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAjudaPresencialMatrAlunoNula() {
		meuPedidoDeAjuda = new AjudaPresencial(null, "economia", "346" ,"quarta", "13:00", "biblioteca", 1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAjudaPresencialDisciplinaVazia() {
		meuPedidoDeAjuda = new AjudaPresencial("444", "", "346" ,"quarta", "13:00", "biblioteca", 1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAjudaPresencialDisciplinaNula() {
		meuPedidoDeAjuda = new AjudaPresencial("444", null, "346" ,"quarta", "13:00", "biblioteca", 1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAjudaPresencialDiaVazio() {
		meuPedidoDeAjuda = new AjudaPresencial("444", "economia", "346" ," ", "13:00", "biblioteca", 1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAjudaPresencialDiaNulo() {
		meuPedidoDeAjuda = new AjudaPresencial("444", "economia", "346" ,null, "13:00", "biblioteca", 1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAjudaPresencialHorarioVazio() {
		meuPedidoDeAjuda = new AjudaPresencial("444", "economia", "346" ,"quarta", " ", "biblioteca", 1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAjudaPresencialHorarioNulo() {
		meuPedidoDeAjuda = new AjudaPresencial("444", "economia", "346" ,"quarta", null, "biblioteca", 1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAjudaPresencialLocalVazio() {
		meuPedidoDeAjuda = new AjudaPresencial("444", "economia", "346" ,"quarta", "13:00", "   ", 1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAjudaPresencialLocalNulo() {
		meuPedidoDeAjuda = new AjudaPresencial("444", "economia", "346" ,"quarta", "13:00", null, 1);
	}
}
