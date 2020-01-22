package game;

import java.util.Arrays;

public class Board {

    private int numCols;
    private int numRows;
    private int numberOfShips;
    private String[][] boardMatrix;

    public Board(int numCols, int numRows, int numberOfShips) {
        this.numCols = numCols;
        this.numRows = numRows;
        this.numberOfShips = numberOfShips;
        boardMatrix = new String[numRows][numCols];
        fillBoardWithInitValues();
    }

    private void fillBoardWithInitValues() {
        Arrays.stream(boardMatrix).forEach(array -> Arrays.fill(array, Ship.EMPTY.toString()));
    }

    public String[][] getBoardMatrix() {
        return boardMatrix;
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

