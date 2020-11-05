package ru.sbrf.game2048;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract public class Board {
    protected int weigh;
    protected int height;
    protected Map<Key, Integer> board = new HashMap<>();

    public Board(int weigh, int height) {
        this.weigh = weigh;
        this.height = height;
    }

    /** Заполняем поле элементами из входного списка.
     * Если размер не совпадает с размером игрового поля, выбрасывается RuntimeException.
    * Если нужно задать пустой элемент, указываем null.
    */
    public abstract void fillBoard(List<Integer> list);

    /** Возвращаем все ключи, у которых значение null. */
    public abstract List<Key> availableSpace();

    /** Добавляем элемент {@param value} по ключу {@param key}. */
    public abstract void addItem(Key key, Integer value);

    /** Ищем уже существующий ключ по координатам {@param i} {@param j}. */
    public abstract Key getKey(int i, int j);

    /** Получаем значение по {@param key} */
    public abstract Integer getValue(Key key);

    /** Получаем столбец ключей, по которым потом можно будет выбрать значения. */
    public abstract List<Key> getColumn(int j);

    /** Получаем строку ключей, по которым потом можно будет выбрать значения. */
    public abstract List<Key> getRow(int i);

    /** Проверяем, есть ли такое значение на поле. */
    public abstract boolean hasValue(Integer value);

    /** Получаем строку значений по строке ключей. */
    public abstract List<Integer> getValues(List<Key> keys);
}
