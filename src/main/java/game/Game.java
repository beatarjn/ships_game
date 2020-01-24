package game;

import java.util.Random;

public class Game {

    public static final String PARAMS = "%s: %d";
    private final Board board;
    private final Renderer renderer;
    private final Random randomGenerator;
    private final I0Device io;
    private int missedGuesses;
    private int successGuesses;
    private int numberOfGuesses;

    public Game(Board board, Renderer renderer, Random randomGenerator, I0Device io) {
        this.board = board;
        this.renderer = renderer;
        this.randomGenerator = randomGenerator;
        this.io = io;
    }

    public void startGame() {
        deployComputerShips();
        displayBoard();
    }

    private void displayBoard() {
        String renderBoard = renderer.renderGameBoard(this.board);
        io.output(renderBoard);
    }

    public void battle() {
        playerTurn();
        displayBoard();
        io.outputLine();
        io.outputLine("Computer ships: " + board.getNumberOfShips());
    }

    public void playerTurn() {
        io.outputLine("\nYOUR TURN");
        int x;
        int y;
        do {
            x = askForXCoordinate();
            y = askForYCoordinate();

            if (checkIfValidGuess(x, y, board)) {
                if (Ship.SHIP.toString().equalsIgnoreCase(getPoint(x, y))) {
                    io.outputLine("You hit the ship!");
                    board.getBoardMatrix()[x][y] = Ship.HIT.toString();
                    board.decrementNumberOfShips();
                    successGuesses++;
                } else if (Ship.EMPTY.toString().equalsIgnoreCase(getPoint(x, y))) {
                    io.outputLine("Sorry, you missed");
                    board.getBoardMatrix()[x][y] = Ship.MISSED.toString();
                    missedGuesses++;
                }
            } else if (!checkIfValidGuess(x, y, board)) {
                io.outputLine("You can't place ships outside the " + board.getNumRows() + " by " + board.getNumCols() + " grid");
            }
            numberOfGuesses++;
        } while (!checkIfValidGuess(x, y, board));
    }

    public void gameOver() {
        io.outputLine("THE END");
        io.outputLine(String.format(PARAMS, "Number of guesses done", numberOfGuesses));
        io.outputLine(String.format(PARAMS, "Number of missed guesses: ", missedGuesses));
        io.outputLine(String.format(PARAMS, "Number of successful guesses: ", successGuesses));
        io.outputLine(String.format("%s: %d %p", "Success ratio: ", getSuccessRate(), "%"));
    }

    private boolean checkIfValidGuess(int x, int y, Board board) {
        return (x >= 0 && x < board.getNumRows()) && (y >= 0 && y < board.getNumCols());
    }

    private void deployComputerShips() {
        for (int i = 1; i <= board.getNumberOfShips(); ) {
            int x = randomGenerator.nextInt(10);
            int y = randomGenerator.nextInt(10);
            if ((x >= 0 && x < board.getNumRows()) && (y >= 0 && y < board.getNumCols())) {
                board.getBoardMatrix()[x][y] = Ship.SHIP.toString();
                i++;
            }
        }
    }

    public boolean isGameOver() {
        return board.getNumberOfShips() == 0;
    }

    private String getPoint(int x, int y) {
        return board.getBoardMatrix()[x][y];
    }

    private int askForYCoordinate() {
        return io.askForInt("Enter Y coordinate: ");
    }

    private int askForXCoordinate() {
        return io.askForInt("Enter X coordinate: ");
    }

    private double getSuccessRate() {
        return ((double) successGuesses / (double) numberOfGuesses) * 100;
    }
}
