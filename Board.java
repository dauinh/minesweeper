/*
 * Create a grid-based board (e.g., 10x10)
 * Randomly place mines on the board
 * Calculate numbers for non-mine cells indicating the count of adjacent mines 
 */
import java.util.*;

public class Board {

    private int n;
    private int mineCount;
    private List<Integer> mines;
    private int[][] grid;

    public Board(int size) {
        n = size;
        mineCount = size / 9;
        grid = new int[n][n];
        populateMines();
    }

    public Board(int size, int mineCount) {
        this.mineCount = mineCount;
        n = size;
        grid = new int[n][n];
        populateMines();
    }

    private void populateMines() {
        this.mines = new ArrayList<>();

        // Fill list with mines as -1 and non-mine as 0
        for (int i=0; i<n*n; i++) {
            if (i <= mineCount) this.mines.add(-1);
            else this.mines.add(0);
        }

        // Shuffle list
        Collections.shuffle(mines);
    }

    private void calculateAdjacent() {
        for (int i=0; i<n*n; i++) {
            int r = i / n;
            int c = i % n;
            if (this.mines.get(i) == -1) calculateAdjacentHelper(r, c);
        }
    }

    private void calculateAdjacentHelper(int r, int c) {        
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};

        for (int[] direction : directions) {
            int curRow = r + direction[0];
            int curCol = c + direction[1];
            if (curRow >= 0 && curCol >= 0 && curRow < n && curCol < n && grid[curRow][curCol] != mineCount + 1) {
                grid[curRow][curCol] += 1;
            }
        }
        grid[r][c] = mineCount + 1;
    }

    private int getSize() {
        return n;
    }

    private void displayMines() {
        for (int i=0; i<mines.size(); i++) {
            if (i % n == 0) System.out.println();
            System.out.print(mines.get(i) + " ");
        }
    }

    private void display() {
        for (int i=0; i < n; i++) {
            for (int j=0; j < n; j++) {
                System.out.print(grid[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Board initialBoard = new Board(9, 10);

        System.out.println("mine field");
        initialBoard.displayMines();

        System.out.println("\n\ngame");
        initialBoard.calculateAdjacent();
        initialBoard.display();
    }
}
