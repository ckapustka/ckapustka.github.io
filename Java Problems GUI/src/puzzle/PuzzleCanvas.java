package puzzle;

import framework.Canvas;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JFrame;

/**
 *
 * @author ckapustka
 */
public class PuzzleCanvas extends Canvas {

    public PuzzleCanvas(PuzzleState state) {
        super(state);
        this.setPreferredSize(new Dimension(270, 270));
        this.tiles = new Rectangle2D[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int x = j * 90;
                int y = i * 90;
                this.tiles[i][j] = new Rectangle2D.Double(x, y, 90, 90);
            } 
        }
    } 
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        
        PuzzleState state = (PuzzleState) getDisplayState();
        g2.setStroke(new BasicStroke(5));
        Font font = new Font("SansSerif", Font.BOLD, 70);
        g2.setFont(font);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int n = state.getOneSquare(i, j);
                if (n != 0) {
                    String number = Integer.toString(n);
                    g2.setColor(Color.red);
                    g2.fill(this.tiles[i][j]);
                    g2.setColor(Color.black);
                    g2.draw(this.tiles[i][j]);
                    int x = 25 + 90 * j;
                    int y = 70 + 90 * i;
                    g2.setColor(Color.white);
                    g2.drawString(number, x, y);
                    
                }
            }
        }        
    }    
    
    //Main method (used for testing)
    public static void main(String[] args){
        JFrame frame = new JFrame("PuzzleCanvas Test");

        frame.add(new PuzzleCanvas(new PuzzleState(2, 8, 3, 1, 6, 4, 7, 0, 5)));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    private Rectangle2D[][] tiles;    
}
