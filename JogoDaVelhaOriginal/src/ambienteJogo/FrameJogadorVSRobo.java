package ambienteJogo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class FrameJogadorVSRobo extends JFrame{
	JLabel lbAprendizado, lbFundo, lbVitoriasJogador, lbVitoriasCPU, lbEmpates, lbRobo, lbBalao, lbFalaRobo, lbPlacar;
	JButton btJogo [][], btAprendizadoOK;
	JTextField tfAprendizado;
	JPanel plJogo, plPlacar;
	ImageIcon icBalaoAmarelo, icBalaoAzul, icBalaoBranco, icBalaoVerde, icBalaoVermelho, icO, icX, icRobo, icBranco;
	final int APRENDIZADO = 0, JOGO = 1, HUMORALEGRE = 0, HUMORRAIVA = 1, HUMORLEMBROU = 2, HUMORESQUECEU = 3, HUMORMODIFICOUAPRENDIZADO = 4; 
	AprendizadoReforco ar;
	Matriz estadoAtual;
	int vitoriasJogador, vitoriasComputador, empates;

	public FrameJogadorVSRobo(){
		inicializaFrame();
		inicializaComponentes();
	}

	private void inicializaFrame(){
		try {  
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  

		this.setTitle("Jogo da velha - Aprendizado por reforço");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagens/robotLayoutPrincipal.png"));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.setSize(1024, 550);
		this.setLocationRelativeTo(this);
		this.setVisible(true);
	}

	private void inicializaComponentes(){
		plJogo = new JPanel();
		plJogo.setBackground(Color.WHITE);
		add(plJogo);
		plJogo.setBounds(350, 5, 450, 450);
		plJogo.setLayout(null);

		lbAprendizado = new JLabel("Aprendizado");
		add(lbAprendizado);
		lbAprendizado.setBounds(145, 5, 150, 20);

		tfAprendizado = new JTextField("0.1");
		add(tfAprendizado);
		tfAprendizado.setBounds(5, 30, 340, 30);
		tfAprendizado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {
				String caracteres="0987654321.";
				if(!caracteres.contains(ev.getKeyChar()+"")){
					ev.consume();
				}
			}

		});

		btAprendizadoOK = new JButton("OK");
		add(btAprendizadoOK);
		btAprendizadoOK.setBounds(5, 65, 340, 30);

		plPlacar = new JPanel();
		plPlacar.setBackground(Color.YELLOW);
		add(plPlacar);
		plPlacar.setBounds(5, 100, 340, 200);

		lbPlacar = new JLabel("Placar");
		plPlacar.add(lbPlacar);
		lbPlacar.setFont(new Font("Arial", Font.BOLD, 20));
		lbPlacar.setBounds(10, 90, 150, 50);

		lbVitoriasJogador = new JLabel("Vitórias do Jogador: ");
		plPlacar.add(lbVitoriasJogador);
		lbVitoriasJogador.setBounds(5, 80, 150, 20);

		lbVitoriasCPU = new JLabel("Vitórias do Jogador: ");
		plPlacar.add(lbVitoriasJogador);
		lbVitoriasJogador.setBounds(5, 110, 150, 20);

		lbEmpates = new JLabel("Empates: ");
		plPlacar.add(lbEmpates);
		lbEmpates.setBounds(5, 140, 150, 20);


		icBalaoAmarelo = new ImageIcon("Imagens/balao-amarelo.png");
		icBalaoAzul = new ImageIcon("Imagens/balao-azul.png");
		icBalaoBranco = new ImageIcon("Imagens/balao-branco.png");
		icBalaoVerde = new ImageIcon("Imagens/balao-verde.png");
		icBalaoVermelho = new ImageIcon("Imagens/balao-vermelho.png");
		icRobo = new ImageIcon("Imagens/robotLayoutPrincipal.png");
		icO = new ImageIcon("Imagens/O-icon.png");
		icX = new ImageIcon("Imagens/X-icon.png");
		icBranco = new ImageIcon("Imagens/Branco.png");

		Image img = icO.getImage() ;  
		Image newimg = img.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH ) ;  
		icO = new ImageIcon(newimg);

		Image img1 = icBranco.getImage();
		Image newimg1 = img1.getScaledInstance(135, 135,  java.awt.Image.SCALE_SMOOTH ) ;
		icBranco = new ImageIcon(newimg1);

		lbBalao = new JLabel();
		lbBalao.setIcon(icBalaoBranco);
		add(lbBalao);
		lbBalao.setBounds(805, 5, 200, 200);

		lbRobo = new JLabel();
		lbRobo.setIcon(icRobo);
		add(lbRobo);
		lbRobo.setBounds(805, 250, 200, 200);

		vitoriasJogador = 0;
		vitoriasComputador = 0;
		empates = 0;

		btJogo = new JButton[3][3];
		for(int linha = 0; linha < 3; linha++){
			for(int coluna = 0; coluna < 3; coluna++){
				btJogo[linha][coluna] = new JButton();
				plJogo.add(btJogo[linha][coluna]);
				btJogo[linha][coluna].setBounds(coluna*150, linha*150, 150, 150);
				btJogo[linha][coluna].addActionListener(new eventosJogo(linha, coluna, JOGO));
				btJogo[linha][coluna].setIcon(icBranco);
			}
		}
		ar = new AprendizadoReforco();
		this.limpaEstadoAtual();
	}

	private class eventosJogo implements ActionListener{
		int linha, coluna, tipoAlteracao;

		public eventosJogo(int linha, int coluna, int tipoAlteracao){
			this.linha = linha;
			this.coluna = coluna;
			this.tipoAlteracao = tipoAlteracao;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			btJogo[linha][coluna].setIcon(icX);
			atualizaEstadoAtual();
			if(!FinalDaPartida()){
				int jogadaCPU[] = ar.jogadaCPU(estadoAtual);
				btJogo[jogadaCPU[0]][jogadaCPU[1]].setIcon(icO);
				atualizaEstadoAtual();				
				FinalDaPartida();
			}
		}
	}

	public void limpaEstadoAtual(){
		estadoAtual = new Matriz();
		for(int linha = 0; linha < 3; linha++){
			for(int coluna = 0; coluna < 3; coluna++){
				CelulaMatriz aux = new CelulaMatriz();				 
				estadoAtual.matriz[linha][coluna] = aux;
			}
		}
	}

	public boolean FinalDaPartida() { // Verificando vitorias e empates
		//verificndo se a vitória é do jogador 1;
		boolean acabou = false;
		if ((btJogo[0][0].getIcon().equals(btJogo[0][1].getIcon()) && btJogo[0][1].getIcon().equals(btJogo[0][2].getIcon()))&& btJogo[0][0].getIcon().equals(icX)|| 
				(btJogo[1][0].getIcon().equals(btJogo[1][1].getIcon()) && btJogo[1][1].getIcon().equals(btJogo[1][2].getIcon()))&& btJogo[1][0].getIcon().equals(icX)|| 
				(btJogo[2][0].getIcon().equals(btJogo[2][1].getIcon()) && btJogo[2][1].getIcon().equals(btJogo[2][2].getIcon()))&& btJogo[2][0].getIcon().equals(icX)||
				(btJogo[0][0].getIcon().equals(btJogo[1][0].getIcon()) && btJogo[1][0].getIcon().equals(btJogo[2][0].getIcon()))&& btJogo[0][0].getIcon().equals(icX)|| 
				(btJogo[0][1].getIcon().equals(btJogo[1][1].getIcon()) && btJogo[1][1].getIcon().equals(btJogo[2][1].getIcon()))&& btJogo[0][1].getIcon().equals(icX)|| 
				(btJogo[0][2].getIcon().equals(btJogo[1][2].getIcon()) && btJogo[1][2].getIcon().equals(btJogo[2][2].getIcon()))&& btJogo[0][2].getIcon().equals(icX)||
				(btJogo[0][0].getIcon().equals(btJogo[1][1].getIcon()) && btJogo[1][1].getIcon().equals(btJogo[2][2].getIcon()))&& btJogo[0][0].getIcon().equals(icX)|| 
				(btJogo[0][2].getIcon().equals(btJogo[1][1].getIcon()) && btJogo[1][1].getIcon().equals(btJogo[2][0].getIcon()))&& btJogo[0][2].getIcon().equals(icX)) 
		{
			JOptionPane.showMessageDialog(null,"Vencedor é o 1º jogador");
			limpaEstadoAtual();
			ar.finalPartida(1, (float)0.1);
			Restart();
			vitoriasJogador++;
			lbVitoriasJogador.setText("Vitórias do 1º Jogador: "+vitoriasJogador);
			acabou = true;
			//verificndo se a vitória é do computador		
		} else if ((btJogo[0][0].getIcon().equals(btJogo[0][1].getIcon()) && btJogo[0][1].getIcon().equals(btJogo[0][2].getIcon()))&& btJogo[0][0].getIcon().equals(icO)|| 
				(btJogo[1][0].getIcon().equals(btJogo[1][1].getIcon()) && btJogo[1][1].getIcon().equals(btJogo[1][2].getIcon()))&& btJogo[1][0].getIcon().equals(icO)|| 
				(btJogo[2][0].getIcon().equals(btJogo[2][1].getIcon()) && btJogo[2][1].getIcon().equals(btJogo[2][2].getIcon()))&& btJogo[2][0].getIcon().equals(icO)||
				(btJogo[0][0].getIcon().equals(btJogo[1][0].getIcon()) && btJogo[1][0].getIcon().equals(btJogo[2][0].getIcon()))&& btJogo[0][0].getIcon().equals(icO)|| 
				(btJogo[0][1].getIcon().equals(btJogo[1][1].getIcon()) && btJogo[1][1].getIcon().equals(btJogo[2][1].getIcon()))&& btJogo[0][1].getIcon().equals(icO)|| 
				(btJogo[0][2].getIcon().equals(btJogo[1][2].getIcon()) && btJogo[1][2].getIcon().equals(btJogo[2][2].getIcon()))&& btJogo[0][2].getIcon().equals(icO)||
				(btJogo[0][0].getIcon().equals(btJogo[1][1].getIcon()) && btJogo[1][1].getIcon().equals(btJogo[2][2].getIcon()))&& btJogo[0][0].getIcon().equals(icO)|| 
				(btJogo[0][2].getIcon().equals(btJogo[1][1].getIcon()) && btJogo[1][1].getIcon().equals(btJogo[2][0].getIcon()))&& btJogo[0][2].getIcon().equals(icO)) 
		{
			JOptionPane.showMessageDialog(null,"Vencedor é o computador");
			limpaEstadoAtual();
			ar.finalPartida(0,(float)0.1);
			Restart();
			vitoriasComputador++;
			lbVitoriasCPU.setText("Vitórias do computador: "+vitoriasComputador);
			acabou = true;
			//verificndo se a ocorreu um empate;
		} else if (!(btJogo[0][0].getIcon().equals(icBranco))
				&& !(btJogo[0][1].getIcon().equals(icBranco))
				&& !(btJogo[0][2].getIcon().equals(icBranco))
				&& !(btJogo[1][0].getIcon().equals(icBranco))
				&& !(btJogo[1][1].getIcon().equals(icBranco))
				&& !(btJogo[1][2].getIcon().equals(icBranco))
				&& !(btJogo[2][0].getIcon().equals(icBranco))
				&& !(btJogo[2][1].getIcon().equals(icBranco))
				&& !(btJogo[2][2].getIcon().equals(icBranco))) 
		{
			JOptionPane.showMessageDialog(null,"Empate");
			limpaEstadoAtual();
			ar.finalPartida(2,(float)0.1);
			Restart();
			empates++;
			lbEmpates.setText("Empates: "+empates);
			acabou = true;
		}
		return acabou;
	}

	public void Restart() 
	{
		btJogo[0][0].setIcon(icBranco);
		btJogo[0][1].setIcon(icBranco);
		btJogo[0][2].setIcon(icBranco);
		btJogo[1][0].setIcon(icBranco);
		btJogo[1][1].setIcon(icBranco);
		btJogo[1][2].setIcon(icBranco);
		btJogo[2][0].setIcon(icBranco);
		btJogo[2][1].setIcon(icBranco);
		btJogo[2][2].setIcon(icBranco);
	}


	public void atualizaEstadoAtual(){
		estadoAtual = new Matriz();
		for(int linha = 0; linha < 3; linha++){
			for(int coluna = 0; coluna < 3; coluna++){
				CelulaMatriz aux = new CelulaMatriz();
				if(btJogo[linha][coluna].getIcon().equals(icX)){
					aux.setJogadorCPUVazio(1);
					estadoAtual.matriz[linha][coluna] = aux;
				}
				else if(btJogo[linha][coluna].getIcon().equals(icO)){
					aux.setJogadorCPUVazio(-1);
					estadoAtual.matriz[linha][coluna] = aux;
				}
				else {
					aux.setJogadorCPUVazio(0);
					estadoAtual.matriz[linha][coluna] = aux;					
				}
			}
		}
	}

	public static void main(String []args){
		FrameJogadorVSRobo frame = new FrameJogadorVSRobo();
	}


}
