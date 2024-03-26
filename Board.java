import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Board extends JPanel {

  private final int SIZE = 32;
  private final int LEFT = 1, RIGHT = 2, UP = 3, DOWN = 4;
  private boolean isOver = false;
  ArrayList<Wall> walls;
  ArrayList<Snake> snakes;
  Food food;

  Board() {
    initBoard();
  }

  private void initBoard() {
    addKeyListener(new SnakeAapter());
    setFocusable(true);
    initWorld();
  }

  private class SnakeAapter extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
      // TODO Auto-generated method stub
      if (isOver) return;
      int keyCode = e.getKeyCode();
      // int keyCode = KeyEvent.VK_W;
      if (keyCode == KeyEvent.VK_W) {
        moveSnake(UP);
      } else if (keyCode == KeyEvent.VK_A) {
        moveSnake(LEFT);
      } else if (keyCode == KeyEvent.VK_S) {
        moveSnake(DOWN);
      } else if (keyCode == KeyEvent.VK_D) {
        moveSnake(RIGHT);
      }
      repaint();
    }
  }

  private void initWorld() {
    walls = new ArrayList<>();
    snakes = new ArrayList<>();
    food = new Food();
    for (int i = 0; i < 6; ++i) {
      snakes.add(new Snake(320 + 32 * i, 320));
    }
    while (checkFoodCollision()) food.randomFood();
  }

  private void buildWorld(Graphics g) {
    ArrayList<Entity> entities = new ArrayList<>();
    entities.addAll(snakes);
    entities.add(food);
    for (Entity e : entities) {
      g.drawImage(e.getImage(), e.getX(), e.getY(), this);
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    // TODO Auto-generated method stub
    super.paintComponent(g);
    buildWorld(g);
  }

  private boolean checkFoodCollision() {
    for (Snake s : snakes) {
      if (s.getX() == food.getX() && s.getY() == food.getY()) return true;
    }
    return false;
  }

  private void moveSnake(int type) {
    Snake head = snakes.get(0);
    if (type == LEFT) {
      Snake newHead = new Snake(head.getX() - SIZE, head.getY());
      for (int i = 0; i < snakes.size(); ++i) {
        Snake tmp = snakes.get(i);
        snakes.set(i, newHead);
        newHead = tmp;
      }
    } else if (type == RIGHT) {
      Snake newHead = new Snake(head.getX() + SIZE, head.getY());
      for (int i = 0; i < snakes.size(); ++i) {
        Snake tmp = snakes.get(i);
        snakes.set(i, newHead);
        newHead = tmp;
      }
    } else if (type == UP) {
      Snake newHead = new Snake(head.getX(), head.getY() - SIZE);
      for (int i = 0; i < snakes.size(); ++i) {
        Snake tmp = snakes.get(i);
        snakes.set(i, newHead);
        newHead = tmp;
      }
    } else {
      Snake newHead = new Snake(head.getX(), head.getY() + SIZE);
      for (int i = 0; i < snakes.size(); ++i) {
        Snake tmp = snakes.get(i);
        snakes.set(i, newHead);
        newHead = tmp;
      }
    }
  }
}
