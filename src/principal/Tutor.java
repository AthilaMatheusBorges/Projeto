package principal;

import java.util.ArrayList;

public class Tutor extends Aluno {
	private int proficiencia, idTutor, saldo;
	private ArrayList<String> disciplinas;
	private double nota;
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
	public Tutor(Aluno aluno, String disciplina, int proficiencia, int idTutor) {
		super(aluno.getNome(), aluno.getMatricula(), aluno.getCodigoCurso(), aluno.getTelefone(), aluno.getEmail());
		this.proficiencia = proficiencia;
		this.disciplinas = new ArrayList<>();
		adicionaDisciplina(disciplina);
		this.nota = 4;
		this.idTutor = idTutor;
		super.setTipo("tutor");
		this.atendimento = new Atendimento();
		this.saldo = 0;
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

	/**
	 * Retorna o idenficador do Tutor.
	 * 
	 * @return retorna o id do tutor.
	 */
	public int getId() {
		return this.idTutor;
	}

	/**
	 * Metodo que faz o calculo de avaliacao do tutor
	 * 
	 * @param nota2
	 *            valor da nota de uma ajuda em double
	 */
	public void avaliarTutor(int nota2) {
		this.nota = (this.nota * 5 + nota2) / 6;
	}

	/**
	 * metodo que retorna o valor da nota do tutor
	 */
	public double getNota() {
		return this.nota;
	}

	/**
	 * Metodo que apartir da nota de avaliacao do tutor retorna um String com o
	 * nivel dele
	 * 
	 * @return retorna uma String que representa o nivel do tutor
	 */
	public String pegarNivel() {
		if (this.nota > 4.5) {
			return "TOP";
		} else if (this.nota > 3 || this.nota <= 4.5) {
			return "Tutor";
		}
		return "Aprendiz";
	}

	/**
	 * Esse metodo recebe uma doacao para o tutor
	 * 
	 * @param valor
	 *            valor da doacao
	 */
	public void receberDoacao(int valor) {
		this.saldo += valor;

	}

	/**
	 * Retorna o saldo que o tutor tem
	 * 
	 * @return valor do saldo
	 */
	public int getSaldo() {
		return saldo;
	}
}