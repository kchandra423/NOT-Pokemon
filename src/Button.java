import javax.swing.*;

public class Button extends JButton {
	private int num;

	public Button(String name, int num) {
		super(name);
		this.num = num;
	}

	public int getNum() {
		return num;
	}
}