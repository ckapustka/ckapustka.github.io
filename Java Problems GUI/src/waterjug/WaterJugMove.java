package waterjug;

import framework.Move;
import framework.State;

/**
 * This class represents moves in the Water Jug problem.
 * A move object stores its move name and knows how to apply itself
 * to a water jug state to create a new state representing the result
 * of the move.
 * Note that this class extends the abstract class <b>Move</b> and
 * therefore imports <b>framework.Move</b>.
 * This class inherits the <b>getMoveName()</b> method from its parent
 * and thus it should not have an instance field for the move name.
 * @author Cliff Kapustka
*/
public class WaterJugMove extends Move {

    /**
     * Constructs a new water jug move object.
     * Note that the move name is passed to the parent constructor
     * using <b>super</b>.
     * @param moveName the name of this move.
     * It is an error if the name is not one of the following:
     * <ul>
     * <li> "Fill Jug X" </li>
     * <li> "Fill Jug Y" </li>
     * <li> "Empty Jug X" </li>
     * <li> "Empty Jug Y" </li>
     * <li> "Transfer Jug X to Jug Y" </li>
     * <li> "Transfer Jug Y to Jug X" </li>
     * </ul>
     */
    public WaterJugMove(String moveName) {
	super(moveName);

    }

    /**
     * Attempts to perform this move on a given water jug state.
     * Note that this method implements the abstract <b>doMove</b> method declared
     * in the parent.
     * Thus the argument of type <b>State</b> must be cast to type
     * <b>WaterJugState</b> before processing.
     * The move to perform is determined by this object's move name.
     * If the move can be performed a new water jug state object is returned that
     * reflects this move.
     * A move cannot be performed if trying to fill or transfer to an already
     * full jug, or if trying to empty or transfer from an empty jug.
     * If the move cannot be performed <b>null</b> is returned.
     * @param otherState the water jug state on which this move is to be performed
     * @return a new water jug state reflecting the move, or <b>null</b> if it
     * cannot be performed
     */
    public State doMove(State otherState) {
	WaterJugState state = (WaterJugState) otherState;
        
        if (getMoveName() == "Fill Jug X" && state.getJugX() < 3) {
            return new WaterJugState(3, state.getJugY());
        }
        if (getMoveName() == "Fill Jug Y" && state.getJugY() < 4) {
            return new WaterJugState(state.getJugX(), 4);
        }
        if (getMoveName() == "Empty Jug X" && state.getJugX() > 0) {
            return new WaterJugState(0, state.getJugY());
        }
        if (getMoveName() == "Empty Jug Y" && state.getJugY() > 0) {
            return new WaterJugState(state.getJugX(), 0);
        }
        if (getMoveName() == "Transfer Jug X to Jug Y" && state.getJugX() > 0 && state.getJugY() < 4) {
            int x = state.getJugX();
            int y = state.getJugY();
            
            if (x + y > 4) {
                x = x - (4 - y);
                y = 4;
                return new WaterJugState(x, y);
            }
            else {
                y = x + y;
                x = 0;
                return new WaterJugState(x, y);
            }
        }
        if (getMoveName() == "Transfer Jug Y to Jug X" && state.getJugX() < 3 && state.getJugY() > 0) {
            int x = state.getJugX();
            int y = state.getJugY();
            
            if (x + y > 3) {
                y = y - (3 - x);
                x = 3;
                return new WaterJugState(x, y);
            }
            else {
                x = x + y;
                y = 0;
                return new WaterJugState(x, y);
            }
        }
        else {
            return null; 
        }
    }
    
    // Private methods and instance fields should go here

}
