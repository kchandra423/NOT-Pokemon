import javax.swing.*;

public class Button extends JButton {
	private int num;
	private boolean hasProblem=false;

	public Button(String name, int num) {
		super(name);
		this.num = num;
	}

	public int getNum() {
		return num;
	}
	public boolean hasProblem(){
		return hasProblem;
	}
	public void setProblem(boolean param){
		hasProblem=param;
	}
}