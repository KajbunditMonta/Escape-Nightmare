import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameGUI extends JPanel implements KeyListener {

    private int state = 0;
    private int over = 0;
    public Player a = new Player();
    private Map map = new Map();
    private Image MainCharacter = new ImageIcon("src/MainCharacter.gif").getImage();
    private Image Wall = new ImageIcon("src/wall.Jpg").getImage();
    private Image Monster = new ImageIcon("src/monster.gif").getImage();
    private Image Stone = new ImageIcon("src/stone.jpg").getImage();
    private Image Door = new ImageIcon("src/door.jpg").getImage();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (state == 0) {
            g.setFont(new Font("Tahoma", Font.BOLD, 24));
            g.setColor(Color.WHITE);
            g.drawString("Place [ Space ] to start", 330, 800);
        }

        else if (state == 1) {
            g.setFont(new Font("Consolas", Font.BOLD, 80));
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(a.getA()), 50, 800);

            int m[][] = map.getMap(state);
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m[i].length; j++) {

                    int xM = (j + 1) * 100;
                    int yM = (i + 1) * 100;

                    if (m[i][j] == 0) {
                        g.drawImage(Wall, xM, yM, 100, 100, this);
                    } 
                    else if (m[i][j] == 1) {
                        g.drawRect(xM, yM, 100, 100);
                        g.setColor(Color.BLACK);
                        g.fillRect(xM, yM, 100, 100);
                    } 
                    else if (m[i][j] == 2) {
                        g.drawImage(Monster, xM, yM, 100, 100, this);
                    } 
                    else if (m[i][j] == 3) {
                        g.drawImage(Stone, xM, yM, 100, 100, this);
                    } 
                    else if (m[i][j] == 9) {
                        g.drawImage(Door, xM, yM, 100, 100, this);
                    }

                }
            }
            g.drawImage(MainCharacter, a.getX(), a.getY(), 100, 100, this);
        }

        if (over == 1) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.RED);
            g.setFont(new Font("Tahoma", Font.BOLD, 60));
            g.drawString("Game Over!", 320, 300);
            g.setFont(new Font("Tahoma", Font.BOLD, 30));
            g.drawString("Place [ Space ] to Restart", 300, 400);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();

        if (state == 0 && key == ' ') {
            state = 1;
            a.setPlayer(700, 100, 23);
            repaint();
        }

        int[][] m = map.getMap(state);
        int xP = (a.getX() / 100) - 1;
        int yP = (a.getY() / 100) - 1;

        if (state > 0 && key == 'w' && a.getA() != 0) {
            if (canWalk(xP, yP - 1, m)) {
                a.moveUp();
            }
            repaint();
        }
        else if (state > 0 && key == 's' && a.getA() != 0) {
            if (canWalk(xP, yP + 1, m)) {
                a.moveDown();
            }
            repaint();
        } 
        else if (state > 0 && key == 'a' && a.getA() != 0) {
            if (canWalk(xP - 1, yP, m)) {
                a.moveLeft();
            }
            repaint();
        } 
        else if (state > 0 && key == 'd' && a.getA() != 0) {
            if (canWalk(xP + 1, yP, m)) {
                a.moveRight();
            }
            repaint();
        } 
        else if (state > 0 && a.getA() == 0) {
            over = 1;
            repaint();
        }

        if (key == ' ' && over == 1 && state == 1) {
            over = 0;
            a.setPlayer(700, 100, 23);
            repaint();
        }
    }

    public boolean canWalk (int x, int y, int[][] m) {

        if (y < 0 || y >= m.length || x < 0 ||x >= m[0].length) {
            return false;
        }
        else if (m[y][x] == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
