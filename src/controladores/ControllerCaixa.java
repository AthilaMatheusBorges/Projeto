package controladores;

import java.io.Serializable;

/**
 * Responsavel por controlar o caixa do sistema.
 * @author Roundhouse Kick Group
 *
 */
public class ControllerCaixa implements Serializable{

	private static final long serialVersionUID = -579402274383333408L;
	private int caixa;
	
	/**
	 * Inicia o caixa com valor 0;
	 */
	public ControllerCaixa() {
		this.caixa = 0;
	}
	
	/**
	 * Adiciona o valor recebido ao caixa.
	 * @param valor eh o valor a ser adicionado.
	 */
	public void adicionaAoCaixa(int valor){
		this.caixa += valor;
	}

	/**
	 * Recupera o saldo do caixa.
	 * @return retorna o saldo do caixa.
	 */
	public int getSaldo() {
		return this.caixa;
	}
}
