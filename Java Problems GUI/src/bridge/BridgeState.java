package bridge;

import framework.State;
import graph.SimpleVertex;

/**
 * This class represents states of the Bridge Crossing problem.
 * It creates new bridge states, tests states for equality,
 * and produces string representations of them.
 * Note that this class implements the <b>State</b> interface
 * and therefore imports <b>framework.State</b>.
 * Except for the import and the class header, this class can be
 * the same as in the previous assignment.
 * @author ckapustka
 */
public class BridgeState extends SimpleVertex implements State {

    /**
     * Creates a new bridge state.  
     * Besides storing the positions of the persons and flashlight, a
     * bridge state should also store the time taken to get to this state in
     * integer minutes.
     * @param p1Position position of the person who can cross in 1 minute
     * @param p2Position position of the person who can cross in 2 minutes
     * @param flashlightPosition position of the flashlight
     * @param p5Position position of the person who can cross in 5 minutes
     * @param p10Position  position of the person who can cross in 10 minutes
     * @param timeSoFar time taken so far
     */
    public BridgeState(Position p1Position, 
                       Position p2Position, 
                       Position flashlightPosition, 
                       Position p5Position, 
                       Position p10Position,
                       int timeSoFar) {
        this.p1Position = p1Position;
        this.p2Position = p2Position;
        this.flashlightPosition = flashlightPosition;
        this.p5Position = p5Position; 
        this.p10Position = p10Position;
        this.timeSoFar += timeSoFar;
    }
    
    /**
     * Compares this bridge state with another for equality.
     * Note that this method overrides the <b>equals</b> method defined
     * in <b>java.lang.Object</b>.
     * Thus the argument of type <b>Object</b> must be cast to type
     * <b>BridgeState</b> before processing.
     * Two bridge states are equal if the positions of the persons and 
     * flashlight in one state are matched by their positions in the other.
     * Note that the time taken to cross so far is not taken into account
     * when considering equality.
     * @param other the other bridge state to be compared with this one.
     * @return whether this state is equal to the other state
     */
    public boolean equals(Object other) {
        BridgeState state = (BridgeState) other;
        
        return this.p1Position == state.getP1Position() &&
               this.p2Position == state.getP2Position() &&
               this.flashlightPosition == state.getFlashlightPosition() &&
               this.p5Position == state.getP5Position() &&
               this.p10Position == state.getP10Position();
    }
    
    /**
     * Creates a string representation of this state for display to the user
     * trying to solve the problem.
     * Note that the time so far to cross is part of the string representation.
     * @return the string representation of this state
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String p1West = " P1 |   |\n";
        String p1East = "    |   | P1\n";
        String p2West = " P2 |   |\n";
        String p2East = "    |   | P2\n";
        String flashlightWest = "  f |===|\n";
        String flashlightEast = "    |===| f\n";
        String p5West = " P5 |   |\n";
        String p5East = "    |   | P5\n";
        String p10West = "P10 |   |\n";
        String p10East = "    |   |P10\n";
        String time = "Time elapsed so far: " + getTimeSoFar() + " minutes.\n";
        
        if (getP1Position() == Position.WEST) {
            sb.append(p1West);
        }
        else {
            sb.append(p1East);
        }
        if (getP2Position() == Position.WEST) {
            sb.append(p2West);
        }
        else {
            sb.append(p2East);
        }
        if (getFlashlightPosition() == Position.WEST) {
            sb.append(flashlightWest);
        }
        else {
            sb.append(flashlightEast);
        }
        if (getP5Position() == Position.WEST) {
            sb.append(p5West);
        }
        else {
            sb.append(p5East);
        }
        if (getP10Position() == Position.WEST) {
            sb.append(p10West);
        }
        else {
            sb.append(p10East);
        }
        sb.append(time);
        
        return sb.toString();
    }

    /**
     * Getter (accessor) for the position of the flashlight in this state.
     * @return the position of the flashlight
     */
    public Position getFlashlightPosition() {
        return flashlightPosition; 
    }

    /**
     * Getter (accessor) for the position of person P1 in this state.
     * @return the position of person P1
     */
    public Position getP1Position() {
        return p1Position; 
    }

    /**
     * Getter (accessor) for the position of person P2 in this state.
     * @return the position of person P2
     */
    public Position getP2Position() {
        return p2Position; 
    }

    /**
     * Getter (accessor) for the position of person P5 in this state.
     * @return the position of person P5
     */
    public Position getP5Position() {
        return p5Position; 
    }

    /**
     * Getter (accessor) for the position of person P10 in this state.
     * @return the position of person P10
     */
    public Position getP10Position() {
        return p10Position; 
    }

    /**
     * Getter (accessor) for the time taken to get to this state.
     * @return the time taken to get to this state
     */
    public int getTimeSoFar() {
        return timeSoFar; 
    }
    
    // Private methods and instance fields should go here
    
    private Position p1Position;
    private Position p2Position;
    private Position flashlightPosition;
    private Position p5Position;
    private Position p10Position;
    private int timeSoFar;
}