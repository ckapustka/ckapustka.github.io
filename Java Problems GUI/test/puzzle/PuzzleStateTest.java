package puzzle;

import static java.util.Arrays.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A class to test the PuzzleState class
 * @author ckapustka
 */
public class PuzzleStateTest {
    
//    int[][] p1 = new int[][] {
//        {1, 2, 3},
//        {4, 0, 5},
//        {6, 7, 8}
//    };
//        
//    int[][] p2 = new int[][] {
//        {2, 8, 7},
//        {6, 5, 4},
//        {3, 1, 0}
//    };
//    
//    int [][] p1Copy = new int[][] {
//        {1, 2, 3},
//        {4, 0, 5},
//        {6, 7, 8}
//    };
    
    PuzzleState state1 = new PuzzleState(1, 2, 3, 4, 0, 5, 6, 7, 8);
    PuzzleState state2 = new PuzzleState(2, 8, 7, 6, 5, 4, 3, 1, 0);
    PuzzleState state1Copy = new PuzzleState(1, 2, 3, 4, 0, 5, 6, 7, 8);
    
    @Test
    public void testEquals() {
        assertTrue(state1.equals(state1Copy));
        assertFalse(state2.equals(state1));
        assertFalse(state1Copy.equals(state2));
    }
    
    @Test
    public void testAccessors() {
        assertTrue(deepEquals(state1.getSquares(), state1Copy.getSquares()));
        assertFalse(deepEquals(state2.getSquares(), state1.getSquares()));
        assertTrue(state1.getOneSquare(1, 0) == 4);
        assertTrue(state2.getOneSquare(2, 1) == 1);
    }
}
