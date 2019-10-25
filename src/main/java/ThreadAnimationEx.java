import javax.swing.*;
import java.awt.*;

public class ThreadAnimationEx extends JFrame {

    public ThreadAnimationEx(){
        initUI();
    }
    private void initUI(){
        add(new Board());
        setResizable(false);
        pack();

        setTitle("asda");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){

        EventQueue.invokeLater(() -> {
            JFrame ex = new ThreadAnimationEx();
            ex.setVisible(true);
        });
    }
}
