package bridge;

import framework.Move;
import framework.State;

/**
 * This class represents moves in the Bridge Crossing problem.
 * A move object stores its move name and knows how to apply itself
 * to a bridge state to create a new state representing the result
 * of the move.
 * Note that this class extends the abstract class <b>Move</b> and
 * therefore imports <b>framework.Move</b>.
 * This class inherits the <b>getMoveName()</b> method from its parent
 * and thus it should not have an instance field for the move name.
 * Other than that, it can be essentially the same as in the previous
 * assignment.
 * @author your name here
 */
public class BridgeMove extends Move {

    /**
     * Constructs a new bridge move object.
     * Note that the move name is passed to the parent constructor
     * using <b>super</b>.
     * @param moveName the name of this move.
     * It is an error if the name is not one of the following:
     * <ul>
     * <li> "P1 crosses alone" </li>
     * <li> "P2 crosses alone" </li>
     * <li> "P5 crosses alone" </li>
     * <li> "P10 crosses alone" </li>
     * <li> "P1 crosses with P2" </li>
     * <li> "P1 crosses with P5" </li>
     * <li> "P1 crosses with P10" </li>
     * <li> "P2 crosses with P5" </li>
     * <li> "P2 crosses with P10" </li>
     * <li> "P5 crosses with P10" </li>
     * </ul>
     */
    public BridgeMove(String moveName) {
        super(moveName);
        
    }
    
