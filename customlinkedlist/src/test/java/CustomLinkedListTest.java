import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@FixMethodOrder
public class CustomLinkedListTest {

    private List<Integer> linkedList;

    @Before
    public void init() {
        linkedList = new CustomLinkedList<>();
    }

    @Test
    public void testThatWeCanInitializeObject() {
        assertThat(linkedList, is(notNullValue()));
    }

    @Test
    public void testThatIfListHasJustInitializedIsEmpty() {
        assertTrue(linkedList.isEmpty());
    }

    @Test
    public void testThatIfListEmptyThenContainsReturnFalse() {
        assertTrue(linkedList.isEmpty());
        assertFalse(linkedList.contains(1));
    }

    @Test
    public void testThatAddMethodWorksRight() {
        assertTrue(linkedList.isEmpty());
        linkedList.add(2);
        assertThat(linkedList.size(), is(equalTo(1)));
        assertTrue(linkedList.contains(2));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testThatMethodGetThrowsExceptionFromOutOfBounds() {
        assertTrue(linkedList.isEmpty());
        linkedList.add(2);
        assertThat(linkedList.size(), is(equalTo(1)));
        linkedList.get(1);
    }

    @Test
    public void testThatGetElementByIndexWorksRight() {
        linkedList.add(2);
        assertThat(linkedList.get(0), is(equalTo(2)));
    }

    @Test
    public void testThatListPermitStoringNullValue() {
        linkedList.add(10);
        linkedList.add(11);
        linkedList.add(null);
        linkedList.add(13);
        assertThat(linkedList.size(), is(equalTo(4)));
        assertTrue(linkedList.contains(null));
    }

    @Test
    public void testThatCapacityGoOutOfDefaultCapacity() {
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(6);
        linkedList.add(7);
        linkedList.add(8);
        linkedList.add(9);
        linkedList.add(10);
        linkedList.add(11);
        linkedList.add(12);
        linkedList.add(13);
        linkedList.add(14);
        linkedList.add(15);
        linkedList.add(16);
        assertThat(linkedList.size(), is(equalTo(16)));
    }

    @Test(expected = NoSuchElementException.class)
    public void testThatIndexOfThrowsExceptionIfElementIsntInIt() {
        linkedList.indexOf(1);
    }

    @Test
    public void testThatIndexOfReturnsCorrectIndexForValue() {
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(6);
        linkedList.add(7);
        linkedList.add(8);
        assertThat(linkedList.indexOf(7), is(equalTo(6)));
    }

    @Test
    public void testThatRemovedElementByIndexNonExistsInList() {
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        assertThat(linkedList.size(), is(equalTo(5)));
        linkedList.remove(4);
        assertFalse(linkedList.contains(5));
        assertThat(linkedList.size(), is(equalTo(4)));
    }

    @Test
    public void testThatRemovedElementByValueNonExistsInList() {
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        assertThat(linkedList.size(), is(equalTo(5)));
        linkedList.remove(new Integer(5));
        assertFalse(linkedList.contains(5));
        assertThat(linkedList.size(), is(equalTo(4)));
    }

    @Test
    public void testThatSublistWorksRight() {
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(6);
        linkedList.add(7);
        linkedList.add(8);
        linkedList.add(9);
        linkedList.add(10);
        List<Integer> integers = linkedList.subList(0, 6);
        for (int i = 0; i < integers.size(); i++) {
            assertTrue(integers.contains(linkedList.get(i)));
        }
    }

    @Test
    public void testThatSetMethodWorksRight() {
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(6);
        assertThat(linkedList.get(5), is(equalTo(6)));
        linkedList.set(5, 9);
        assertThat(linkedList.get(5), is(equalTo(9)));
    }

    @Test
    public void lastIndexOfTest() {
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(4);
        assertThat(linkedList.lastIndexOf(4), is(equalTo(4)));
    }

    @Test
    public void toArrayTest() {
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(6);
        Object[] objects = linkedList.toArray();
        assertThat(objects.length, is(equalTo(linkedList.size())));
        for (int i = 0; i < objects.length; i++) {
            assertEquals(linkedList.get(i), objects[i]);
        }
    }

    @Test
    public void addAllTest() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        linkedList.addAll(list);
        assertThat(list.size(), is(equalTo(linkedList.size())));
        for (Integer e : list) {
            assertTrue(linkedList.contains(e));
        }
    }

    @Test
    public void deleteAll() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(6);
        linkedList.add(7);
        assertThat(linkedList.size(), is(equalTo(7)));
        linkedList.removeAll(list);
        assertThat(linkedList.size(), is(equalTo(1)));
        for (Integer e : list) {
            assertFalse(linkedList.contains(e));
        }
        assertTrue(linkedList.contains(7));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAllWithIndexThrowsExceptionIfIndexIsOutOfSize() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        linkedList.addAll(2, list);
    }

    @Test
    public void addAllByIndexTest() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);

        assertTrue(linkedList.isEmpty());
        linkedList.add(8);
        linkedList.add(9);
        linkedList.addAll(0, list);
        assertThat(linkedList.size(), is(equalTo(13)));
    }

    @Test
    public void testThatRetainsMethodWorksRight() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);


        linkedList.add(1);
        linkedList.add(10);
        linkedList.add(8);
        linkedList.add(7);
        linkedList.add(6);
        linkedList.add(9);
        linkedList.retainAll(list);
        assertTrue(linkedList.contains(1));
        assertTrue(linkedList.contains(6));
        assertFalse(linkedList.contains(10));
    }

    @Test
    public void testIfAllElementsFromOtherListAraContainsInThisArrayMethodContainsReturnTrue(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(6);
        linkedList.add(7);
        assertTrue(linkedList.containsAll(list));
    }
}