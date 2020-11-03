package ru.sbrf.game2048;

import org.testng.annotations.Test;

import static java.util.Arrays.asList;
import static org.testng.Assert.*;

public class GameHelperTest {

    private GameHelper helper = new GameHelper();

    @Test
    public void testMoveAndMergeEqual() {
        assertEquals(helper.moveAndMergeEqual(asList(1,2,null,3)), asList(1,2,3,null));
        assertEquals(helper.moveAndMergeEqual(asList(2,2,null,3)), asList(4,3,null,null));
        assertEquals(helper.moveAndMergeEqual(asList(2,2,4,4)), asList(4,8,null,null));
        assertEquals(helper.moveAndMergeEqual(asList(2,2,2,3)), asList(4,2,3,null));
        assertEquals(helper.moveAndMergeEqual(asList(2,null,null,2)), asList(4,null,null,null));
        assertEquals(helper.moveAndMergeEqual(asList(null,null,null,null)), asList(null,null,null,null));
        assertEquals(helper.moveAndMergeEqual(asList(null,null,null,2)), asList(2,null,null,null));
        assertEquals(helper.moveAndMergeEqual(asList(null,null,2,2)), asList(4,null,null,null));
    }
}