package sokoban;

import java.util.Scanner;

public class Main {
    private static Scanner scanner;

	public static void main(String[] args) {
        String gameMap[] = {
        		" #####   ",
	            "##...### ",
	            "#.*.*..# ",
	            "#.#.##.##",
	            "#....OOA#",
	            "###.#...#",
	            "  #...###",
	            "  #####  "
        };
 
        GameBoard board = new GameBoard(gameMap);
        scanner = new Scanner(System.in);
        int round = 0;
 
        while(true) {
            System.out.println("Round: " + round);
            System.out.println(board);
            System.out.println();
 
            System.out.print("Your choice (awsd - movement, q - quit):");
 
            String choiceStr = scanner.nextLine();
 
            if(choiceStr.length() == 0) {
                continue;
            }
 
            char choice = choiceStr.toLowerCase().charAt(0);
 
            if(choice == 'q') {
                System.out.println("Good bye");
                break;
            }
 
            GameBoard.Direction dir = GameBoard.Direction.STILL;
            switch(choice) {
            case 'a':
                dir = GameBoard.Direction.LEFT;
                break;
            case 'w':
                dir = GameBoard.Direction.UP;
                break;
            case 's':
                dir = GameBoard.Direction.DOWN;
                break;
            case 'd':
                dir = GameBoard.Direction.RIGHT;
                break;
            }
            if(board.canPlayerMove(dir)) {
                board.movePlayer(dir);
            }
            round++;
        }
    }
}
