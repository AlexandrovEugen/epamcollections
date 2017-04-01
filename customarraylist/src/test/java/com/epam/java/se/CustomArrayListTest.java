package com.epam.java.se;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CustomArrayListTest {


    private List<Integer> arrayList;

    @Before
    public void init() {
        arrayList = new CustomArrayList<>();
    }

    @Test
    public void testThatWeCanInitializeObject() {
        assertThat(arrayList, is(notNullValue()));
    }

    @Test
    public void testThatIfListHasJustInitializedIsEmpty() {
        assertTrue(arrayList.isEmpty());
    }

    @Test
    public void testThatIfListEmptyThenContainsReturnFalse() {
        assertTrue(arrayList.isEmpty());
        assertFalse(arrayList.contains(1));
    }

    @Test
    public void testThatAddMethodWorksRight() {
        assertTrue(arrayList.isEmpty());
        arrayList.add(2);
        assertThat(arrayList.size(), is(equalTo(1)));
        assertTrue(arrayList.contains(2));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testThatMethodGetThrowsExceptionFromOutOfBounds() {
        assertTrue(arrayList.isEmpty());
        arrayList.add(2);
        assertThat(arrayList.size(), is(equalTo(1)));
        arrayList.get(1);
    }

    @Test
    public void testThatGetElementByIndexWorksRight() {
        arrayList.add(2);
        assertThat(arrayList.get(0), is(equalTo(2)));
    }

    @Test
    public void testThatListPermitStoringNullValue() {
        arrayList.add(10);
        arrayList.add(11);
        arrayList.add(null);
        arrayList.add(13);
        assertThat(arrayList.size(), is(equalTo(4)));
        assertTrue(arrayList.contains(null));
    }

    @Test
    public void testThatCapacityGoOutOfDefaultCapacity() {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(7);
        arrayList.add(8);
        arrayList.add(9);
        arrayList.add(10);
        arrayList.add(11);
        arrayList.add(12);
        arrayList.add(13);
        arrayList.add(14);
        arrayList.add(15);
        arrayList.add(16);
        assertThat(arrayList.size(), is(equalTo(16)));
    }

    @Test(expected = NoSuchElementException.class)
    public void testThatIndexOfThrowsExceptionIfElementIsntInIt() {
        arrayList.indexOf(1);
    }

    @Test
    public void testThatIndexOfReturnsCorrectIndexForValue() {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(7);
        arrayList.add(8);
        assertThat(arrayList.indexOf(7), is(equalTo(6)));
    }

    @Test
    public void testThatRemovedElementByIndexNonExistsInList() {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        assertThat(arrayList.size(), is(equalTo(5)));
        arrayList.remove(4);
        assertFalse(arrayList.contains(5));
        assertThat(arrayList.size(), is(equalTo(4)));
    }

    @Test
    public void testThatRemovedElementByValueNonExistsInList() {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        assertThat(arrayList.size(), is(equalTo(5)));
        arrayList.remove(new Integer(5));
        assertFalse(arrayList.contains(5));
        assertThat(arrayList.size(), is(equalTo(4)));
    }

    @Test
    public void testThatSublistWorksRight() {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(7);
        arrayList.add(8);
        arrayList.add(9);
        arrayList.add(10);
        List<Integer> integers = arrayList.subList(0, 6);
        for (int i = 0; i < integers.size(); i++) {
            assertTrue(integers.contains(arrayList.get(i)));
        }
    }

    @Test
    public void testThatSetMethodWorksRight() {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        assertThat(arrayList.get(5), is(equalTo(6)));
        arrayList.set(5, 9);
        assertThat(arrayList.get(5), is(equalTo(9)));
    }

    @Test
    public void lastIndexOfTest(){
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(4);
        assertThat(arrayList.lastIndexOf(4), is(equalTo(4)));
    }
}