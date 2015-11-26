import javax.swing.*;

import java.awt.*;

public class Sobre extends JFrame{
	JPanel spanel[];//oeste
	JLabel txt1, txt2, txt3, txt4, txt5, txt6, txt7, campoimgS; // oeste
	ImageIcon imgS; //ICONES PARA OS BOTÕES
	
	public Sobre() {
		
		super("Sobre:");
		
		// CRIAÇÃO DO CONTAINER PRINCIPAL
		Container cp = getContentPane(); 
		
		// CONTAINER PRINCIPAL ADICIONANDO AO BorderLayout COM ESPAÇAMENTO DE 10PX POR 10PX
		cp.setLayout(new BorderLayout(10, 10)); 
		
		spanel = new JPanel[6];
		//INICIALIZANDO O ARRAY sul
		for (int i=0; i<spanel.length; i++){
			spanel[i] = new JPanel();
		}
		//INICIALIZANDO O CONTAINER NORTE E SUL
		Container norte = new JPanel();

		txt7 = new JLabel("Regras:");
		norte.add(txt7);
		
		Container sul = new JPanel();
		sul.add(spanel[0].add(txt1 = new JLabel("O tabuleiro é uma matriz de três linhas por três colunas.")));
		sul.add(spanel[1].add(txt2 = new JLabel("Dois jogadores escolhem uma marcação cada um, geralmente um círculo (O) e um xis (X).")));
		sul.add(spanel[2].add(txt3 = new JLabel("Os jogadores jogam alternadamente, uma marcação por vez, numa lacuna que esteja vazia.")));
		sul.add(spanel[3].add(txt4 = new JLabel("O objectivo é conseguir três círculos ou três xis em linha, quer horizontal, vertical")));
		sul.add(spanel[4].add(txt5 = new JLabel("ou diagonal , e ao mesmo tempo, quando possível, impedir o adversário de ganhar na")));
		sul.add(spanel[5].add(txt6 = new JLabel("próxima jogada.")));
		sul.setLayout(new BoxLayout(sul, BoxLayout.Y_AXIS));
		
		Container centro = new JPanel();
		imgS = new ImageIcon(getClass().getResource("fotos/Homer4.png"));
		campoimgS = new JLabel(imgS);
		centro.add(campoimgS);
		
		Style();
		
		cp.add(BorderLayout.NORTH, norte);
		cp.add(BorderLayout.SOUTH, sul);
		cp.add(BorderLayout.CENTER, centro);
		

		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
		
	}
	public void Style(){
		txt1.setFont(new Font("Arial", Font.BOLD, 20));
		txt2.setFont(new Font("Arial", Font.BOLD, 20));
		txt3.setFont(new Font("Arial", Font.BOLD, 20));
		txt4.setFont(new Font("Arial", Font.BOLD, 20));
		txt5.setFont(new Font("Arial", Font.BOLD, 20));
		txt6.setFont(new Font("Arial", Font.BOLD, 20));
		txt7.setForeground(Color.BLUE);
		txt7.setFont(new Font("Arial", Font.BOLD, 30));
	}
}