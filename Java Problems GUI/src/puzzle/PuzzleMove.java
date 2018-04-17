package puzzle;

import framework.Move;
import framework.State;

/**
 *
 * @author ckapustka
 */
public class PuzzleMove extends Move {
 
    public PuzzleMove(String moveName) {
        super(moveName);
    }
    
    public State doMove(State otherState) {
        PuzzleState state = (PuzzleState) otherState;
        
        if (getMoveName() == "Slide Tile 1" && adjacentTo(state, 1)) {
            return this.swap(state, 1);
        }
        if (getMoveName() == "Slide Tile 2" && adjacentTo(state, 2)) {
            return this.swap(state, 2);
        }
        if (getMoveName() == "Slide Tile 3" && adjacentTo(state, 3)) {
            return this.swap(state, 3);
        }
        if (getMoveName() == "Slide Tile 4" && adjacentTo(state, 4)) {
            return this.swap(state, 4);
        }
        if (getMoveName() == "Slide Tile 5" && adjacentTo(state, 5)) {
            return this.swap(state, 5);
        }
        if (getMoveName() == "Slide Tile 6" && adjacentTo(state, 6)) {
            return this.swap(state, 6);
        }
        if (getMoveName() == "Slide Tile 7" && adjacentTo(state, 7)) {
            return this.swap(state, 7);
        }
        if (getMoveName() == "Slide Tile 8" && adjacentTo(state, 8)) {
            return this.swap(state, 8);
        }
        else {
            return null;
        }
    }
    
    public boolean adjacentTo(PuzzleState state, int n) {
        int row0 = state.getRow(0);
        int column0 = state.getColumn(0);
        int rowN = state.getRow(n);
        int columnN = state.getColumn(n);
        boolean sameColumn = (row0 == rowN + 1 || row0 == rowN - 1) && column0 == columnN;
        boolean sameRow = (column0 == columnN + 1 || column0 == columnN - 1) && row0 == rowN;
        
        return sameColumn || sameRow; 
    }
    
    public PuzzleState swap(PuzzleState currentState, int n) {
        int[][] newSquares = new int[3][3];
        int row1 = 0;
        int row2 = 0;
        int column1 = 0; 
        int column2 = 0;
        
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                newSquares[r][c] = currentState.getOneSquare(r, c);
                if (currentState.getOneSquare(r, c) == n) {
                    row1 = r;
                    column1 = c;
                }
                if (currentState.getOneSquare(r, c) == 0) {
                    row2 = r;
                    column2 = c;
                }
            }
        }
        newSquares[row1][column1] = 0;
        newSquares[row2][column2] = n;
        
        int s1 = newSquares[0][0];
        int s2 = newSquares[0][1];
        int s3 = newSquares[0][2];
        int s4 = newSquares[1][0];
        int s5 = newSquares[1][1];
        int s6 = newSquares[1][2];
        int s7 = newSquares[2][0];
        int s8 = newSquares[2][1];
        int s9 = newSquares[2][2];

        return new PuzzleState(s1, s2, s3, s4, s5, s6, s7, s8, s9);
    }
    
//    //Used to test doMove(State) method
    public static void main(String[] args) {
        PuzzleMove move = new PuzzleMove("Slide Tile 1");
        PuzzleMove move2 = new PuzzleMove("Slide Tile 2");
        PuzzleMove move3 = new PuzzleMove("Slide Tile 3");
        PuzzleState state = new PuzzleState(1, 2, 3, 4, 0, 5, 6, 7, 8);
        
        System.out.print(state.toString() + "\n");
        
        move.doMove(state);
        System.out.print(state.toString() + "\n");
        
        move2.doMove(state);
        System.out.print(state.toString() + "\n");
        
        move3.doMove(state);
        System.out.print(state.toString() + "\n");
    }
}
