package principal;

import java.util.HashMap;
import java.util.Map;

public class Auxiliar {

	/**
	 * Verifica se o e-mail é valido, se a matrícula está cadastrada no sistema de
	 * alunos e se o nome é vazio ou null
	 * 
	 * @param listaDeAlunos
	 *            hashMap de alunos com a chave sendo a String matricula
	 * @param nome
	 *            nome do aluno em String
	 * @param matricula
	 *            matricula do aluno em String
	 * @param email
	 *            email do aluno em String
	 */
	public void verificaCadastroAluno(Map<String, Aluno> listaDeAlunos, String nome, String matricula, String email) {
		String erro = "";
		if (!verificaEmail(email)) {
			erro = "Email invalido";
		} else if (listaDeAlunos.containsKey(matricula)) {
			erro = "Aluno de mesma matricula ja cadastrado";
		} else if (nome.trim().equals("") || nome == null) {
			erro = "Nome nao pode ser vazio ou nulo";

		}
		if (!erro.equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de aluno: " + erro);
		}

	}

	/**
	 * Verifica se o email passado como parametro eh valido possui "@" e digitos
	 * antes e dps dele
	 * 
	 * @param email
	 *            email do a ser avaliado
	 * @return um booleano true caso seja email valido e false caso nao
	 */
	private boolean verificaEmail(String email) {
		int posicao = email.indexOf("@");
		if (posicao == -1) {
			return false;
		} else if (email.length() < 3) {
			return false;
		} else if (posicao == 0 || posicao == email.length() - 1) {
			return false;
		}
		return true;

	}

	/**
	 * Verifica se a matricula nos metodos de getInfoAluno e recuperaAluno sao
	 * validas e foram cadastradas no map de alunos
	 * 
	 * @param listaDeAlunos
	 *            hashMap de alunos com a chave sendo a String matricula
	 * @param metodo
	 *            nome do metodo que chama esse método em String
	 * @param matricula
	 *            do aluno em String
	 */

	public void verificaMatricula(Map<String, Aluno> listaDeAlunos, String metodo, String matricula) {
		if (metodo.equals("getInfoAluno") && !listaDeAlunos.containsKey(matricula)) {
			throw new IllegalArgumentException("Erro na obtencao de informacao de aluno: Aluno nao encontrado");
		} else if (metodo.equals("recuperaAluno") && !listaDeAlunos.containsKey(matricula)) {
			throw new IllegalArgumentException("Erro na busca por aluno: Aluno nao encontrado");
		}

	}

	/**
	 * Verifica se a matricula eh valida, so o valor de proficiencia eh valido, e se
	 * o possivel tutor ja eh tutor dessa disciplina
	 * 
	 * @param listaDeAlunos
	 *            hashMap de alunos com a chave sendo a String matricula
	 * @param matricula
	 *            do aluno em String
	 * @param disciplina
	 *            nome da disciplina em String
	 * @param proficiencia
	 *            valor de proficiencia em inteiro
	 * @return um booleano true se tiver tudo ok e false se nao
	 */
	public boolean verificaDadosParaTornarTutor(Map<String, Aluno> listaDeAlunos, String matricula, String disciplina,
			int proficiencia) {
		if (!listaDeAlunos.containsKey(matricula)) {
			throw new IllegalArgumentException("Erro na definicao de papel: Tutor nao encontrado");
		} else if (proficiencia < 1 || proficiencia > 5) {
			throw new IllegalArgumentException("Erro na definicao de papel: Proficiencia invalida");
		} else if (listaDeAlunos.get(matricula).getTipo().equals("tutor")) {
			String email = listaDeAlunos.get(matricula).getEmail();
			if (recuperaTutorPorEmail(listaDeAlunos, email).confereSeJaEtutorDaDisciplina(disciplina)) {
				throw new IllegalArgumentException("Erro na definicao de papel: Ja eh tutor dessa disciplina");
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * recupera um tutor pelo email
	 * 
	 * @param listaDeAlunos
	 *            hashMap de alunos com a chave sendo a String matricula
	 * @param email
	 *            email do aluno em String
	 * @return o Tutor procurado
	 */

	public Tutor recuperaTutorPorEmail(Map<String, Aluno> listaDeAlunos, String email) {
		for (Aluno aluno : listaDeAlunos.values()) {
			if (aluno.getEmail().equalsIgnoreCase(email)) {
				if (verificaTutor(listaDeAlunos, aluno.getMatricula())) {
					Tutor tutor = (Tutor) aluno;
					return tutor;
				}
			}
		}
		return null;
	}

	/**
	 * Verifica se o aluno é tutor ou nao
	 * 
	 * @param listaDeAlunos
	 *            hashMap de alunos com a chave sendo a String matricula
	 * @param matricula
	 *            matricula do aluno em String
	 * @return um booleano de sim para caso ja seja tuto e false caso nao
	 */
	public boolean verificaTutor(Map<String, Aluno> listaDeAlunos, String matricula) {
		if (listaDeAlunos.get(matricula).getTipo().equals("tutor")) {
			return true;
		}
		return false;
	}

	/**
	 * Vai conferir se o email eh de um tutor, se o email eh vazio ou em branco se o
	 * horario eh vaziou ou em branco e se o dia eh vazio ou em branco
	 * 
	 * @param listaDeAlunos
	 *            hashMap de alunos com a chave sendo a String matricula
	 * @param email
	 *            email do aluno em String
	 * @param horario
	 *            horario para encontro em String
	 * @param dia
	 *            dia do encontro em String
	 */

	public void confereCadastrarHorario(Map<String, Aluno> listaDeAlunos, String email, String horario, String dia) {
		String erro = "";
		if (email.trim().equals("")){
			erro = "email nao pode ser vazio ou em branco" ;
		}else if (recuperaTutorPorEmail(listaDeAlunos, email) == null)  {
			erro = "tutor nao cadastrado";
		} else if (horario.trim().equals("")) {
			erro = "horario nao pode ser vazio ou em branco";
		} else if (dia.trim().equals("")) {
			erro = "dia nao pode ser vazio ou em branco";
		}
		if (!erro.equals("")) {
			throw new IllegalArgumentException("Erro no cadastrar horario: " + erro);
		}

	}

	/**
	 * Confere se o email eh de um tutor, se o email eh vazio ou em branco e se o
	 * local eh vazio ou em branco
	 * 
	 * @param listaDeAlunos
	 *            hashMap de alunos com a chave sendo a String matricula
	 * @param email
	 *            email do aluno em String
	 * @param local
	 *            local para o encontro em String
	 */
	public void confereCadastrarLocalDeAtendimento(Map<String, Aluno> listaDeAlunos, String email, String local) {
		String erro = "";
		if (email.trim().equals("")) {
			erro = "email nao pode ser vazio ou em branco";
		}else if (recuperaTutorPorEmail(listaDeAlunos, email) == null) {
			erro = "tutor nao cadastrado";
		} 
		else if (local.trim().equals("")) {
			erro = "local nao pode ser vazio ou em branco";
		}
		if (!erro.equals("")) {
			throw new IllegalArgumentException("Erro no cadastrar local de atendimento: " + erro);
		}

	}

}
