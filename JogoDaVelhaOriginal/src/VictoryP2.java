import javax.swing.*;

import java.awt.*;

public class VictoryP2 extends JFrame {
	Container p2, s2;
	ImageIcon icon2;
	JLabel campoImg2, nome2;

	public VictoryP2() {
		super("Vitória:");

		icon2 = new ImageIcon(getClass().getResource("fotos/Homer3.png"));
		campoImg2 = new JLabel(icon2);
		p2 = new JPanel();
		s2 = new JPanel();
		nome2 = new JLabel("2º Jogador WINs");
		setLayout(new BorderLayout());
		add(BorderLayout.CENTER, p2);
		add(BorderLayout.NORTH, s2);
		p2.add(campoImg2);
		s2.add(nome2);
		Style();
		pack();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public void Style() {
		nome2.setFont(new Font("Arial", Font.BOLD, 30));
		nome2.setForeground(Color.RED);
	}
}
