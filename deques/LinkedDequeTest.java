package deques;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** Performs some basic linked deque tests. */
public class LinkedDequeTest {

    /** Adds a few strings to the deque, checking isEmpty() and size() are correct. */
    @Test
    public void addIsEmptySizeTest() {
        LinkedDeque<String> lld = new LinkedDeque<>();
        assertTrue(lld.isEmpty());

        lld.addFirst("front");
        assertEquals(1, lld.size());
        assertFalse(lld.isEmpty());

        lld.addLast("middle");
        assertEquals(2, lld.size());

        lld.addLast("back");
        assertEquals(3, lld.size());
    }

    /** Adds an item, then removes an item, and ensures that the deque is empty afterwards. */
    @Test
    public void addRemoveTest() {
        LinkedDeque<Integer> lld = new LinkedDeque<>();
        assertTrue(lld.isEmpty());

        lld.addFirst(10);
        assertFalse(lld.isEmpty());

        lld.removeFirst();
        assertTrue(lld.isEmpty());
    }

    @Test
    public void addFirstAndLastTest() {
        LinkedDeque<String> lld = new LinkedDeque<>();

        lld.addFirst("1");
        assertEquals("1", lld.get(0));
        assertEquals(1, lld.size());


        lld.addLast("2");
        assertEquals("2", lld.get(1));
        assertEquals(2, lld.size());
        lld.addLast("3");
        lld.addLast("4");

        assertEquals("1", lld.removeFirst());
        assertEquals("4", lld.removeLast());
        assertEquals("2", lld.removeFirst());
        assertEquals("3", lld.removeLast());
    }
}
