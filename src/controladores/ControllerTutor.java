package controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import comparadores.ComparatorMaiorProficiecia;
import tutoria.Tutor;

/**
 * Responsavel por controlar os tutores. Armazena os tutores em um
 * mapa<matricula, Tutor>. Todos os metodos referentes a Tutor estao
 * encapsulados nesta classe.
 * 
 * @author Roundhouse Kick Group
 *
 */
public class ControllerTutor implements Serializable{

	private static final long serialVersionUID = -5600144571916066270L;
	private Map<String, Tutor> listaDeTutores;

	/**
	 * Inicia o mapa da lista de tures.
	 */
	public ControllerTutor() {
		this.listaDeTutores = new HashMap<>();
	}

	/**
	 * Metodo privado que cria um tutor.
	 * 
	 * @param matricula
	 *            eh a matricula do tutor.
	 * @param identificador
	 *            eh o identificador do tutor.
	 * @return retorna o tutor criado.
	 */

	/**
	 * Adiciona um tutor ao mapa de tutores.
	 * 
	 * @param tutor
	 *            eh o tutor a ser adicionado.
	 */
	public void adicionarTutor(Tutor tutor) {
		this.listaDeTutores.put(tutor.getTutorMatricula(), tutor);
	}

	/**
	 * Recebe a matricula de um aluno, uma disciplina e a proficiencia sobre a
	 * disciplina e o cadastra como tutor.
	 * 
	 * @param matricula
	 *            eh a matricula.
	 * @param disciplina
	 *            eh a disciplina.
	 * @param proficiencia
	 *            eh a proficiencia.
	 */
	public void tornarTutor(String matricula, String disciplina, int proficiencia) {
		if (temTutor(matricula))
			getTutor(matricula).adicionarDisciplina(disciplina, proficiencia);
		else {
			Tutor tutor = new Tutor(matricula, this.listaDeTutores.size());
			tutor.adicionarDisciplina(disciplina, proficiencia);
			adicionarTutor(tutor);
		}
	}

	/**
	 * Recupera o tutor a partir da matricula.
	 * 
	 * @param matricula
	 *            eh a matricula do tutor
	 * @return retorna o objeto do tutor.
	 */
	public Tutor getTutor(String matricula) {
		if (temTutor(matricula))
			return this.listaDeTutores.get(matricula);
		return null;
	}

	/**
	 * Verifica se a matricula eh de um tutor.
	 * 
	 * @param matricula
	 *            eh a matricula a ser veriificada.
	 * @return retorna true se a matricula estiver cadastrada, false caso contrario.
	 */
	public boolean temTutor(String matricula) {
		return this.listaDeTutores.containsKey(matricula);
	}

	/**
	 * Valida a matricula passada como parametro.
	 * 
	 * @param matricula
	 *            eh a matricula a ser verificada.
	 */
	public void verificaTutor(String matricula) {
		if (!this.listaDeTutores.containsKey(matricula))
			throw new IllegalArgumentException("Erro na busca por tutor: Tutor nao encontrado");
	}

	/**
	 * Percore o mapa dos tutores e armazena numa lista as matriculas de cada um.
	 * 
	 * @return retorna uma lista com as matriculas de todos os tutores.
	 */
	public ArrayList<String> listarTutores() {
		ArrayList<String> saida = new ArrayList<>();
		for (String matricula : this.listaDeTutores.keySet()) {
			if (temTutor(matricula)) {
				saida.add(matricula);
			}
		}
		return saida;
	}

	/**
	 * Monta uma lista com os tutores da disciplina. Organiza a lista por nota e
	 * pega o primeiro da lista.
	 * 
	 * @param disciplina
	 *            eh a disciplina.
	 * @return retorna o tutor com maior nota na disciplina.
	 */
	public Tutor maiorProficiencia(String disciplina) {
		ArrayList<Tutor> tutoresDisponiveis = new ArrayList<>();
		for (Tutor tutor : this.listaDeTutores.values()) {
			if (tutor.temDisciplina(disciplina))
				tutoresDisponiveis.add(tutor);
		}
		Collections.sort(tutoresDisponiveis, new ComparatorMaiorProficiecia());
		return tutoresDisponiveis.get(0);
	}

	/**
	 * Monta uma lista com os tutores disponiveis a partir dos parametros passados.
	 * Organiza a lista por nota e pega o primeiro da lista.
	 * 
	 * @param disciplina
	 *            eh a disciplina.
	 * @param horario
	 *            eh o horario.
	 * @param dia
	 *            eh o dia.
	 * @param localInteresse
	 *            eh o local.
	 * @return retorna o tutor disponiveis no dia, horario e local com maior nota na
	 *         disciplina.
	 */
	public Tutor maiorProficiencia(String disciplina, String horario, String dia, String localInteresse) {
		ArrayList<Tutor> tutoresDisponiveis = new ArrayList<>();
		for (Tutor tutor : this.listaDeTutores.values()) {
			if (tutor.temDisciplina(disciplina) && tutor.consultaHorario(horario, dia)
					&& tutor.consultaLocal(localInteresse))
				tutoresDisponiveis.add(tutor);
		}
		Collections.sort(tutoresDisponiveis, new ComparatorMaiorProficiecia());
		return tutoresDisponiveis.get(0);
	}

	/**
	 * Avalia o tutor.
	 * 
	 * @param matricula
	 *            eh a matricula do tutor a ser avaliado.
	 * @param nota
	 *            eh a nota da avaliacao do tutor.
	 * @return retorna uma confirmacao da avaliacao do tutor.
	 */
	public String avaliarTutor(String matricula, int nota) {
		getTutor(matricula).avaliarTutor(nota);
		return "Tutor avaliado";
	}

	/**
	 * Recupera a taxa do tutor.
	 * 
	 * @param matriculaTutor
	 *            eh a matricula do tutor.
	 * @return retorna a taxa do tutor.
	 */
	public double getTaxaTutor(String matriculaTutor) {
		return getTutor(matriculaTutor).getTaxaTutor();
	}

	/**
	 * Recupera o tutor da matricula e recebe uma doacao.
	 * 
	 * @param matricula
	 *            eh a matricula do tutor.
	 * @param valor
	 *            eh o valor da doacao.
	 */
	public void receberDoacao(String matricula, double valor) {
		getTutor(matricula).receberDoacao(valor);
	}
}
