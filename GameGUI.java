import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameGUI extends JPanel implements KeyListener {

    private int state = 0;
    private int over = 0;
    private int pause;
    public Player a = new Player();
    private Map map = new Map();
    private int m[][];
    private Image MainCharacter = new ImageIcon("src/MainCharacter.gif").getImage();
    private Image Wall = new ImageIcon("src/wall.Jpg").getImage();
    private Image Monster = new ImageIcon("src/monster.gif").getImage();
    private Image Stone = new ImageIcon("src/stone.jpg").getImage();
    private Image Door = new ImageIcon("src/door.jpg").getImage();
    private Image Trap = new ImageIcon("src/trap.jpg").getImage();

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

        else if (state == 2 && pause == 1) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("Tahoma", Font.BOLD, 60));
            g.drawString("Congratulations+", 320, 300);
            g.setFont(new Font("Tahoma", Font.BOLD, 30));
            g.drawString("Place [ Space ] to Continue", 300, 400);
        }

        else if (state == 2 && pause == 0) {
            g.setFont(new Font("Consolas", Font.BOLD, 80));
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(a.getA()), 50, 900);

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
                    else if (m[i][j] == 4) {
                        g.drawImage(Trap, xM, yM, 100, 100, this);
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
        pause = 0;

        if (state == 0 && key == ' ') {
            state = 1;
            loadMap();
            a.setPlayer(700, 100, 24);
            repaint();
        }

        if (state == 2 && key == ' ') {
            loadMap();
            a.setPlayer(100, 500, 24);
            repaint();
        }

        int xP = (a.getX() / 100) - 1;
        int yP = (a.getY() / 100) - 1;

        if (state > 0 && key == 'w' && a.getA() != 0) {

            int w = Walk(xP, yP - 1, m);

            if (w == 1) {
                a.moveUp();
                yP--;
            }

            else if (w == 2 && m[yP - 2][xP] != 0) {
                m[yP - 1][xP] = 0;
                a.Action();
            }
            else if (w == 2 && m[yP - 2][xP] == 0) {
                m[yP - 1][xP] = 0;
                m[yP - 2][xP] = 2;
                a.Action();
            }

            else if (w == 3 && m[yP - 2][xP] == 0) {
                m[yP - 1][xP] = 0;
                m[yP - 2][xP] = 3;
                a.Action();
                yP--;
            }

            else if (w == 4) {
                a.moveUp();
                a.Action();
                yP--;
            }

            else if (w == 9) {
                state++;
                pause++;
            }

            repaint();
        }

        else if (state > 0 && key == 's' && a.getA() != 0) {

            int w = Walk(xP, yP + 1, m);

            if (w == 1) {
                a.moveDown();
                yP++;
            }
            else if (w == 2 && m[yP + 2][xP] != 0) {
                m[yP + 1][xP] = 0;
                a.Action();
            }
            else if (w == 2 && m[yP + 2][xP] == 0) {
                m[yP + 1][xP] = 0;
                m[yP + 2][xP] = 2;
                a.Action();
            }

            else if (w == 3 && m[yP + 2][xP] == 0) {
                m[yP + 1][xP] = 0;
                m[yP + 2][xP] = 3;
                a.Action();
            }

            else if (w == 4) {
                a.moveDown();
                a.Action();
                yP++;
            }

            else if (w == 9) {
                state++;
                pause++;
            }

            repaint();
        } 

        else if (state > 0 && key == 'a' && a.getA() != 0) {

            int w = Walk(xP - 1, yP, m);

            if (w == 1) {
                a.moveLeft();
                xP--;
            }

            else if (w == 2 && m[yP][xP - 2] != 0) {
                m[yP][xP - 1] = 0;
                a.Action();
            }
            else if (w == 2 && m[yP][xP - 2] == 0) {
                m[yP][xP - 1] = 0;
                m[yP][xP - 2] = 2;
                a.Action();
            }

            else if (w == 3 && m[yP][xP - 2] == 0) {
                m[yP][xP - 1] = 0;
                m[yP][xP - 2] = 3;
                a.Action();
            }

            else if (w == 4) {
                a.moveLeft();
                a.Action();
                xP--;
            }

            else if (w == 9) {
                state++;
                pause++;
            }

            repaint();
        }

        else if (state > 0 && key == 'd' && a.getA() != 0) {

            int w = Walk(xP + 1, yP, m);

            if (w == 1) {
                a.moveRight();
                xP++;
            }

            else if (w == 2 && m[yP][xP + 2] != 0) {
                m[yP][xP + 1] = 0;
                a.Action();
            }
            else if (w == 2 && m[yP][xP + 2] == 0) {
                m[yP][xP + 1] = 0;
                m[yP][xP + 2] = 2;
                a.Action();
            }

            else if (w == 3 && m[yP][xP + 2] == 0) {
                m[yP][xP + 1] = 0;
                m[yP][xP + 2] = 3;
                a.Action();
            }

            else if (w == 4) {
                a.moveLeft();
                a.Action();
                xP++;
            }

            else if (w == 9) {
                state++;
                pause++;
            }

            repaint();
        } 

        else if (state > 0 && a.getA() == 0) {
            over = 1;
            repaint();
        }

        if (key == ' ' && over == 1 && state == 1) {
            over = 0;
            loadMap();
            a.setPlayer(700, 100, 24);
            repaint();
        }

        if (key == ' ' && over == 1 && state == 1) {
            over = 0;
            loadMap();
            a.setPlayer(100, 500, 24);
            repaint();
        }

    }

    public int Walk (int x, int y, int[][] m) {
        // 0 = cann't walk
        // 1 = can walk
        // 2 = monster
        // 3 = stone
        // 4 = trap
        // 9 = exit
        if (y < 0 || y >= m.length || x < 0 || x >= m[0].length) {
            return 0;
        }
        else if (m[y][x] == 0) {
            return 1;
        }
        else if (m[y][x] == 2) {
            return 2;
        }
        else if (m[y][x] == 3) {
            return 3;
        }
        else if (m[y][x] == 4) {
            return 4;
        }
        else if (m[y][x] == 9) {
            return 9;
        }
        return 0;
    }

    private void loadMap() {
        m = map.getMap(state - 1);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
