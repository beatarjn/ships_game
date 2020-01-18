package game;

import java.util.Arrays;

public class Board {

    private int numCols;
    private int numRows;
    private int numberOfShips;
    private String[][] gameBoard;

    public Board(int numCols, int numRows, int numberOfShips) {
        this.numCols = numCols;
        this.numRows = numRows;
        this.numberOfShips = numberOfShips;
        gameBoard = new String[numRows][numCols];
        fillBoardWithInitValues();
    }

    private void fillBoardWithInitValues() {
        Arrays.stream(gameBoard).forEach(array -> Arrays.fill(array, " "));
    }



    public String[][] getGameBoard() {
        return gameBoard;
    }

    public int getNumCols() {
        return numCols;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumberOfShips() {
        return numberOfShips;
    }

    public void decrementNumberOfShips() {
        numberOfShips--;
    }
}

