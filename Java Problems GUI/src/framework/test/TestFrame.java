package framework.test;

import bridge.BridgeCanvas;
import bridge.BridgeProblem;
import bridge.BridgeState;
import bridge.Position;
import framework.GUI;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import puzzle.PuzzleCanvas;
import puzzle.PuzzleProblem;
import puzzle.PuzzleState;
import waterjug.WaterJugCanvas;
import waterjug.WaterJugProblem;
import waterjug.WaterJugState;

/**
 * A class to display the bridge crossing and water jug problems in a tabbed pane
 * within an application frame.
 * @author tcolburn
 */
public class TestFrame extends JApplet {
    
    private void initTestFrame() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Bridge", new JScrollPane(new GUI(new BridgeProblem(), new BridgeCanvas(new BridgeState(Position.WEST,
                                                                                Position.WEST,
                                                                                Position.WEST,
                                                                                Position.WEST,
                                                                                Position.WEST,
                                                                                0)))));
        tabbedPane.add("Water Jug", new JScrollPane(new GUI(new WaterJugProblem(), new WaterJugCanvas(new WaterJugState(0, 0)))));
        tabbedPane.add("Puzzle", new JScrollPane(new GUI(new PuzzleProblem(), new PuzzleCanvas(new PuzzleState(2, 8, 3, 1, 6, 4, 7, 0, 5)))));
        JApplet frame = new JApplet();
        frame.add(tabbedPane);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setVisible(true);
    }
    
    @Override
    public void init() {
        try {
         // Use invokeAndWait() so that init() does not exit before GUI constructed
         SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
               initTestFrame();
            }
         });
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
    
//    public static void main(String[] args) {
//        new TestFrame();
//    }
    
}
