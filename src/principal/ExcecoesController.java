package principal;

import java.util.HashMap;
import java.util.Map;

public class ExcecoesController {

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
	public void verificaCadastroAluno(boolean emailValido, String nome, String matricula, String email) {
		String erro = "";
		if (!verificaEmail(email)) {
			erro = "Email invalido";
		} else if (emailValido) {
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
	public boolean verificaDadosParaTornarTutor(boolean emailValido, boolean ehTutor, Tutor tutor, String disciplina,
			int proficiencia) {
		if (!emailValido) {
			throw new IllegalArgumentException("Erro na definicao de papel: Tutor nao encontrado");
		} else if (proficiencia < 1 || proficiencia > 5) {
			throw new IllegalArgumentException("Erro na definicao de papel: Proficiencia invalida");
		} else if (ehTutor) {
			
			if(tutor.confereSeJaEtutorDaDisciplina(disciplina)) {
				throw new IllegalArgumentException("Erro na definicao de papel: Ja eh tutor dessa disciplina");
			} else {
				return false;
			}
		}
		return true;
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

	public void confereCadastrarHorario(Tutor tutor, String email, String horario, String dia) {
		String erro = "";
		if (email.trim().equals("")){
			erro = "email nao pode ser vazio ou em branco" ;
		}else if (tutor == null)  {
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
	public void confereCadastrarLocalDeAtendimento(Tutor tutor, String email, String local) {
		String erro = "";
		if (email.trim().equals("")) {
			erro = "email nao pode ser vazio ou em branco";
		}else if (tutor == null) {
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
