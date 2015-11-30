package persistenciaDados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import ambienteJogo.Matriz;
//import Matriz;


public class ManipulacaoArquivos {
	public void novoArquivo(){
		try{
			File arquivo = new File("Inteligencia.arp");
			if(!arquivo.exists()){
				arquivo.createNewFile();
			}
		} 
		catch(Exception erro){
			JOptionPane.showMessageDialog(null, "Erro", "Erro ao salvar arquivo", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public ArrayList<Matriz> lerArquivo(){
		ArrayList<Matriz> matrizArquivo = new ArrayList<Matriz>();
		try{
			File arquivo = new File("Inteligencia.arp");
			if(arquivo.exists() && arquivo.length() > 0){
				FileInputStream fi = new FileInputStream(arquivo);
				ObjectInputStream ois = new ObjectInputStream(fi);
				matrizArquivo = (ArrayList<Matriz>) ois.readObject();
				ois.close();
				fi.close();
			}
		} 
		catch(Exception erro){
			JOptionPane.showMessageDialog(null, "Erro ao abrir arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		return matrizArquivo;
	}
	
	public void gravaArquivo(ArrayList<Matriz> matrizArquivo){
		try {
			FileOutputStream fi = new FileOutputStream(
					"Inteligencia.arp");
			ObjectOutputStream arquivo = new ObjectOutputStream(fi);
			arquivo.writeObject(matrizArquivo);
			arquivo.close();
			fi.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void deletarArquivo(){
		File arquivo = new File("Inteligencia.arp");
		arquivo.delete();
		novoArquivo();
	}
	
}
