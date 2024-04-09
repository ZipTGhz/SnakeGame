package Entity;

import Main.GamePanel;
import Main.KeyHandle;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class SnakeManager {

  GamePanel gp;
  KeyHandle keyH;
  public ArrayList<Snake> snakes;
  final int STEP = 64;
  public int snakeDirection;

  public SnakeManager(GamePanel gp, KeyHandle keyH) {
    snakeDirection = gp.LEFT;
    this.gp = gp;
    this.keyH = keyH;
    initSnake();
  }

  private void initSnake() {
    snakes = new ArrayList<>();
    int x = 64 * 8, y = 64 * 6;
    for (int i = 0; i < 3; ++i, x += 64) snakes.add(new Snake(x, y));
  }

  public void update() {
    if (gp.gameOver) return;

    if (snakeDirection != gp.RIGHT && keyH.direction == gp.LEFT) {
      snakeDirection = gp.LEFT;
    } else if (snakeDirection != gp.LEFT && keyH.direction == gp.RIGHT) {
      snakeDirection = gp.RIGHT;
    } else if (snakeDirection != gp.DOWN && keyH.direction == gp.UP) {
      snakeDirection = gp.UP;
    } else if (snakeDirection != gp.UP && keyH.direction == gp.DOWN) {
      snakeDirection = gp.DOWN;
    }
    boolean isCollision =
      gp.cChecker.checkBlockCollision() || gp.cChecker.checkSnakeCollision();
    if (isCollision) {
      gp.gameOver = true;
      return;
    }
    Snake newHead = new Snake(
      snakes.getFirst().getX(),
      snakes.getFirst().getY()
    );
    Snake tmpTail = new Snake(snakes.getLast().getX(), snakes.getLast().getY());
    for (int i = snakes.size() - 1; i > 0; --i) {
      snakes.set(i, snakes.get(i - 1));
    }
    if (snakeDirection == gp.LEFT) {
      newHead.setX(newHead.getX() - STEP);
    } else if (snakeDirection == gp.RIGHT) {
      newHead.setX(newHead.getX() + STEP);
    } else if (snakeDirection == gp.UP) {
      newHead.setY(newHead.getY() - STEP);
    } else if (snakeDirection == gp.DOWN) {
      newHead.setY(newHead.getY() + STEP);
    }
    snakes.set(0, newHead);
    if (gp.fSpawner.isAteFood()) {
      gp.fSpawner.spawn();
      snakes.add(tmpTail);
    }
  }

  public void draw(Graphics2D g2) {
    for (Snake snake : snakes) {
      g2.drawImage(
        snake.getImage(),
        snake.getX(),
        snake.getY(),
        gp.SIZE,
        gp.SIZE,
        null
      );
    }
  }
}
