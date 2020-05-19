import javax.swing.*;

public class Button2 extends JButton {
	private int num;

	public Button2(String name, int num) {
		super(name);
		this.num = num;
	}

	public int getNum() {
		return num;
	}
}
