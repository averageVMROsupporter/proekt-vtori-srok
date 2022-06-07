package project2;

import java.util.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

public class LevelOne3 extends JFrame {
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
	static File file = new File("highscore.txt");
	static LinkedList<String> list = new LinkedList<>();
	Scanner sc = new Scanner(System.in);

	public LevelOne3() {
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
				setGrid();
				printLevel();
				printGrid();
			}
		});
		buttonStart.setBounds(27, 376, 149, 45);

		contentPane.add(buttonStart);

		Button buttonHighscore = new Button("Show Highscore");
		buttonHighscore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Iterator it1 = list.iterator();
				int br1 = 0;
				String copy1 = "";
				while (it1.hasNext()) {
					Object sus = it1.next();
					if (!sus.toString().equals(",")) {
						copy1 = copy1 + sus.toString();
					} else if (br1 == 0) {
						int maikati = Integer.parseInt(copy1);
						highscore = Integer.parseInt(copy1);
						br1 = 1;
						copy1 = "";
					} else if (Integer.parseInt(copy1) < highscore) {
						highscore = Integer.parseInt(copy1);
						copy1 = "";
					}
					else copy1 = "";
				}
				if (highscore != 0) {
					System.out.println(highscore);
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
		btnUndo.setBounds(21, 183, 158, 28);
		contentPane.add(btnUndo);

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
				setMovement("");
			}
		} else {
			lvlComplete();
			highscore(file);
		}
	}

	// zapazva imeto i score na vseki igrach minal nivoto v file
	public void highscore(File file) {
		
		PrintWriter fw = null;
		try {
			fw = new PrintWriter(file);
			if (brHighscore!=1) {
				if (!list.isEmpty()) {
					Iterator it = list.iterator();
					while (it.hasNext()) {
						String amongus = it.next().toString();
						fw.write(amongus);
					}
				}
			}
			fw.print(moves);
			fw.print(",");
			String amongus = Integer.toString(moves);
			list.add(amongus);
			if (brHighscore == 1) {
				if (moves < highscore) {
					highscore = moves;
				}
			} else
				highscore = moves;
			brHighscore = 1;
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// pokazva finalen ekran kogato nivoto e pobedeno
	public void lvlComplete() {
		waiting();
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

	public void setGrid(String[][] grid) {
		this.grid = grid;
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
				if (i == geroi / 100) {
					if (j == geroi % 100) {
						grid[i][j] = "OO";
					}
				}
				for (int k = 0; k < winCon.length; k++) {
					if (i == winCon[k] / 100) {
						if (j == winCon[k] % 100) {
							grid[i][j] = "__";
						}
					}
				}
				for (int k = 0; k < kutiiki.length; k++) {
					if (i == kutiiki[k] / 100) {
						if (j == kutiiki[k] % 100) {
							grid[i][j] = "<>";
						}
					}
				}
				if (steni[0] != 0) {
					for (int k = 0; k < steni.length; k++) {
						if (i == steni[k] / 100) {
							if (j == steni[k] % 100) {
								grid[i][j] = "[]";
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
					file.createNewFile();
					FileReader fr= new FileReader(file);
					int asfddsaf;    
			          while((asfddsaf=fr.read())!=-1) {   
			        	  String s = "" + (char)asfddsaf;
			        	  list.add(s);    
			          }
			          fr.close();    
					LevelOne3 frame = new LevelOne3();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
