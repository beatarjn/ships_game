package game;

import java.util.Random;

public class Game {

    private final Board board;
    private final Renderer renderer;
    private final Random randomGenerator;
    private final I0Device io;

    private int computerShips;
    private int missedGuesses;
    private int successGuesses;
    private int numberOfGuesses;

    public Game(Board board, Renderer renderer, Random randomGenerator, I0Device io){
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
            x = io.askForInt("Enter X coordinate: ");
            y = io.askForInt("Enter Y coordinate: ");;

            if (checkIfValidGuess(x, y, board)) {
                if (board.getGameBoard()[x][y] == "S") {
                    io.outputLine("You hit the ship!");
                    board.getGameBoard()[x][y] = "H";
                    board.decrementNumberOfShips();
                    successGuesses++;
                } else if (board.getGameBoard()[x][y] == " ") {
                    io.outputLine("Sorry, you missed");
                    board.getGameBoard()[x][y] = "X";
                    missedGuesses++;
                }
            } else if (checkIfInvalidGuess(x, y, board)) {
                io.outputLine("You can't place ships outside the " + board.getNumRows() + " by " + board.getNumCols() + " grid");
            }
            numberOfGuesses++;
        } while (checkIfInvalidGuess(x, y, board));
    }

    public void gameOver() {

        io.outputLine("THE END");
        io.outputLine("Number of guesses done: " + numberOfGuesses);
        io.outputLine("Number of missed guesses: " + missedGuesses);
        io.outputLine("Number of successful guesses: " + successGuesses);
        double result = (double)successGuesses/ (double)numberOfGuesses;
        io.outputLine("Success ratio: " + (result * 100) + "%");

    }

    private boolean checkIfInvalidGuess(int x, int y, Board board) {
        return (x < 0 || x >= board.getNumRows()) || (y < 0 || y >= board.getNumCols());
    }

    private boolean checkIfValidGuess(int x, int y, Board board) {
        return (x >= 0 && x < board.getNumRows()) && (y >= 0 && y < board.getNumCols());
    }

    private void deployComputerShips() {
        for (int i = 1; i <= board.getNumberOfShips(); ) {
            int x = randomGenerator.nextInt(10);
            int y = randomGenerator.nextInt(10);
            if ((x >= 0 && x < board.getNumRows()) && (y >= 0 && y < board.getNumCols())) {
                board.getGameBoard()[x][y] = "S";
                i++;
            }
        }
    }

    public boolean isGameOver() {
        return board.getNumberOfShips() == 0;
    }
}
