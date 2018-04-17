package puzzle;

import framework.Move;
import framework.Problem;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ckapustka
 */
public class PuzzleProblem extends Problem {
    
    public PuzzleProblem() {
        PuzzleState initial = new PuzzleState(2, 8, 3, 1, 6, 4, 7, 0, 5);
        setCurrentState(initial);
        this.finalState = new PuzzleState(1, 2, 3, 8, 0, 4, 7, 6, 5);
        
        List<Move> moveList = new ArrayList<Move>();
        PuzzleMove slideTile1 = new PuzzleMove("Slide Tile 1");
        PuzzleMove slideTile2 = new PuzzleMove("Slide Tile 2");
        PuzzleMove slideTile3 = new PuzzleMove("Slide Tile 3");
        PuzzleMove slideTile4 = new PuzzleMove("Slide Tile 4");
        PuzzleMove slideTile5 = new PuzzleMove("Slide Tile 5");
        PuzzleMove slideTile6 = new PuzzleMove("Slide Tile 6");
        PuzzleMove slideTile7 = new PuzzleMove("Slide Tile 7");
        PuzzleMove slideTile8 = new PuzzleMove("Slide Tile 8");
        moveList.add(slideTile1);
        moveList.add(slideTile2);
        moveList.add(slideTile3);
        moveList.add(slideTile4);
        moveList.add(slideTile5);
        moveList.add(slideTile6);
        moveList.add(slideTile7);
        moveList.add(slideTile8);
        setMoves(moveList);
        
        String introduction = "Welcome to the 8-Puzzle Problem\n\n" +
                "You are given a 3x3 in which 8 numbered tiles can slide around.\n" +
                "There is one blank space that holds no tile. A legal move consists\n" +
                "of sliding a tile into the blank space if the tile is adjacent to it.\n" +
                "The goal is to move the tiles around until the board looks like the final\n" +
                "state below:\n" +
                "+---+---+---+\n" +
                "| 1 | 2 | 3 |\n" +
                "+---+---+---+\n" +
                "| 8 |   | 4 |\n" +
                "+---+---+---+\n" +
                "| 7 | 6 | 5 |\n" +
                "+---+---+---+\n";
        setIntroduction(introduction);
    }
    
    public boolean success() {
        PuzzleState current = (PuzzleState) getCurrentState();
        
        if (current.equals(this.finalState)) {
            return true;
        }
        else {
            return false;
        }
    }
    
    //Used to test success() method
     public static void main(String[] args) {
        PuzzleProblem problem = new PuzzleProblem();
        PuzzleState state = new PuzzleState(1, 2, 3, 8, 6, 4, 7, 0, 5);
        PuzzleMove move = new PuzzleMove("Slide Tile 6");
        
        problem.setCurrentState(state);
        System.out.print(state.toString() + "\n");
        problem.setCurrentState(move.doMove(state));
        System.out.print(problem.getCurrentState().toString() + "\n");
        if (problem.success()) {
            System.out.print("SUCCESS!");
        }
        else {
            System.out.print("FAILED");
        }
    }
     
     private PuzzleState finalState;
}
