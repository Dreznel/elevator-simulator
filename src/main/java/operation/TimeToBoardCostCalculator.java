package operation;

import elevator.Direction;
import elevator.Elevator;


//Default calculator which tries to get people boarded as quickly as possible.
public class TimeToBoardCostCalculator implements CostCalculator {
    @Override
    public Cost getCostToStopAtFloor(Elevator e, int callingFloor, Direction direction) {
        boolean isPossible = false;
        int score = 100;

        if(e.isIdle()) {
            isPossible = true;
        }

        //TODO: Handle "stopped" case, which is currently impossible.
        if(e.getDirection() == direction) {
            switch(direction) {
                case UP:
                    if (e.getCurrentFloor() <= callingFloor) {
                        isPossible = true;
                    }
                    break;
                case DOWN:
                    if (e.getCurrentFloor() >= callingFloor) {
                        isPossible = true;
                    }
                    break;
            }
        }

        if(isPossible) {
            score = calculateScore(e, callingFloor);
        }

        return new CostImpl(score, isPossible);
    }

    private int calculateScore(Elevator e, int callingFloor) {
        if(e.stoppingAt(callingFloor)) {
            return 0;
        }

        return e.getStopsBeforeFloor(callingFloor) + (Math.abs(e.getCurrentFloor() - callingFloor));
    }
}
