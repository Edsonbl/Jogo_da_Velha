import javax.swing.*;

import java.awt.*;

public class VictoryP1 extends JFrame{
	Container p1, s1;
	ImageIcon icon1;
	JLabel campoImg1, nome1;
	public VictoryP1(){
		super("Vitória:");
		
		icon1 = new ImageIcon(getClass().getResource("fotos/Homer1.png"));
		campoImg1 = new JLabel(icon1);
		p1 = new JPanel();
		s1 = new JPanel();
		nome1 = new JLabel("1º Jogador WINs");
		setLayout(new BorderLayout());
		add(BorderLayout.CENTER, p1);
		add(BorderLayout.NORTH, s1);
		p1.add(campoImg1);
		s1.add(nome1);
		Style();
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
		
	}
	public void Style(){
		nome1.setFont(new Font("Arial", Font.BOLD, 30));
		nome1.setForeground(Color.GREEN);
	}
}
