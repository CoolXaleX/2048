package ru.sbrf.game2048;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract public class Board<K, V> {
    protected int weigh;
    protected int height;
    protected Map<K, V> board = new HashMap<K, V>();

    public Board(int weigh, int height) {
        this.weigh = weigh;
        this.height = height;
    }

    /* Заполняем поле элементами. Если размер не совпадает с размером игрового поля, выбрасывается RuntimeException.
    * Если нужно задать пустой элемент, указываем null.
    */
    public abstract void fillBoard(List<V> list);

    /* Ищем все ключи, у которых значение полей null. */
    public abstract List<K> availableSpace();

    public abstract void addItem(K key, V value);

    public abstract K getKey(int i, int j);

    public abstract V getValue(Key key);

    public abstract void setItem(Key key, V value);

    public abstract List<K> getColumn(int i);

    public abstract List<K> getRow(int j);

    public abstract boolean hasValue(V value);

    public abstract List<V> getValues(List<K> keys);
}
