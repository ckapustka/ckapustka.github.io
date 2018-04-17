package puzzle;

import framework.Console;

/**
 *
 * @author ckapustka
 */
public class PuzzleConsole {
        /**
     * This method launches the console.
     * @param args ignored
     */
    public static void main(String[] args) {
        Console console = new Console(new PuzzleProblem());
        console.start();
    } 
}
