package controladores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import principal.Aluno;

public class ControllerAluno {

	private Map<String, Aluno> listaDeAlunos;

	public ControllerAluno() {
		this.listaDeAlunos = new HashMap<>();
	}

	private Aluno criaAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		verificaCadastroAluno(nome, matricula, telefone, email);
		return new Aluno(nome, matricula, codigoCurso, telefone, email);
	}

	public void adicionaAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		Aluno aluno = criaAluno(nome, matricula, codigoCurso, telefone, email);
		this.listaDeAlunos.put(matricula, aluno);
	}

	public String recuperaAluno(String matricula) {
		verificaMatricula("busca por aluno", matricula);
		return this.listaDeAlunos.get(matricula).toString();
	}

	public Aluno getAlunoPorEmail(String email) {
		if (verificaEmail(email))
			for (Aluno aluno : this.listaDeAlunos.values()) {
				if (aluno.getEmail().equalsIgnoreCase(email))
					return aluno;
			}
		return null;
	}

	public ArrayList getListaDeAlunos() {
		ArrayList<Aluno> alunos = new ArrayList<Aluno>(listaDeAlunos.values());
		return alunos;
	}

	public String getInfoAluno(String matricula, String atributo) {
		verificaMatricula("obtencao de informacao de aluno", matricula);
		switch (atributo.toLowerCase()) {
		case "nome":
			return this.listaDeAlunos.get(matricula).getNome();
		case "telefone":
			return this.listaDeAlunos.get(matricula).getTelefone();
		case "email":
			return this.listaDeAlunos.get(matricula).getEmail();
		default:
			throw new IllegalArgumentException("Erro na obtencao de informacao de aluno: Atributo invalido.");
		}
	}

	public boolean matriculaCadastrada(String matricula) {
		return listaDeAlunos.containsKey(matricula);
	}

	/**
	 * Verifica se o e-mail é valido, se a matrícula está cadastrada no sistema de
	 * alunos e se o nome é vazio ou null
	 * 
	 * @param nome
	 *            nome do aluno em String
	 * @param matricula
	 *            matricula do aluno em String
	 * 
	 * @param telefone
	 *            eh o telefone.
	 * @param email
	 *            email do aluno em String
	 */
	public void verificaCadastroAluno(String nome, String matricula, String telefone, String email) {
		String erro = "";
		if (!verificaEmail(email))
			erro = "Email invalido";
		else if (this.listaDeAlunos.containsKey(matricula))
			erro = "Aluno de mesma matricula ja cadastrado";
		else if (nome.trim().equals("") || nome == null)
			erro = "Nome nao pode ser vazio ou nulo";
		else if (telefone == null)
			erro = "Telefone nao pode ser nulo";

		if (!erro.equals(""))
			throw new IllegalArgumentException("Erro no cadastro de aluno: " + erro);
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
	 * @param matriculaValida
	 *            um booleano true caso a matricula esteja no hashMap e false caso
	 *            nao
	 * @param metodo
	 *            nome do metodo que chama esse método em String
	 * @param matricula
	 *            do aluno em String
	 */

	public void verificaMatricula(String quemSouEu, String matricula) {
		if (!this.listaDeAlunos.containsKey(matricula))
			throw new IllegalArgumentException("Erro na " + quemSouEu + ": Aluno nao encontrado");
	}
}
