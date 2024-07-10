/*
 * Allow players to reveal cells
 * Implement a flagging system for suspected mines
 * End the game when a mine is revealed
 * Win the game when all non-mine cells are revealed
 */

import java.util.*;

public class Game {

    private Board board;
    private boolean isEnded = false;

    public Game(int size) {
        board = new Board(size);
        board.initialize();
    }

    public Game(int size, int mineCount) {
        board = new Board(size, mineCount);
        board.initialize();
    }

    /* Main game loop */
    public void play(String option, int r, int c) {
        if (option.equals("1")) {
            // mine cell
            if (board.getCell(r, c).isMine()) {
                board.getCell(r, c).reveal();
                isEnded = true;
            }
            // safe cell
            else board.revealAdjacentCells(r, c);

        } else if (option.equals("2")) {
            board.getCell(r, c).flag();
            System.out.println(board.getCell(r, c).isFlagged());
        }
        board.displayGameState();
    }

    public static void main(String[] args) {
        Scanner scannerObj = new Scanner(System.in);
        Game game = new Game(9, 10);
        
        System.out.println("\n\nWelcome to Minesweeper\n");
        System.out.println("O: uncovered cell; 1-9: safe cell; X: mine\n");
        System.out.println("Enter CTRL + C to exit any time\n");

        System.out.println();
        game.board.displayGameState();

        while (!game.isEnded) {
            System.out.print("\n1. Reveal cell\n2. Flag cell\nChoose action: ");
            String option = scannerObj.next();
            option.strip();

            System.out.print("\nEnter cell coords in this format row,column: ");
            String coords = scannerObj.next();
            String[] parts = coords.split(",");
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);

            game.play(option, row, col);
            System.out.println("\n-------------------------");
        }

        System.out.println("\nYou lost!");
        game.board.displayGameState();

        scannerObj.close();

    }
}
