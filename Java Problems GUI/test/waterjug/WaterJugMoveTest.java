package waterjug;

import framework.Move;
import framework.State;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A class to test the WaterJugMove class.
 * @author Cliff Kapustka
 */
public class WaterJugMoveTest {
    
    // You should use the BridgeMoveTest class as a model for setting up
    // the tests with private instance fields here.
    private State start = new WaterJugState(0, 0);
    private State full = new WaterJugState(3, 4);
    private State xFull = new WaterJugState(3, 0);
    private State yFull = new WaterJugState(0, 4);
    private Move fillX = new WaterJugMove("Fill Jug X");
    private Move fillY = new WaterJugMove("Fill Jug Y");
    private Move emptyX = new WaterJugMove("Empty Jug X");
    private Move emptyY = new WaterJugMove("Empty Jug Y");
    private Move xToY = new WaterJugMove("Transfer Jug X to Jug Y");
    private Move yToX = new WaterJugMove("Transfer Jug Y to Jug X");
    /**
     * Tests filling jug X
     */
    @Test
    public void testFillX() {
        WaterJugState next = (WaterJugState) fillX.doMove(start);
        assertTrue(next.equals(new WaterJugState(3, 0)));
    }

    /**
     * Tests filling jug Y
     */
    @Test
    public void testFillY() {
        WaterJugState next = (WaterJugState) fillY.doMove(start);
        assertTrue(next.equals(new WaterJugState(0, 4)));
    }
    
    /**
     * Tests emptying jug X
     */
    @Test
    public void testEmptyX() {
        WaterJugState next = (WaterJugState) emptyX.doMove(full);
        assertTrue(next.equals(new WaterJugState(0, 4)));
    }
    
    /**
     * Tests emptying jug Y
     */
    @Test
    public void testEmptyY() {
        WaterJugState next = (WaterJugState) emptyY.doMove(full);
        assertTrue(next.equals(new WaterJugState(3, 0)));
    }
    
    /**
     * Tests transferring jug X to jug Y
     */
    @Test
    public void testXToY() {
        WaterJugState next = (WaterJugState) xToY.doMove(xFull);
        assertTrue(next.equals(new WaterJugState(0, 3)));
    }
    
    /**
     * Tests transferring jug Y to jug X
     */
    @Test
    public void testYToX() {
        WaterJugState next = (WaterJugState) yToX.doMove(yFull);
        assertTrue(next.equals(new WaterJugState(3, 1)));
    }
}
