import java.awt.*;

public abstract class DataSender {
    DataSender receiver;
    Color color = Color.GREEN;
    int toSend = 0;

    private int x;
    private int y;
    private int r = 50;

    DataSender(int x, int y) {
        this.x = x;
        this.y = y;
    }

    DataSender(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    void setReceiver(DataSender receiver) {
        this.receiver = receiver;
    }

    public abstract void update();

    public abstract void send();

    public void receive() {

    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, r, r);
    }
}
