package framework;

import javax.swing.JComponent;

/**
 *This abstract class paints graphical renderings of the state of the application domain
 * @author Cliff Kapustka
 */
public abstract class Canvas extends JComponent {
    
    /**
     * Constructs a new Canvas object
     * @param state  the state of the problem
     */
    public Canvas(State state) {
        this.displayState = state;
        this.previousState = state;
    }
    
    /**
     * Accessor to acquire the current state
     * @return the current state
     */
    public State getDisplayState() {
        return this.displayState;
    }
    
    /**
     * Mutator to set the current state 
     * @param state the state being set to the current state
     */
    public void setDisplayState(State state) {
        previousState = this.displayState;
        displayState = state;
    }
    
    /**
     * Accessor to acquire the state previous to the current one
     * @return  the previous state
     */
    public State getPreviousState() {
        return this.previousState;
    }
    
    /**
     * Mutator to set the state prior to the current one
     * @param state  the state to be set to the prior state
     */
    public void setPreviousState(State state) {
        this.previousState = state;
    }
    
    /**
     * Used to create graphics with animation
     */
    public void render() {
        repaint();
    }
    
    //Private instances
    private State displayState;
    private State previousState;
}