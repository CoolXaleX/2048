package ru.sbrf.game2048;

import java.util.List;

import static java.util.Arrays.asList;

public class Game2048Test {
    private final static Game game = new Game2048();

    public static void main(String[] args) {
        Board<Key, Integer> b = game.getGameBoard();
        if (!b.availableSpace().isEmpty()) throw new RuntimeException("Game board must be empty before initialize");
        b.fillBoard(asList(2,null,null,8, 2,2,8,8, 2,null,2,2, 4,2,4,2048));
        if (!game.hasWin()) throw new RuntimeException("hasWin not work =(");
        game.move(Direction.LEFT);
        if (b.availableSpace().size() != 5) throw new RuntimeException("move must be add item");
        assertLists(b.getValues(b.getRow(0)).subList(0,2), asList(2, 8));
        assertLists(b.getValues(b.getRow(1)).subList(0,2), asList(4, 16));
        assertLists(b.getValues(b.getRow(2)).subList(0,2), asList(4, 2));
        assertLists(b.getValues(b.getRow(3)), asList(4, 2, 4, 2048));
        game.move(Direction.DOWN);
        assertLists(b.getValues(b.getColumn(0)).subList(1,4), asList(2, 4, 8));
        assertLists(b.getValues(b.getColumn(1)).subList(1,4), asList(8, 16, 4));

        game.init();
        if (b.availableSpace().size() != 14) throw new RuntimeException("init must be add 2 item");
        if (!game.canMove()) throw new RuntimeException("canMove not work =(");
        game.addItem();
        if (b.availableSpace().size() != 13) throw new RuntimeException("addItem must be add 1 item");

    }

    private static void assertLists(List l1, List l2) {
        if (!l2.equals(l1)) throw new RuntimeException("l1: " + l1 + " not equals l2: " + l2);
    }


}
