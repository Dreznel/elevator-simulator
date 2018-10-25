package elevator;

import contracts.StopQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorStopQueueTest {

    StopQueue fixture = new ElevatorStopQueue();

    private void setupAscending() {
        int[] stops = { 1, 5, 10, 15 };
        fixture = new ElevatorStopQueue(stops);
    }

    private void setupDescending() {
        int[] stops = { 20, 15, 10, 5 };
        fixture = new ElevatorStopQueue(stops);
    }


    @Test
    void peek_regularCase() {
        setupAscending();
        int expected = 1;
        int actual = fixture.peek();
        assertEquals(expected, actual);
    }

    @Test
    void peek_noNextStop() {
        setupAscending();
        int expected = -1;
        int actual = fixture.peek();
        assertEquals(expected, actual);
    }

    @Test
    void addStop_addAtEndAscending() {
        setupAscending();
        int stopToAdd = 20;
        assertTrue(fixture.getStops().indexOf(stopToAdd) == -1, "Invalid test: stop existed before adding.");

        int sizeBefore = fixture.getStops().size();
        fixture.addStop(stopToAdd);
        int sizeAfter = fixture.getStops().size();

        assertEquals(sizeBefore + 1, sizeAfter);

        int expectedIndex = sizeBefore + 1;
        int actualIndex = fixture.getStops().indexOf(stopToAdd);
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    void addStop_addAtEndDescending() {
        setupDescending();
        int stopToAdd = 1;
        assertTrue(fixture.getStops().indexOf(stopToAdd) == -1, "Invalid test: stop existed before adding.");

        int sizeBefore = fixture.getStops().size();
        fixture.addStop(stopToAdd);
        int sizeAfter = fixture.getStops().size();

        assertEquals(sizeBefore + 1, sizeAfter);

        int expectedIndex = sizeBefore + 1;
        int actualIndex = fixture.getStops().indexOf(stopToAdd);
        assertEquals(expectedIndex, actualIndex);
    }


}