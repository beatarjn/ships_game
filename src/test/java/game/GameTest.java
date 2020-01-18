package game;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Random;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    @Mock
    Renderer renderer;
    @Mock
    Random randomGenerator;
    @Mock
    I0Device io;

    @Test
    public void testStartGame() {

        //given
        Board board = new Board(4,3,12);
        Game game = new Game(board, renderer, randomGenerator, io);

        String mockRender = "foo";
        when(renderer.renderGameBoard(board)).thenReturn(mockRender);

        //when
        game.startGame();

        //then
        verify(renderer, times(1)).renderGameBoard(board);
        verify(io, times(1)).output(mockRender);

        verifyNoMoreInteractions(renderer, io);

    }

}
