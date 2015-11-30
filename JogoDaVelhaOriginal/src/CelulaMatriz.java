import java.io.Serializable;

public class CelulaMatriz implements Serializable{
	private int jogadorCPUVazio;
	private float porcentagem;
	
	public CelulaMatriz(){
		jogadorCPUVazio = 0;
		porcentagem = (float)0.5;
	}
	
	public int getJogadorCPUVazio() {
		return jogadorCPUVazio;
	}
	public void setJogadorCPUVazio(int jogadorCPUVazio) {
		this.jogadorCPUVazio = jogadorCPUVazio;
	}
	public float getPorcentagem() {
		return porcentagem;
	}
	public void setPorcentagem(float porcentagem) {
		this.porcentagem = porcentagem;
	}
	
	
}
