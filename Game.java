/*
 * Allow players to reveal cells
 * Implement a flagging system for suspected mines
 * End the game when a mine is revealed
 * Win the game when all non-mine cells are revealed
 */

import java.util.*;

public class Game {

    private Board board;

    public Game(int size) {
        board = new Board(size);
    }

    public Game(int size, int mineCount) {
        board = new Board(size, mineCount);
    }

    /* Main game loop */
    public void play() {
        

    }

    public static void main(String[] args) {
        Scanner scannerObj = new Scanner(System.in);
        Game game = new Game(9, 10);
        
        System.out.println("Welcome to Minesweeper\n");
        System.out.println("0: uncovered cell; 1: safe cell; -1: mine\n");
        System.out.println("Enter CTRL + C to exit any time\n");

        
        // game.displayGameState();

        // System.out.print("\nEnter coords to reveal mine, e.g. 3 2:");
        // String move = scannerObj.nextLine();
        // String[] parts = move.split(" ");
        // int row = Integer.parseInt(parts[0]);
        // int col = Integer.parseInt(parts[1]);

        // game.reveal(row, col);
        // game.displayGameState();

        scannerObj.close();

    }
}
