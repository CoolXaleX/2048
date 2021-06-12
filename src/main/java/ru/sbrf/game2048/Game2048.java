package ru.sbrf.game2048;

import java.util.*;

public class Game2048 implements Game {

    public static final int GAME_SIZE = 4;
    private final Board<Key, Integer> board = new SquareBoard<>(GAME_SIZE);

    private final Random random = new Random();
    private final GameHelper helper = new GameHelper();

    @Override
    public void init() {
        ArrayList<Integer> list = new ArrayList<>(GAME_SIZE * GAME_SIZE);
        for (int i = 0; i < GAME_SIZE * GAME_SIZE; i++) {
            list.add(null);
        }
        board.fillBoard(list);
        try {
            addItem();
            addItem();
        } catch (NotEnoughSpace e) {
            //Не верно инициализировали борд
        }
    }

    private Integer generateRandomValue() {
        return Math.random() < 0.9 ? 2 : 4;
    }

    @Override
    public boolean canMove() {
        return !board.availableSpace().isEmpty();
    }

    @Override
    public boolean move(Direction direction) {
        boolean moved = false;
        switch (direction) {
            case LEFT:
                for (int i = 0; i < GAME_SIZE; i++) {
                    moved |= moveLine(board.getRow(i));
                }
                break;
            case RIGHT:
                for (int i = 0; i < GAME_SIZE; i++) {
                    List<Key> list = board.getRow(i);
                    Collections.reverse(list);
                    moved |= moveLine(list);
                }
                break;
            case UP:
                for (int j = 0; j < GAME_SIZE; j++) {
                    moved |= moveLine(board.getColumn(j));
                }
                break;
            case DOWN:
                for (int j = 0; j < GAME_SIZE; j++) {
                    List<Key> list = board.getColumn(j);
                    Collections.reverse(list);
                    moved |= moveLine(list);
                }
                break;
        }
        if (moved) {
            try {
                addItem();
            } catch (NotEnoughSpace notEnoughSpace) {
                return false;
            }
        }
        return true;
    }

    private boolean moveLine(List<Key> oldKeys) {
        List<Integer> oldValues = board.getValues(oldKeys);
        List<Integer> mergedList = helper.moveAndMergeEqual(oldValues);
        if (!oldValues.equals(mergedList)) {
            Iterator<Integer> iter = mergedList.iterator();
            for (Key key : oldKeys) {
                board.addItem(key, iter.next());
            }
            return true;
        }
        return false;
    }

    @Override
    public void addItem() throws NotEnoughSpace {
        List<Key> emptyKeys = board.availableSpace();
        if (emptyKeys.isEmpty()) throw new NotEnoughSpace();
        board.addItem(emptyKeys.get(random.nextInt(emptyKeys.size())), generateRandomValue());
    }

    @Override
    public Board getGameBoard() {
        return board;
    }

    @Override
    public boolean hasWin() {
        return board.hasValue(64);
    }

}
