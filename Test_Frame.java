package testing_stuff;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Test_Frame extends JFrame {

	private JPanel contentPane;
	JLabel[][] lblArr= new JLabel[6][6];
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test_Frame frame = new Test_Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Test_Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		for(int i=0; i<lblArr.length; i++) {
			for(int j=0; j<lblArr[0].length; j++) {
				if(i!=1 || j!=1) {
					lblArr[i][j]= new JLabel(new ImageIcon(Test_Frame.class.getResource("/pics/geroi.png")));
		lblArr[i][j].setBounds(10 +i*32,100+j*32, 32,32);
		add(lblArr[i][j]);
		lblArr[i][j].setVisible(true);
				}
				
			}
		}
		
		lblArr[1][1]= new JLabel(new ImageIcon(Test_Frame.class.getResource("/pics/stena.png")));
		lblArr[1][1].setBounds(10 +1*32,100+1*32, 32,32);
		add(lblArr[1][1]);
		lblArr[1][1].setVisible(true);
		
		lblArr[0][0].setVisible(false);

		lblArr[0][0]= new JLabel(new ImageIcon(Test_Frame.class.getResource("/pics/stena.png")));
		lblArr[0][0].setBounds(10 +0*32,100+0*32, 32,32);
		add(lblArr[0][0]);
		lblArr[0][0].setVisible(true);
	}

}
