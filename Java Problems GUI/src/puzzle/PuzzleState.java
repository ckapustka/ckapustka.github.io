package puzzle;

import framework.State;
import graph.SimpleVertex;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author ckapustka
 */
public class PuzzleState extends SimpleVertex implements State {
 
    public PuzzleState(int s1, int s2, int s3, int s4, int s5, int s6, int s7, int s8, int s9) {
        this.squares = new int[3][3];
        this.squares[0][0] = s1;
        this.squares[0][1] = s2;
        this.squares[0][2] = s3;
        this.squares[1][0] = s4;
        this.squares[1][1] = s5;
        this.squares[1][2] = s6;
        this.squares[2][0] = s7;
        this.squares[2][1] = s8;
        this.squares[2][2] = s9;
        
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
        this.s6 = s6;
        this.s7 = s7;
        this.s8 = s8;
        this.s9 = s9;
    }
    
    public boolean equals(Object other) {
        PuzzleState state = (PuzzleState) other;
        return Arrays.deepEquals(this.squares, state.getSquares());
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append("\n");
            for (int j = 0; j < 3; j++) {
                if (getOneSquare(i, j) != 0) {
                    sb.append(getOneSquare(i, j) + "  ");
                }
                else {
                    sb.append("   ");
                }
            }
        }
        return sb.toString();
    }
    
    public void setSquares(int[][] s) {
        this.squares = s;
    }
    
    public int[][] getSquares() {
        return this.squares;
    }
    
    public int getOneSquare(int row, int column) {
        return this.squares[row][column];
    }
    
    public int getRow(int n) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (getOneSquare(i, j) == n) {
                    return i;              
                }
            }
        }
        return -1;
    }
    
    public int getColumn(int n) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (getOneSquare(i, j) == n) {
                    return j;              
                }
            }
        }
        return -1;
    }
    
    //Used to test toString() method
    public static void main(String[] args) {
        PuzzleState state = new PuzzleState(1, 2, 3, 4, 0, 5, 6, 7, 8);
        System.out.print(state.toString() + "\n");
        System.out.print(state.getRow(7) +"\n" + state.getColumn(7) + "\n");
    }
    
    private int[][] squares;
    private int s1;
    private int s2;
    private int s3;
    private int s4;
    private int s5;
    private int s6;
    private int s7;
    private int s8;
    private int s9;
}
