import javax.swing.*;

public class KTextField extends JTextField {
    private int column;
    private int row;



    public KTextField(String x) {
        super(x);
        column=-1;
        row=-1;

    }






    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
    public void setColumn(int x){
        column=x;
    }
    public void setRow(int x){
        row=x;
    }
}
