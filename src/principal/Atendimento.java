package principal;

import java.util.ArrayList;
import java.util.HashMap;

public class Atendimento {
	
	private HashMap<String, ArrayList<String>> horarios;
	private ArrayList<String> locais;
	
	public Atendimento() {
		this.horarios = new HashMap<>();
	}
	
	public void cadastrarLocalDeAtendimento(String local) {
		this.locais.add(local);
	}

	public void cadastrarHorario(String horario, String dia) {
		if(temDia(dia))
			this.horarios.get(dia).add(horario);
		else
			this.horarios.put(dia, new ArrayList<String>());
			this.horarios.get(dia).add(horario);
	}

	private boolean temDia(String dia) {
		if(this.horarios.containsKey(dia))
			return true;
		return false;
	}
	
	
}
