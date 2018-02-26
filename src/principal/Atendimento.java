package principal;

import java.util.ArrayList;
import java.util.HashMap;

public class Atendimento {
	
	private HashMap<String, ArrayList<String>> horarios;
	private ArrayList<String> locais;
	
	public Atendimento() {
		this.horarios = new HashMap<>();
		this.locais = new ArrayList<>();
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

	public boolean consultaHorario(String horario, String dia) {
		if(temDia(dia))
			for(String hora : this.horarios.get(dia)) {
				if(hora.equals(horario))
					return true;
			}
		return false;
	}
	
	public boolean consultaLocal(String local) {
		for(String localAtendimento : this.locais) {
			if(localAtendimento.equalsIgnoreCase(local))
				return true;
		}
		return false;
	}
	
	
}
