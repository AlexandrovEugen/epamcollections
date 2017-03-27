package com.epam.java.se;


import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@FixMethodOrder
public class CustomHashMapTest {

    private CustomHashMap<Integer,String> hashMap;

    @Before
    public void init(){
        hashMap = new CustomHashMap<>();
    }

    @Test
    public void testThatThisClassCanInitializeObject(){
        assertThat(hashMap, is(notNullValue()));
    }

    @Test
    public void testThatJustInitializedMapIsEmpty(){
        assertTrue(hashMap.isEmpty());
    }

    @Test
    public void testThatInNewMapContainsKeyReturnsFalseForAnyElement(){
        assertThat(hashMap.containsKey(1), is(false));
    }

    @Test
    public void testThatInNewMapContainsValueReturnFalseForAnyElement(){
        assertThat(hashMap.containsValue("empty"), is(false));
    }

    @Test
    public void testThatPutMethodWorksRight(){
        hashMap.put(1, "first");
        hashMap.containsKey(1);
    }

    @Test(expected = NullPointerException.class)
    public void testThatUnableToPutNullKey(){
        hashMap.put(null, null);
    }

    @Test
    public void testThatPutMethodAllowsToAddNullValue(){
        hashMap.put(1, null);
        assertThat(hashMap.isEmpty(), is(false));
    }

    @Test
    public void testThatMethodGetReturnValueIfiItExists(){
        assertTrue(hashMap.isEmpty());
        hashMap.put(1, "get");
        hashMap.put(2, "set");
        hashMap.put(3, "pet");
        hashMap.put(4, "ret");
        hashMap.put(5, "met");
        assertFalse(hashMap.isEmpty());
        assertThat(hashMap.get(5),is(equalTo("met")));
    }

    @Test
    public void testThatRemoveMethodWorksRight(){
        assertTrue(hashMap.isEmpty());
        hashMap.put(1, "get");
        hashMap.put(2, "set");
        hashMap.put(3, "pet");
        hashMap.put(4, "ret");
        hashMap.put(5, "met");
        assertFalse(hashMap.isEmpty());
        assertTrue(hashMap.containsKey(1));
        assertTrue(hashMap.containsValue("get"));
        hashMap.remove(1);
        assertFalse(hashMap.containsValue("get"));
    }
}