package game;


import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class RendererTest {

    @Test
    public void testRenderer() {

        //given
        Renderer renderer = new Renderer();
        Board board = new Board(4, 5, 6);


        //when
        String renderBoard = renderer.renderGameBoard(board);

        //then
        String expectedRender = "  0123\n" +
                                "0|    |0\n" +
                                "1|    |1\n" +
                                "2|    |2\n" +
                                "3|    |3\n" +
                                "4|    |4\n" +
                                "  0123\n";
        Assert.assertEquals(expectedRender, renderBoard);

    }
}
