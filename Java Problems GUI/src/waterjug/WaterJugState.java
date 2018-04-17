package waterjug;

import framework.State;
import graph.SimpleVertex;

/**
 * This class represents states of the Water Jug problem.
 * It creates new water jug states, tests states for equality,
 * and produces string representations of them.
 * Note that this class implements the <b>State</b> interface
 * and therefore imports <b>framework.State</b>.
 */
public class WaterJugState extends SimpleVertex implements State {

    /**
     * Creates a new water jug state
     * Stores the number of gallons of water in Jug X and Jug Y
     * @param jugX gallons of water in Jug X
     * @param jugY gallons of water in Jug Y
     */
    public WaterJugState(int jugX, int jugY) {
        this.jugX = jugX;
        this.jugY = jugY;
    }

    /**
       Tests for equality between this state and the argument state.
       Two states are equal if the X jugs have the same amount of water
       and the Y jugs have the same amount of water.
       @param other the state to test against this state
       @return whether the states are equal
    */
    public boolean equals(Object other) {
        WaterJugState state = (WaterJugState) other;
        
	return this.jugX == state.getJugX() &&
                this.jugY == state.getJugY(); 
    }

    /**
       Creates a primitive, non-GUI representation of this WaterJugState object.
       @return the string representation of this water jug state
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        String x4 = "       ";
        String x3 = "|   |  ";
        String x2 = "|   |  ";
        String x1 = "|   |  ";
        String y4 = "|   |\n";
        String y3 = "|   |\n";
        String y2 = "|   |\n";
        String y1 = "|   |\n";
        String bars =    "+---+ " + " +---+\n";
        String xy =      "  X   " + "   Y \n";
        
        switch (getJugX()) {
            case(0):
                break;
            case(1):
                x1 = "|***|  ";
                break;
            case(2):
                x1 = "|***|  ";
                x2 = "|***|  ";
                break;
            case(3):
                x1 = "|***|  ";
                x2 = "|***|  ";
                x3 = "|***|  ";
                break;
            default:
                break;
        }
        
        switch (getJugY()) {
            case(0):
                break;
            case(1):
                y1 = "|***|\n";
                break;
            case(2):
                y1 = "|***|\n";
                y2 = "|***|\n";
                break;
            case(3):
                y1 = "|***|\n";
                y2 = "|***|\n";
                y3 = "|***|\n";
                break;
            case(4):
                y1 = "|***|\n";
                y2 = "|***|\n";
                y3 = "|***|\n";
                y4 = "|***|\n";
                break;
            default:
                break;
        }
        sb.append(x4);
        sb.append(y4);
        sb.append(x3);
        sb.append(y3);
        sb.append(x2);
        sb.append(y2);
        sb.append(x1);
        sb.append(y1);
        sb.append(bars);
        sb.append(xy);
  
	return sb.toString(); 
    }
    
    /**
     * Getter (accessor) for the gallons in Jug X in this state
     * @return the number of gallons in Jug X
     */
    public int getJugX() {
        return jugX;
    }
    
    /**
     * Getter (accessor) for the gallons in Jug Y in this state
     * @return the number of gallons in Jug Y
     */
    public int getJugY() {
        return jugY;
    }
    
    //Used to test toString()
    /*public static void main(String[] args) {
        
        WaterJugState state1 = new WaterJugState(0, 0);
        WaterJugState state2 = new WaterJugState(3, 4);
        WaterJugState state3 = new WaterJugState(2, 3);
        System.out.println(state1.toString());
        System.out.println(state2.toString());
        System.out.println(state3.toString());
    }*/
    
    // Private methods and instance fields should go here
    
    private int jugX;
    private int jugY;
}
