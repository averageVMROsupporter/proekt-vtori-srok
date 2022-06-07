package project2;

public class TestGame {

	public static void main(String[] args) {
		System.out.println("You are the OO");
		System.out.println("You can move with: W, A, S, D and restart the level with R");
		System.out.println("The goal is to push the boxes(<>) on top of the pressure plates(__) and the walls([]) will block your movement");
		System.out.println("If you win you would get a very cool custom handmade level completion screen :)");

		LevelOne l1=new LevelOne();
		l1.setGrid();
		l1.printGrid();
		l1.play();
		l1.waiting();
		l1.waiting();
		LevelTwo l2=new LevelTwo();
		l2.setGrid();
		l2.printGrid();
		l2.play();
		l1.waiting();
		l1.waiting();
		LevelThree l3=new LevelThree();
		l3.setGrid();
		l3.printGrid();
		l3.play();

	}

}
