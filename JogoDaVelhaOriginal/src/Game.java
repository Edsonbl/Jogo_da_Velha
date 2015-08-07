import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class Game extends JFrame {

	JButton cbut1, cbut2, cbut3, cbut4, cbut5, cbut6, cbut7, cbut8, cbut9, but; // centro
	JButton lbut1, lbut2, Sobrebut; // lest
	JPanel lpanel[];// lest
	JButton obut1; // oeste
	JPanel p, opanel[];// oeste
	JLabel txt1, txt2, txt3, txt4, txt5; // oeste
	JTextField campo1, campo2; // oeste
	boolean player = true; // player of the game
	int vic1, vic2, empate; // cont off victories and draws
	ImageIcon iconX, iconO, iconB; // ICONES PARA OS BOTÕES
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
		leste.add(lpanel[2].add(lbut1 = new JButton("Sair do Jogo")));
		leste.add(lpanel[3]);
		leste.add(lpanel[4].add(lbut2 = new JButton("Novo Jogo")));
		leste.add(lpanel[5]);
		leste.add(lpanel[6].add(Sobrebut = new JButton("Sobre")));
		leste.add(lpanel[7]);
		leste.add(lpanel[8]);

		// INICIALIZANDO ICONES DOS BOTOES
		iconB = new ImageIcon(getClass().getResource("fotos/Branco.png"));
		iconX = new ImageIcon(getClass().getResource("fotos/x.png"));
		iconO = new ImageIcon(getClass().getResource("fotos/Rosquinha.png"));

		// INICIALIZANDO OS BOTOES, MONTANDO O PAINEL CENTRAL
		Container centro = new JPanel();
		centro.setLayout(new GridLayout(3, 3, 5, 5));
		centro.setSize(new Dimension(5, 5));
		centro.add(cbut1 = new JButton(iconB));
		centro.add(cbut2 = new JButton(iconB));
		centro.add(cbut3 = new JButton(iconB));
		centro.add(cbut4 = new JButton(iconB));
		centro.add(cbut5 = new JButton(iconB));
		centro.add(cbut6 = new JButton(iconB));
		centro.add(cbut7 = new JButton(iconB));
		centro.add(cbut8 = new JButton(iconB));
		centro.add(cbut9 = new JButton(iconB));

		// MONTANDO O CONTAINER OESTE UTILIZANDO PAINEIS EXTRAS PARA DAR UMA
		// ESTETICA MAIS AGRADAVEL
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

		MouseAdapter mouseGer = new MouseAdapter() {
			public void mousePressed(MouseEvent g) {

				if (player == true) {

					if ((g.getSource() == cbut1)
							&& (cbut1.getIcon().equals(iconB))) {
						cbut1.setIcon(iconX);
						player = false;
					} else if ((g.getSource() == cbut2)
							&& (cbut2.getIcon().equals(iconB))) {
						cbut2.setIcon(iconX);
						player = false;
					} else if ((g.getSource() == cbut3)
							&& (cbut3.getIcon().equals(iconB))) {
						cbut3.setIcon(iconX);
						player = false;
					} else if ((g.getSource() == cbut4)
							&& (cbut4.getIcon().equals(iconB))) {
						cbut4.setIcon(iconX);
						player = false;
					} else if ((g.getSource() == cbut5)
							&& (cbut5.getIcon().equals(iconB))) {
						cbut5.setIcon(iconX);
						player = false;
					} else if ((g.getSource() == cbut6)
							&& (cbut6.getIcon().equals(iconB))) {
						cbut6.setIcon(iconX);
						player = false;
					} else if ((g.getSource() == cbut7)
							&& (cbut7.getIcon().equals(iconB))) {
						cbut7.setIcon(iconX);
						player = false;
					} else if ((g.getSource() == cbut8)
							&& (cbut8.getIcon().equals(iconB))) {
						cbut8.setIcon(iconX);
						player = false;
					} else if ((g.getSource() == cbut9)
							&& (cbut9.getIcon().equals(iconB))) {
						cbut9.setIcon(iconX);
						player = false;
					}
				}

				else if (player == false) {

					if ((g.getSource() == cbut1)
							&& (cbut1.getIcon().equals(iconB))) {
						cbut1.setIcon(iconO);
						player = true;
					} else if ((g.getSource() == cbut2)
							&& (cbut2.getIcon().equals(iconB))) {
						cbut2.setIcon(iconO);
						player = true;
					} else if ((g.getSource() == cbut3)
							&& (cbut3.getIcon().equals(iconB))) {
						cbut3.setIcon(iconO);
						player = true;
					} else if ((g.getSource() == cbut4)
							&& (cbut4.getIcon().equals(iconB))) {
						cbut4.setIcon(iconO);
						player = true;
					} else if ((g.getSource() == cbut5)
							&& (cbut5.getIcon().equals(iconB))) {
						cbut5.setIcon(iconO);
						player = true;
					} else if ((g.getSource() == cbut6)
							&& (cbut6.getIcon().equals(iconB))) {
						cbut6.setIcon(iconO);
						player = true;
					} else if ((g.getSource() == cbut7)
							&& (cbut7.getIcon().equals(iconB))) {
						cbut7.setIcon(iconO);
						player = true;
					} else if ((g.getSource() == cbut8)
							&& (cbut8.getIcon().equals(iconB))) {
						cbut8.setIcon(iconO);
						player = true;
					} else if ((g.getSource() == cbut9)
							&& (cbut9.getIcon().equals(iconB))) {
						cbut9.setIcon(iconO);
						player = true;
					}
				}
				FinalDaPartida();
			}
		};
		// add buts
		cbut1.addMouseListener(mouseGer);
		cbut2.addMouseListener(mouseGer);
		cbut3.addMouseListener(mouseGer);
		cbut4.addMouseListener(mouseGer);
		cbut5.addMouseListener(mouseGer);
		cbut6.addMouseListener(mouseGer);
		cbut7.addMouseListener(mouseGer);
		cbut8.addMouseListener(mouseGer);
		cbut9.addMouseListener(mouseGer);

		MouseAdapter mouseGerLeste = new MouseAdapter() {
			public void mousePressed(MouseEvent s) {
				if (s.getSource() == lbut1) { // But "sair do jogo"
					System.exit(0);
				}
				if (s.getSource() == lbut2) { // But "novo jogo"
					Restart();
				}
				if (s.getSource() == Sobrebut) { // But "Sobre o jogo"
					// InfDoJogo();
					Window4();
				}
			}
		};
		lbut1.addMouseListener(mouseGerLeste);
		lbut2.addMouseListener(mouseGerLeste);
		Sobrebut.addMouseListener(mouseGerLeste);

		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(600, 300);
	}

	public void FinalDaPartida() { // Verificando vitorias e empates
		if ((cbut1.getIcon().equals(cbut2.getIcon()) && cbut2.getIcon().equals(
				cbut3.getIcon()))
				&& cbut1.getIcon().equals(iconX)
				|| (cbut4.getIcon().equals(cbut5.getIcon()) && cbut5.getIcon()
						.equals(cbut6.getIcon()))
				&& cbut4.getIcon().equals(iconX)
				|| (cbut7.getIcon().equals(cbut8.getIcon()) && cbut8.getIcon()
						.equals(cbut9.getIcon()))
				&& cbut7.getIcon().equals(iconX)
				||

				(cbut1.getIcon().equals(cbut4.getIcon()) && cbut4.getIcon()
						.equals(cbut7.getIcon()))
				&& cbut1.getIcon().equals(iconX)
				|| (cbut2.getIcon().equals(cbut5.getIcon()) && cbut5.getIcon()
						.equals(cbut8.getIcon()))
				&& cbut2.getIcon().equals(iconX)
				|| (cbut3.getIcon().equals(cbut6.getIcon()) && cbut6.getIcon()
						.equals(cbut9.getIcon()))
				&& cbut3.getIcon().equals(iconX)
				||

				(cbut1.getIcon().equals(cbut5.getIcon()) && cbut5.getIcon()
						.equals(cbut9.getIcon()))
				&& cbut1.getIcon().equals(iconX)
				|| (cbut3.getIcon().equals(cbut5.getIcon()) && cbut5.getIcon()
						.equals(cbut7.getIcon()))
				&& cbut3.getIcon().equals(iconX)) {
			Window1();
			// JOptionPane.showMessageDialog(null,"Vencedor é o 1º jogador");
			Restart();
			vic1++;
			txt2.setText("Vitórias do 1º Jogador: " + vic1);
		
		} else if ((cbut1.getIcon().equals(cbut2.getIcon()) && cbut2.getIcon()
				.equals(cbut3.getIcon()))
				&& cbut1.getIcon().equals(iconO)
				|| (cbut4.getIcon().equals(cbut5.getIcon()) && cbut5.getIcon()
						.equals(cbut6.getIcon()))
				&& cbut4.getIcon().equals(iconO)
				|| (cbut7.getIcon().equals(cbut8.getIcon()) && cbut8.getIcon()
						.equals(cbut9.getIcon()))
				&& cbut7.getIcon().equals(iconO)
				||

				(cbut1.getIcon().equals(cbut4.getIcon()) && cbut4.getIcon()
						.equals(cbut7.getIcon()))
				&& cbut1.getIcon().equals(iconO)
				|| (cbut2.getIcon().equals(cbut5.getIcon()) && cbut5.getIcon()
						.equals(cbut8.getIcon()))
				&& cbut2.getIcon().equals(iconO)
				|| (cbut3.getIcon().equals(cbut6.getIcon()) && cbut6.getIcon()
						.equals(cbut9.getIcon()))
				&& cbut3.getIcon().equals(iconO)
				||

				(cbut1.getIcon().equals(cbut5.getIcon()) && cbut5.getIcon()
						.equals(cbut9.getIcon()))
				&& cbut1.getIcon().equals(iconO)
				|| (cbut3.getIcon().equals(cbut5.getIcon()) && cbut5.getIcon()
						.equals(cbut7.getIcon()))
				&& cbut3.getIcon().equals(iconO)) {
			Window2();
			// JOptionPane.showMessageDialog(null,"Vencedor é o 2º jogador");
			Restart();
			vic2++;
			txt4.setText("Vitórias do 2º Jogador: " + vic2);
		} else if (!(cbut1.getIcon().equals(iconB))
				&& !(cbut2.getIcon().equals(iconB))
				&& !(cbut3.getIcon().equals(iconB))
				&& !(cbut4.getIcon().equals(iconB))
				&& !(cbut5.getIcon().equals(iconB))
				&& !(cbut6.getIcon().equals(iconB))
				&& !(cbut7.getIcon().equals(iconB))
				&& !(cbut8.getIcon().equals(iconB))
				&& !(cbut9.getIcon().equals(iconB))) {
			Window3();
			// JOptionPane.showMessageDialog(null,"Empate");
			Restart();
			empate++;
			txt5.setText("Empates: " + empate);
		}
	}

	public void Restart() {
		cbut1.setIcon(iconB);
		cbut2.setIcon(iconB);
		cbut3.setIcon(iconB);
		cbut4.setIcon(iconB);
		cbut5.setIcon(iconB);
		cbut6.setIcon(iconB);
		cbut7.setIcon(iconB);
		cbut8.setIcon(iconB);
		cbut9.setIcon(iconB);
		player = true;
	}

	public void Window1() {
		new VictoryP1();
	}

	public void Window2() {
		new VictoryP2();
	}

	public void Window3() {
		new Empate();
	}

	public void Window4() {
		new Sobre();
	}

	public static void main(String[] args) {
		new Game();
	}
}