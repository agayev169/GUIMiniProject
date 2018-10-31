import java.awt.*;

public class Buffer extends DataSender {
    private long nextReceive = 0;

    Buffer(int x, int y) {
        super(x, y);
    }

    Buffer(int x, int y, int r) {
        super(x, y, r);
    }

    @Override
    public void update() {
        send();

        if (nextReceive < System.currentTimeMillis()) {
            color = Color.GREEN;
        }
    }

    @Override
    public void send() {
        if (nextReceive < System.currentTimeMillis() && toSend > 0) {
            receiver.receive();
            toSend--;
        }
    }

    @Override
    public void receive() {
        if (nextReceive < System.currentTimeMillis() && toSend == 0) {
            color = Color.RED;
            nextReceive = System.currentTimeMillis() + (long) (Math.random() * 2000);
            toSend++;
        }
    }
}
