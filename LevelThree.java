package project2;

public class LevelThree extends LevelOne {

	public LevelThree() {
		levelSetup();
	}
	public void levelSetup() {
		int[]kutiiiiki= {302, 303, 204, 405, 407};
		int[]winCondition= {704, 705, 706, 707, 708};
		int stenichki[]= {3,4,5,6,7, 101,100,102,103,107, 200,203,207,208,209,210, 300,310, 400,404,408,410, 501,500,502,504,508,510, 601,604,605,606,607,608,610, 701,710, 801,802,803,804,805,806,807,808,809,810};
		String[][] grid=new String[9][11];
		super.setGrid(grid);
		super.setKutiiki(kutiiiiki);
		super.setWinCon(winCondition);
		super.setGeroi(401);
		super.setSteni(stenichki);

	}
	public void printLevel() {
		System.out.println("Level 3 ");
	}

}
