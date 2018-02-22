package principal;

public class Aluno implements Comparable<Aluno> {
	private String matricula, nome, telefone, email;
	private int codigoCurso;
	private double nota;
	
	public Aluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		this.nome = nome;
		this.matricula = matricula;
		this.codigoCurso = codigoCurso;
		this.telefone = telefone.trim();
		this.email = email;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}

	public int getCodigoCurso() {
		return codigoCurso;
	}
	
	public String toString() {
		if(getTelefone().equals("")) {
			return getMatricula() + " - " + getNome() + " - " + getCodigoCurso() + " - " + getEmail();
		}
		return getMatricula() + " - " + getNome() + " - " + getCodigoCurso() + " - " + getTelefone() + " - " + getEmail();
	}

	@Override
	public int compareTo(Aluno outroAluno) {
		return getNome().compareTo(outroAluno.getNome());
	}
}
