package game;

import java.util.Random;
import java.util.Scanner;

public class BattleShips {
    public static int computerShips;

    public static void main(String[] args) {
        I0Device io = new I0Device(new Scanner(System.in));
        io.outputLine("**** Battle Ships ****");
        Game game = bootstrapGame(io);
        game.startGame();
        do {
            game.battle();
        } while (!game.isGameOver());
        game.gameOver();
    }

    private static Game bootstrapGame(I0Device input) {

        int numCols = input.askForInt("Enter number of columns for game board: ");;
        int numRows = input.askForInt("Enter number of rows for game board: ");
        int computerShips = input.askForInt("Enter number of ships: ");
        int maxNumberOfShips = numRows * numCols;
        while (computerShips > maxNumberOfShips) {
            computerShips = input.askForInt("Number of ships is too big. Please choose again: ");
        }
        Board board = new Board(numCols, numRows, computerShips);

        return new Game(board, new Renderer(), new Random(), input);

    }


}
