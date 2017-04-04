import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;


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
}