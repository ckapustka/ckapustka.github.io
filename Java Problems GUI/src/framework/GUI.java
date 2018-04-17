package framework;

import graph.Vertex;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
/**
 * A class that creates GUI components for solving search problems.
 * @author Cliff Kapustka
 */    

public class GUI extends JComponent {
    
    public GUI(Problem problem, Canvas canvas) {
        this.problem = problem;
        this.canvas = canvas;
        this.setInitialState(problem.getCurrentState());
        this.canvas.setDisplayState(problem.getCurrentState());
        this.canvas.render();
        this.setLayout(new BoxLayout(this, 1));
               
        //Intro
        JPanel introPanel = new JPanel();
        intro = new JTextPane();
        StyledDocument doc = intro.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        intro.setText(GUI.this.problem.getIntroduction());
        intro.setEditable(false);
        intro.setFont(new Font("Monospaced", 1, 14));
        introPanel.add(intro);
        this.add(introPanel);
        
        JPanel MSPanel = new JPanel();
        
        //Display State
        statePanel = new JPanel();
        statePanel.setBorder(new TitledBorder("Current State"));
        statePanel.add(canvas);
        MSPanel.add(statePanel);
        
        //Moves
        movePanel = new JPanel(new GridLayout(0, 1, 0, 5));
        movePanel.setBorder(new TitledBorder("Possible Moves"));
        for (Move move : GUI.this.problem.getMoves()) {
                JButton button = new JButton(move.getMoveName());
                button.addActionListener(new Listener(move));
                movePanel.add(button);
        }
        MSPanel.add(movePanel);
        
        this.add(MSPanel);
        
        //Reset Button
        JButton reset = new JButton("RESET");
        reset.setPreferredSize(new Dimension(100, 25));
        reset.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.this.moveCount = 0;
                GUI.this.problem.setCurrentState(GUI.this.initialState);
                GUI.this.canvas.setDisplayState(GUI.this.initialState);
                GUI.this.canvas.render();
                solveButton.setEnabled(true);
                showMovesButton.setEnabled(false);
                nextMoveButton.setEnabled(false);
                stat1.setText("        ");
                stat2.setText("        ");
                stat3.setText("        ");
                
            }
        });
        
        //Search stats and Solve capability
        final JRadioButton bfsButton = new JRadioButton("Breadth-first");
        final JRadioButton dfsButton = new JRadioButton("Depth-first");
        
        ButtonGroup groupButtons = new ButtonGroup();
        groupButtons.add(bfsButton);
        groupButtons.add(dfsButton);
        groupButtons.setSelected(bfsButton.getModel(), true);
        
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(0, 2));
        radioPanel.setBorder(new TitledBorder("Search Types"));
        radioPanel.add(bfsButton);
        radioPanel.add(dfsButton);
        
        solveButton = new JButton("SOLVE");
        solveButton.setPreferredSize(new Dimension(100, 50));
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (dfsButton.isSelected()) {
                    GUI.this.problem.solve(1);
                }
                else {
                    GUI.this.problem.solve(0);
                }
                solveButton.setEnabled(false);
                showMovesButton.setEnabled(true);
                nextMoveButton.setEnabled(true);
                stack = GUI.this.problem.getStack();
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                stat1.setText(Integer.toString(stack.size()));
                stat2.setText(Integer.toString(GUI.this.problem.getQueueOps()));
                stat3.setText(Integer.toString(GUI.this.problem.getMaxQueueSize()));
            }
        });
        
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(2, 1));
        searchPanel.add(radioPanel);
        searchPanel.add(solveButton);
        
        nextMoveButton = new JButton("SHOW NEXT MOVE");
        nextMoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (!stack.empty()) {
                    State state = (State) stack.pop();
                    GUI.this.canvas.setDisplayState(state);
                    GUI.this.canvas.render();
                }
            }
        });
        
        showMovesButton = new JButton("SHOW ALL MOVES");
        showMovesButton.setEnabled(false);
        
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (stack.empty()) {
                    timer.stop();
                }
                else {
                    State state = (State) stack.pop();
                    GUI.this.canvas.setDisplayState(state);
                    GUI.this.canvas.render();
                }
            }
        });
        
        showMovesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                timer.start();
            }
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1));
        buttonPanel.add(nextMoveButton);
        buttonPanel.add(showMovesButton);
        
        JPanel statPanel = new JPanel();
        statPanel.setLayout(new GridLayout(3, 1));
        statPanel.setBorder(new TitledBorder("Solution Statistics"));
        stat1 = new JLabel();
        stat2 = new JLabel();
        stat3 = new JLabel();
        stat1.setText("        ");
        stat2.setText("        ");
        stat3.setText("        ");
        statPanel.add(new JLabel("Solution Length: "));
        statPanel.add(stat1);
        statPanel.add(new JLabel("Num of DEQUE Ops: "));
        statPanel.add(stat2);
        statPanel.add(new JLabel("Max DEQUE Size: "));
        statPanel.add(stat3);
        
        JPanel SBPanel = new JPanel();
        SBPanel.setLayout(new GridLayout(2, 1));
        SBPanel.add(statPanel);
        SBPanel.add(buttonPanel);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(searchPanel);
        bottomPanel.add(reset);
        bottomPanel.add(SBPanel);
        add(bottomPanel);
        
        solveButton.setEnabled(true);
        showMovesButton.setEnabled(false);
        nextMoveButton.setEnabled(false);
    }
    
    public final void setInitialState(State state) {
        this.initialState = state;
    }
    
    private void messagePane(String message) {
        JOptionPane.showMessageDialog(this.intro, message);
    }

    private class Listener implements ActionListener {
        private Move move;

        private Listener(Move move) {
            this.move = move;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            canvas.setPreviousState(GUI.this.problem.getCurrentState());
            State newState = this.move.doMove(GUI.this.problem.getCurrentState());
            if (newState != null) {
                GUI.this.moveCount++;
                GUI.this.problem.setCurrentState(newState);
                canvas.setDisplayState(GUI.this.problem.getCurrentState());
                canvas.render();
                if (GUI.this.problem.success()) {
                    GUI.this.messagePane("Congratulations. You solved the problem in " + GUI.this.moveCount + " moves.");
                }
            } else {
                GUI.this.messagePane("Illegal Move");
            }
        }
    }

    private JTextPane intro;
    private JPanel statePanel;
    private JPanel movePanel;
    private Problem problem;
    private Canvas canvas;
    private State initialState;
    private int moveCount;
    private Stack<Vertex> stack;
    private Timer timer;
    private JLabel stat1;
    private JLabel stat2;
    private JLabel stat3;
    private JButton nextMoveButton;
    private JButton showMovesButton;
    private JButton solveButton;
}
