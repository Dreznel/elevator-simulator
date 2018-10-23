package utility;

import java.util.TreeSet;

public class OrderedSetQueue {
    private TreeSet<Integer> set;
    private boolean frontOfQueueIsHighestValue; //Determines whether the front of the queue is the highest number or the lowest.

    public OrderedSetQueue() {
        set = new TreeSet<Integer>();
        frontOfQueueIsHighestValue = false;
    }

    public void insert(Integer i) {
        set.add(i);
    }

    public boolean contains(Integer i) {
        return set.contains(i);
    }

    public Integer pop() {
        if(set.isEmpty()) {
            return -1;
        }

        Integer returnValue;
        returnValue = frontOfQueueIsHighestValue ? set.last() : set.first();
        set.remove(returnValue);
        return returnValue;
    }

    public Integer peek() {
        if(set.isEmpty()) {
            return -1;
        }

        Integer returnValue;
        returnValue = frontOfQueueIsHighestValue ? set.last() : set.first();
        return returnValue;
    }

    public boolean hasNext() {
        return peek() != -1;
    }

    //Purely adding because I want to use this to practice stream api. This is not good design.
    public TreeSet<Integer> getSetForStreamApi() {
        return set;
    }

    public void setFrontOfQueueToHighest() {
        this.frontOfQueueIsHighestValue = true;
    }

    public void setFrontOfQueueToLowest() {
        this.frontOfQueueIsHighestValue = false;
    }

}
