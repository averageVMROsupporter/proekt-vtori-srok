package project2;

public class LevelTwo extends LevelOne {

	public LevelTwo() {
		levelSetup();
	}

	public void levelSetup() {
		int[] kutiiiiki = { 303, 403, 305, 504 };
		int[] winCondition = { 103, 401, 306, 604 };
		int stenichki[] = { 2, 3, 4, 102, 104, 202, 204, 205, 206, 207, 301, 300, 302, 307, 400, 405, 406, 407, 501,
				500, 502, 503, 505, 603, 605, 703, 704, 705 };
		String[][] grid = new String[8][8];
		super.setGrid(grid);
		super.setKutiiki(kutiiiiki);
		super.setWinCon(winCondition);
		super.setGeroi(404);
		super.setSteni(stenichki);
	}
}
