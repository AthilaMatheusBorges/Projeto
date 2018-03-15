package tutoria;

import java.io.Serializable;

public class Tutor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2339490982350104891L;
	private Nivel nivel;
	private String tutorMatricula;
	private int idTutor, saldo;
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
		this.nivel = NivelTutor.TUTOR;
		this.tutorMatricula = tutorMatricula;
		this.disciplinas = new Disciplina();
		this.nota = 4;
		this.idTutor = idTutor;
		this.atendimento = new Atendimento();
		this.saldo = 0;
	}

	/**
	 * Recupera a taxa do tutor.
	 * @return retorna a taxa do tutor.
	 */
	public double getTaxaTutor() {
		return this.nivel.calculaTaxa(this.nota);
	}
	
	/**
	 * Verifica se o tutor ja eh tutor da disciplina.
	 * @param disciplina eh a disciplina a ser verificada.
	 * @return retorna true se for tutor da disciplina, false caso contrario.
	 */
	public boolean temDisciplina(String disciplina) {
		return this.disciplinas.temDisciplina(disciplina);
	}
	
	/**
	 * Adiciona a disciplina as disciplinas do tutor.
	 * @param disciplina eh a disciplina a ser adicionada.
	 * @param proficiencia eh a proficiencia do tutor sobre a disciplina.
	 */
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
	 * Avalida o tutor e atualiza o nivel.
	 * 
	 * @param nota2
	 *            valor da avaliacao.
	 */
	public void avaliarTutor(int nota2) {
		this.nota = (this.nota * 5 + nota2) / 6;
		atualizarNivel();
	}

	/**
	 * Recupera a nota do tutor.
	 * @return retorna a nota do tutor.
	 */
	public double getNota() {
		return this.nota;
	}

	/**
	 * Pega o nivel do tutor.
	 * @return retorna uma string do nivel do tutor.
	 */
	public String pegarNivel() {
		return this.nivel.pegarNivel();
	}
	
	/**
	 * Atualiza o nivel do tutor a partir de sua nota.
	 */
	private void atualizarNivel() {
		if(this.nota > 4.5)
			this.nivel = NivelTutor.TOP;
		else if (3 < this.nota && this.nota <= 4.5) 
			this.nivel = NivelTutor.TUTOR;
		else
			this.nivel = NivelTutor.APRENDIZ;
	}

	/**
	 * Recebe uma doacao para o tutor.
	 * 
	 * @param valor
	 *            valor da doacao.
	 */
	public void receberDoacao(double valor) {
		this.saldo += valor;

	}

	/**
	 * Retorna o saldo do tutor.
	 * 
	 * @return valor do saldo.
	 */
	public int getSaldo() {
		return saldo;
	}

	/**
	 * Recupera a nota do tutor.
	 * 
	 * @return retorna a nota do tutor com duas casas decimais.
	 */
	public String pegarNota() {
		return String.format("%.2f", getNota());
	}
	
	
}