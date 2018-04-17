package waterjug;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A class to test the WaterJugState class
 * @author Cliff Kapustka
 */
public class WaterJugStateTest {
    
    private WaterJugState state1 = new WaterJugState(0, 0);
    private String state1String = "       |   |\n" +
                                  "|   |  |   |\n" +
                                  "|   |  |   |\n" +
                                  "|   |  |   |\n" +
                                  "+---+  +---+\n" +
                                  "  X      Y  \n";
                                 
    private WaterJugState state2 = new WaterJugState(3, 4);
    private String state2String = "       |***|\n" +
                                  "|***|  |***|\n" +
                                  "|***|  |***|\n" +
                                  "|***|  |***|\n" +
                                  "+---+  +---+\n" +
                                  "  X      Y  \n";
    private WaterJugState state2Copy = new WaterJugState(3, 4);
    
    private WaterJugState state3 = new WaterJugState(3, 0);
    private String state3String = "       |   |\n" +
                                  "|***|  |   |\n" +
                                  "|***|  |   |\n" +
                                  "|***|  |   |\n" +
                                  "+---+  +---+\n" +
                                  "  X      Y  \n";
    
    private WaterJugState state4 = new WaterJugState(0, 4);
    private String state4String = "       |***|\n" +
                                  "|   |  |***|\n" +
                                  "|   |  |***|\n" +
                                  "|   |  |***|\n" +
                                  "+---+  +---+\n" +
                                  "  X      Y  \n";
    
    // You may want to add more instance fields for testing

    /**
     * This method tests the accessors (getters) for <b>WaterJugState</b> objects.
     */
    @Test
    public void testAccessors() {
        assertTrue(state2.getJugX() == 3);
        assertTrue(state2.getJugY() == 4);
        assertTrue(state3.getJugX() == 3);
        assertTrue(state3.getJugY() == 0);
    }

    /**
     * This method tests the <b>equals</b> method for <b>WaterJugState</b> objects.
     */
    @Test
    public void testEquals() {
        assertFalse(state1.equals(state2));
        assertTrue(state2.equals(state2Copy));
        assertFalse(state3.equals(state4));
    }

    /**
     * This method tests the <b>toString</b> method for <b>WaterJugState</b> objects.
     * Look at the definitions of <b>state1String</b> and <b>state2String</b> to see
     * how <b>toString</b> should format a state's string representation.
     */
    @Test
    public void testToString() {
        //Tested using alternate method
    }
}
