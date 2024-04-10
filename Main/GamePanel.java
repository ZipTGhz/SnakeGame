package Main;

import Entity.SnakeManager;
import Title.TitleManager;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

  private final long oneBillionNano = (int) 1e9;

  private Layer layer = new Layer(this);
  private Thread gameThread;
  private KeyHandle keyH = new KeyHandle();

  public boolean gameOver = false;
  public boolean isPause = false;
  public int score = 0;
  public final int SPEED = 5;
  public final int LEFT = 1, RIGHT = 2, UP = 3, DOWN = 4;
  public final int SIZE = 64;

  public SnakeManager snake = new SnakeManager(this, keyH);
  public TitleManager tm = new TitleManager(this);
  public CollisionChecker cChecker = new CollisionChecker(this);
  public FoodSpawner fSpawner = new FoodSpawner(this);

  public GamePanel() {
    this.setDoubleBuffered(true);
    addKeyListener(keyH);
    setFocusable(true);
  }

  public void startGameThread() {
    gameThread = new Thread(this);
    gameThread.start();
  }

  @Override
  public void run() {
    double delta = 0, drawInterVal = oneBillionNano / SPEED;
    long lastTime = System.nanoTime(), currentTime;
    fSpawner.spawn();
    while (gameThread != null) {
      currentTime = System.nanoTime();
      delta += (currentTime - lastTime) / drawInterVal;
      lastTime = currentTime;
      if (delta >= 1) {
        snake.update();
        repaint();
        --delta;
      }
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    tm.draw(g2);
    fSpawner.draw(g2);
    snake.draw(g2);
    layer.draw(g2);
  }
}
