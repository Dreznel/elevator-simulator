package utility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderedSetQueueTest {

    private OrderedSetQueue fixture;

    @BeforeEach
    void setup() {
        fixture = new OrderedSetQueue();
        fixture.insert(0);
        fixture.insert(1);
        fixture.insert(2);
        fixture.insert(3);
        fixture.insert(4);
        fixture.insert(5);
        fixture.insert(6);
        fixture.insert(7);
    }

    /*
    @Test
    void insert() {
    }

    @Test
    void contains() {
    }
    */

    @Test
    void popHighest() {
        fixture.setFrontOfQueueToHighest();
        int initialSize = fixture.getSetForStreamApi().size();

        int expected = 7;
        int actual = fixture.pop();
        assertEquals(expected, actual);
        assertEquals(initialSize-1, fixture.getSetForStreamApi().size());
    }

    @Test
    void popLowest() {
        fixture.setFrontOfQueueToLowest();
        int initialSize = fixture.getSetForStreamApi().size();

        int expected = 0;
        int actual = fixture.pop();
        assertEquals(expected, actual);
        assertEquals(initialSize-1, fixture.getSetForStreamApi().size());
    }

    @Test
    void peekHighest() {
        fixture.setFrontOfQueueToHighest();
        int initialSize = fixture.getSetForStreamApi().size();

        int expected = 7;
        int actual = fixture.peek();
        assertEquals(expected, actual);
        assertEquals(initialSize, fixture.getSetForStreamApi().size());
    }

    @Test
    void peekLowest() {
        fixture.setFrontOfQueueToLowest();
        int initialSize = fixture.getSetForStreamApi().size();

        int expected = 0;
        int actual = fixture.peek();
        assertEquals(expected, actual);
        assertEquals(initialSize, fixture.getSetForStreamApi().size());
    }

    @Test
    void peekEmpty() {
        fixture = new OrderedSetQueue();
        int expected = -1;
        int actual = fixture.peek();
        assertEquals(expected, actual);
    }

    @Test
    void hasNext() {
        assertTrue(fixture.hasNext());
    }

    @Test
    void doesNotHaveNext() {
        fixture = new OrderedSetQueue();
        assertFalse(fixture.hasNext());
    }
}