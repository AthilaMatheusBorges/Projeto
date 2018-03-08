package controladores;

import java.util.HashMap;
import java.util.Map;

import principal.AjudaOnline;
import principal.AjudaPresencial;
import principal.PedidoDeAjuda;

public class ControllerAjuda {

	private Map<Integer, PedidoDeAjuda> pedidosDeAjuda;

	public ControllerAjuda() {
		this.pedidosDeAjuda = new HashMap<>();
	}

	public PedidoDeAjuda getPedidoDeAjuda(int idAjuda) {
		return this.pedidosDeAjuda.get(idAjuda);
	}

	public int adicionaAjuda(String matrAluno, String disciplina, String tutorMatricula, String horario, String dia,
			String localInteresse) {
		PedidoDeAjuda pedido = new AjudaPresencial(matrAluno, disciplina, tutorMatricula, dia, horario, localInteresse,
				getQntdPedidosAjuda() + 1);
		this.pedidosDeAjuda.put(pedido.getIdAjuda(), pedido);
		return pedido.getIdAjuda();
	}
	
	public int adicionaAjuda(String matrAluno, String disciplina, String tutorMatricula) {
		PedidoDeAjuda pedido = new AjudaOnline(matrAluno, disciplina, tutorMatricula, getQntdPedidosAjuda() + 1);
		this.pedidosDeAjuda.put(pedido.getIdAjuda(), pedido);
		return pedido.getIdAjuda();
	}
	
	public int getQntdPedidosAjuda() {
		return this.pedidosDeAjuda.size();
	}
	
	public String getMatriculaTutorAjuda(int idAjuda) {
		return this.pedidosDeAjuda.get(idAjuda).getTutorMatricula();
	}
	
	public void validaIdAjuda(String quemSouEu, int idAjuda) {
		if (idAjuda < 0)
			throw new IllegalArgumentException(
					"Erro ao tentar recuperar " + quemSouEu + " : id nao pode menor que zero ");
		else if (idAjuda > this.pedidosDeAjuda.size())
			throw new IllegalArgumentException("Erro ao tentar recuperar " + quemSouEu + " : id nao encontrado ");
	}
	
	public void avaliarAjuda(int idAjuda) {
		if (!pedidosDeAjuda.containsKey(idAjuda)) 
			throw new IllegalArgumentException("Erro na avaliacao de tutor: id nao encontrado ");
		else if (getPedidoDeAjuda(idAjuda).tutorAvaliado()) 
			throw new IllegalArgumentException("Erro na avaliacao de tutor: Ajuda ja avaliada");
		getPedidoDeAjuda(idAjuda).avaliar();
	}
	
	
	
	public String getDescricaoTutor(int idAjuda) {
		return getPedidoDeAjuda(idAjuda).getDescricaoTutor();
	}
	
}
