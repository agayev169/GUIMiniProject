import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JPanel implements ActionListener {

    long nextSend = 0;

    static final int WIDTH = 640, HEIGHT = 480;
    Timer timer = new Timer(1, this);
    static Thread[] threads = new Thread[4];
    static Buffer[] buffers = new Buffer[3];

    @Override
    protected void paintComponent(Graphics g) {
        timer.start();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        for (Thread th : threads) {
            th.draw(g);
            th.update();
        }
        for (Buffer buf : buffers) {
            buf.draw(g);
            buf.update();
        }
        if (nextSend < System.currentTimeMillis()) {
            threads[0].receive();
            nextSend = System.currentTimeMillis() + 3000;
        }
    }

    public static void main(String[] args) {
        threads[0] = new Thread(50, HEIGHT / 2 - 100);
        threads[1] = new Thread(190, HEIGHT / 2 - 100);
        threads[2] = new Thread(330, HEIGHT / 2 - 100);
        threads[3] = new Thread(470, HEIGHT / 2 - 100);
        buffers[0] = new Buffer(120, HEIGHT * 3 / 4 - 100);
        buffers[1] = new Buffer(260, HEIGHT * 3 / 4 - 100);
        buffers[2] = new Buffer(400, HEIGHT * 3 / 4 - 100);
        threads[0].setReceiver(buffers[0]);
        buffers[0].setReceiver(threads[1]);
        threads[1].setReceiver(buffers[1]);
        buffers[1].setReceiver(threads[2]);
        threads[2].setReceiver(buffers[2]);
        buffers[2].setReceiver(threads[3]);


        JFrame jf = new JFrame();
        Main jp = new Main();
        jf.setSize(WIDTH, HEIGHT);
        jf.setTitle("Drawing Threads");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.add(jp);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }
}