    /**
     * Attempts to perform this move on a given bridge state.
     * Note that this method implements the abstract <b>doMove</b> method declared
     * in the parent.
     * Thus the argument of type <b>State</b> must be cast to type
     * <b>BridgeState</b> before processing.
     * The move to perform is determined by this object's move name.
     * If the move can be performed a new bridge state object is returned that
     * reflects this move.
     * A move cannot be performed if the flashlight is not on the same side
     * as the crossing person(s), or if a pair of persons who are crossing are not
     * on the same side.
     * If the move cannot be performed <b>null</b> is returned.
     * @param otherState the bridge state on which this move is to be performed
     * @return a new bridge state reflecting the move, or <b>null</b> if it
     * cannot be performed
     */
    public State doMove(State otherState) {
        BridgeState state = (BridgeState) otherState;
        
        if (getMoveName() == "P1 crosses alone" &&
                state.getFlashlightPosition() == state.getP1Position()) {
            if (state.getP1Position() == Position.WEST) {
                return new BridgeState(Position.EAST,
                                        state.getP2Position(),
                                        Position.EAST,
                                        state.getP5Position(),
                                        state.getP10Position(),
                                        state.getTimeSoFar()+1);
            }
            else {
                return new BridgeState(Position.WEST,
                                        state.getP2Position(),
                                        Position.WEST,
                                        state.getP5Position(),
                                        state.getP10Position(),
                                        state.getTimeSoFar()+1);
            }
        }
        if (getMoveName() == "P2 crosses alone" &&
                state.getFlashlightPosition() == state.getP2Position()) {
            if (state.getP2Position() == Position.WEST) {
                return new BridgeState(state.getP1Position(),
                                        Position.EAST,
                                        Position.EAST,
                                        state.getP5Position(),
                                        state.getP10Position(),
                                        state.getTimeSoFar()+2);
            }
            else {
                return new BridgeState(state.getP1Position(),
                                        Position.WEST,
                                        Position.WEST,
                                        state.getP5Position(),
                                        state.getP10Position(),
                                        state.getTimeSoFar()+2);
            }
        }
        if (getMoveName() == "P5 crosses alone" &&
                state.getFlashlightPosition() == state.getP5Position()) {
            if (state.getP5Position() == Position.WEST) {
                return new BridgeState(state.getP1Position(),
                                        state.getP2Position(),
                                        Position.EAST,
                                        Position.EAST,
                                        state.getP10Position(),
                                        state.getTimeSoFar()+5);
            }
            else {
                return new BridgeState(state.getP1Position(),
                                        state.getP2Position(),
                                        Position.WEST,
                                        Position.WEST,
                                        state.getP10Position(),
                                        state.getTimeSoFar()+5);
            }
        }
        if (getMoveName() == "P10 crosses alone" &&
                state.getFlashlightPosition() == state.getP10Position()) {
            if (state.getP10Position() == Position.WEST) {
                return new BridgeState(state.getP1Position(),
                                        state.getP2Position(),
                                        Position.EAST,
                                        state.getP5Position(),
                                        Position.EAST,
                                        state.getTimeSoFar()+10);
            }
            else {
                return new BridgeState(state.getP1Position(),
                                        state.getP2Position(),
                                        Position.WEST,
                                        state.getP5Position(),
                                        Position.WEST,
                                        state.getTimeSoFar()+10);
            }
        }
        if (getMoveName() == "P1 crosses with P2" &&
                state.getFlashlightPosition() == state.getP1Position() &&
                state.getP1Position() == state.getP2Position()) {
            if (state.getP1Position() == Position.WEST) {
                return new BridgeState(Position.EAST,
                                        Position.EAST,
                                        Position.EAST,
                                        state.getP5Position(),
                                        state.getP10Position(),
                                        state.getTimeSoFar()+2);
            }
            else {
                return new BridgeState(Position.WEST,
                                        Position.WEST,
                                        Position.WEST,
                                        state.getP5Position(),
                                        state.getP10Position(),
                                        state.getTimeSoFar()+2);
            }
        }
        if (getMoveName() == "P1 crosses with P5" &&
                state.getFlashlightPosition() == state.getP1Position() &&
                state.getP1Position() == state.getP5Position()) {
            if (state.getP1Position() == Position.WEST) {
                return new BridgeState(Position.EAST,
                                        state.getP2Position(),
                                        Position.EAST,
                                        Position.EAST,
                                        state.getP10Position(),
                                        state.getTimeSoFar()+5);
            }
            else {
                return new BridgeState(Position.WEST,
                                        state.getP2Position(),
                                        Position.WEST,
                                        Position.WEST,
                                        state.getP10Position(),
                                        state.getTimeSoFar()+5);
            }
        }
        if (getMoveName() == "P1 crosses with P10" &&
                state.getFlashlightPosition() == state.getP1Position() &&
                state.getP1Position() == state.getP10Position()) {
            if (state.getP1Position() == Position.WEST) {
                return new BridgeState(Position.EAST,
                                        state.getP2Position(),
                                        Position.EAST,
                                        state.getP5Position(),
                                        Position.EAST,
                                        state.getTimeSoFar()+10);
            }
            else {
                return new BridgeState(Position.WEST,
                                        state.getP2Position(),
                                        Position.WEST,
                                        state.getP5Position(),
                                        Position.WEST,
                                        state.getTimeSoFar()+10);
            }
        }
        if (getMoveName() == "P2 crosses with P5" &&
                state.getFlashlightPosition() == state.getP2Position() &&
                state.getP2Position() == state.getP5Position()) {
            if (state.getP2Position() == Position.WEST) {
                return new BridgeState(state.getP1Position(),
                                        Position.EAST,
                                        Position.EAST,
                                        Position.EAST,
                                        state.getP10Position(),
                                        state.getTimeSoFar()+5);
            }
            else {
                return new BridgeState(state.getP1Position(),
                                        Position.WEST,
                                        Position.WEST,
                                        Position.WEST,
                                        state.getP10Position(),
                                        state.getTimeSoFar()+5);
            }
        }
        if (getMoveName() == "P2 crosses with P10" &&
                state.getFlashlightPosition() == state.getP2Position() &&
                state.getP2Position() == state.getP10Position()) {
            if (state.getP2Position() == Position.WEST) {
                return new BridgeState(state.getP1Position(),
                                        Position.EAST,
                                        Position.EAST,
                                        state.getP5Position(),
                                        Position.EAST,
                                        state.getTimeSoFar()+10);
            }
            else {
                return new BridgeState(state.getP1Position(),
                                        Position.WEST,
                                        Position.WEST,
                                        state.getP5Position(),
                                        Position.WEST,
                                        state.getTimeSoFar()+10);
            }
        }
        if (getMoveName() == "P5 crosses with P10" &&
                state.getFlashlightPosition() == state.getP5Position() &&
                state.getP5Position() == state.getP10Position()) {
            if (state.getP5Position() == Position.WEST) {
                return new BridgeState(state.getP1Position(),
                                        state.getP2Position(),
                                        Position.EAST,
                                        Position.EAST,
                                        Position.EAST,
                                        state.getTimeSoFar()+10);
            }
            else {
                return new BridgeState(state.getP1Position(),
                                        state.getP2Position(),
                                        Position.WEST,
                                        Position.WEST,
                                        Position.WEST,
                                        state.getTimeSoFar()+10);
            }
        }
        else {
            return null;
        } 
    }
    
    // Private methods and instance fields should go here

}
