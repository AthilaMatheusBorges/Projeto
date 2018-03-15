package controladores;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import pedidoAjuda.AjudaOnline;
import pedidoAjuda.AjudaPresencial;
import pedidoAjuda.PedidoDeAjuda;

/**
 * Responsavel por controlar os pedidos de ajuda. Armazena os pedidos em
 * mapa<idAjuda, PedidoDeAjuda>. Todos os metodos referentes a ajuda estao
 * encapsulados nesta classe.
 * 
 * @author Roundhouse Kick Group
 *
 */
public class ControllerAjuda implements Serializable{

	private static final long serialVersionUID = -945899848719041173L;
	private Map<Integer, PedidoDeAjuda> pedidosDeAjuda;

	/**
	 * Inicia o mapa de pedidos de ajuda.
	 */
	public ControllerAjuda() {
		this.pedidosDeAjuda = new HashMap<>();
	}

	/**
	 * Recupera um Pedido de Ajuda e retorna o proprio objeto.
	 * 
	 * @param idAjuda
	 *            eh o idendificador da ajuda.
	 * @return retorna o objeto do Pedido de Ajuda.
	 */
	public PedidoDeAjuda getPedidoDeAjuda(int idAjuda) {
		validaIdAjuda("ajuda", idAjuda);
		return this.pedidosDeAjuda.get(idAjuda);
	}

	/**
	 * Adiciona um Pedido de Ajuda Presencial no mapa. Cria o objeto do pedido e o
	 * adiciona ao mapa.
	 * 
	 * @param matrAluno
	 *            eh a matricula do aluno que fez o pedido de ajuda.
	 * @param disciplina
	 *            eh a disciplina da ajuda.
	 * @param tutorMatricula
	 *            eh a matricula do tutor da ajuda.
	 * @param horario
	 *            eh o horario da ajuda.
	 * @param dia
	 *            eh o dia da ajuda.
	 * @param localInteresse
	 *            eh o local da ajuda.
	 * @return retorna o identificador do pedido de ajuda.
	 */
	public int adicionaAjuda(String matrAluno, String disciplina, String tutorMatricula, String horario, String dia,
			String localInteresse) {
		PedidoDeAjuda pedido = new AjudaPresencial(matrAluno, disciplina, tutorMatricula, dia, horario, localInteresse,
				getQntdPedidosAjuda() + 1);
		this.pedidosDeAjuda.put(pedido.getIdAjuda(), pedido);
		return pedido.getIdAjuda();
	}

	/**
	 * Adiciona um Pedido de Ajuda Online no mapa. Cria o objeto do pedido e o
	 * adiciona ao mapa.
	 * 
	 * @param matrAluno
	 *            eh a matricula do aluno que fez o pedido de ajuda.
	 * @param disciplina
	 *            eh a disciplina da ajuda.
	 * @param tutorMatricula
	 *            eh a matricula do tutor da ajuda.
	 * @return retorna o identificador do pedido de ajuda.
	 */
	public int adicionaAjuda(String matrAluno, String disciplina, String tutorMatricula) {
		PedidoDeAjuda pedido = new AjudaOnline(matrAluno, disciplina, tutorMatricula, getQntdPedidosAjuda() + 1);
		this.pedidosDeAjuda.put(pedido.getIdAjuda(), pedido);
		return pedido.getIdAjuda();
	}

	/**
	 * Retorna a quantidade de pedidos de ajuda realizados.
	 * 
	 * @return a quantidade de pedidos de ajuda realizados.
	 */
	public int getQntdPedidosAjuda() {
		return this.pedidosDeAjuda.size();
	}

	/**
	 * Recupera a matricula do tutor da ajuda.
	 * 
	 * @param idAjuda
	 *            eh o identificador da ajuda.
	 * @return retorna a matricula do tutor.
	 */
	public String getMatriculaTutorAjuda(int idAjuda) {
		return this.pedidosDeAjuda.get(idAjuda).getTutorMatricula();
	}

	/**
	 * Valida o identificador da ajuda.
	 * 
	 * @param quemSouEu
	 *            eh o nome de quem esta sendo verificado.
	 * @param idAjuda
	 *            eh o identificador da ajuda.
	 */
	public void validaIdAjuda(String quemSouEu, int idAjuda) {
		if (idAjuda < 0)
			throw new IllegalArgumentException(
					"Erro ao tentar recuperar " + quemSouEu + " : id nao pode menor que zero ");
		else if (idAjuda > this.pedidosDeAjuda.size())
			throw new IllegalArgumentException("Erro ao tentar recuperar " + quemSouEu + " : id nao encontrado ");
	}

	/**
	 * Avalia a ajuda. Antes verifica se a ajuda ja esta avaliada.
	 * 
	 * @param idAjuda
	 *            eh o identificador da ajuda.
	 */
	public void avaliarAjuda(int idAjuda) {
		if (!pedidosDeAjuda.containsKey(idAjuda))
			throw new IllegalArgumentException("Erro na avaliacao de tutor: id nao encontrado ");
		else if (getPedidoDeAjuda(idAjuda).ajudaAvaliada())
			throw new IllegalArgumentException("Erro na avaliacao de tutor: Ajuda ja avaliada");
		getPedidoDeAjuda(idAjuda).avaliar();
	}

	/**
	 * Recupera a descricao do tutor da ajuda.
	 * 
	 * @param idAjuda
	 *            eh o identificador da ajuda.
	 * @return retorna a descricao do tutor da ajuda.
	 */
	public String getDescricaoTutor(int idAjuda) {
		return getPedidoDeAjuda(idAjuda).getDescricaoTutor();
	}

	/**
	 * Retorna uma informacao do pedido de ajuda baseado em um atributo.
	 * 
	 * @param idAjuda
	 *            A ajuda a ser acessada.
	 * @param atributo
	 *            O atributo que indica a informacao de retorno.
	 * @return A informacao pedida.
	 */
	public String getInfoAjuda(int idAjuda, String atributo) {
		validaIdAjuda("info da ajuda", idAjuda);
		PedidoDeAjuda ajuda = getPedidoDeAjuda(idAjuda);
		return ajuda.getInfoAjuda(atributo);

	}

}
