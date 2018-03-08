package principal;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Tutor {
	private String tutorMatricula;
	private int proficiencia, idTutor, saldo;
	private double nota;
	private Atendimento atendimento;
	private Disciplina disciplinas;

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
	public Tutor(String tutorMatricula,int idTutor) {
		this.tutorMatricula = tutorMatricula;
		this.disciplinas = new Disciplina();
		this.nota = 4;
		this.idTutor = idTutor;
		this.atendimento = new Atendimento();
		this.saldo = 0;
	}

	public double getTaxaTutor() {
		double taxa = 0;
		if (pegarNivel().equals("TOP")) {
			taxa = (90 + ((getNota() - 4.5)* 10)) / 100.0;
		} else if (pegarNivel().equals("Tutor")) {
			taxa = 80 / 100.0;
		} else if (pegarNivel().equals("Aprendiz")) {
			taxa = (0.4 - ((3.0 - getNota())*10)/100);
		}
		return taxa;
	}
	
	public boolean temDisciplina(String disciplina) {
		return this.disciplinas.temDisciplina(disciplina);
	}
	
	public void adicionarDisciplina(String disciplina, int proficiencia) {
		this.disciplinas.adicionarDisciplina(disciplina, proficiencia);
	}
	/**
	 * Recupera a matricula do tutor.
	 * 
	 * @return retorna a matricula do tutor.
	 */
	public String getTutorMatricula() {
		return this.tutorMatricula;
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
		} else if (3 < this.nota && this.nota <= 4.5) {
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
	public void receberDoacao(double valor) {
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

	/**
	 * Vai pegar o valor da nota e retornar formatado
	 * 
	 * @return retorna uma s
	 */
	public String pegarNota() {
		return String.format("%.2f", getNota());
	}
	
	
}