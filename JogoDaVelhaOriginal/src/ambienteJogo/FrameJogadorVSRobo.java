package ambienteJogo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.management.relation.RelationNotification;
import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class FrameJogadorVSRobo extends JFrame{
	JLabel lbAprendizado, lbFundo, lbVitoriasJogador, lbVitoriasCPU, lbEmpates, lbRobo, lbBalao, lbPlacar;
	JButton btJogo [][], btAprendizadoOK, btReiniciarPlacar, btEsquecer;
	JTextField tfAprendizado;
	JPanel plJogo, plPlacar;
	ImageIcon icBalaoAmarelo, icBalaoAzul, icBalaoBranco, icBalaoVerde, icBalaoVermelho, icO, icX, icRobo, icBranco;
	final int APRENDIZADO = 0, JOGO = 1, REINICIARPLACAR = 2, ESQUECERAPRENDIZADO = 3; 
	AprendizadoReforco ar;
	Matriz estadoAtual;
	int vitoriasJogador, vitoriasComputador, empates, jogou[][];
	double aprendizado;
	boolean iniciouJogada;
	
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
		this.setSize(1024, 490);
		this.setLocationRelativeTo(this);
		this.setVisible(true);
	}

	private void inicializaComponentes(){
		plJogo = new JPanel();
		plJogo.setBackground(Color.WHITE);
		add(plJogo);
		plJogo.setBounds(300, 5, 450, 450);
		plJogo.setLayout(null);

		lbAprendizado = new JLabel("Aprendizado");
		add(lbAprendizado);
		lbAprendizado.setBounds(120, 5, 150, 20);

		tfAprendizado = new JTextField("0.1");
		add(tfAprendizado);
		tfAprendizado.setBounds(5, 30, 290, 30);
		tfAprendizado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {
				String caracteres="0987654321.";
				lbBalao.setIcon(icBalaoAmarelo);
				lbBalao.setText("<html>Vou modificar <br>meu aprendizado!!!<html>");
				lbBalao.setHorizontalTextPosition( SwingConstants.CENTER );
				lbBalao.setVerticalTextPosition( SwingConstants.CENTER );					

				
				if(!caracteres.contains(ev.getKeyChar()+"")){
					ev.consume();
				}
			}

		});

		btAprendizadoOK = new JButton("OK");
		add(btAprendizadoOK);
		btAprendizadoOK.setBounds(5, 65, 290, 30);
		btAprendizadoOK.addActionListener(new eventosJogo(-1, -1, APRENDIZADO));

		plPlacar = new JPanel();
		plPlacar.setBackground(Color.YELLOW);
		add(plPlacar);
		plPlacar.setBounds(5, 100, 290, 320);
		plPlacar.setLayout(null);

		lbPlacar = new JLabel("Placar");
		plPlacar.add(lbPlacar);
		lbPlacar.setFont(new Font("Arial", Font.BOLD, 20));
		lbPlacar.setBounds(115, 5, 150, 50);

		lbVitoriasJogador = new JLabel("Vitórias do Jogador: "+vitoriasJogador);
		plPlacar.add(lbVitoriasJogador);
		lbVitoriasJogador.setBounds(5, 100, 250, 20);
		lbVitoriasJogador.setFont(new Font("Arial", Font.BOLD, 20));

		lbVitoriasCPU = new JLabel("Vitórias do Computador: "+vitoriasComputador);
		plPlacar.add(lbVitoriasCPU);
		lbVitoriasCPU.setBounds(5, 130, 250, 20);
		lbVitoriasCPU.setFont(new Font("Arial", Font.BOLD, 20));

		lbEmpates = new JLabel("Empates: "+empates);
		plPlacar.add(lbEmpates);
		lbEmpates.setBounds(5, 160, 250, 20);
		lbEmpates.setFont(new Font("Arial", Font.BOLD, 20));

		btReiniciarPlacar = new JButton("Reiniciar Placar");
		add(btReiniciarPlacar);
		btReiniciarPlacar.setBounds(5, 425, 290, 30);
		btReiniciarPlacar.addActionListener(new eventosJogo(-1, -1, REINICIARPLACAR));

		icBalaoAmarelo = new ImageIcon("Imagens/balao-amarelo.png");
		icBalaoAzul = new ImageIcon("Imagens/balao-azul.png");
		icBalaoBranco = new ImageIcon("Imagens/balao-branco.png");
		icBalaoVerde = new ImageIcon("Imagens/balao-verde.png");
		icBalaoVermelho = new ImageIcon("Imagens/balao-vermelho.png");
		icRobo = new ImageIcon("Imagens/robotLayoutPrincipal.png");
		icO = new ImageIcon("Imagens/O-icon.png");
		icX = new ImageIcon("Imagens/X-icon.png");
		icBranco = new ImageIcon("Imagens/robotIcone.png");

		Image img = icO.getImage() ;  
		Image newimg = img.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH ) ;  
		icO = new ImageIcon(newimg);

		Image img1 = icBranco.getImage();
		Image newimg1 = img1.getScaledInstance(1, 1,  java.awt.Image.SCALE_SMOOTH ) ;
		icBranco = new ImageIcon(newimg1);

		lbBalao = new JLabel("Olá, vamos jogar?", icBalaoAzul, SwingConstants.CENTER);
		lbBalao.setHorizontalTextPosition( SwingConstants.CENTER );
		lbBalao.setVerticalTextPosition( SwingConstants.CENTER );
		add(lbBalao);
		lbBalao.setBounds(730, 5, 300, 200);

		lbRobo = new JLabel();
		lbRobo.setIcon(icRobo);
		add(lbRobo);
		lbRobo.setBounds(815, 115, 200, 200);

		btEsquecer = new JButton("Esquecer Aprendizado");
		add(btEsquecer);
		btEsquecer.setBounds(750, 300, 265, 30);
		btEsquecer.addActionListener(new eventosJogo(-1, -1, ESQUECERAPRENDIZADO));

		vitoriasJogador = 0;
		vitoriasComputador = 0;
		empates = 0;

		btJogo = new JButton[3][3];
		jogou = new int[3][3];
		for(int linha = 0; linha < 3; linha++){
			for(int coluna = 0; coluna < 3; coluna++){
				btJogo[linha][coluna] = new JButton();
				plJogo.add(btJogo[linha][coluna]);
				btJogo[linha][coluna].setBounds(coluna*150, linha*150, 150, 150);
				btJogo[linha][coluna].addActionListener(new eventosJogo(linha, coluna, JOGO));
				btJogo[linha][coluna].setIcon(icBranco);
				jogou[linha][coluna] = 0;
			}
		}
		ar = new AprendizadoReforco();
		this.limpaEstadoAtual();
		
		aprendizado = 0.1;
		iniciouJogada = false;
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				ar.gravarArquivo();
			}
		});

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
			switch(tipoAlteracao){
			case JOGO:
				if(jogou[linha][coluna] == 0){
					btJogo[linha][coluna].setIcon(icX);
					atualizaEstadoAtual();
					jogou[linha][coluna] = 1;
					if(!FinalDaPartida()){
						double jogadaCPU[] = ar.jogadaCPU(estadoAtual);
						btJogo[(int)jogadaCPU[0]][(int)jogadaCPU[1]].setIcon(icO);
						atualizaEstadoAtual();
						jogou[(int)jogadaCPU[0]][(int)jogadaCPU[1]] = 1;
						if(jogadaCPU[2] == 0){
							lbBalao.setIcon(icBalaoVerde);
							lbBalao.setText("Sua Vez!");
							lbBalao.setHorizontalTextPosition( SwingConstants.CENTER );
							lbBalao.setVerticalTextPosition( SwingConstants.CENTER );							
						}
						else {
							lbBalao.setIcon(icBalaoAmarelo);
							lbBalao.setText("<html>Lembrei dessa <br>jogada, com a ação <br>valendo: <br>"+jogadaCPU[3]+"</html>");
							lbBalao.setHorizontalTextPosition( SwingConstants.CENTER );
							lbBalao.setVerticalTextPosition( SwingConstants.CENTER );
						}
						FinalDaPartida();
					}									
				}
				else{
					lbBalao.setIcon(icBalaoVermelho);
					lbBalao.setText("<html>Essa casa já <br>foi preenchida</html>");
					lbBalao.setHorizontalTextPosition( SwingConstants.CENTER );
					lbBalao.setVerticalTextPosition( SwingConstants.CENTER );					
				}
				iniciouJogada = true;
				break;
			case APRENDIZADO:
				if(!iniciouJogada){
					if(aprendizado < Double.parseDouble(tfAprendizado.getText())){
						lbBalao.setIcon(icBalaoAzul);
						lbBalao.setText("<html>Vou Aprender <br>mais rápido</html>");
						lbBalao.setHorizontalTextPosition( SwingConstants.CENTER );
						lbBalao.setVerticalTextPosition( SwingConstants.CENTER );					
					}
					else{
						lbBalao.setIcon(icBalaoVermelho);
						lbBalao.setText("<html>Vou Aprender <br>mais devagar<html>");
						lbBalao.setHorizontalTextPosition( SwingConstants.CENTER );
						lbBalao.setVerticalTextPosition( SwingConstants.CENTER );					
					}
					aprendizado = Double.parseDouble(tfAprendizado.getText());
				}
				else{
					lbBalao.setIcon(icBalaoAmarelo);
					lbBalao.setText("<html>Só pode modificar<br> o aprendizado no inicio<br> da rodada!!!</html>");
					lbBalao.setHorizontalTextPosition( SwingConstants.CENTER );
					lbBalao.setVerticalTextPosition( SwingConstants.CENTER );					
					tfAprendizado.setText("0.1");
				}
				break;
			case REINICIARPLACAR:
				lbBalao.setIcon(icBalaoBranco);
				lbBalao.setText("<html>O placar foi <br>reiniciado!!!</html>");
				lbBalao.setHorizontalTextPosition( SwingConstants.CENTER );
				lbBalao.setVerticalTextPosition( SwingConstants.CENTER );					
				vitoriasJogador = 0;
				vitoriasComputador = 0;
				empates = 0;
				lbVitoriasJogador.setText("Vitórias do Jogador: "+vitoriasJogador);
				lbVitoriasCPU.setText("Vitórias do computador: "+vitoriasComputador);
				lbEmpates.setText("Empates: "+empates);
				break;
			case ESQUECERAPRENDIZADO:
				lbBalao.setIcon(icBalaoBranco);
				lbBalao.setText("Esqueci de tudo...");
				lbBalao.setHorizontalTextPosition( SwingConstants.CENTER );
				lbBalao.setVerticalTextPosition( SwingConstants.CENTER );					
				ar.deletarArquivo();
				break;
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
			ar.finalPartida(1,aprendizado);
			Restart();
			vitoriasJogador++;
			lbVitoriasJogador.setText("Vitórias do Jogador: "+vitoriasJogador);
			lbBalao.setIcon(icBalaoVermelho);
			lbBalao.setText("Perdi essa rodada...");
			lbBalao.setHorizontalTextPosition( SwingConstants.CENTER );
			lbBalao.setVerticalTextPosition( SwingConstants.CENTER );					
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
			ar.finalPartida(0,aprendizado);
			Restart();
			vitoriasComputador++;
			lbVitoriasCPU.setText("Vitórias do computador: "+vitoriasComputador);
			lbBalao.setIcon(icBalaoVerde);
			lbBalao.setText("Consegui Vencer!!!");
			lbBalao.setHorizontalTextPosition( SwingConstants.CENTER );
			lbBalao.setVerticalTextPosition( SwingConstants.CENTER );					

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
			ar.finalPartida(2,aprendizado);
			Restart();
			empates++;
			lbEmpates.setText("Empates: "+empates);
			lbBalao.setIcon(icBalaoBranco);
			lbBalao.setText("Empatamos");
			lbBalao.setHorizontalTextPosition( SwingConstants.CENTER );
			lbBalao.setVerticalTextPosition( SwingConstants.CENTER );					

			acabou = true;
		}
		return acabou;
	}

	public void Restart() 
	{
		for(int linha = 0; linha < 3; linha++){
			for(int coluna = 0; coluna < 3; coluna++){
				btJogo[linha][coluna].setIcon(icBranco);
				jogou[linha][coluna] = 0;
			}
		}
		iniciouJogada = false;
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
