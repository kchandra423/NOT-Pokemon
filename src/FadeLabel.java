import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
public class FadeLabel extends JLabel {
    private float alpha;
    private float fadeRate;
    private Timer timer=new Timer(100, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            float alpha=getAlpha();
            alpha += fadeRate;
            if (alpha < 0) {
                alpha = 0;
                fadeRate = 0.05f;
            } else if (alpha >= 1) {
                alpha = 1;
                timer.stop();
            } else {
                alpha += fadeRate;
            }
            setAlpha(alpha);

        }
    });
    public FadeLabel(Icon image,float fadeRateParam){
        super(image);
        setAlpha(0);
        fadeRate=fadeRateParam;
    }
    public void faidIn(){
            timer.start();

    }
    public void setAlpha(float value){
        if (value > 1) {

            value = 1;

        }
        if (alpha != value) {
            float old = alpha;
            alpha = value;
            firePropertyChange("alpha", old, alpha);
            repaint();
        }
    }
    public float getAlpha(){
            return alpha;
    }
    public void paint(Graphics g) {
        // This is one of the few times I would directly override paint
        // This makes sure that the entire paint chain is now using
        // the alpha composite, including borders and child components
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getAlpha()));
        super.paint(g2d);
        g2d.dispose();
    }
}
