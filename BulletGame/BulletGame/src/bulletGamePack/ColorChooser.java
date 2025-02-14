package bulletGamePack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorChooser extends JFrame implements ActionListener {
	JButton change;
	JButton btnExit;
	JPanel panel;
    Color c; //색상
    
   
	public void JColorChooser() {
		change = new JButton("색깔 바꾸기");
		panel = new JPanel();
		panel.add(change);

		add(panel, BorderLayout.CENTER);

		setSize(150, 100);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		change.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		c = JColorChooser.showDialog(this, "색을 선택하세요", Color.white);
		BulletGame bg = new BulletGame();
		bg.c = c;
		this.dispose();

	}

}
