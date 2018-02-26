package principal;

import java.util.ArrayList;

public class Tutor extends Aluno {
	private int proficiencia;
	private ArrayList<String> disciplinas;
	private double nota;
	private Atendimento atendimento;

	public Tutor(Aluno aluno, String disciplina, int proficiencia){
		super(aluno.getNome(), aluno.getMatricula(), aluno.getCodigoCurso(), aluno.getTelefone(), aluno.getEmail());
		this.proficiencia = proficiencia;
		this.disciplinas = new ArrayList<>();
		adicionaDisciplina(disciplina);
		this.nota = 4;
		super.setTipo("tutor");
		this.atendimento = new Atendimento();
	}

	public void adicionaDisciplina(String disciplina2) {
		this.disciplinas.add(disciplina2);
	}
	
	public boolean confereSeJaEtutorDaDisciplina(String disciplina) {
		for(String nome : disciplinas) {
			if(nome.equalsIgnoreCase(disciplina)) {
				return true;
			}
		}
		return false;
	}

	
	public void cadastrarHorario(String horario, String dia) {
		this.atendimento.cadastrarHorario(horario, dia);
		
	}
	
	public void cadastrarLocalDeAtendimento(String local) {
		this.atendimento.cadastrarLocalDeAtendimento(local);
	}

	public boolean consultaHorario(String horario, String dia) {
		return atendimento.consultaHorario(horario, dia);
	}

	public boolean consultaLocal(String local) {
		return atendimento.consultaLocal(local);
	}
}
