package bridge;

import framework.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JFrame;
import java.awt.Font;

/**
 *This class extends Canvas for the Bridge-Crossing problem domain
 * @author Cliff Kapustka
 */
public class BridgeCanvas extends Canvas {
    /**
     * Constructs a new BridgeCanvas
     * @param state the state of the bridge problem
     */
    public BridgeCanvas(BridgeState state) {
        super(state);
        this.setPreferredSize(new Dimension(280, 300));
        this.drawRiver();
        this.drawBridge();        
    }
    
    /**
     * Overrides paintComponent to draw objects specific to this class
     * @param g the Graphics component used to draw objects
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Color XwaterX = new Color(0, 191, 205);
        Color XgrassX = new Color(154, 205, 50);
        Color XbridgeX = new Color(184, 134, 11);
        
        g2.setColor(XgrassX);
        g2.fill(new RoundRectangle2D.Double(0.0, 0.0, 280.0, 300.0, 30.0, 30.0));
        g2.setColor(XwaterX);
        g2.fill(this.river);
        g2.setColor(XbridgeX);
        g2.fill(this.bridge);
        g2.setColor(Color.BLACK);
        g2.draw(this.bridge);
        g2.setFont(new Font("SansSerif", Font.BOLD, 28));
        this.drawText(g2);
    }
    
    /**
     * Uses GeneralPath to construct a bridge graphic
     */
    private void drawBridge() {
        this.bridge = new GeneralPath();
        int x = 60;
        int y = 115;
        
        this.bridge.moveTo(x, y);
        
        this.bridge.lineTo(x + 160, y);
        this.bridge.lineTo(x + 160, y + 60);
        this.bridge.lineTo(x, y + 60); 
        this.bridge.closePath();
        for (int i = 0; i < 8; i++) {
            this.bridge.moveTo(x + 20, y);
            this.bridge.lineTo(x + 20, y + 60);
            x = x + 20;
        }
        this.bridge.closePath();
    }
    /**
     * Uses GeneralPath to construct a river graphic
     */
    private void drawRiver() {
        this.river = new GeneralPath();
        int x = 90;
        int y = 0;
        
        this.river.moveTo(x, y);
        this.river.quadTo(x - 15, y + 50, x, y + 100);
        this.river.quadTo(x + 25, y + 150, x, y + 200);
        this.river.quadTo(x - 15, y + 250, x, y + 300);
        this.river.lineTo(x + 95, y + 300);
        this.river.quadTo(x + 70, y + 250, x + 95, 200);
        this.river.quadTo(x + 110, y + 150, x + 95, 100);
        this.river.quadTo(x + 70, y + 50, x + 95, 0);
        this.river.closePath();
    }
    
    /**
     * Draws text graphics to represent the characters
     * @param g2 the Graphics component used to draw objects
     */
    public void drawText(Graphics g2) {
        int west = 15;
        int east = 225;
        BridgeState state = (BridgeState) this.getDisplayState();
        Position p1 = state.getP1Position();
        Position p2 = state.getP2Position();
        Position f = state.getFlashlightPosition();
        Position p5 = state.getP5Position();
        Position p10 = state.getP10Position();
        int time = state.getTimeSoFar();
        
        if (p1 == Position.WEST) {
            g2.drawString("P1", west, 50);
        }
        else {
            g2.drawString("P1", east, 50);
        }
        if (p2 == Position.WEST) {
            g2.drawString("P2", west, 100);
        }
        else {
            g2.drawString("P2", east, 100);
        }
        if (f == Position.WEST) {
            g2.drawString("=(", west, 155);
        }
        else {
            g2.drawString(" )=", east, 155);
        }
        if (p5 == Position.WEST) {
            g2.drawString("P5", west, 215);
        }
        else {
            g2.drawString("P5", east, 215);
        }
        if (p10 == Position.WEST) {
            g2.drawString("P10", west, 265);
        }
        else {
            g2.drawString("P10", east, 265);
        }
        g2.drawString(Integer.toString(time) + " min", 100, 155);
    }
    
    //Private instances
    private GeneralPath bridge;
    private GeneralPath river;
    
    //Main method (for testing)
    public static void main(String[] args) {
        JFrame frame = new JFrame("BridgeCanvas Test");
        frame.add(new BridgeCanvas(new BridgeState(Position.EAST,
                                                    Position.EAST,
                                                    Position.EAST,
                                                    Position.EAST,
                                                    Position.EAST,
                                                    15)));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
}
