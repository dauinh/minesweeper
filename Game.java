import java.util.*;

public class Game {

    private int n;
    private int mineCount;
    private int[][] mineMap;
    private int[][] gameState;

    public Game(int size) {
        n = size;
        mineCount = size / 9;
        mineMap = new int[n][n];
        gameState = new int[n][n];
        populateMines();
    }

    public Game(int size, int mineCount) {
        this.mineCount = mineCount;
        n = size;
        mineMap = new int[n][n];
        gameState = new int[n][n];
        populateMines();
    }

    public void populateMines() {
        List<Integer> mines = new ArrayList<>();

        // Fill list with randomized mines
        for (int i=0; i<mineCount; i++) {
            mines.add(-1);
        }
        for (int i=0; i<(n*n - mineCount); i++) {
            mines.add(0);
        }
        Collections.shuffle(mines);

        // Populate mineMap
        for (int i=0; i<mines.size(); i++) {
            int row = i / n;
            int col = i % n;

            mineMap[row][col] = mines.get(i);
        }
    }

    public void reveal(int row, int col) {
        if (mineMap[row][col] == 0) {
            gameState[row][col] = 1;
        } else {
            gameState[row][col] = mineMap[row][col];
        }
        
    }

    public int getSize() {
        return n;
    }

    private void displayMineMap() {
        for (int i=0; i < n; i++) {
            for (int j=0; j < n; j++) {
                System.out.print(mineMap[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void displayGameState() {
        for (int i=0; i < n; i++) {
            for (int j=0; j < n; j++) {
                System.out.print(gameState[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Game initialState = new Game(9, 10);
        
        initialState.displayMineMap();
        // initialState.displayGameState();
    }
}
