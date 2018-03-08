package controladores;

public class ControllerCaixa {

	private int caixa;
	
	public ControllerCaixa() {
		this.caixa = 0;
	}
	
	public void adicionaAoCaixa(int valor){
		this.caixa += valor;
	}

	public int getSaldo() {
		return this.caixa;
	}
}
