import java.awt.*;

public class Thread extends DataSender {
    Thread(int x, int y) {
        super(x, y);
    }

    Thread(int x, int y, int r) {
        super(x, y, r);
    }

    @Override
    public void update() {
        color = Color.GREEN;
        send();
    }

    @Override
    public void send() {
        if (toSend > 0 && receiver != null) {
            receiver.update();
            receiver.receive();
            toSend--;
        }
    }

    @Override
    public void receive() {
        toSend++;
    }

    public void log() {
        System.out.println("toSend: " + toSend);
    }
}
