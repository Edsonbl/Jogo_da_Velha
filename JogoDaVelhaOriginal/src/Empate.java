import javax.swing.*;

import java.awt.*;

public class Empate extends JFrame{
	JLabel txtE, campoimgE;
	ImageIcon imgS;
	
	public Empate() {
		
		super("Empate?");
		
		// CRIAÇÃO DO CONTAINER PRINCIPAL
		Container cp = getContentPane(); 
		
		// CONTAINER PRINCIPAL ADICIONANDO AO BorderLayout COM ESPAÇAMENTO DE 10PX POR 10PX
		cp.setLayout(new BorderLayout(10, 10)); 
		
		Container norte = new JPanel();
		txtE = new JLabel("Empate:");
		norte.add(txtE);
		
		Container centro = new JPanel();
		imgS = new ImageIcon(getClass().getResource("fotos/Homer2.jpg"));
		campoimgE = new JLabel(imgS);
		centro.add(campoimgE);
		
		Style();
		
		cp.add(BorderLayout.NORTH, norte);
		cp.add(BorderLayout.CENTER, centro);
		

		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);

	}
	public void Style(){
		txtE.setForeground(Color.ORANGE);
		txtE.setFont(new Font("Arial", Font.BOLD, 30));
	}
}