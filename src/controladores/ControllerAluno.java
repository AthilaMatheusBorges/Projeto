package controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import aluno.Aluno;
import comparadores.OrdenaPorNomeAlunos;

/**
 * Responsavel por controlar os alunos. Armazena os alunos em um mapa<matricula,
 * Aluno>. Todos os metodos referentes a Aluno estao encapsulados nesta classe.
 * 
 * @author Roundhouse Kick Group
 *
 */
public class ControllerAluno implements Serializable{

	private static final long serialVersionUID = 6225428294330458797L;
	private Map<String, Aluno> listaDeAlunos;

	/**
	 * Inicia o mapa da lista de alunos.
	 */
	public ControllerAluno() {
		this.listaDeAlunos = new HashMap<>();
	}

	/**
	 * Metodo que cria um aluno. Antes verifica se os parametros sao validos.
	 * 
	 * @param nome
	 *            eh o nome do aluno.
	 * @param matricula
	 *            eh a matricula do aluno.
	 * @param codigoCurso
	 *            eh o codigo do curso.
	 * @param telefone
	 *            eh o telefone do aluno.
	 * @param email
	 *            eh o email do aluno.
	 * @return retorna o Aluno criado.
	 */
	private Aluno criaAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		verificaCadastroAluno(nome, matricula, telefone, email);
		return new Aluno(nome, matricula, codigoCurso, telefone, email);
	}

	/**
	 * Cria um aluno e diciona no mapa.
	 * 
	 * @param nome
	 *            eh o nome do aluno.
	 * @param matricula
	 *            eh a matricula do aluno.
	 * @param codigoCurso
	 *            eh o codigo do curso.
	 * @param telefone
	 *            eh o telefone do aluno.
	 * @param email
	 *            eh o email do aluno.
	 */
	public void adicionaAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		Aluno aluno = criaAluno(nome, matricula, codigoCurso, telefone, email);
		this.listaDeAlunos.put(matricula, aluno);
	}

	/**
	 * Recupera um aluno a apartir da matricula.
	 * 
	 * @param matricula
	 *            eh a matricula do aluno.
	 * @return retorna o toString() do aluno.
	 */
	public String recuperaAluno(String matricula) {
		verificaMatricula("busca por aluno", matricula);
		return this.listaDeAlunos.get(matricula).toString();
	}

	/**
	 * Recupera um aluno a partir do email.
	 * 
	 * @param email
	 *            eh o email do aluno.
	 * @return retorna o objeto do aluno.
	 */
	public Aluno getAlunoPorEmail(String email) {
		if (verificaEmail(email))
			for (Aluno aluno : this.listaDeAlunos.values()) {
				if (aluno.getEmail().equalsIgnoreCase(email))
					return aluno;
			}
		return null;
	}
	
	public Aluno getAlunoPorMatricula(String matricula) {
		return listaDeAlunos.get(matricula);
	}

	/**
	 * Lista os alunos em ordem alfabetica.
	 * 
	 * @return retorna uma string com a lista de todos os alunos cadastrados.
	 */
	public String listarAlunos() {
		ArrayList<Aluno> alunos = new ArrayList<Aluno>(this.listaDeAlunos.values());
		Collections.sort(alunos, new OrdenaPorNomeAlunos());
		String lista = "";
		for (Aluno aluno : alunos) {
			lista += aluno.toString() + ", ";
		}
		if (lista.length() == 0)
			return "";
		return lista.substring(0, lista.length() - 2);
	}

	/**
	 * Obtem uma informacao do aluno.
	 * 
	 * @param matricula
	 *            eh a matricula do aluno.
	 * @param atributo
	 *            eh a informacao a ser recuperada do aluno.
	 * @return retorna a informacao do aluno.
	 */
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

	/**
	 * Verifica se a matricula esta cadastrada.
	 * 
	 * @param matricula
	 *            eh a matricula a ser verificada.
	 * @return retorna true se a matricula estiver cadastrada, false caso contrario.
	 */
	public boolean matriculaCadastrada(String matricula) {
		return listaDeAlunos.containsKey(matricula);
	}

	/**
	 * Verifica se o e-mail é valido, se a matrícula está cadastrada e se o nome é
	 * vazio ou null
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
	private void verificaCadastroAluno(String nome, String matricula, String telefone, String email) {
		String erro = "";
		if (!verificaEmail(email))
			erro = "Email invalido";
		else if (matriculaCadastrada(matricula))
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
	 * antes e depois.
	 * 
	 * @param email
	 *            email a ser avaliado
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
	 * @param quemSouEu
	 *            nome de quem esta sendo verificado.
	 * @param matricula
	 *            matricula do aluno
	 */
	private void verificaMatricula(String quemSouEu, String matricula) {
		if (!this.listaDeAlunos.containsKey(matricula))
			throw new IllegalArgumentException("Erro na " + quemSouEu + ": Aluno nao encontrado");
	}
	
	/**
	 * metodo que tem como finalidade retornar um array de alunos do controller de alunos
	 * @return
	 */
	public ArrayList<Aluno> getListaDeAlunos() {
		ArrayList<Aluno> alunos = new ArrayList<Aluno>(listaDeAlunos.values());
		return alunos;
	}
}
