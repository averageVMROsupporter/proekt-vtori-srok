package project_second_term;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

public class LevelSelection extends JFrame {
	static LevelSelection frame;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LevelSelection();
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
	public LevelSelection() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLvl1 = new JButton("Level 1");
		btnLvl1.setBounds(49, 83, 89, 23);
		contentPane.add(btnLvl1);
		
		JButton btnLvl2 = new JButton("Level 2");
		btnLvl2.setBounds(170, 83, 89, 23);
		contentPane.add(btnLvl2);
		
		JButton btnLvl3 = new JButton("Level 3");
		btnLvl3.setBounds(292, 83, 89, 23);
		contentPane.add(btnLvl3);
		
		JLabel lblGameName = new JLabel("The name of the game");
		lblGameName.setBounds(128, 30, 191, 14);
		contentPane.add(lblGameName);
		
		JLabel lblHighscore2 = new JLabel("high score: 35 moves");
		lblHighscore2.setBounds(170, 151, 149, 14);
		contentPane.add(lblHighscore2);
		
		JLabel lblHighscore1 = new JLabel("highscore: 3 moves");
		lblHighscore1.setBounds(34, 139, 126, 14);
		contentPane.add(lblHighscore1);
		
		JLabel lblHighscore3 = new JLabel("highscore: 100 moves");
		lblHighscore3.setBounds(277, 126, 136, 14);
		contentPane.add(lblHighscore3);
		
		btnLvl1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LevelOne2 frame2 = new LevelOne2();
				frame.setVisible(false);
				frame2.setVisible(true);
			}
			
		});
	}

}
