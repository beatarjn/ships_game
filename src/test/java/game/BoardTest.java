package game;


import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    public void testBoardInitialSetup(){

        //given
        int numOfCols = 4;
        int numOfRows = 3;
        int numOfShips = 5;

        //when
        Board board = new Board(numOfCols, numOfRows, numOfShips);

        //then
        Assert.assertEquals(4, board.getNumCols());
        Assert.assertEquals(3, board.getNumRows());
        Assert.assertEquals(5, board.getNumberOfShips());

        Assert.assertArrayEquals(board.getGameBoard()[0], new String[]{" ", " ", " ", " "});
        Assert.assertArrayEquals(board.getGameBoard()[1], new String[]{" ", " ", " ", " "});
        Assert.assertArrayEquals(board.getGameBoard()[2], new String[]{" ", " ", " ", " "});
    }

}
