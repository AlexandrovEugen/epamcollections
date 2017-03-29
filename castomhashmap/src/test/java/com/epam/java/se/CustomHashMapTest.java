package com.epam.java.se;


import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

@FixMethodOrder
public class CustomHashMapTest {

    private CustomHashMap<Integer, String> hashMap;

    @Before
    public void init() {
        hashMap = new CustomHashMap<>();
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
        hashMap.put(1, "first");
        assertTrue(hashMap.containsValue("first"));
        assertEquals("first", hashMap.put(1, "second"));
        assertFalse(hashMap.containsValue("first"));
        assertThat(hashMap.get(1), is(equalTo("second")));
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
        hashMap.put(1, "get");
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
        hashMap.put(3, "pet");
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
        hashMap.put(1, "a");
        hashMap.put(2, "b");
        hashMap.put(3, "c");
        hashMap.put(4, "d");
        hashMap.put(5, "e");
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
        hashMap.put(1, "a");
        hashMap.put(2, "b");
        hashMap.put(3, "c");
        hashMap.put(4, "d");
        hashMap.put(5, "e");
        hashMap.put(6, "f");
        hashMap.put(7, "g");
        assertEquals(7, hashMap.size());
        hashMap.clear();
        assertTrue(hashMap.isEmpty());
    }
}