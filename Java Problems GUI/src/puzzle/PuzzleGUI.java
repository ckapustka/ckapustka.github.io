package puzzle;

import framework.GUI;
import javax.swing.JFrame;

/**
 *
 * @author ckapustka
 */
public class PuzzleGUI extends JFrame {
    
    public PuzzleGUI() {
        PuzzleState initial = new PuzzleState(2, 8, 3, 1, 6, 4, 7, 0, 5);
        add(new GUI(new PuzzleProblem(), new PuzzleCanvas(initial)));
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
    /**
     * This method launches the gui.
     * @param args ignored
     */
    public static void main(String[] args) {
        new PuzzleGUI();
    }
}
