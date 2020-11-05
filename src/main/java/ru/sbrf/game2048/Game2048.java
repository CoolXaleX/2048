package ru.sbrf.game2048;

import java.util.*;

public class Game2048 implements Game {

    private final Board board;

    private final Random random = new Random();
    private final GameHelper helper = new GameHelper();

    public Game2048(Board board) {
        this.board = board;
    }

    @Override
    public void init() {

    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public void move(Direction direction) {

    }

    @Override
    public void addItem() {

    }

    @Override
    public Board getGameBoard() {
        return board;
    }

    @Override
    public boolean hasWin() {
        return false;
    }

}
