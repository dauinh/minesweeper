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
        if (option.equals("a")) {
            // mine cell
            if (board.getCell(r, c).isMine()) {
                board.getCell(r, c).reveal();
                isEnded = true;
            }
            // safe cell
            else board.revealAdjacentCells(r, c);

        } else if (option.equals("b")) {
            board.getCell(r, c).flag();
            System.out.println(board.getCell(r, c).isFlagged());
        }
        board.displayGameState();
    }

    public boolean evaluateWin() {
        if (board.evaluateWin()) return true;
        return false;
    }

    public static void main(String[] args) {
        Scanner scannerObj = new Scanner(System.in);
        Game game = new Game(2, 1);
        
        System.out.println("\n\nWelcome to Minesweeper\n");
        System.out.println("O: uncovered cell; 1-9: safe cell; X: mine\n");
        System.out.println("There are " + game.board.getMineCount() + " mines. Good luck!");
        System.out.println("Enter CTRL + C to exit any time\n");

        System.out.println();
        game.board.displayMines();
        game.board.displayGameState();

        while (!game.isEnded) {
            System.out.print("\n(a) Reveal cell\n(b) Flag cell\nChoose action: ");
            String option = scannerObj.next();
            option.strip();

            System.out.print("\nEnter cell coords in this format row,column: ");
            String coords = scannerObj.next();
            String[] parts = coords.split(",");
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);
            
            System.out.println("\n-------------------------");
            game.play(option, row, col);
            if (game.evaluateWin()) game.isEnded = true;
        }
        if (game.evaluateWin()) {
            System.out.println("\nYou won!");
        } else {
            System.out.println("\nYou lost!");
        }
        System.out.println("\nFinal game board\n");
        game.board.displayGameState();

        scannerObj.close();

    }
}
