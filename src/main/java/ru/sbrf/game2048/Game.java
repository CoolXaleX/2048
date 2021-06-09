package ru.sbrf.game2048;

public interface Game {
    /** Метод иницирует начало игры. */
    void init();

    /** Метод проверяет, можем ли мы делать игровой ход. */
    boolean canMove();

    /** Метод делает игровой ход в направлении {@param direction} и возвращает, совершился ход или нет.*/
    boolean move(Direction direction);

    /** Добавляет новый элемент в игру. */
    void addItem() throws NotEnoughtSpace;

    /** Получение игрового поля. */
    Board getGameBoard();

    /** случилось ли событие победы в игре. */
    boolean hasWin();
}
