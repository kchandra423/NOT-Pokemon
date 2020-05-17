import javax.swing.*;

public class Button extends JButton {
	private int num;
	private boolean hasProblem;

	public Button(String name, int num) {
		super(name);
		this.num = num;
		hasProblem = false;
	}

	public int getNum() {
		return num;
	}

	public boolean hasProblem() {
		return hasProblem;
	}

	public void setProblem(boolean problem) {
		hasProblem = problem;
	}
}