package ru.sbrf.game2048;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class Game2048Test {

    @Spy
    private GameHelper helper;

    @Mock
    private SquareBoard<Integer> board = new SquareBoard<>(2);

    @InjectMocks
    private Game2048 instance;

    @Captor
    ArgumentCaptor<List<Integer>> captor;

    @Test
    void init() throws NotEnoughSpace {
        doNothing().when(board).fillBoard(captor.capture());
        doReturn(List.of(new Key(1,1))).when(board).availableSpace();
        instance.init();
        verify(board).fillBoard(anyList());
        assertEquals(asList(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null),
                captor.getValue());
    }
}