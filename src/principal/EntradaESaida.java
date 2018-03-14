package principal;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import controladores.ControllerAjuda;
import controladores.ControllerAluno;
import controladores.ControllerCaixa;
import controladores.ControllerTutor;

public class EntradaESaida {

	public void salvar(Object objeto, String nomeArquivo) throws IOException {
		ObjectOutputStream objectOut = null;
		try {
			objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nomeArquivo)));			
			objectOut.writeObject(objeto);
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (objectOut != null) objectOut.close();
		}
	}
	
	public Object carregar(String nomeArquivo) throws IOException, ClassNotFoundException {
		ObjectInputStream arqObjectos = null;
		try{
			arqObjectos = new ObjectInputStream(new FileInputStream(nomeArquivo));
			return arqObjectos.readObject();
		}finally{
			if(arqObjectos != null)
				arqObjectos.close();
		}
	}
//
//	public ControllerTutor carregarTutor(String nomeArquivo) throws IOException, ClassNotFoundException {
//		ObjectInputStream arqObjectos = null;
//		try{
//			arqObjectos = new ObjectInputStream(new FileInputStream(nomeArquivo));
//			return (ControllerTutor) arqObjectos.readObject();
//		}finally{
//			if(arqObjectos != null)
//				arqObjectos.close();
//		}
//	}
//	
//	
//	public ControllerAjuda carregarAjuda(String nomeArquivo) throws IOException, ClassNotFoundException {
//		ObjectInputStream arqObjectos = null;
//		try{
//			arqObjectos = new ObjectInputStream(new FileInputStream(nomeArquivo));
//			return (ControllerAjuda) arqObjectos.readObject();
//		}finally{
//			if(arqObjectos != null)
//				arqObjectos.close();
//		}
//	}
//	
//	public ControllerCaixa carregarCaixa(String nomeArquivo) throws IOException, ClassNotFoundException {
//		ObjectInputStream arqObjectos = null;
//		try{
//			arqObjectos = new ObjectInputStream(new FileInputStream(nomeArquivo));
//			return (ControllerCaixa) arqObjectos.readObject();
//		}finally{
//			if(arqObjectos != null)
//				arqObjectos.close();
//		}
//	}
	
//	public void salvarControllerAluno(ControllerAluno cAluno, String nomeArquivo) throws IOException {
//		ObjectOutputStream objectOut = null;
//		try {
//			objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nomeArquivo)));			
//			objectOut.writeObject(cAluno);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		finally {
//			if (objectOut != null) objectOut.close();
//		}
//	}
//
//	public void salvarControllerTutor(ControllerTutor cTutor, String nomeArquivo) throws IOException {
//		ObjectOutputStream objectOut = null;
//		try {
//			objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nomeArquivo)));			
//			objectOut.writeObject(cTutor);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		finally {
//			if (objectOut != null) objectOut.close();
//		}
//	}
//
//	public void salvarControllerAjuda(ControllerAjuda cAjuda, String nomeArquivo) throws IOException {
//		ObjectOutputStream objectOut = null;
//		try {
//			objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nomeArquivo)));			
//			objectOut.writeObject(cAjuda);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		finally {
//			if (objectOut != null) objectOut.close();
//		}
//	}
//
//		
//	public void salvarControllerCaixa(ControllerCaixa cCaixa, String nomeArquivo) throws IOException {
//		ObjectOutputStream objectOut = null;
//		try {
//			objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nomeArquivo)));			
//			objectOut.writeObject(cCaixa);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		finally {
//			if (objectOut != null) objectOut.close();
//		}
//	}
}
