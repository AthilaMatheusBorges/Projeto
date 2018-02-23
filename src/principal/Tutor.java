package principal;

public class Tutor extends Aluno {
	private int proficiencia;
	private String disciplina;
	private double nota;

	public Tutor(Aluno aluno, String disciplina, int proficiencia){
		super(aluno.getNome(), aluno.getMatricula(), aluno.getCodigoCurso(), aluno.getTelefone(), aluno.getEmail());
		this.proficiencia = proficiencia;
		this.disciplina = disciplina;
		this.nota = 4;
		super.setTipo("tutor");
	}

}
