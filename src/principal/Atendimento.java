package principal;

import java.util.ArrayList;
import java.util.HashMap;

public class Atendimento {

	private HashMap<String, ArrayList<String>> horarios;
	private ArrayList<String> locais;

	/**
	 * Constroi um Atendimento do Tutor.
	 */
	public Atendimento() {
		this.horarios = new HashMap<>();
		this.locais = new ArrayList<>();
	}

	/**
	 * Cadastra um local de atendimento.
	 * 
	 * @param local
	 *            eh o local do atendimento.
	 */
	public void cadastrarLocalDeAtendimento(String local) {
		this.locais.add(local);
	}

	/**
	 * Cadastra o dia e horario de atendimento do tutor.
	 * 
	 * @param horario
	 *            eh o horario do atendimento.
	 * @param dia
	 *            eh o dia do atendimento.
	 */
	public void cadastrarHorario(String horario, String dia) {
		if (temDia(dia))
			this.horarios.get(dia).add(horario);
		else
			this.horarios.put(dia, new ArrayList<String>());
		this.horarios.get(dia).add(horario);
	}

	/**
	 * Verifica se o tutor tem atendimento no dia.
	 * 
	 * @param dia
	 *            eh o dia a ser verificado.
	 * @return retorna true caso tenha, false caso contrario.
	 */
	private boolean temDia(String dia) {
		if (this.horarios.containsKey(dia))
			return true;
		return false;
	}

	/**
	 * Verifica se o tutor tem atendimento no dia e horario passados.
	 * 
	 * @param horario
	 *            eh o horario do atendimento.
	 * @param dia
	 *            eh o dia do atendimento.
	 * @return retorna true caso tenha o horario, false caso contrario.
	 */
	public boolean consultaHorario(String horario, String dia) {
		if (temDia(dia))
			for (String hora : this.horarios.get(dia)) {
				if (hora.equals(horario))
					return true;
			}
		return false;
	}

	/**
	 * Verifica se o tutor tem atendimento no local.
	 * 
	 * @param local
	 *            eh o local do atendimento.
	 * @return retorna true caso tenha, false caso contrario.
	 */
	public boolean consultaLocal(String local) {
		for (String localAtendimento : this.locais) {
			if (localAtendimento.equalsIgnoreCase(local))
				return true;
		}
		return false;
	}

}
