package game_app;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Canvas;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.awt.Button;

public class LevelTwoFrame extends LevelOneFrame {
	// poleto na igrata, koeto e dvumeren masiv
	String[][] grid = new String[9][11];
	// dolu cheirite sa masivi, koito zapazvat kordinatite na obektite v nivoto,
	// kato parviqt im kordinat e chisloto deleno na 100, a vtoriq ostataka mu pri
//	 delenie na 100
//	int kutiiki[] = { 404 };
//	int winCon[] = { 300 };
//	int geroi = 305;
//	int steni[] = { 0 };

	


	// promenlivi za zapisvaneto na imeto na igracha i hodovete za koito e minal
	// nivoto
	int highscore = 0;
	String name;
	// movement e promenlivata koqto zapazva na koq posoka e bil mradnat geroq ili
	// dali nivoto e bilo restartirano/varnato nazad
	String movement = "w";
	// tezi v promenlivi prosto sa nuzhni na metodite da rabotqt
	int br = 0;
	int geroidefault = geroi;
	int kutiikidefault[] = new int[20];
	int moves = 0;
	LinkedList<Integer> undoStack = new LinkedList<>();
	boolean counterBug = true;
	int brHighscore = 0;
	private JPanel contentPane;
	JLabel[][] lblArr = new JLabel[grid.length][grid[0].length];
	int sus = 0;
	boolean amogus = true;
	boolean amongus = true;
	static File file = new File("highscore1.txt");
	static LinkedList<String> list = new LinkedList<>();
	Scanner sc = new Scanner(System.in);

	public LevelTwoFrame() {
		super();
		levelSetup();
	}

	// proverqva koga e minato nivoto
		public boolean levelCompletion() {
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[0].length; j++) {
					if (grid[i][j] == "__") {
						return false;
					}
				}
			}

			return true;
		}

	// printira bezpolezen tekst
	public void printLevel() {
		System.out.println("Level 2 ");
	}
	public void setGrid() {

		if (sus == 0) {
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[0].length; j++) {
					lblArr[i][j] = new JLabel(new ImageIcon(LevelOneFrame.class.getResource("geroi.png")));
					lblArr[i][j].setBounds(300 + j * 32, 100 + i * 32, 32, 32);
					add(lblArr[i][j]);
				}
			}
			sus = 1;
		}
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = "  ";

				lblArr[i][j].setVisible(false);

				lblArr[i][j] = new JLabel(new ImageIcon(LevelOneFrame.class.getResource("pod.png")));
				lblArr[i][j].setBounds(300 + j * 32, 100 + i * 32, 32, 32);
				add(lblArr[i][j]);
				lblArr[i][j].setVisible(true);

				if (i == geroi / 100 && j == geroi % 100) {
					grid[i][j] = "OO";
					lblArr[i][j].setVisible(false);
					lblArr[i][j] = new JLabel(new ImageIcon(LevelOneFrame.class.getResource("geroi.png")));
					lblArr[i][j].setBounds(300 + j * 32, 100 + i * 32, 32, 32);
					add(lblArr[i][j]);
					lblArr[i][j].setVisible(true);
					amogus = false;
				}
				for (int k = 0; k < winCon.length; k++) {
					if (i == winCon[k] / 100 && j == winCon[k] % 100) {
						grid[i][j] = "__";
						if (amogus) {
							lblArr[i][j].setVisible(false);
							lblArr[i][j] = new JLabel(new ImageIcon(LevelOneFrame.class.getResource("pressure plate.png")));
							lblArr[i][j].setBounds(300 + j * 32, 100 + i * 32, 32, 32);
							add(lblArr[i][j]);
							lblArr[i][j].setVisible(true);
						}
						amongus = false;
					}
				}
				for (int k = 0; k < winCon.length; k++) {
					if (i == kutiiki[k] / 100 && j == kutiiki[k] % 100) {
						grid[i][j] = "<>";
						if (amongus) {
							lblArr[i][j].setVisible(false);
							lblArr[i][j] = new JLabel(new ImageIcon(LevelOneFrame.class.getResource("kutiq.png")));
							lblArr[i][j].setBounds(300 + j * 32, 100 + i * 32, 32, 32);
							add(lblArr[i][j]);
							lblArr[i][j].setVisible(true);
						} else {
							lblArr[i][j].setVisible(false);
							lblArr[i][j] = new JLabel(
									new ImageIcon(LevelOneFrame.class.getResource("kutiika varhu pressure plate.png")));
							lblArr[i][j].setBounds(300 + j * 32, 100 + i * 32, 32, 32);
							add(lblArr[i][j]);
							lblArr[i][j].setVisible(true);
						}
					}
				}
				amongus = true;
				for (int k = 0; k < steni.length; k++) {
					if (steni[0] != 0)
						if (i == steni[k] / 100 && j == steni[k] % 100) {
							grid[i][j] = "[]";
							lblArr[i][j].setVisible(false);
							lblArr[i][j] = new JLabel(new ImageIcon(LevelOneFrame.class.getResource("stena.png")));
							lblArr[i][j].setBounds(300 + j * 32, 100 + i * 32, 32, 32);
							add(lblArr[i][j]);
							lblArr[i][j].setVisible(true);
						}
				}
				amogus = true;
			}
		}
	}
	// printira "frame-ovete" na nivoto
	public void printGrid() {

		for (int i = 0; i < grid[0].length + 1; i++) {
			System.out.print("--");
		}
		System.out.println();
		for (int i = 0; i < grid.length; i++) {
			System.out.print("|");
			for (int j = 0; j < grid[0].length; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.print("|");
			System.out.println();
		}
		for (int i = 0; i < grid[0].length + 1; i++) {
			System.out.print("--");
		}
		System.out.println();
	}

	public void levelSetup() {
		int geroi = 404;
		int[] kutiiki = { 303, 403, 305, 504 };
		int[] winCon = { 103, 401, 306, 604 };
		int steni[] = { 2, 3, 4, 102, 104, 202, 204, 205, 206, 207, 301, 300, 302, 307, 400, 405, 406, 407, 501,
				500, 502, 503, 505, 603, 605, 703, 704, 705 };
		String[][] grid = new String[8][8];
		super.setGeroi(geroi);
		super.setGrid(grid);
		super.setKutiiki(kutiiki);
		super.setWinCon(winCon);
		
		super.setSteni(steni);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					file.createNewFile();
					FileReader fr = new FileReader(file);
					int asfddsaf;
					while ((asfddsaf = fr.read()) != -1) {
						String s = "" + (char) asfddsaf;
						list.add(s);
					}
					fr.close();
					LevelTwoFrame frame = new LevelTwoFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
