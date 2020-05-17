import javax.swing.*;

public class Button extends JButton {
	private int num;//used for identifying which button was pressed
	private boolean hasProblem=false;//used for saying if the column of this confirmation button has a problem,
	// like one of the moves is invalid
	//doesnt actually mean the button has a problem, but i couldnt think of a better name

	public Button(String name, int num) {//self explanatory
		super(name);
		this.num = num;
	}

	public int getNum() {
		return num;
	}//self explanatory
	public boolean hasProblem(){//self explanatory
		return hasProblem;
	}
	public void setProblem(boolean param){//self explanatory
		hasProblem=param;
	}
}