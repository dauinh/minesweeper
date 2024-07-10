public class Cell {
    private boolean isMine = false;
    private int adjacentMines = 0;
    private boolean isRevealed = false;
    private boolean isFlagged = false;

    public Cell() {}

    public boolean isMine() {
        return this.isMine;
    }

    public void setMine() {
        this.isMine = true;
    }

    public int getAdjacentMines() {
        return this.adjacentMines;
    }

    public void setAdjacentMines(int n) {
        this.adjacentMines = n;
    }

    public boolean isRevealed() {
        return this.isRevealed;
    }

    public void reveal() {
        this.isRevealed = true;
    }

    public boolean isFlagged() {
        return this.isFlagged;
    }

    public void flag() {
        this.isFlagged = !this.isFlagged;
    }
}
