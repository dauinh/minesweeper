/*
 * Allow players to reveal cells
 * Implement a flagging system for suspected mines
 * End the game when a mine is revealed
 * Win the game when all non-mine cells are revealed
 */

import java.util.*;

public class Game {

    private Board board;
    private boolean isEnded;

    public Game(int size) {
        board = new Board(size);
        board.initialize();
    }

    public Game(int size, int mineCount) {
        board = new Board(size, mineCount);
        board.initialize();
    }

    /* Main game loop */
    public void play(int option, int r, int c) {
        board.displayAdjacentMines();

        // player reveals cell
        if (option == 1) {
            // mine cell
            if (board.getCell(r, c).isMine()) isEnded = true;
            // safe cell
            else board.revealAdjacentCells(r, c);

        }
        // player flags cell
        else {
            board.flagCell(r, c);
        }
        board.displayGameState();
    }

    public static void main(String[] args) {
        Scanner scannerObj = new Scanner(System.in);
        Game game = new Game(9, 10);
        
        System.out.println("\n\nWelcome to Minesweeper\n");
        System.out.println("O: uncovered cell; 1-9: safe cell; X: mine\n");
        System.out.println("Enter CTRL + C to exit any time\n");

        
        game.play(1, 8, 8);

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
