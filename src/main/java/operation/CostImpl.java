package operation;

public class CostImpl implements Cost {

    private int score;
    private boolean isPossible;

    public CostImpl(int score, boolean isPossible) {
        this.score = score;
        this.isPossible = isPossible;
    }

    @Override
    public boolean isPossible() {
        return isPossible;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(Cost c) {


        if(isPossible && c.isPossible()) {
            if (this.score < c.getScore()) {
                return -1;
            } else if (this.score > c.getScore()) {
                return 1;
            } else {
                return 0;
            }
        }

        if(isPossible && !c.isPossible()) {
            return -1;
        } else if(!isPossible && c.isPossible()) {
            return 1;
        } else {                                    //Neither is possible.
            return 0;
        }
    }
}
