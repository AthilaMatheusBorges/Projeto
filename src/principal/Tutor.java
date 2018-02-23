package principal;

public class Tutor extends Aluno {
	private int proficiencia;
	private String disciplina;
	private double nota;
	private Atendimento atendimento;

	public Tutor(Aluno aluno, String disciplina, int proficiencia){
		super(aluno.getNome(), aluno.getMatricula(), aluno.getCodigoCurso(), aluno.getTelefone(), aluno.getEmail());
		this.proficiencia = proficiencia;
		this.disciplina = disciplina;
		this.nota = 4;
		super.setTipo("tutor");
		this.atendimento = new Atendimento();
	}

	public void cadastrarHorario(String horario, String dia) {
		this.atendimento.cadastrarHorario(horario, dia);
		
	}
	
	public void cadastrarLocalDeAtendimento(String local) {
		this.atendimento.cadastrarLocalDeAtendimento(local);
	}

}
