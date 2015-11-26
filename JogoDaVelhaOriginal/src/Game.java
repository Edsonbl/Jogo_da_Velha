import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class Game extends JFrame {
	
	JButton cbut [][];// centro 
	JButton bSair, bNovo, bSobre; // lest
	JPanel lpanel[];// lest
	JButton obut1; // oeste
	JPanel p, opanel[];// oeste
	JLabel txt1, txt2, txt3, txt4, txt5; // oeste
	JTextField campo1, campo2; // oeste
	boolean player = true; // player of the game
	int vic1, vic2, empate; // cont off victories and draws
	ImageIcon iconX, iconO, iconB; // Icones Para Os Botões
	FlowLayout oFlow;

	public Game() {

		super("Jogo da Velha:                       Version: Sinpsons");

		// CRIAÇÃO DO CONTAINER PRINCIPAL
		Container cp = getContentPane();

		// CONTAINER PRINCIPAL ADICIONANDO AO BorderLayout COM ESPAÇAMENTO DE
		// 10PX POR 10PX
		cp.setLayout(new BorderLayout(10, 10));

		// INICIALIZANDO OS PAINEIS LESTE E OESTE
		lpanel = new JPanel[9];
		opanel = new JPanel[16];

		// INICIALIZANDO O ARRAY OEST
		for (int i = 0; i < opanel.length; i++) {
			opanel[i] = new JPanel();
		}
		// INICIALIZANDO O ARRAY LESTE
		for (int i = 0; i < lpanel.length; i++) {
			lpanel[i] = new JPanel();
		}
		// INICIALIZANDO O CONTAINER NORTE E SUL
		Container norte = new JPanel(); // AINDA SEM FUNÇÃO
		Container sul = new JPanel(); // AINDA SEM FUNÇÃO

		// MONTANDO O PAINEL LESTE, VIDA A ELE :)
		Container leste = new JPanel();
		leste.setLayout(new GridLayout(9, 0));
		leste.add(lpanel[0]);
		leste.add(lpanel[1]);
		leste.add(lpanel[2].add(bSair = new JButton("Sair do Jogo")));
		leste.add(lpanel[3]);
		leste.add(lpanel[4].add(bNovo = new JButton("Novo Jogo")));
		leste.add(lpanel[5]);
		leste.add(lpanel[6].add(bSobre = new JButton("Sobre")));
		leste.add(lpanel[7]);
		leste.add(lpanel[8]);

		// INICIALIZANDO ICONES DOS BOTOES
		iconB = new ImageIcon(getClass().getResource("fotos/Branco.png"));
		iconX = new ImageIcon(getClass().getResource("fotos/x.png"));
		iconO = new ImageIcon(getClass().getResource("fotos/Rosquinha.png"));

		// INICIALIZANDO OS BOTOES, MONTANDO O PAINEL CENTRAL
		cbut = new JButton[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cbut[i][j] = new JButton();
			}
		}
		
		Container centro = new JPanel();
		centro.setLayout(new GridLayout(3, 3, 5, 5));
		centro.setSize(new Dimension(5, 5));
		
		centro.add(cbut[0][0] = new JButton(iconB));
		centro.add(cbut[0][1] = new JButton(iconB));
		centro.add(cbut[0][2] = new JButton(iconB));
		centro.add(cbut[1][0] = new JButton(iconB));
		centro.add(cbut[1][1] = new JButton(iconB));
		centro.add(cbut[1][2] = new JButton(iconB));
		centro.add(cbut[2][0] = new JButton(iconB));
		centro.add(cbut[2][1] = new JButton(iconB));
		centro.add(cbut[2][2] = new JButton(iconB));

		/* MONTANDO O CONTAINER OESTE UTILIZANDO PAINEIS EXTRAS PARA DAR UMA
		ESTETICA MAIS AGRADAVEL*/
		Container oeste = new JPanel(new GridLayout(16, 0, 5, 5));
		oeste.add(opanel[0].add(txt1 = new JLabel("1º Jogador : ")));
		oeste.add(opanel[1]);// .add(campo1 = new JTextField(10)));
		oeste.add(opanel[2].add(txt2 = new JLabel("Vitórias do 1º Jogador: 0")));
		oeste.add(opanel[3]);
		oeste.add(opanel[4]);
		oeste.add(opanel[5].add(txt3 = new JLabel("2º Jogador : ")));
		oeste.add(opanel[6]);// .add(campo2 = new JTextField(10)));
		oeste.add(opanel[7].add(txt4 = new JLabel("Vitórias do 2º Jogador: 0")));
		oeste.add(opanel[8]);
		oeste.add(opanel[9].add(txt5 = new JLabel("Empates: 0")));
		oeste.add(opanel[10]);
		oeste.add(opanel[11]);
		oeste.add(opanel[12]);// .add(obut1 = new JButton("Travar Nombres")));
		oeste.add(opanel[13]);
		oeste.add(opanel[14]);
		oeste.add(opanel[15]);
		oeste.setLayout(new BoxLayout(oeste, BoxLayout.Y_AXIS));

		// JUNTANDO OS PAINEIS
		cp.add(BorderLayout.NORTH, norte);
		cp.add(BorderLayout.SOUTH, sul);
		cp.add(BorderLayout.CENTER, centro);
		cp.add(BorderLayout.EAST, leste);
		cp.add(BorderLayout.WEST, oeste);

		//ACAO DAS JOGADAS
		MouseAdapter mouseGer = new MouseAdapter() {
			public void mousePressed(MouseEvent g) {
				//JOGADOR 1
				if (player == true) {

					if ((g.getSource() == cbut[0][0])&& (cbut[0][0].getIcon().equals(iconB))) {cbut[0][0].setIcon(iconX);
						player = false;
					} else if ((g.getSource() == cbut[0][1])&& (cbut[0][1].getIcon().equals(iconB))) {cbut[0][1].setIcon(iconX);
						player = false;
					} else if ((g.getSource() == cbut[0][2])&& (cbut[0][2].getIcon().equals(iconB))) {cbut[0][2].setIcon(iconX);
						player = false;
					} else if ((g.getSource() == cbut[1][0])&& (cbut[1][0].getIcon().equals(iconB))) {cbut[1][0].setIcon(iconX);
						player = false;
					} else if ((g.getSource() == cbut[1][1])&& (cbut[1][1].getIcon().equals(iconB))) {cbut[1][1].setIcon(iconX);
						player = false;
					} else if ((g.getSource() == cbut[1][2])&& (cbut[1][2].getIcon().equals(iconB))) {cbut[1][2].setIcon(iconX);
						player = false;
					} else if ((g.getSource() == cbut[2][0])&& (cbut[2][0].getIcon().equals(iconB))) {cbut[2][0].setIcon(iconX);
						player = false;
					} else if ((g.getSource() == cbut[2][1])&& (cbut[2][1].getIcon().equals(iconB))) {cbut[2][1].setIcon(iconX);
						player = false;
					} else if ((g.getSource() == cbut[2][2])&& (cbut[2][2].getIcon().equals(iconB))) {cbut[2][2].setIcon(iconX);
						player = false;
					}
				}
				//JOGADOR 2
				/*
				BOM E SO JOGAR O ALGORITIMO AQUI PARA QUE O JOGADOR 2 SEJA "CPU" NO LUGAR DOS IF'S 
				VEM O RESULTADO DO ALGORITIMO DEPOIS ALTERA O BOOLEAN "player" PARA QUE O PRIMEIRO JOGADOR JOGUE
				P.S.: DEXEI COMENTADO SO PARA QUESTÃO DE VISUALIZAR A IDEIA ANTERIOR!
				*/ 
				/*if (player == false) {

					if ((g.getSource() == cbut[0][0])&& (cbut[0][0].getIcon().equals(iconB))) {cbut[0][0].setIcon(iconO);
						player = true;
					} else if ((g.getSource() == cbut[0][1])&& (cbut[0][1].getIcon().equals(iconB))) {cbut[0][1].setIcon(iconO);
						player = true;
					} else if ((g.getSource() == cbut[0][2])&& (cbut[0][2].getIcon().equals(iconB))) {cbut[0][2].setIcon(iconO);
						player = true;
					} else if ((g.getSource() == cbut[1][0])&& (cbut[1][0].getIcon().equals(iconB))) {cbut[1][0].setIcon(iconO);
						player = true;
					} else if ((g.getSource() == cbut[1][1])&& (cbut[1][1].getIcon().equals(iconB))) {cbut[1][1].setIcon(iconO);
						player = true;
					} else if ((g.getSource() == cbut[1][2])&& (cbut[1][2].getIcon().equals(iconB))) {cbut[1][2].setIcon(iconO);
						player = true;
					} else if ((g.getSource() == cbut[2][0])&& (cbut[2][0].getIcon().equals(iconB))) {cbut[2][0].setIcon(iconO);
						player = true;
					} else if ((g.getSource() == cbut[2][1])&& (cbut[2][1].getIcon().equals(iconB))) {cbut[2][1].setIcon(iconO);
						player = true;
					} else if ((g.getSource() == cbut[2][2])&& (cbut[2][2].getIcon().equals(iconB))) {cbut[2][2].setIcon(iconO);
						player = true;
					}
				}*/
				FinalDaPartida();
			}
		};
		//ADD ACAO DOS BUTS
		cbut[0][0].addMouseListener(mouseGer);
		cbut[0][1].addMouseListener(mouseGer);
		cbut[0][2].addMouseListener(mouseGer);
		cbut[1][0].addMouseListener(mouseGer);
		cbut[1][1].addMouseListener(mouseGer);
		cbut[1][2].addMouseListener(mouseGer);
		cbut[2][0].addMouseListener(mouseGer);
		cbut[2][1].addMouseListener(mouseGer);
		cbut[2][2].addMouseListener(mouseGer);

		MouseAdapter mouseGerLeste = new MouseAdapter() {
			public void mousePressed(MouseEvent s) {
				if (s.getSource() == bSair) { // But "sair do jogo"
					System.exit(0);
				}
				if (s.getSource() == bNovo) { // But "novo jogo"
					Restart();
				}
				if (s.getSource() == bSobre) { // But "Sobre o jogo"
					// InfDoJogo();
					Sobre();
				}
			}
		};
		bSair.addMouseListener(mouseGerLeste);
		bNovo.addMouseListener(mouseGerLeste);
		bSobre.addMouseListener(mouseGerLeste);

		setResizable(false);
		setSize(600, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void FinalDaPartida() { // Verificando vitorias e empates
		//verificndo se a vitória é do jogador 1;
		if ((cbut[0][0].getIcon().equals(cbut[0][1].getIcon()) && cbut[0][1].getIcon().equals(cbut[0][2].getIcon()))&& cbut[0][0].getIcon().equals(iconX)|| 
			(cbut[1][0].getIcon().equals(cbut[1][1].getIcon()) && cbut[1][1].getIcon().equals(cbut[1][2].getIcon()))&& cbut[1][0].getIcon().equals(iconX)|| 
			(cbut[2][0].getIcon().equals(cbut[2][1].getIcon()) && cbut[2][1].getIcon().equals(cbut[2][2].getIcon()))&& cbut[2][0].getIcon().equals(iconX)||
			(cbut[0][0].getIcon().equals(cbut[1][0].getIcon()) && cbut[1][0].getIcon().equals(cbut[2][0].getIcon()))&& cbut[0][0].getIcon().equals(iconX)|| 
			(cbut[0][1].getIcon().equals(cbut[1][1].getIcon()) && cbut[1][1].getIcon().equals(cbut[2][1].getIcon()))&& cbut[0][1].getIcon().equals(iconX)|| 
			(cbut[0][2].getIcon().equals(cbut[1][2].getIcon()) && cbut[1][2].getIcon().equals(cbut[2][2].getIcon()))&& cbut[0][2].getIcon().equals(iconX)||
			(cbut[0][0].getIcon().equals(cbut[1][1].getIcon()) && cbut[1][1].getIcon().equals(cbut[2][2].getIcon()))&& cbut[0][0].getIcon().equals(iconX)|| 
			(cbut[0][2].getIcon().equals(cbut[1][1].getIcon()) && cbut[1][1].getIcon().equals(cbut[2][0].getIcon()))&& cbut[0][2].getIcon().equals(iconX)) 
		{
			Jogador_1_Win();
			// JOptionPane.showMessageDialog(null,"Vencedor é o 1º jogador");
			Restart();
			vic1++;
			txt2.setText("Vitórias do 1º Jogador: " + vic1);
			//verificndo se a vitória é do jogador 1;		
		} else if ((cbut[0][0].getIcon().equals(cbut[0][1].getIcon()) && cbut[0][1].getIcon().equals(cbut[0][2].getIcon()))&& cbut[0][0].getIcon().equals(iconO)|| 
				   (cbut[1][0].getIcon().equals(cbut[1][1].getIcon()) && cbut[1][1].getIcon().equals(cbut[1][2].getIcon()))&& cbut[1][0].getIcon().equals(iconO)|| 
				   (cbut[2][0].getIcon().equals(cbut[2][1].getIcon()) && cbut[2][1].getIcon().equals(cbut[2][2].getIcon()))&& cbut[2][0].getIcon().equals(iconO)||
				   (cbut[0][0].getIcon().equals(cbut[1][0].getIcon()) && cbut[1][0].getIcon().equals(cbut[2][0].getIcon()))&& cbut[0][0].getIcon().equals(iconO)|| 
				   (cbut[0][1].getIcon().equals(cbut[1][1].getIcon()) && cbut[1][1].getIcon().equals(cbut[2][1].getIcon()))&& cbut[0][1].getIcon().equals(iconO)|| 
				   (cbut[0][2].getIcon().equals(cbut[1][2].getIcon()) && cbut[1][2].getIcon().equals(cbut[2][2].getIcon()))&& cbut[0][2].getIcon().equals(iconO)||
				   (cbut[0][0].getIcon().equals(cbut[1][1].getIcon()) && cbut[1][1].getIcon().equals(cbut[2][2].getIcon()))&& cbut[0][0].getIcon().equals(iconO)|| 
				   (cbut[0][2].getIcon().equals(cbut[1][1].getIcon()) && cbut[1][1].getIcon().equals(cbut[2][0].getIcon()))&& cbut[0][2].getIcon().equals(iconO)) 
		{
			Jogador_2_Win();
			// JOptionPane.showMessageDialog(null,"Vencedor é o 2º jogador");
			Restart();
			vic2++;
			txt4.setText("Vitórias do 2º Jogador: " + vic2);
			//verificndo se a ocorreu um empate;
		} else if (!(cbut[0][0].getIcon().equals(iconB))
				&& !(cbut[0][1].getIcon().equals(iconB))
				&& !(cbut[0][2].getIcon().equals(iconB))
				&& !(cbut[1][0].getIcon().equals(iconB))
				&& !(cbut[1][1].getIcon().equals(iconB))
				&& !(cbut[1][2].getIcon().equals(iconB))
				&& !(cbut[2][0].getIcon().equals(iconB))
				&& !(cbut[2][1].getIcon().equals(iconB))
				&& !(cbut[2][2].getIcon().equals(iconB))) 
		{
			Empate();
			// JOptionPane.showMessageDialog(null,"Empate");
			Restart();
			empate++;
			txt5.setText("Empates: " + empate);
		}
	}

	public void Restart() 
	{
		cbut[0][0].setIcon(iconB);
		cbut[0][1].setIcon(iconB);
		cbut[0][2].setIcon(iconB);
		cbut[1][0].setIcon(iconB);
		cbut[1][1].setIcon(iconB);
		cbut[1][2].setIcon(iconB);
		cbut[2][0].setIcon(iconB);
		cbut[2][1].setIcon(iconB);
		cbut[2][2].setIcon(iconB);
		player = true;
	}

	public void Jogador_1_Win() 
	{
		new VictoryP1();
	}

	public void Jogador_2_Win() 
	{
		new VictoryP2();
	}

	public void Empate() 
	{
		new Empate();
	}

	public void Sobre() 
	{
		new Sobre();
	}

	public static void main(String[] args) 
	{
		new Game();
	}
}