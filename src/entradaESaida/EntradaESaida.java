package entradaESaida;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class EntradaESaida {

	/**
	 * Salva o estado atual do sistema.
	 * 
	 * @param objeto
	 *            O objeto a ser salvo.
	 * @param nomeArquivo
	 *            O nome do arquivo onde sera salvo o objeto.
	 * @throws IOException
	 *             A excecao de Entrada e Saida.
	 */
	public void salvar(Object objeto, String nomeArquivo) throws IOException {
		ObjectOutputStream objectOut = null;
		try {
			objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nomeArquivo)));
			objectOut.writeObject(objeto);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (objectOut != null)
				objectOut.close();
		}
	}

	/**
	 * Carrega o estado do sistema salvo anteriormente.
	 * 
	 * @param nomeArquivo
	 *            O nome do arquivo onde esta salvo o estado.
	 * @return O objeto.
	 * @throws IOException
	 *             A excecao de entrada e saida.
	 * @throws ClassNotFoundException
	 *             A excecao de classe nao encontrada.
	 */
	public Object carregar(String nomeArquivo) throws IOException, ClassNotFoundException {
		ObjectInputStream arqObjectos = null;
		try {
			arqObjectos = new ObjectInputStream(new FileInputStream(nomeArquivo));
			return arqObjectos.readObject();
		} finally {
			if (arqObjectos != null)
				arqObjectos.close();
		}
	}
}
