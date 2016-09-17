package sokoban;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardGameMovementTest {
	
		static String smallBoardMap[] = {
	        " #####",
	        "#*O.A#",
	        "#...O#",
	        "##..*#",
	        " #####"
	    };
		
		static String largeBoardMap[] = {
				" #####   ",
	            "##...### ",
	            "#.*.*..# ",
	            "#.#.##.##",
	            "#....OOA#",
	            "###.#...#",
	            "  #...###",
	            "  #####  "
		};
		
	    private GameBoard smallBoard;
	    private GameBoard largeBoard;
	 
	    @Before
	    public void setUp() {
	        smallBoard = new GameBoard(smallBoardMap);
	        largeBoard = new GameBoard(largeBoardMap);
	    }

	    @Test
	    public void testGetBoardNextItemHasWall() {
	        assertEquals('#', smallBoard.getBoardNextItem(1, 1, GameBoard.Direction.UP));
	        assertEquals('#', smallBoard.getBoardNextItem(1, 1, GameBoard.Direction.LEFT));
	        assertEquals('#', largeBoard.getBoardNextItem(0, 0, GameBoard.Direction.RIGHT));
	        assertEquals('#', largeBoard.getBoardNextItem(0, 1, GameBoard.Direction.DOWN));
	        
	    }
	    
	    @Test
	    public void testGetBoardNextItemHasSpace() {
	        assertEquals(' ', smallBoard.getBoardNextItem(1, 0, GameBoard.Direction.UP));
	        assertEquals(' ', smallBoard.getBoardNextItem(3, 0, GameBoard.Direction.DOWN));
	        assertEquals(' ', largeBoard.getBoardNextItem(0, 1, GameBoard.Direction.LEFT));
	        assertEquals(' ', largeBoard.getBoardNextItem(7, 6, GameBoard.Direction.RIGHT));
	    }
	    
	    @Test
	    public void testGetBoardNextItemHasBox() {
	        assertEquals('O', smallBoard.getBoardNextItem(1, 1, GameBoard.Direction.RIGHT));
	        assertEquals('O', smallBoard.getBoardNextItem(2, 5, GameBoard.Direction.LEFT));
	        assertEquals('O', largeBoard.getBoardNextItem(5, 6, GameBoard.Direction.UP));
	        assertEquals('O', largeBoard.getBoardNextItem(3, 5, GameBoard.Direction.DOWN));
	    }
	    
	    @Test
	    public void testGetBoardNextItemHasExit() {
	        assertEquals('*', smallBoard.getBoardNextItem(0, 1, GameBoard.Direction.DOWN));
	        assertEquals('*', smallBoard.getBoardNextItem(4, 4, GameBoard.Direction.UP));
	        assertEquals('*', largeBoard.getBoardNextItem(2, 5, GameBoard.Direction.LEFT));
	        assertEquals('*', largeBoard.getBoardNextItem(2, 1, GameBoard.Direction.RIGHT));
	    }
	    
	    @Test
	    public void testGetBoardNextItemHasPlayer() {
	        assertEquals('A', smallBoard.getBoardNextItem(1, 3, GameBoard.Direction.RIGHT));
	        assertEquals('A', largeBoard.getBoardNextItem(5, 7, GameBoard.Direction.UP));
	    }
	    
	    @Test
	    public void testGetBoardNextItemHasDot() {
	        assertEquals('.', smallBoard.getBoardNextItem(2, 0, GameBoard.Direction.RIGHT));
	        assertEquals('.', smallBoard.getBoardNextItem(3, 1, GameBoard.Direction.UP));
	        assertEquals('.', largeBoard.getBoardNextItem(0, 2, GameBoard.Direction.DOWN));
	        assertEquals('.', largeBoard.getBoardNextItem(2, 2, GameBoard.Direction.LEFT));
	    }
	    
}
