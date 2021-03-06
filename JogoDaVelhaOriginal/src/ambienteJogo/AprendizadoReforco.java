package ambienteJogo;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import persistenciaDados.ManipulacaoArquivos;

public class AprendizadoReforco{
	private ArrayList<Matriz> matrizesArquivo;
	private ArrayList<Matriz> matrizTemporaria;
	private ManipulacaoArquivos arquivo;

	public AprendizadoReforco(){
		arquivo = new ManipulacaoArquivos();
		arquivo.novoArquivo();
		matrizesArquivo = arquivo.lerArquivo();
		matrizTemporaria = new ArrayList<Matriz>();
	}

	public double[] jogadaCPU(Matriz estadoAtual){
		estadoAtual.numeracaoMatriz = -1;
		double resultado [] = new double [4];
		boolean lembrouJogada = false;

		for(int i = 0; i < matrizesArquivo.size(); i++){
			if(matrizesArquivo.get(i).comparaTodos(estadoAtual)){
				resultado = matrizesArquivo.get(i).verificaMelhorAcao();
				lembrouJogada = true;
				estadoAtual.numeracaoMatriz = i;
				break;
			}
		}

		Matriz matrizAux = new Matriz(estadoAtual);
		matrizTemporaria.add(matrizAux);

		if(!lembrouJogada){
			resultado = matrizAux.verificaMelhorAcaoRandomica();
		}

		matrizTemporaria.get(matrizTemporaria.size() - 1).matriz[(int)resultado[0]][(int)resultado[1]].setPorcentagem(1);
		return resultado;
	}

	public void finalPartida(int vencedor, double taxaAprendizagem){
		if(vencedor == 0){
			for (int i = 0; i < matrizTemporaria.size(); i++){
				if(matrizTemporaria.get(i).numeracaoMatriz == -1){					
					matrizTemporaria.get(i).numeracaoMatriz = matrizesArquivo.size();
					Matriz matrizAux = new Matriz(matrizTemporaria.get(i));
					matrizAux.voltaPorcentagemInicial();
					matrizAux.atualizaPorcentagemEstado(matrizTemporaria.get(i).matriz, taxaAprendizagem);
					matrizesArquivo.add(matrizAux);
				}
				else {
					matrizesArquivo.get(matrizTemporaria.get(i).numeracaoMatriz).atualizaPorcentagemEstado(matrizTemporaria.get(i).matriz, taxaAprendizagem);
				}
			}
		}
		else if(vencedor == 1){
			for(int i = 0; i < matrizTemporaria.size();i++){
				if(matrizTemporaria.get(i).numeracaoMatriz == -1){
					matrizTemporaria.get(i).numeracaoMatriz = matrizesArquivo.size();
					Matriz matrizAux = new Matriz(matrizTemporaria.get(i));
					matrizAux.voltaPorcentagemInicial();
					int linhaColuna[] = matrizTemporaria.get(i).pegaValor1();
					matrizTemporaria.get(i).matriz[linhaColuna[0]][linhaColuna[1]].setPorcentagem(0);
					matrizAux.atualizaPorcentagemEstado(matrizTemporaria.get(i).matriz, taxaAprendizagem);
					matrizesArquivo.add(matrizAux);
				}
				else {
					int linhaColuna[] = matrizTemporaria.get(i).pegaValor1();
					matrizTemporaria.get(i).matriz[linhaColuna[0]][linhaColuna[1]].setPorcentagem(0);
					matrizesArquivo.get(matrizTemporaria.get(i).numeracaoMatriz).atualizaPorcentagemEstado(matrizTemporaria.get(i).matriz, taxaAprendizagem);
				}				
			}
		}
		else {
			for(int i = 0; i < matrizTemporaria.size();i++){
				if(matrizTemporaria.get(i).numeracaoMatriz == -1){
					matrizTemporaria.get(i).numeracaoMatriz = matrizesArquivo.size();
					Matriz matrizAux = new Matriz(matrizTemporaria.get(i));
					matrizAux.voltaPorcentagemInicial();
					int linhaColuna[] = matrizTemporaria.get(i).pegaValor1();
					matrizTemporaria.get(i).matriz[linhaColuna[0]][linhaColuna[1]].setPorcentagem(0.5);
					matrizAux.atualizaPorcentagemEstado(matrizTemporaria.get(i).matriz, taxaAprendizagem);
					matrizesArquivo.add(matrizAux);

				}
				else {
					int linhaColuna[] = matrizTemporaria.get(i).pegaValor1();
					matrizTemporaria.get(i).matriz[linhaColuna[0]][linhaColuna[1]].setPorcentagem(0.5);
					matrizesArquivo.get(matrizTemporaria.get(i).numeracaoMatriz).atualizaPorcentagemEstado(matrizTemporaria.get(i).matriz, taxaAprendizagem);
				}				
			}			
		}
		matrizTemporaria = new ArrayList<Matriz>();
		for(int i = 0; i < matrizesArquivo.size(); i++){
			for(int linha = 0; linha < 3; linha++){
				for(int coluna = 0; coluna < 3; coluna++){
					System.out.println("linha,coluna: "+linha+","+coluna+"\n"+matrizesArquivo.get(i).matriz[linha][coluna].getPorcentagem());
				}
			}			
		}
	}
	
	public void gravarArquivo(){
		arquivo.gravaArquivo(matrizesArquivo);
	}
	
	public void deletarArquivo(){
		arquivo.deletarArquivo();
	}
}
