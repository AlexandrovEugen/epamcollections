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

    private CustomTreeMap<Integer, String> hashMap;

    @Before
    public void init() {
        hashMap = new CustomTreeMap<>();
    }

    @Test
    public void testThatThisClassCanInitializeObject() {
        assertThat(hashMap, is(notNullValue()));
    }

    @Test
    public void testThatJustInitializedMapIsEmpty() {
        assertTrue(hashMap.isEmpty());
    }

    @Test
    public void testThatInNewMapContainsKeyReturnsFalseForAnyElement() {
        assertThat(hashMap.containsKey(1), is(false));
    }

    @Test
    public void testThatInNewMapContainsValueReturnFalseForAnyElement() {
        assertThat(hashMap.containsValue("empty"), is(false));
    }

    @Test
    public void testThatPutMethodWorksRight() {
        hashMap.put(1, "first");
        hashMap.containsKey(1);
    }


    @Test
    public void testThatIsKeyExistThatMethodPutAddNewValueAndGetOld() {
        hashMap.put(1, "first1");
        assertTrue(hashMap.containsValue("first1"));
        assertEquals("first1", hashMap.put(1, "second1"));
        assertFalse(hashMap.containsValue("first1"));
        assertThat(hashMap.get(1), is(equalTo("second1")));
    }

    @Test(expected = NullPointerException.class)
    public void testThatUnableToPutNullKey() {
        hashMap.put(null, null);
    }

    @Test
    public void testThatPutMethodAllowsToAddNullValue() {
        hashMap.put(1, null);
        assertThat(hashMap.isEmpty(), is(false));
    }

    @Test
    public void testThatMethodGetReturnValueIfiItExists() {
        assertTrue(hashMap.isEmpty());
        hashMap.put(1, "qet");
        hashMap.put(2, "set");
        hashMap.put(3, "pet");
        hashMap.put(4, "ret");
        hashMap.put(5, "met");
        assertFalse(hashMap.isEmpty());
        assertThat(hashMap.get(5), is(equalTo("met")));
    }

    @Test
    public void testThatRemoveMethodWorksRight() {
        assertTrue(hashMap.isEmpty());
        hashMap.put(1, "get");
        hashMap.put(2, "set");
        hashMap.put(3, "qet");
        hashMap.put(4, "ret");
        hashMap.put(5, "met");
        assertFalse(hashMap.isEmpty());
        assertEquals(5, hashMap.size());
        assertTrue(hashMap.containsKey(1));
        assertTrue(hashMap.containsValue("get"));
        hashMap.remove(1);
        assertFalse(hashMap.containsValue("get"));
        assertEquals(4, hashMap.size());
    }

    @Test
    public void testThatSizeCanWentToOutOfDefaultBucketsLength() {
        hashMap.put(1, "1");
        hashMap.put(2, "2");
        hashMap.put(3, "3");
        hashMap.put(4, "4");
        hashMap.put(5, "5");
        hashMap.put(6, "f");
        hashMap.put(7, "g");
        hashMap.put(8, "h");
        hashMap.put(9, "i");
        hashMap.put(10, "j");
        hashMap.put(11, "k");
        hashMap.put(12, "l");
        hashMap.put(13, "m");
        hashMap.put(14, "n");
        hashMap.put(15, "o");
        hashMap.put(16, "p");
        hashMap.put(17, "q");
        hashMap.put(18, "r");
        hashMap.put(19, "s");
        hashMap.put(20, "t");
        hashMap.put(21, "u");
        assertEquals(21, hashMap.size());
    }

    @Test
    public void testThatAfterClearMethodMapWillBeEmpty(){
        hashMap.put(1, "1");
        hashMap.put(2, "2");
        hashMap.put(3, "3");
        hashMap.put(4, "4");
        hashMap.put(5, "5");
        hashMap.put(6, "6");
        hashMap.put(7, "7");
        assertEquals(7, hashMap.size());
        hashMap.clear();
        assertTrue(hashMap.isEmpty());
    }

    @Test
    public void testThatEntrySetContainsAllKeyValuePairs(){
        hashMap.put(1, "1");
        hashMap.put(2, "2");
        hashMap.put(3, "3");
        hashMap.put(4, "4");
        hashMap.put(5, "5");
        hashMap.put(6, "6");
        hashMap.put(7, "7");
        hashMap.put(8, "8");
        hashMap.put(9, "9");

        Set<Map.Entry<Integer, String>> entrySet = hashMap.entrySet();
        for (Map.Entry<Integer, String> entry: entrySet){
            assertTrue(hashMap.containsKey(entry.getKey()));
            assertTrue(hashMap.containsValue(entry.getValue()));
        }
    }

    @Test
    public void testThatKeySetContainsAllKeyFromMap(){
        hashMap.put(1, "1");
        hashMap.put(2, "2");
        hashMap.put(3, "3");
        hashMap.put(4, "4");
        hashMap.put(5, "5");
        hashMap.put(6, "6");
        hashMap.put(7, "7");
        hashMap.put(8, "8");
        hashMap.put(9, "9");

        Set<Integer> keySet = hashMap.keySet();
        for (Integer key: keySet) {
            assertTrue(hashMap.containsKey(key));
        }
    }

    @Test
    public void testThatAllEntriesFromAnotherMapAreContainedInThis(){
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
        assertTrue(hashMap.isEmpty());
        hashMap.putAll(anotherMap);
        assertThat(hashMap.size(), is(equalTo(anotherMap.size())));
        for (Map.Entry<Integer, String> entry : anotherMap.entrySet()) {
            assertTrue(hashMap.containsKey(entry.getKey()));
            assertTrue(hashMap.containsValue(entry.getValue()));
        }
    }



    @Test
    public void testThatValuesMethodContainsAllValuesFromMap(){
        hashMap.put(1, "1");
        hashMap.put(2, "2");
        hashMap.put(3, "3");
        hashMap.put(4, "4");
        hashMap.put(5, "5");
        hashMap.put(6, "6");
        hashMap.put(7, "7");
        hashMap.put(8, "8");
        hashMap.put(9, "9");

        Collection<String> values = hashMap.values();
        assertThat(values.size(), is(equalTo(hashMap.size())));

        for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
            assertTrue(values.contains(entry.getValue()));
        }
    }
}
