package project_final_version;

import java.util.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Canvas;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;


public class LevelOneFinalVersion extends JFrame {
	Icon[][] iconGrid = new Icon[6][6];
	JLabel [][] lblGrid = new JLabel[6][6];
	// poleto na igrata, koeto e dvumeren masiv
	String[][] grid = new String[6][6];
	// dolu cheirite sa masivi, koito zapazvat kordinatite na obektite v nivoto,
	// kato parviqt im kordinat e chisloto deleno na 100, a vtoriq ostataka mu pri
	// delenie na 100
	int kutiiki[] = { 404 };
	int winCon[] = { 300 };
	int geroi = 305;
	int steni[] = { 0 };
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
	private JPanel contentPane;
	boolean counterBug = true;
	int brHighscore = 0;
	static LinkedList<Object> list = new LinkedList<>();
	Scanner sc = new Scanner(System.in);

	public LevelOneFinalVersion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// buton koito marda geroq nagore
		JButton btnMoveUp = new JButton("^");
		btnMoveUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMovement("w");
				play();
				if (levelCompletion()) {
					play();
				}
			}
		});
		btnMoveUp.setBounds(76, 70, 45, 45);
		contentPane.add(btnMoveUp);

		// buton koito marda geroq nalqvo
		JButton btnMoveLeft = new JButton("<-");
		btnMoveLeft.setBounds(21, 126, 45, 45);
		contentPane.add(btnMoveLeft);
		btnMoveLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMovement("a");
				play();
				if (levelCompletion()) {
					play();
				}
			}
		});

		// buton koito marda geroq nadolu
		JButton btnMoveDown = new JButton("v");
		btnMoveDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMovement("s");
				play();
				if (levelCompletion()) {
					play();
				}
			}
		});
		btnMoveDown.setBounds(76, 126, 45, 45);
		contentPane.add(btnMoveDown);

		// buton koito marda geroq nadqsno
		JButton btnMoveRight = new JButton("->");
		btnMoveRight.setBounds(131, 126, 45, 45);
		contentPane.add(btnMoveRight);
		btnMoveRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMovement("d");
				play();
				if (levelCompletion()) {
					play();
				}
			}
		});

		Button buttonStart = new Button("Start game");
		buttonStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setGrid();//error
				printLevel();
				printGrid();
			}
		});
		buttonStart.setBounds(27, 376, 149, 45);

		contentPane.add(buttonStart);

		Button buttonHighscore = new Button("Show Highscore");
		buttonHighscore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (highscore != 0) {
					Iterator it = list.iterator();
					int id = highscore;
					int br = 0;
					while (it.hasNext()) {
						br++;
						if (it.next().equals(id)) {
							System.out.println(list.get(br - 2));
							System.out.println(list.get(br - 2));
						}
					}
				} else
					System.out.println("No one has beaten the level yet");
			}
		});
		buttonHighscore.setBounds(27, 297, 149, 21);
		contentPane.add(buttonHighscore);

		JButton btnRestart = new JButton("Restart");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMovement("r");
				play();
			}
		});
		btnRestart.setBounds(21, 22, 155, 28);
		contentPane.add(btnRestart);

		JButton btnUndo = new JButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMovement("z");
				setGrid();
				printLevel();
				printGrid();
				play();
			}
		});
		btnUndo.setBounds(21, 182, 158, 28);
		contentPane.add(btnUndo);
		
