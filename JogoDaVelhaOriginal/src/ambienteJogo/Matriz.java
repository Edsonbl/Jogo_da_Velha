package ambienteJogo;
import java.io.Serializable;
import java.util.Random;

public class Matriz implements Serializable{
	CelulaMatriz [][] matriz;
	int numeracaoMatriz;
		
	public Matriz(Matriz estadoArquivo){
		this.numeracaoMatriz = estadoArquivo.numeracaoMatriz;
		this.matriz = new CelulaMatriz[3][3];
		for (int linha = 0; linha < 3;linha++){
			for (int coluna = 0; coluna < 3; coluna++){
				CelulaMatriz aux = new CelulaMatriz();
				aux.setJogadorCPUVazio(estadoArquivo.matriz[linha][coluna].getJogadorCPUVazio());
				aux.setPorcentagem(estadoArquivo.matriz[linha][coluna].getPorcentagem());
				matriz[linha][coluna] = aux;
			}
		}
	}
	
	public Matriz(){
		matriz = new CelulaMatriz[3][3];
	}
	
	public boolean comparaTodos(Matriz estadoAtual){
		boolean resultado = true;
		for (int linha = 0; linha < 3; linha++){
			for (int coluna = 0; coluna < 3; coluna++){
				if(matriz[linha][coluna].getJogadorCPUVazio() != estadoAtual.matriz[linha][coluna].getJogadorCPUVazio()){
					resultado = false;					
					break;
				}
			}
			if(resultado == false){
				break;
			}
		}
		return resultado;
	}
	
	public void atualizaPorcentagemEstado(CelulaMatriz matrizAux[][], float taxaAprendizado){
		for(int linha = 0; linha < 3; linha++){
			for(int coluna = 0; coluna < 3; coluna++){
				matriz[linha][coluna].setPorcentagem(matriz[linha][coluna].getPorcentagem() + taxaAprendizado*(matrizAux[linha][coluna].getPorcentagem() - matriz[linha][coluna].getPorcentagem()));
			}
		}		
	}
	
	public int [] verificaMelhorAcao(){
		int [] resultado = new int[2];
		float melhorAcao = matriz[0][0].getPorcentagem();
		
		for(int linha = 0; linha < 3; linha++){
			for (int coluna = 1; coluna < 3; coluna++){
				if(matriz[linha][coluna].getPorcentagem() > melhorAcao){
					melhorAcao = matriz[linha][coluna].getPorcentagem();
					System.out.println("Melhor Ação: "+melhorAcao);
					resultado[0] = linha;
					resultado[1] = coluna;
				}
			}
		}
		if(melhorAcao <= 0.5){
			resultado = verificaMelhorAcaoRandomica();
			System.out.println("Escolheu Melhor ação randômica");
		}
		return resultado;
	}
	
	public int [] verificaMelhorAcaoRandomica(){
		int resultado[] = new int [2];
		Random random = new Random();
		boolean encontrouRandomico = false;
		int linha;
		int coluna;
		while(!encontrouRandomico){
			linha = random.nextInt(3);
			coluna = random.nextInt(3);
			if(matriz[linha][coluna].getJogadorCPUVazio() == 0){
				encontrouRandomico = true;
				resultado[0] = linha;
				resultado[1] = coluna;
			}
		}
		return resultado;
	}
	
	public int[] pegaValor1(){
		int []resultado = new int[2];
		for(int linha = 0; linha < 3; linha++){
			for(int coluna = 0; coluna < 3; coluna++){
				if(matriz[linha][coluna].getPorcentagem() == 1){
					resultado[0] = linha;
					resultado[1] = coluna;
				}
			}
		}
		return resultado;
	}
	
	public void voltaPorcentagemInicial(){
		for(int linha = 0; linha < 3; linha++){
			for(int coluna = 0; coluna < 3; coluna++){
				matriz[linha][coluna].setPorcentagem((float)0.5);
			}
		}
	}
}
