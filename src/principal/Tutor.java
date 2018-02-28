package principal;

import java.util.ArrayList;

public class Tutor extends Aluno {
	private int proficiencia;
	private ArrayList<String> disciplinas;
	private double nota, saldo;
	private Atendimento atendimento;

	/**
	 * Constroi o Tutor a partir dos parametros passados.
	 * 
	 * @param aluno
	 *            eh o objeto Aluno.
	 * @param disciplina
	 *            eh a disciplina.
	 * @param proficiencia
	 *            eh a proficiencia do tutor sobre a discipĺina.
	 */
	public Tutor(Aluno aluno, String disciplina, int proficiencia) {
		super(aluno.getNome(), aluno.getMatricula(), aluno.getCodigoCurso(), aluno.getTelefone(), aluno.getEmail());
		this.proficiencia = proficiencia;
		this.disciplinas = new ArrayList<>();
		adicionaDisciplina(disciplina);
		this.nota = 4;
		this.saldo = 0;
		super.setTipo("tutor");
		this.atendimento = new Atendimento();
	}

	/**
	 * Adiciona uma disciplina a lista de disciplinas do tutor.
	 * 
	 * @param disciplina2
	 *            eh a nova disciplina do tutor.
	 */
	public void adicionaDisciplina(String disciplina) {
		this.disciplinas.add(disciplina);
	}

	/**
	 * Confere se o tutor ja eh tutor de determinada disciplina.
	 * 
	 * @param disciplina
	 *            eh a disciplina a ser verificada.
	 * @return retorna true caso ja seja tutor da disciplina, false caso contrario.
	 */
	public boolean confereSeJaEtutorDaDisciplina(String disciplina) {
		for (String nome : disciplinas) {
			if (nome.equalsIgnoreCase(disciplina)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Cadastra o horario de atendimento do tutor.
	 * 
	 * @param horario
	 *            eh o horario de atendimento.
	 * @param dia
	 *            eh o dia do atendimento.
	 */
	public void cadastrarHorario(String horario, String dia) {
		this.atendimento.cadastrarHorario(horario, dia);

	}

	/**
	 * Cadatra local de atendimento do tutor.
	 * 
	 * @param local
	 *            eh o local do atendimento.
	 */
	public void cadastrarLocalDeAtendimento(String local) {
		this.atendimento.cadastrarLocalDeAtendimento(local);
	}

	/**
	 * Consulta o horario de atendimento do tutor.
	 * 
	 * @param horario
	 *            eh o horario a ser verificado.
	 * @param dia
	 *            eh o dia a ser verificado.
	 * @return retorna true se houver o horario, false caso contrario.
	 */
	public boolean consultaHorario(String horario, String dia) {
		return atendimento.consultaHorario(horario, dia);
	}

	/**
	 * Consulta o local de atendimento do tutor.
	 * 
	 * @param local
	 *            eh o local a ser verificado.
	 * @return retorna true se o local for valido, false caso contrario.
	 */
	public boolean consultaLocal(String local) {
		return atendimento.consultaLocal(local);
	}
}
