package operation;

public interface Cost extends Comparable<Cost> {
    public boolean isPossible();
    public int getScore();
}
