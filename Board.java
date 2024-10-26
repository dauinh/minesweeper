/*
 * Create a grid-based board
 * Randomly place mines on the board
 * Calculate numbers for non-mine cells indicating the count of adjacent mines 
 */
import java.util.*;

public class Board {

    private int n;
    private int mineCount;
    private Cell[][] grid;

    public Board(int size) {
        n = size;
        mineCount = size / 9;
        grid = new Cell[n][n];
        for (int r=0; r<n; r++) {
            for (int c=0; c<n; c++) {
                grid[r][c] = new Cell();
            }
        }
    }

    public Board(int size, int mineCount) {
        this.mineCount = mineCount;
        n = size;
        grid = new Cell[n][n];
        for (int r=0; r<n; r++) {
            for (int c=0; c<n; c++) {
                grid[r][c] = new Cell();
            }
        }
    }

    public void initialize() {
        populateMines();
        calculateAdjacent();
    }

    public int getSize() {
        return n;
    }

    public Cell getCell(int r, int c) {
        return this.grid[r][c];
    }

    public void revealAdjacentCells(int r, int c) {
        if (r < 0 || c < 0 || r >= n || c >= n || 
            grid[r][c].isMine() || grid[r][c].isRevealed()) return;
        
        grid[r][c].reveal();

        if (grid[r][c].getAdjacentMines() == 0) {
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
            for (int[] d : directions) {
                revealAdjacentCells(r + d[0], c + d[1]);
            }
        } return;
        
    }

    private void populateMines() {
        List<Integer> mines = new ArrayList<>();

        // Fill list with mines as -1 and non-mine as 0
        for (int i=0; i<n*n; i++) {
            if (i <= mineCount) mines.add(-1);
            else mines.add(0);
        }

        // Shuffle list
        Collections.shuffle(mines);

        // Add mines to grid
        for (int i=0; i<n*n; i++) {
            int r = i / n;
            int c = i % n;

            if (mines.get(i) == -1) grid[r][c].setMine();
        }
    }

    private void calculateAdjacent() {
        for (int r=0; r<n; r++) {
            for (int c=0; c<n; c++) {
                if (grid[r][c].isMine()) calculateAdjacentHelper(r, c);
            }
        }
    }

    private void calculateAdjacentHelper(int r, int c) {        
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};

        for (int[] d : directions) {
            int curRow = r + d[0];
            int curCol = c + d[1];
            if (curRow >= 0 && curCol >= 0 && curRow < n && curCol < n && !grid[curRow][curCol].isMine()) {
                int curMines = grid[curRow][curCol].getAdjacentMines();
                grid[curRow][curCol].setAdjacentMines(curMines + 1);
            }
        }
    }

    public int getMineCount() {
        return mineCount;
    }

    public boolean evaluateWin() {
        boolean allMinesFlagged = true;
        boolean allNonMinesRevealed = true;
        for (int i=0; i<n*n; i++) {
            int r = i / n;
            int c = i % n;
            // all non-mines are not revealed
            if (!grid[r][c].isMine() && !grid[r][c].isRevealed()) allNonMinesRevealed = false;
            // all mines are not flagged
            else if (grid[r][c].isMine() && !grid[r][c].isFlagged()) allMinesFlagged = false;
        }
        return allMinesFlagged && allNonMinesRevealed;
    }

    public void displayMines() {
        for (int r=0; r<n; r++) {
            for (int c=0; c<n; c++) {
                System.out.print(grid[r][c].isMine() + "  ");
            } System.out.println();
        }
    }

    public void displayAdjacentMines() {
        for (int r=0; r < n; r++) {
            for (int c=0; c < n; c++) {
                System.out.print(grid[r][c].getAdjacentMines() + "  ");
            } System.out.println();
        }
    }

    public void displayGameState() {
        System.out.print("    ");
        for (int r=0; r<n; r++) System.out.print(r + " ");
        System.out.println("\n   ------------------");

        for (int r=0; r < n; r++) {
            for (int c=0; c < n; c++) {
                if (c == 0) System.out.print(r + " | ");
                if (grid[r][c].isRevealed() && grid[r][c].isMine()) {
                    System.out.print("X" + " ");
                } else if (grid[r][c].isRevealed()) {
                    System.out.print(grid[r][c].getAdjacentMines() + " ");
                } else if (grid[r][c].isFlagged()) {
                    System.out.print("f" + " ");
                } else {
                    System.out.print("O ");
                }
            } System.out.println();
        }
    }

    public static void main(String[] args) {
        Board board = new Board(9, 10);

        System.out.println("mine field");
        board.initialize();
        board.displayMines();

        System.out.println("\n\ngame");
        board.displayAdjacentMines();
        board.displayGameState();
    }
}
