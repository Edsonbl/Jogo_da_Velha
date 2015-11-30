package persistenciaDados;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
//import Matriz;

public class ManipulacaoArquivos {
	public void novoArquivo(){
		try{
			FileOutputStream fo = new FileOutputStream("Inteligencia.arp");
			ObjectOutputStream arquivo = new ObjectOutputStream(fo);
			
			arquivo.close();
			fo.close();
		} 
		catch(Exception erro){
			JOptionPane.showMessageDialog(null, "Erro", "Erro ao salvar arquivo", JOptionPane.ERROR_MESSAGE);
		}
	}
	/*
	public ArrayList<Matriz> abrirAmbiente(){
		String diretorioArquivo = null;
		JFileChooser escolhe = new JFileChooser();
		Celula[][] ambiente = new Celula[7][20];
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivos de ambiente (*.rpa)", "rpa");
		escolhe.setFileFilter(filtro);

		if (escolhe.showOpenDialog(null) == 0){
			diretorioArquivo = escolhe.getSelectedFile().getPath();
			diretorioArquivo = diretorioArquivo.replace('\\', '/');

			try{
				FileInputStream fi = new FileInputStream(diretorioArquivo);
				ObjectInputStream arquivo = new ObjectInputStream(fi);
				ambiente = (Celula[][]) arquivo.readObject();
				arquivo.close();
				fi.close();
				local = diretorioArquivo;
			} 
			catch(Exception erro){
				JOptionPane.showMessageDialog(null, "Erro ao abrir arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}

		return ambiente;
	}*/		

	
}
