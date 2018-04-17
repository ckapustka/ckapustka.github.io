package waterjug;

import framework.Canvas;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *This class extends Canvas for the WaterJug problem domain (includes animation)
 * @author Cliff Kapustka
 */
public class WaterJugCanvas extends Canvas {
 
    public WaterJugCanvas(WaterJugState state) {
        super(state);
        this.setPreferredSize(new Dimension(300, 300));
        this.drawContainerX();
        this.drawContainerY();
        this.timerX = new Timer(10, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                WaterJugCanvas.this.repaint();
                if (WaterJugCanvas.this.currentLevelX == WaterJugCanvas.this.nextLevelX) {
                    WaterJugCanvas.this.timerX.stop();
                }
                if (WaterJugCanvas.this.currentLevelX < WaterJugCanvas.this.nextLevelX) {
                    WaterJugCanvas.this.currentLevelX++;
                }
                else {
                    WaterJugCanvas.this.currentLevelX--;
                }
            }
        });
        this.timerY = new Timer(10, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                WaterJugCanvas.this.repaint();
                if (WaterJugCanvas.this.currentLevelY == WaterJugCanvas.this.nextLevelY) {
                    WaterJugCanvas.this.timerY.stop();
                }
                if (WaterJugCanvas.this.currentLevelY < WaterJugCanvas.this.nextLevelY) {
                    WaterJugCanvas.this.currentLevelY++;
                }
                else {
                    WaterJugCanvas.this.currentLevelY--;
                }
            }
        });
    }
    
    /**
     * Overrides paintComponent to draw specific objects to this class
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setColor(Color.red);
        g2.fill(new RoundRectangle2D.Double(0.0, 0.0, 300.0, 300.0, 30.0, 30.0));
        g2.setStroke(new BasicStroke(5.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setColor(Color.black);
        g2.draw(containerX);
        g2.draw(containerY);
        g2.setFont(new Font("SansSerif", Font.BOLD, 28));
        g2.setColor(Color.white);
        this.drawText(g2);
        this.drawWaterX(g2);
        this.drawWaterY(g2);
    }
    
    /**
     * Overrides render to repaint using timers for animation
     */
    @Override
    public void render() {
        this.currentLevelX = this.findYForX((WaterJugState) this.getPreviousState());
        this.currentLevelY = this.findYForY((WaterJugState) this.getPreviousState());
        this.nextLevelX = this.findYForX((WaterJugState) this.getDisplayState());
        this.nextLevelY = this.findYForY((WaterJugState) this.getDisplayState());
        this.timerX.start();
        this.timerY.start();
        this.setPreviousState(this.getDisplayState());
    }
    
    /**
     * Utility method used to find the y-coordinate for the water level in Jug X
     * @param state  the state of the WaterJug problem
     * @return integer  the y-coordinate of the water level in Jug X
     */
    public int findYForX(WaterJugState state) {
        int g = state.getJugX();
        return 225 - (g * GALLONS);
    }
    
    /**
     * Utility method used to find the y-coordinate for the water level in Jug Y
     * @param state  the state of the WaterJug problem
     * @return integer  the y-coordinate of the water level in Jug Y
     */
    public int findYForY(WaterJugState state) {
        int g = state.getJugY();
        return 225 - (g * GALLONS);
    }
    
    /**
     * Draws outline of Jug X
     */
    private void drawContainerX() {
        this.containerX = new GeneralPath();
        this.containerX.moveTo(25, 75);
        this.containerX.lineTo(25, 225);
        this.containerX.lineTo(125, 225);
        this.containerX.lineTo(125, 75);
    }   
    
    /**
     * Draws outline Jug Y
     */
    private void drawContainerY() {
        this.containerY = new GeneralPath();
        this.containerY.moveTo(175, 25);
        this.containerY.lineTo(175, 225);
        this.containerY.lineTo(275, 225);
        this.containerY.lineTo(275, 25);
    }
    
    /**
     * Draws and fills in the amount of water in Jug X
     * @param g2 the Graphics2D component used for drawing
     */
    public void drawWaterX(Graphics2D g2) {
        GeneralPath pathX = new GeneralPath();
        g2.setColor(Color.blue);
        
        pathX.moveTo(25, 225);
        pathX.lineTo(25, this.currentLevelX);
        pathX.lineTo(125, this.currentLevelX);
        pathX.lineTo(125, 225);
        pathX.closePath();
        g2.fill(pathX);
        g2.setColor(Color.black);
        g2.draw(this.containerX);
    }
    
    /**
     * Draws and fills in the amount of water in Jug Y
     * @param g2  the Graphics2D component used for drawing
     */
    public void drawWaterY(Graphics2D g2) {
        GeneralPath pathY = new GeneralPath();
        g2.setColor(Color.blue);
        pathY.moveTo(175, 225);
        pathY.lineTo(175, currentLevelY);
        pathY.lineTo(275, currentLevelY);
        pathY.lineTo(275, 225);
        pathY.closePath();
        g2.fill(pathY);
        g2.setColor(Color.black);
        g2.draw(this.containerY);
    }
    
    /**
     * Draws the text labels 
     * @param g2  the Graphics2D component used for drawing
     */
    public void drawText(Graphics2D g2) {
        WaterJugState state = (WaterJugState) this.getDisplayState();
        g2.drawString("X (" + state.getJugX() + ")", 45, 260);
        g2.drawString("Y (" + state.getJugY() + ")", 195, 260);
    }
    
    //Private instances
    private GeneralPath containerX;
    private GeneralPath containerY;
    private Timer timerX;
    private Timer timerY;
    private int currentLevelX;
    private int currentLevelY;
    private int nextLevelX;
    private int nextLevelY;
    private static final int GALLONS = 50;
    
    //Main method (for testing)
    public static void main(String[] args) {
        JFrame frame = new JFrame("WaterJugCanvas Test");
        frame.add(new WaterJugCanvas(new WaterJugState(3, 4)));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
}
