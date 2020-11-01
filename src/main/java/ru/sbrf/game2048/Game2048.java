package ru.sbrf.game2048;/*
 * Copyright 1998-2014 Konstantin Bulenkov http://bulenkov.com/about
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.*;

/**
 * @author Konstantin Bulenkov
 */
public class Game2048 implements Game {

    public static final int GAME_SIZE = 4;
    public final SquareBoard<Integer> board = new SquareBoard<>(GAME_SIZE);

    int myScore = 0;
    Random random = new Random();
    GameHelper helper = new GameHelper();

    public Game2048() {

    }

    public void left() {
        boolean needAddTile = false;
        for (int i = 0; i < 4; i++) {
            Tile[] line = getLine(i);
            Tile[] merged = mergeLine(moveLine(line));
            setLine(i, merged);
            if (!needAddTile && !compare(line, merged)) {
                needAddTile = true;
            }
        }

        if (needAddTile) {
            addTile();
        }
    }

    public void right() {
//        myTiles = rotate(180);
//        left();
//        myTiles = rotate(180);
    }

    public void up() {
//        myTiles = rotate(270);
//        left();
//        myTiles = rotate(90);
    }

    public void down() {
//        myTiles = rotate(90);
//        left();
//        myTiles = rotate(270);
    }

    private void addTile() {
//        List<Tile> list = board.availableSpace();
//        if (!availableSpace().isEmpty()) {
//            int index = (int) (Math.random() * list.size()) % list.size();
//            Tile emptyTime = list.get(index);
//            emptyTime.value = Math.random() < 0.9 ? 2 : 4;
//        }
    }

//    private boolean isFull() {
//        return availableSpace().size() == 0;
//    }

    @Override
    public void init() {
        myScore = 0;
//        myWin = false;
//        myLose = false;
        ArrayList<Integer> list = new ArrayList<>(GAME_SIZE * GAME_SIZE);
        for (int i = 0; i < GAME_SIZE * GAME_SIZE; i++) {
            list.add(null);
        }
        board.fillBoard(list);
        try {
            addItem();
            addItem();
        } catch (GameOverException e) {
            //Не верно инициализировали борд
        }
    }

    private Integer generateRandomValue() {
        return Math.random() < 0.9 ? 2 : 4;
    }

    @Override
    public boolean canMove() {
        return board.availableSpace().isEmpty();
    }

    @Override
    public void move(Direction direction) throws GameOverException {
        if (!canMove()) throw new GameOverException();
        boolean moved = false;
        switch (direction) {
            case LEFT:
                for (int i = 0; i < GAME_SIZE; i++) {
                    moved |= moveLine(board.getColumn(i));
                }
                break;
            case RIGHT: break;
            case UP: break;
            case DOWN: break;
        }
        if (moved) {
            addItem();
        }
    }

    private boolean moveLine(List<Key> oldKeys) {
        List<Integer> oldValues = board.getValues(oldKeys);
        List<Integer> mergedList = helper.moveAndMergeEqual(oldValues);
        if (!oldValues.equals(mergedList)) {
            Iterator<Integer> iter = mergedList.iterator();
            for (Key key : oldKeys) {
                board.setItem(key, iter.next());
            }
            return true;
        }
        return false;
    }

    @Override
    public void addItem() throws GameOverException {
        List<Key> emptyKeys = board.availableSpace();
        if (emptyKeys.isEmpty()) throw new GameOverException();
        board.addItem(emptyKeys.get(random.nextInt(emptyKeys.size())), generateRandomValue());
    }

    @Override
    public int getScore() {
        return myScore;
    }

    @Override
    public SquareBoard<Integer> getGameBoard() {
        return board;
    }

    @Override
    public boolean hasWin() {
        return board.hasValue(2048);
    }

    private boolean compare(Tile[] line1, Tile[] line2) {
        if (line1 == line2) {
            return true;
        } else if (line1.length != line2.length) {
            return false;
        }

        for (int i = 0; i < line1.length; i++) {
            if (line1[i].value != line2[i].value) {
                return false;
            }
        }
        return true;
    }

    private Tile[] rotate(int angle) {
        Tile[] newTiles = new Tile[4 * 4];
        int offsetX = 3, offsetY = 3;
        if (angle == 90) {
            offsetY = 0;
        } else if (angle == 270) {
            offsetX = 0;
        }

        double rad = Math.toRadians(angle);
        int cos = (int) Math.cos(rad);
        int sin = (int) Math.sin(rad);
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                int newX = (x * cos) - (y * sin) + offsetX;
                int newY = (x * sin) + (y * cos) + offsetY;
//                newTiles[(newX) + (newY) * 4] = tileAt(x, y);
            }
        }
        return newTiles;
    }

    private Tile[] moveLine(Tile[] oldLine) {
        LinkedList<Tile> l = new LinkedList<Tile>();
        for (int i = 0; i < 4; i++) {
            if (!oldLine[i].isEmpty())
                l.addLast(oldLine[i]);
        }
        if (l.size() == 0) {
            return oldLine;
        } else {
            Tile[] newLine = new Tile[4];
            ensureSize(l, 4);
            for (int i = 0; i < 4; i++) {
                newLine[i] = l.removeFirst();
            }
            return newLine;
        }
    }

    private Tile[] mergeLine(Tile[] oldLine) {
        LinkedList<Tile> list = new LinkedList<Tile>();
        for (int i = 0; i < 4 && !oldLine[i].isEmpty(); i++) {
            int num = oldLine[i].value;
            if (i < 3 && oldLine[i].value == oldLine[i + 1].value) {
                num *= 2;
                myScore += num;
                int ourTarget = 2048;
                if (num == ourTarget) {
//                    myWin = true;
                }
                i++;
            }
            list.add(new Tile(num));
        }
        if (list.size() == 0) {
            return oldLine;
        } else {
            ensureSize(list, 4);
            return list.toArray(new Tile[4]);
        }
    }

    private static void ensureSize(List<Tile> l, int s) {
        while (l.size() != s) {
            l.add(new Tile());
        }
    }

    private Tile[] getLine(int index) {
        Tile[] result = new Tile[4];
        for (int i = 0; i < 4; i++) {
//            result[i] = tileAt(i, index);
        }
        return result;
    }

    private void setLine(int index, Tile[] re) {
//        System.arraycopy(re, 0, myTiles, index * 4, 4);
    }


    static class Tile {
        int value;

        public Tile() {
            this(0);
        }

        public Tile(int num) {
            value = num;
        }

        public boolean isEmpty() {
            return value == 0;
        }


    }


}
