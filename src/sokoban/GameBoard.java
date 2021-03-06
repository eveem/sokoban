package sokoban;

public class GameBoard {
	
	private int height;
    private int width;
    private String[] baseBoard;
 
    private int playerRow;
    private int playerCol;
 
    private int numBoxes;
    private int[] boxRows;
    private int[] boxCols;
    
    private int numExits;
    private int[] exitRows;
    private int[] exitCols;
 
    public enum Direction {
        UP, RIGHT, DOWN, LEFT, STILL
    }
    
    public GameBoard(String[] map) {
        loadBoard(map);
    }
 
    public void loadBoard(String[] map) {
        height = map.length;
        width = map[0].length();
        numBoxes = 0;
        boxRows = new int[height*width];
        boxCols = new int[height*width];
 
        numExits = 0;
        exitRows = new int[height*width];
        exitCols = new int[height*width];
        
        baseBoard = new String[height];
        for(int r = 0; r < height; r++) {
            baseBoard[r] = "";
            for(int c = 0; c < width; c++) {
                char mch = map[r].charAt(c);
                char sch = '.';
                switch(mch) {
                case 'A': 
                    playerRow = r;
                    playerCol = c;
                    break;
                case 'O':
                    boxRows[numBoxes] = r;
                    boxCols[numBoxes] = c;
                    numBoxes++;
                    break;
                case '*':
                	exitRows[numExits] = r;
                    exitCols[numExits] = c;
                    numExits++;
                    break;
                default:
                    sch = mch;
                }
                baseBoard[r] += sch;
            }
        }
    }
 
    public int getHeight() {
        return height;
    }
 
    public int getWidth() {
        return width;
    }
 
    public int getPlayerRow() {
        return playerRow;
    }
 
    public int getPlayerCol() {
        return playerCol;
    }
 
    public void setPlayerPosition(int r, int c) {
        playerRow = r;
        playerCol = c;
    }
 
    public int getNumBoxes() {
        return numBoxes;
    }
 
    public int[] getBoxPosition(int i) {
        return new int[] { 
                boxRows[i],
                boxCols[i]
        };
    }
 
    public void setBoxPosition(int i, int r, int c) {
        boxRows[i] = r;
        boxCols[i] = c;
    }
 
    public boolean hasPlayerAt(int r, int c) {
        return (playerRow == r) && (playerCol == c);
    }
 
    public boolean hasBoxAt(int r, int c) {
    	for(int i=0; i<numBoxes; i++) {
        	if(boxRows[i] == r && boxCols[i] == c)
        		return true;
        }
        return false;
    }
 
    public boolean hasExitAt(int r, int c) {
        for(int i=0; i<numExits; i++) {
        	if(exitRows[i] == r && exitCols[i] == c)
        		return true;
        }
        return false;
    }
 
    public String toString() {
    	String CurrentBoard = "";
        for(int i=0; i<height; i++) {
        	for(int j=0; j<width; j++) {
        		if(hasPlayerAt(i,j)) {
        			CurrentBoard += 'A';
        		}
        		else if(hasBoxAt(i,j)) {
        			CurrentBoard += 'O';
        		}
        		else if(hasExitAt(i,j)) {
        			CurrentBoard += '*';
        		}
        		else {
        			CurrentBoard += baseBoard[i].charAt(j);
        		}
        	}
        	CurrentBoard += '\n';
        }
    	return CurrentBoard;
    }
    
    public boolean canPlayerMove(Direction dir) {
    	return canPlayerStepOn(getBoardNextItem(getPlayerRow(), getPlayerCol(), dir));
    }
    
    void movePlayer(Direction dir) {
    	int index = -1;
    	int targetrow = getPlayerRow();
    	int targetcol = getPlayerCol();
    	if (canPlayerMove(dir)) {
    		targetrow += getRowDiff(dir);
    		targetcol += getColDiff(dir);
    	}
    	for (int i=0; i<numBoxes; i++) {
        	int[] posebox = getBoxPosition(i);
    		if (posebox[0] == targetrow && posebox[1] == targetcol) {
        		index = i;
        		break;
        	}
        }
    	
    	if (index != -1) {  		
    		char nextis = getBoardNextItem(targetrow, targetcol, dir);
			if (nextis == '.' || nextis == '*') {
				int nextrowbox = targetrow + getRowDiff(dir);
	    		int nextcolbox = targetcol + getColDiff(dir);
				setBoxPosition(index, nextrowbox, nextcolbox);
			}
			if (nextis != '#' && nextis != 'O') {
				setPlayerPosition(targetrow, targetcol);
			}
    	}
    	else {
    		setPlayerPosition(targetrow, targetcol);
    	}
    	
    }
    
    public int getColDiff(Direction dir) {
        switch(dir) {
        case LEFT:
            return -1;
        case RIGHT:
            return 1;
        default:
            return 0;
        }
    }
    
    public int getRowDiff(Direction dir) {
        switch(dir) {
        case UP:
            return -1;
        case DOWN:
            return 1;
        default:
            return 0;
        }
    }
    
    public char getBoardNextItem(int r, int c, Direction dir) {
    	r += getRowDiff(dir);
    	c += getColDiff(dir);
    	if (hasPlayerAt(r,c)) {
        	return 'A';
        }
        else if(hasBoxAt(r,c)) {
        	return 'O';
        }
        else if(hasExitAt(r,c)) {
        	return '*';
        }
        else {
        	return baseBoard[r].charAt(c);
        }
    }
    
    public boolean canPlayerStepOn(char item) {
        return (item == '.') || (item == '*') || (item == ' ') || (item == 'O');
    }
    
    public boolean isSolved() {
    	int countSolved = 0;
        for (int i=0; i<numBoxes; i++) {
        	for (int j=0; j<numExits; j++) {
        		if (boxRows[i] == exitRows[j] && boxCols[i] == exitCols[j]) {
        			countSolved ++;
        		}
        	}
        }
        if (countSolved == numBoxes) {
        	return true;
        }
    	return false;
    }
}
