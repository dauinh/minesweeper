public class Cell {
    private boolean isMine;
    // private int adjacentMines;
    private boolean isRevealed;
    private boolean isFlagged;

    public Cell() {}

    private void setMine() {
        this.isMine = true;
    }

    private boolean reveal() {
        this.isRevealed = true;
        return isMine;
    }

    private void setFlag() {
        this.isFlagged = true;
    }
}
