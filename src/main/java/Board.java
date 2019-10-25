import javax.swing.*;
import java.awt.*;

// Говорят что самыйшикарный и правильный способ анимации
// Здесь нет спецом задуманых интервалов, всё просиходит в потоке, метод run вызывается однажды.
// Для работы используется петля, которая вызывает методы цыкл и репэйнт
public class Board extends JPanel implements Runnable {

    private final int B_WIDTH = 350;
    private final int B_HEIGHT = 350;
    private final int INITIAL_X = -40;
    private final int INITIAL_Y = -40;
    private final int DELAY = 25;

    private Image star;
    private Thread animator;
    private int x, y;

    public Board(){
        initBoard();
    }

    private void loadImage(){
        ImageIcon ii = new ImageIcon("src/main/resources/1.png");
        star = ii.getImage();
    }

    private void initBoard() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        loadImage();

        x = INITIAL_X;
        y = INITIAL_Y;
    }

    // не совсем понял, но судя по всему эта хрень JFrame компонент юзается для различных задач инициализации
    @Override
    public void addNotify(){
        super.addNotify();;

        animator = new Thread(this);
        animator.start();
    }

    @Override
    public void paintComponent(Graphics g){
        g.drawImage(star, x, y, this);
        Toolkit.getDefaultToolkit().sync();
    }

    private void  cycle(){
        x += 1;
        y += 1;

        if (y > B_HEIGHT) {
            y = INITIAL_Y;
            x = INITIAL_X;
        }
    }

    // методы цыкл и репэйнт должны запускаться с различным временем, поэтому мы расчитываем значения переменных
    // используя
    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();
        while (true) {
            cycle();
            repaint();
// мы очень хотим что бы анимация смотрелась ахуенно, поэтому кадры буем считать с помощью системного времени.
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e){
                String msg = String.format("Thread interrupted: %s", e.getMessage());

                JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
            }
            beforeTime = System.currentTimeMillis();
        }

    }
}
