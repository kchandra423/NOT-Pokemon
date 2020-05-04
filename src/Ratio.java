
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
