package controladores;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controladores.ControllerCaixa;

public class ControllerCaixaTest {

	private ControllerCaixa caixa;

	/**
	 * Inicia atributos que serao utilizados para os testes.
	 */
	@Before
	public void inicia() {
		this.caixa = new ControllerCaixa();
		caixa.adicionaAoCaixa(500);
	}

	/**
	 * Testa se o metodo esta adicionando dinheiro.
	 */
	@Test
	public void testAdicionaAoCaixa() {
		caixa.adicionaAoCaixa(200);
	}

	/**
	 * Testa se o metodo lanca excecao caso o dinheiro seja negativo.
	 */
	@Test
	public void testAdicionaAoCaixaInvalido() {
		caixa.adicionaAoCaixa(-50);
	}

	/**
	 * Testa se eh retornado o saldo correto.
	 */
	@Test
	public void testGetSaldo() {
		caixa.adicionaAoCaixa(20);
		assertEquals(520, caixa.getSaldo());
	}

}
