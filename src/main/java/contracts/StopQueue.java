package contracts;

import java.util.List;


public interface StopQueue {
    public int peek();
    public void addStop(int newStop);
    public List<Integer> getStops();
}
