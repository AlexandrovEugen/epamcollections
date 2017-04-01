package com.epam.java.se;


import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;


public class CustomTreeMapTest {

    private CustomTreeMap<Integer, String> treeMap;

    @Before
    public void init() {
        treeMap = new CustomTreeMap<>();
    }

    @Test
    public void testThatThisClassCanInitializeObject() {
        assertThat(treeMap, is(notNullValue()));
    }

    @Test
    public void testThatJustInitializedMapIsEmpty() {
        assertTrue(treeMap.isEmpty());
    }

    @Test
    public void testThatInNewMapContainsKeyReturnsFalseForAnyElement() {
        assertThat(treeMap.containsKey(1), is(false));
    }

    @Test
    public void testThatInNewMapContainsValueReturnFalseForAnyElement() {
        assertThat(treeMap.containsValue("empty"), is(false));
    }

    @Test
    public void testThatPutMethodWorksRight() {
        treeMap.put(1, "first");
        assertTrue(treeMap.containsKey(1));
    }

    @Test
    public void testThatIsKeyExistThatMethodPutAddNewValueAndGetOld() {
        treeMap.put(1, "first1");
        assertTrue(treeMap.containsValue("first1"));
        assertEquals("first1", treeMap.put(1, "second1"));
        assertFalse(treeMap.containsValue("first1"));
        assertThat(treeMap.get(1), is(equalTo("second1")));
    }


    @Test(expected = NullPointerException.class)
    public void testThatUnableToPutNullKey() {
        treeMap.put(null, null);
    }

    @Test
    public void testThatPutMethodAllowsToAddNullValue() {
        treeMap.put(1, null);
        assertThat(treeMap.isEmpty(), is(false));
    }

    @Test
    public void testThatMethodGetReturnValueIfiItExists() {
        assertTrue(treeMap.isEmpty());
        treeMap.put(1, "qet");
        treeMap.put(2, "set");
        treeMap.put(3, "pet");
        treeMap.put(4, "ret");
        treeMap.put(5, "met");
        assertFalse(treeMap.isEmpty());
        assertThat(treeMap.get(5), is(equalTo("met")));
    }

    @Test
    public void testThatRemoveMethodWorksRight() {
        assertTrue(treeMap.isEmpty());
        treeMap.put(1, "get");
        treeMap.put(2, "set");
        treeMap.put(3, "qet");
        treeMap.put(4, "ret");
        treeMap.put(5, "met");
        assertFalse(treeMap.isEmpty());
        assertEquals(5, treeMap.size());
        assertTrue(treeMap.containsKey(1));
        assertTrue(treeMap.containsValue("get"));
        treeMap.remove(1);
        assertFalse(treeMap.containsValue("get"));
        assertEquals(4, treeMap.size());
    }

    @Test
    public void testThatSizeCanWentToOutOfDefaultBucketsLength() {
        treeMap.put(1, "1");
        treeMap.put(2, "2");
        treeMap.put(3, "3");
        treeMap.put(4, "4");
        treeMap.put(5, "5");
        treeMap.put(6, "f");
        treeMap.put(7, "g");
        treeMap.put(8, "h");
        treeMap.put(9, "i");
        treeMap.put(10, "j");
        treeMap.put(11, "k");
        treeMap.put(12, "l");
        treeMap.put(13, "m");
        treeMap.put(14, "n");
        treeMap.put(15, "o");
        treeMap.put(16, "p");
        treeMap.put(17, "q");
        treeMap.put(18, "r");
        treeMap.put(19, "s");
        treeMap.put(20, "t");
        treeMap.put(21, "u");
        assertEquals(21, treeMap.size());
    }

    @Test
    public void testThatAfterClearMethodMapWillBeEmpty() {
        treeMap.put(1, "1");
        treeMap.put(2, "2");
        treeMap.put(3, "3");
        treeMap.put(4, "4");
        treeMap.put(5, "5");
        treeMap.put(6, "6");
        treeMap.put(7, "7");
        assertEquals(7, treeMap.size());
        treeMap.clear();
        assertTrue(treeMap.isEmpty());
    }

    @Test
    public void testThatEntrySetContainsAllKeyValuePairs() {
        treeMap.put(1, "1");
        treeMap.put(2, "2");
        treeMap.put(3, "3");
        treeMap.put(4, "4");
        treeMap.put(5, "5");
        treeMap.put(6, "6");
        treeMap.put(7, "7");
        treeMap.put(8, "8");
        treeMap.put(9, "9");

        Set<Map.Entry<Integer, String>> entrySet = treeMap.entrySet();
        for (Map.Entry<Integer, String> entry : entrySet) {
            assertTrue(treeMap.containsKey(entry.getKey()));
            assertTrue(treeMap.containsValue(entry.getValue()));
        }
    }

    @Test
    public void testThatKeySetContainsAllKeyFromMap() {
        treeMap.put(1, "1");
        treeMap.put(2, "2");
        treeMap.put(3, "3");
        treeMap.put(4, "4");
        treeMap.put(5, "5");
        treeMap.put(6, "6");
        treeMap.put(7, "7");
        treeMap.put(8, "8");
        treeMap.put(9, "9");

        Set<Integer> keySet = treeMap.keySet();
        for (Integer key : keySet) {
            assertTrue(treeMap.containsKey(key));
        }
    }

    @Test
    public void testThatAllEntriesFromAnotherMapAreContainedInThis() {
        Map<Integer, String> anotherMap = new CustomTreeMap<>();
        anotherMap.put(1, "a");
        anotherMap.put(2, "b");
        anotherMap.put(3, "c");
        anotherMap.put(4, "d");
        anotherMap.put(5, "e");
        anotherMap.put(6, "f");
        anotherMap.put(7, "g");
        anotherMap.put(8, "h");
        anotherMap.put(9, "i");
        assertTrue(treeMap.isEmpty());
        treeMap.putAll(anotherMap);
        assertThat(treeMap.size(), is(equalTo(anotherMap.size())));
        for (Map.Entry<Integer, String> entry : anotherMap.entrySet()) {
            assertTrue(treeMap.containsKey(entry.getKey()));
            assertTrue(treeMap.containsValue(entry.getValue()));
        }
    }


    @Test
    public void testThatValuesMethodContainsAllValuesFromMap() {
        treeMap.put(1, "1");
        treeMap.put(2, "2");
        treeMap.put(3, "3");
        treeMap.put(4, "4");
        treeMap.put(5, "5");
        treeMap.put(6, "6");
        treeMap.put(7, "7");
        treeMap.put(8, "8");
        treeMap.put(9, "9");

        Collection<String> values = treeMap.values();
        assertThat(values.size(), is(equalTo(treeMap.size())));

        for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
            assertTrue(values.contains(entry.getValue()));
        }
    }
}
