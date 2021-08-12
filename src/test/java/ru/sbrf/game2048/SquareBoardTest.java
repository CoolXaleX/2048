package ru.sbrf.game2048;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SquareBoardTest {

    private Board<Key, Integer> instance = new SquareBoard<>(2);

    @Test
    void fillBoard() {
        assertDoesNotThrow( () -> instance.fillBoard(List.of(1, 2, 3, 4)));
    }

    @Test
    void fillBoardWithException() {
        assertThrows(RuntimeException.class, () -> instance.fillBoard(List.of(1, 2, 3)));
    }

    @Test
    void availableSpace() {
    }

    @Test
    void addItem() {
    }

    @Test
    void getKey() {
    }

    @Test
    void getValue() {
    }

    @Test
    void getColumn() {
    }

    @Test
    void getRow() {
    }

    @Test
    void hasValue() {
    }

    @Test
    void getValues() {
    }
}