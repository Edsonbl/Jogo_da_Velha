package ambienteJogo;
import java.io.Serializable;

public class CelulaMatriz implements Serializable{
	private int jogadorCPUVazio;
	private double porcentagem;
	
	public CelulaMatriz(){
		jogadorCPUVazio = 0;
		porcentagem = 0.5;
	}
	
	public int getJogadorCPUVazio() {
		return jogadorCPUVazio;
	}
	public void setJogadorCPUVazio(int jogadorCPUVazio) {
		this.jogadorCPUVazio = jogadorCPUVazio;
	}
	public double getPorcentagem() {
		return porcentagem;
	}
	public void setPorcentagem(double porcentagem) {
		this.porcentagem = porcentagem;
	}
	
	
}
