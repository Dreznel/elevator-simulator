package Utility;

import java.util.Queue;
import java.util.TreeSet;

public class OrderedSetQueue {
    private TreeSet<Integer> set;
    private boolean frontOfQueueIsHighestValue; //Determines whether the front of the queue is the highest number or the lowest.

    public OrderedSetQueue() {
        set = new TreeSet<Integer>();
    }

    public void insert(Integer i) {
        set.add(i);
    }

    public boolean contains(Integer i) {
        return set.contains(i);
    }

    public Integer pop() {
        if(set.isEmpty()) {
            return null;
        }

        Integer returnValue;
        returnValue = frontOfQueueIsHighestValue ? set.first() : set.last();
        set.remove(returnValue);
        return returnValue;
    }

    public Integer peek() {
        if(set.isEmpty()) {
            return null;
        }

        Integer returnValue;
        returnValue = frontOfQueueIsHighestValue ? set.first() : set.last();
        return returnValue;
    }

    public boolean hasNext() {
        return peek() != null;
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