//		JLabel lbl00 = new JLabel("");
//		lbl00.setBounds(250, 20, 32, 32);
//		contentPane.add(lbl00);
//		
//		JLabel lbl01 = new JLabel("");
//		lbl01.setBounds(282, 20, 32, 32);
//		contentPane.add(lbl01);
//		
//		JLabel lbl02 = new JLabel("");
//		lbl02.setBounds(314, 20, 32, 32);
//		contentPane.add(lbl02);
//		
//		JLabel lbl03 = new JLabel("");
//		lbl03.setBounds(346, 20, 32, 32);
//		contentPane.add(lbl03);
//		
//		JLabel lbl04 = new JLabel("");
//		lbl04.setBounds(378, 20, 32, 32);
//		contentPane.add(lbl04);
//		
//		JLabel lblNewLabel_4 = new JLabel("");
//		lblNewLabel_4.setBounds(412, 20, 32, 32);
//		contentPane.add(lblNewLabel_4);
//		
//		JLabel lblNewLabel_5 = new JLabel("");
//		lblNewLabel_5.setBounds(250, 52, 32, 32);
//		contentPane.add(lblNewLabel_5);
//		
//		JLabel lblNewLabel_6 = new JLabel("");
//		lblNewLabel_6.setBounds(282,  52, 32, 32);
//		contentPane.add(lblNewLabel_6);
//		
//		JLabel lblNewLabel_7 = new JLabel("");
//		lblNewLabel_7.setBounds(314,  52, 32, 32);
//		contentPane.add(lblNewLabel_7);
//		
//		JLabel lblNewLabel_8 = new JLabel("");
//		lblNewLabel_8.setBounds(346,  52, 32, 32);
//		contentPane.add(lblNewLabel_8);
//		
//		JLabel lblNewLabel_9 = new JLabel("");
//		lblNewLabel_9.setBounds(378,  52, 32, 32);
//		contentPane.add(lblNewLabel_9);
//		
//		JLabel lblNewLabel_10 = new JLabel("");
//		lblNewLabel_10.setBounds(410,  52, 32, 32);
//		contentPane.add(lblNewLabel_10);
//		
//		JLabel lblNewLabel_11 = new JLabel("");
//		lblNewLabel_11.setBounds(250,  84, 32, 32);
//		contentPane.add(lblNewLabel_11);
//		
//		JLabel lblNewLabel_12 = new JLabel("");
//		lblNewLabel_12.setBounds(282, 84, 32, 32);
//		contentPane.add(lblNewLabel_12);
//		
//		JLabel lblNewLabel_13 = new JLabel("");
//		lblNewLabel_13.setBounds(314, 84, 32, 32);
//		contentPane.add(lblNewLabel_13);
//		
//		JLabel lblNewLabel_14 = new JLabel("");
//		lblNewLabel_14.setBounds(346, 84, 32, 32);
//		contentPane.add(lblNewLabel_14);
//		
//		JLabel lblNewLabel_15 = new JLabel("");
//		lblNewLabel_15.setBounds(378, 84, 32, 32);
//		contentPane.add(lblNewLabel_15);
//		
//		JLabel lblNewLabel_16 = new JLabel("");
//		lblNewLabel_16.setBounds(410, 84, 32, 32);
//		contentPane.add(lblNewLabel_16);
//		
//		JLabel lblNewLabel_17 = new JLabel("");
//		lblNewLabel_17.setBounds(250, 116, 32, 32);
//		contentPane.add(lblNewLabel_17);
//		
//		JLabel lblNewLabel_18 = new JLabel("");
//		lblNewLabel_18.setBounds(282, 116, 32, 32);
//		contentPane.add(lblNewLabel_18);
//		
//		JLabel lblNewLabel_19 = new JLabel("");
//		lblNewLabel_19.setBounds(314, 116, 32, 32);
//		contentPane.add(lblNewLabel_19);
//		
//		JLabel lblNewLabel_20 = new JLabel("");
//		lblNewLabel_20.setBounds(346, 116, 32, 32);
//		contentPane.add(lblNewLabel_20);
//		
//		JLabel lblNewLabel_21 = new JLabel("");
//		lblNewLabel_21.setBounds(378, 116, 32, 32);
//		contentPane.add(lblNewLabel_21);
//		
//		JLabel lblNewLabel_22 = new JLabel("");
//		lblNewLabel_22.setBounds(410, 116, 32, 32);
//		contentPane.add(lblNewLabel_22);
//		
//		JLabel lblNewLabel_23 = new JLabel("");
//		lblNewLabel_23.setBounds(250, 148, 32, 32);
//		contentPane.add(lblNewLabel_23);
//		
//		JLabel lblNewLabel_24 = new JLabel("");
//		lblNewLabel_24.setBounds(282, 148, 32, 32);
//		contentPane.add(lblNewLabel_24);
//		
//		JLabel lblNewLabel_25 = new JLabel("");
//		lblNewLabel_25.setBounds(314, 148, 32, 32);
//		contentPane.add(lblNewLabel_25);
//		
//		JLabel lblNewLabel_26 = new JLabel("");
//		lblNewLabel_26.setBounds(346, 148, 32, 32);
//		contentPane.add(lblNewLabel_26);
//		
//		JLabel lblNewLabel_27 = new JLabel("");
//		lblNewLabel_27.setBounds(378, 148, 32, 32);
//		contentPane.add(lblNewLabel_27);
//		
//		JLabel lblNewLabel_28 = new JLabel("");
//		lblNewLabel_28.setBounds(410, 148, 32, 32);
//		contentPane.add(lblNewLabel_28);
//		
//		JLabel lblNewLabel_29 = new JLabel("");
//		lblNewLabel_29.setBounds(250, 180, 32, 32);
//		contentPane.add(lblNewLabel_29);
//		
//		JLabel lblNewLabel_30 = new JLabel("");
//		lblNewLabel_30.setBounds(282, 180, 32, 32);
//		contentPane.add(lblNewLabel_30);
//		
//		JLabel lblNewLabel_31 = new JLabel("");
//		lblNewLabel_31.setBounds(314, 180, 32, 32);
//		contentPane.add(lblNewLabel_31);
//		
//		JLabel lblNewLabel_32 = new JLabel("");
//		lblNewLabel_32.setBounds(346, 180, 32, 32);
//		contentPane.add(lblNewLabel_32);
//		
//		JLabel lblNewLabel_33 = new JLabel("");
//		lblNewLabel_33.setBounds(378, 180, 32, 32);
//		contentPane.add(lblNewLabel_33);
//		
//		JLabel lblNewLabel_34 = new JLabel("");
//		lblNewLabel_34.setBounds(410, 180, 32, 32);
//		contentPane.add(lblNewLabel_34);
//	

	}

	// izchakva
	public static void waiting() {
		try {
			Thread.sleep(800);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	// marda kordinatite na geroq pri saotvetniqt buton
	public void normalCharacterMovement(int geroi, int geroidefault, int[] kutiikidefault) {
		switch (movement) {
		case "w":
			geroi = geroi - 100;
			this.geroi = geroi;
			break;
		case "a":
			geroi = geroi - 1;
			this.geroi = geroi;
			break;
		case "s":
			geroi = geroi + 100;
			this.geroi = geroi;
			break;
		case "d":
			geroi = geroi + 1;
			this.geroi = geroi;
			break;
		case "r":
			for (int i = 0; i < kutiiki.length; i++) {
				this.kutiiki[i] = kutiikidefault[i];
			}
			geroi = geroidefault;
			this.geroi = geroi;
			break;
		case "z":
			undo();
			break;
		}
	}

	// spira geroq da izliza ot ochertaniqta na nivoto
	public void outsideGridCharacterMovemen(int geroi) {
		if (geroi % 100 >= grid[0].length || geroi / 100 >= grid.length || 0 > geroi % 100 || 0 > geroi / 100) {
			switch (movement) {
			case "w":
				geroi = geroi + 100;
				break;
			case "a":
				geroi = geroi + 1;
				break;
			case "s":
				geroi = geroi - 100;
				break;
			case "d":
				geroi = geroi - 1;
				break;
			}
		}
		this.geroi = geroi;
	}

	// spira geroq da vliza v steni
	public void inWallCharacterMovement(int geroi) {
		if (steni[0] != 0) {
			for (int k = 0; k < steni.length; k++) {
				if (geroi / 100 == steni[k] / 100 && geroi % 100 == steni[k] % 100) {
					switch (movement) {
					case "w":
						geroi = geroi + 100;
						break;
					case "a":
						geroi = geroi + 1;
						break;
					case "s":
						geroi = geroi - 100;
						break;
					case "d":
						geroi = geroi - 1;
						break;
					}
				}
			}
		}
		this.geroi = geroi;
	}

	// buta kutiite, kogato igracha se opita da gi butne
	public void normalBoxPushingMovement(int geroi, int[] kutiiki, int i) {
		if (geroi == kutiiki[i]) {
			switch (movement) {
			case "w":
				kutiiki[i] = kutiiki[i] - 100;
				break;
			case "a":
				kutiiki[i] = kutiiki[i] - 1;
				break;
			case "s":
				kutiiki[i] = kutiiki[i] + 100;
				break;
			case "d":
				kutiiki[i] = kutiiki[i] + 1;
				break;
			}
		}
		this.kutiiki[i] = kutiiki[i];
	}

	// prechi na kutiite da izizat ot ochertaniqta na nivoto
	public void outsideGridBoxPushingMovement(int geroi, int[] kutiiki, int i) {
		if (kutiiki[i] % 100 >= grid[0].length || kutiiki[i] / 100 >= grid.length || 0 > kutiiki[i] % 100
				|| 0 > kutiiki[i] / 100) {
			switch (movement) {
			case "w":
				kutiiki[i] = kutiiki[i] + 100;
				geroi = geroi + 100;
				break;
			case "a":
				kutiiki[i] = kutiiki[i] + 1;
				geroi = geroi + 1;
				break;
			case "s":
				kutiiki[i] = kutiiki[i] - 100;
				geroi = geroi - 100;
				break;
			case "d":
				kutiiki[i] = kutiiki[i] - 1;
				geroi = geroi - 1;
				break;
			}
		}
		this.kutiiki[i] = kutiiki[i];
		this.geroi = geroi;
	}

	// spira kutiite da vlizat v steni
	public void inWallBoxPushingMovement(int geroi, int[] kutiiki, int[] steni, int i) {
		if (steni[0] != 0) {
			for (int j = 0; j < steni.length; j++) {
				if (kutiiki[i] / 100 == steni[j] / 100 && kutiiki[i] % 100 == steni[j] % 100) {
					switch (movement) {
					case "w":
						kutiiki[i] = kutiiki[i] + 100;
						geroi = geroi + 100;
						break;
					case "a":
						kutiiki[i] = kutiiki[i] + 1;
						geroi = geroi + 1;
						break;
					case "s":
						kutiiki[i] = kutiiki[i] - 100;
						geroi = geroi - 100;
						break;
					case "d":
						kutiiki[i] = kutiiki[i] - 1;
						geroi = geroi - 1;
						break;
					}
				}
			}
		}
		this.kutiiki[i] = kutiiki[i];
		this.geroi = geroi;
	}

	// buta dve kutii ednovremenno, kogato igracha se opita da gi butne
	public void inBoxBoxPushingMovement(int geroi, int[] kutiiki, int[] steni, int i) {
		for (int j = 0; j < kutiiki.length; j++) {
			if (i != j) {
				if (kutiiki[i] / 100 == kutiiki[j] / 100) {
					if (kutiiki[i] % 100 == kutiiki[j] % 100) {
						switch (movement) {
						case "w":
							kutiiki[i] = kutiiki[i] - 100;
							break;
						case "a":
							kutiiki[i] = kutiiki[i] - 1;
							break;
						case "s":
							kutiiki[i] = kutiiki[i] + 100;
							break;
						case "d":
							kutiiki[i] = kutiiki[i] + 1;
							break;
						}
					}
				}
			}
		}
		this.kutiiki[i] = kutiiki[i];
	}

	// v linkedlist se zapazvat hodovete na igracha za da ima undo buton
	public void fillUndo() {
		if (!getMovement().equals("z")) {
			for (int i = 0; i < kutiiki.length; i++) {
				undoStack.push(kutiiki[i]);
			}
			undoStack.push(geroi);
		}
	}

	// vrashta geroq i mradnatite kutiiki edin hod nazad
	public void undo() {
		if (undoStack.size() != 0) {
			this.geroi = undoStack.pop();
			for (int i = kutiiki.length - 1; i >= 0; i--) {
				kutiiki[i] = undoStack.pop();
			}
		}
	}

	// bug test, koito beshe nuzhen da razreshim edni bugove
	public boolean bugTest(int geroi, int[] kutiiki, int[] steni, boolean counterBug) {
		boolean inWallCharacter = false;
		boolean inWallBox = false;
		boolean inBoxCharacter = false;
		boolean outsideGridBox = false;
		for (int i = 0; i < kutiiki.length; i++) {
			if (geroi == kutiiki[i]) {
				inBoxCharacter = true;
				System.out.println("cum1");
			}
			if (kutiiki[i] % 100 >= grid[0].length || kutiiki[i] / 100 >= grid.length || 0 > kutiiki[i] % 100
					|| 0 > kutiiki[i] / 100) {
				outsideGridBox = true;
				System.out.println("cum2");
			}
			for (int j = 0; j < kutiiki.length; j++) {
				if (geroi / 100 == steni[j] / 100 && geroi % 100 == steni[j] % 100) {
					inWallCharacter = true;
					System.out.println("cum3");
				}
				if ((kutiiki[i] / 100 == steni[j] / 100 && kutiiki[i] % 100 == steni[j] % 100)) {
					inWallBox = true;
					System.out.println("cum4");
				}
			}
		}
		if (geroi % 100 >= grid[0].length || geroi / 100 >= grid.length || 0 > geroi % 100 || 0 > geroi / 100
				|| inWallCharacter || inWallBox || inBoxCharacter || outsideGridBox) {
			counterBug = false;
			this.counterBug = counterBug;
			return true;
		} else {
			return false;
		}
	}

	// vrashta kutiikite na nachalnoto im mqsto v nivoto
	public void setKutiikiDefault() {
		for (int i = 0; i < kutiiki.length; i++) {

			kutiikidefault[i] = kutiiki[i];
		}
		br = 1;
	}

	public void play() {
		if (!levelCompletion()) {
			if (br < 1)
				setKutiikiDefault();
			if (movement.equals("a") || movement.equals("d") || movement.equals("w") || movement.equals("s")
					|| movement.equals("r") || movement.equals("z")) {
				fillUndo();
				normalCharacterMovement(geroi, geroidefault, kutiikidefault);
				outsideGridCharacterMovemen(geroi);
				inWallCharacterMovement(geroi);
				for (int i = 0; i < kutiiki.length; i++) {
					normalBoxPushingMovement(geroi, kutiiki, i);
					outsideGridBoxPushingMovement(geroi, kutiiki, i);
					inWallBoxPushingMovement(geroi, kutiiki, steni, i);
					inBoxBoxPushingMovement(geroi, kutiiki, steni, i);
				}
				moves++;
				setGrid();
				printLevel();
				printGrid();
				setLblGrid();//added
				setMovement("");
			}
		} else {
			lvlComplete();
		//	highscore();
		}
	}

	// zapazva imeto i score na vseki igrach minal nivoto v file
	public void highscore() {
		File file = new File("highscore.txt");
		PrintWriter pw;
		try {
			pw = new PrintWriter(file);
			name = sc.nextLine();
			pw.println(name);
			pw.println(moves);
			pw.println();
			list.add(name);
			list.add(highscore);
			if (brHighscore == 1) {
				if (moves < highscore) {
					highscore = moves;
				}
			} else
				highscore = moves;
			brHighscore = 1;
			pw.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	// pokazva finalen ekran kogato nivoto e pobedeno
	public void lvlComplete() {
//		waiting();
		System.out.println(
				"========================================================================================================");
		System.out.println(
				"                               _____   _____                    _____            _____  _______   _____ ");
		System.out.println(
				" |       |     /  |           |       |     |       /|     /|  |     |  |       |          |     |      ");
		System.out.println(
				" |       |    /   |           |       |     |      / |    / |  |     |  |       |          |     |      ");
		System.out.println(
				" |       |   /    |           |       |     |     /  |   /  |  |_____|  |       |_____     |     |_____ ");
		System.out.println(
				" |       |  /     |           |       |     |    /   |  /   |  |        |       |          |     |      ");
		System.out.println(
				" |       | /      |           |       |     |   /    | /    |  |        |       |          |     |      ");
		System.out.println(
				" |_____  |/       |_____      |_____  |_____|  /     |/     |  |        |_____  |_____     |     |_____ ");
		System.out.println();
		System.out.println(
				"========================================================================================================");
		System.out.println("in " + moves + " moves");
		
	}

	public Icon[][] getIconGrid() {
		return iconGrid;
	}

	public void setLblGrid() {//new
		for(int i=0; i<lblGrid.length; i++) {
			for(int j=0; j<lblGrid[0].length; j++) {
				lblGrid[i][j].setBounds(j, j, i, i);
				getContentPane().add(lblGrid[i][j]);
			
			}
				
		}
	}

	

	public void setKutiiki(int[] kutiiki) {
		this.kutiiki = kutiiki;
	}

	public void setWinCon(int[] winCon) {
		this.winCon = winCon;
	}

	public void setGeroi(int geroi) {
		this.geroi = geroi;
	}

	public void setSteni(int[] steni) {
		this.steni = steni;
	}

	public String getMovement() {
		return movement;
	}

	public void setMovement(String movement) {
		this.movement = movement;
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
		System.out.println("Level 1 ");
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

	// aktualizira grida s kordinatite na obektite v nego
	public void setGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = "  ";
				lblGrid[i][j].setIcon(new ImageIcon(new ImageIcon("pod.png").getImage().getScaledInstance(1, 1, 1)));
				if (i == geroi / 100) {
					if (j == geroi % 100) {
						grid[i][j] = "OO";
						iconGrid[i][j] = new ImageIcon(getClass().getResource("geroi.png"));//
					}
				}
				for (int k = 0; k < winCon.length; k++) {
					if (i == winCon[k] / 100) {
						if (j == winCon[k] % 100) {
							grid[i][j] = "__";
							iconGrid[i][j] = new ImageIcon(getClass().getResource("pressure_plate.png"));//
						}
					}
				}
				for (int k = 0; k < kutiiki.length; k++) {
					if (i == kutiiki[k] / 100) {
						if (j == kutiiki[k] % 100) {
							grid[i][j] = "<>";
							iconGrid[i][j] = new ImageIcon(getClass().getResource("kutiika_varhu_pressure_plate.png"));//
						}
					}
				}
				if (steni[0] != 0) {
					for (int k = 0; k < steni.length; k++) {
						if (i == steni[k] / 100) {
							if (j == steni[k] % 100) {
								grid[i][j] = "[]";
								iconGrid[i][j] = new ImageIcon(getClass().getResource("stena.png"));//
							}
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LevelOneFinalVersion frame = new LevelOneFinalVersion();
					frame.setVisible(true);
					frame.setGrid();
					if(frame.levelCompletion()) frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}