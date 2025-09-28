

public class Player {

    private int x;
    private int y;
    private int a;

    public Player () {}

    public void setPlayer (int x, int y, int a) {
        this.x = x;
        this.y = y;
        this.a = a;
    }

    public int moveLeft() {
        a--;
        return x -= 100;
    }

    public int moveRight() {
        a--;
        return x += 100;
    }

    public int moveUp() {
        a--;
        return y -= 100;
    }

    public int moveDown() {
        a--;
        return y += 100;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getA() {
        return a;
    }

}   
