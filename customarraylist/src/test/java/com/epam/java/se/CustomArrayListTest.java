package com.epam.java.se;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CustomArrayListTest {


    private List<Integer> arrayList;

    @Before
    public void init(){
        arrayList = new CustomArrayList<>();
    }

    @Test
    public void testThatWeCanInitializeObject(){
        assertThat(arrayList, is(notNullValue()));
    }

    @Test
    public void testThatIfListHasJustInitializedIsEmpty(){
        assertTrue(arrayList.isEmpty());
    }

    @Test
    public void testThatIfListEmptyThenContainsReturnFalse(){
        assertTrue(arrayList.isEmpty());
        assertFalse(arrayList.contains(1));
    }
}