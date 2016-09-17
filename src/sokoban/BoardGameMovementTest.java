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
	    private GameBoard smallBoard;
	 
	    @Before
	    public void setUp() {
	        smallBoard = new GameBoard(smallBoardMap);
	    }

	    @Test
	    public void testGetBoardNextItemHasWall() {
	        assertEquals('#', smallBoard.getBoardNextItem(1, 1, GameBoard.Direction.UP));
	        assertEquals('#', smallBoard.getBoardNextItem(1, 1, GameBoard.Direction.LEFT));
	    }
	    
	    @Test
	    public void testGetBoardNextItemHasSpace() {
	        assertEquals(' ', smallBoard.getBoardNextItem(1, 0, GameBoard.Direction.UP));
	        assertEquals(' ', smallBoard.getBoardNextItem(3, 0, GameBoard.Direction.DOWN));
	    }
	    
	    @Test
	    public void testGetBoardNextItemHasBox() {
	        assertEquals('O', smallBoard.getBoardNextItem(1, 1, GameBoard.Direction.RIGHT));
	        assertEquals('O', smallBoard.getBoardNextItem(2, 5, GameBoard.Direction.LEFT));
	    }
	    
	    @Test
	    public void testGetBoardNextItemHasExit() {
	        assertEquals('*', smallBoard.getBoardNextItem(0, 1, GameBoard.Direction.DOWN));
	        assertEquals('*', smallBoard.getBoardNextItem(4, 4, GameBoard.Direction.UP));
	    }
	    
	    @Test
	    public void testGetBoardNextItemHasPlayer() {
	        assertEquals('A', smallBoard.getBoardNextItem(1, 3, GameBoard.Direction.RIGHT));
	    }
}
