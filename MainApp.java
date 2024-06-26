import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scannerObj = new Scanner(System.in);
        Game game = new Game(9, 10);
        
        System.out.println("Welcome to Minesweeper\n");
        System.out.println("0: uncovered cell; 1: safe cell; -1: mine\n");
        System.out.println("Enter CTRL + C to exit any time\n");

        
        game.displayGameState();

        System.out.print("\nEnter coords to reveal mine, e.g. 3 2:");
        String move = scannerObj.nextLine();
        String[] parts = move.split(" ");
        int row = Integer.parseInt(parts[0]);
        int col = Integer.parseInt(parts[1]);

        game.reveal(row, col);
        game.displayGameState();

        scannerObj.close();

    }
}
