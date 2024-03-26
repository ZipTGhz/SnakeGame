import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel {

  private final int SIZE = 32;
  private final int LEFT = 1, RIGHT = 2, UP = 3, DOWN = 4;
  private final int REFRESH_RATE = 250;

  private int boardWidth, boardHeight;
  private int direction = LEFT;
  private boolean isOver = false;

  private ArrayList<Wall> walls;
  private ArrayList<Snake> snakes;
  private Food food;

  private Timer timer = new Timer(
    REFRESH_RATE,
    new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        moveSnake(direction);
        repaint();
      }
    }
  );

  Board(int width, int height) {
    // this.width = width;
    // this.height = height;
    initBoard();
  }

  private void initBoard() {
    addKeyListener(new SnakeAapter());
    setFocusable(true);
    initWorld();
    draw();
  }

  private class SnakeAapter extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
      if (isOver) return;
      int keyCode = e.getKeyCode();
      if (
        (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) &&
        direction != DOWN
      ) {
        direction = UP;
      } else if (
        (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) &&
        direction != RIGHT
      ) {
        direction = LEFT;
      } else if (
        (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) &&
        direction != UP
      ) {
        direction = DOWN;
      } else if (
        (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) &&
        direction != LEFT
      ) {
        direction = RIGHT;
      } else if (keyCode == KeyEvent.VK_P) {
        timer.stop();
      } else if (keyCode == KeyEvent.VK_R) {
        timer.start();
      }
    }
  }

  private void initWorld() {
    String level =
      "**************\n" +
      "*            *\n" +
      "*            *\n" +
      "*            *\n" +
      "*            *\n" +
      "*            *\n" +
      "*            *\n" +
      "*            *\n" +
      "*            *\n" +
      "*            *\n" +
      "*            *\n" +
      "*            *\n" +
      "**************\n";
    walls = new ArrayList<>();
    for (int i = 0, x = 0, y = 0; i < level.length(); ++i) {
      char c = level.charAt(i);
      if (c == '*') {
        walls.add(new Wall(x, y));
        x += SIZE;
      } else if (c == '\n') {
        y += SIZE;
        boardHeight = y;
        boardWidth = Math.max(boardWidth, x);
        x = 0;
      } else {
        x += SIZE;
      }
    }
    snakes = new ArrayList<>();
    food = new Food(boardWidth, boardHeight);
    for (int i = 0; i < 3; ++i) {
      snakes.add(
        new Snake(boardWidth / 2 / 32 * 32 + 32 * i, boardHeight / 2 / 32 * 32)
      );
    }
    while (checkGenerateFoodCollision()) food.randomFood();
  }

  private void draw() {
    timer.start();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    buildWorld(g);
  }

  private void buildWorld(Graphics g) {
    ArrayList<Entity> entities = new ArrayList<>();
    entities.add(food);
    entities.addAll(snakes);
    entities.addAll(walls);
    for (Entity e : entities) {
      g.drawImage(e.getImage(), e.getX(), e.getY(), this);
    }
    if (isOver) {
      g.drawString("Game Over!", 540, 320);
    }
  }

  private boolean checkGenerateFoodCollision() {
    for (Snake s : snakes) {
      if (s.getX() == food.getX() && s.getY() == food.getY()) return true;
    }
    for (Wall s : walls) {
      if (s.getX() == food.getX() && s.getY() == food.getY()) return true;
    }
    return false;
  }

  private void moveSnake(int type) {
    Snake head = snakes.get(0);
    Snake tail = snakes.get(snakes.size() - 1);
    for (int i = snakes.size() - 1; i > 0; --i) {
      snakes.set(i, snakes.get(i - 1));
    }
    if (type == LEFT) {
      snakes.set(0, new Snake(head.getX() - SIZE, head.getY()));
    } else if (type == RIGHT) {
      snakes.set(0, new Snake(head.getX() + SIZE, head.getY()));
    } else if (type == UP) {
      snakes.set(0, new Snake(head.getX(), head.getY() - SIZE));
    } else {
      snakes.set(0, new Snake(head.getX(), head.getY() + SIZE));
    }
    if (isAteFood()) {
      snakes.add(tail);
      do {
        food.randomFood();
      } while (checkGenerateFoodCollision());
    }
    if (checkWallCollistion() || checkSnakeCollision()) {
      isOver = true;
      timer.stop();
    }
  }

  private boolean isAteFood() {
    Snake head = snakes.get(0);
    return head.getX() == food.getX() && head.getY() == food.getY();
  }

  private boolean checkWallCollistion() {
    Snake head = snakes.get(0);
    for (Wall w : walls) {
      if (w.getX() == head.getX() && w.getY() == head.getY()) return true;
    }
    return false;
  }

  private boolean checkSnakeCollision() {
    Snake head = snakes.get(0);
    for (int i = 1; i < snakes.size(); ++i) {
      Snake s = snakes.get(i);
      if (s.getX() == head.getX() && s.getY() == head.getY()) return true;
    }
    return false;
  }
}
