//
//  Author: Eric Chang
//  Revised: Eric Chang
//           5/5/20
//
//  Notes:
//      This class is a ratio with two ints
//
//  Bugs:
//      none
//
public class Ratio {
    private final int first;
    private final int second;

    public Ratio(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public String toString() {
        return first + ":" + second;
    }
}