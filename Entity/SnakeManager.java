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
    if (keyH.isPaused) return;
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
      snakes.add(tmpTail);
      gp.score += gp.SPEED;
      gp.fSpawner.spawn();
    }
  }

  public void draw(Graphics2D g2) {
    for (int i = 0; i < snakes.size(); ++i) {
      if (i == 0) {
        drawHeadGraphic(snakes.get(i), g2);
      } else if (i == snakes.size() - 1) {
        drawTailGraphic(snakes.get(i - 1), snakes.get(i), g2);
      } else {
        drawBodyGraphic(
          snakes.get(i - 1),
          snakes.get(i),
          snakes.get(i + 1),
          g2
        );
      }
    }
  }

  private void drawHeadGraphic(Snake h, Graphics2D g2) {
    if (snakeDirection == gp.LEFT) {
      g2.drawImage(h.headLeft, h.getX(), h.getY(), gp.SIZE, gp.SIZE, null);
    } else if (snakeDirection == gp.RIGHT) {
      g2.drawImage(h.headRight, h.getX(), h.getY(), gp.SIZE, gp.SIZE, null);
    } else if (snakeDirection == gp.UP) {
      g2.drawImage(h.headUp, h.getX(), h.getY(), gp.SIZE, gp.SIZE, null);
    } else if (snakeDirection == gp.DOWN) {
      g2.drawImage(h.headDown, h.getX(), h.getY(), gp.SIZE, gp.SIZE, null);
    }
  }

  private void drawTailGraphic(Snake l, Snake t, Graphics2D g2) {
    if (t.getX() - gp.SIZE == l.getX() && t.getY() == l.getY()) {
      g2.drawImage(t.tailRight, t.getX(), t.getY(), gp.SIZE, gp.SIZE, null);
    } else if (t.getX() + gp.SIZE == l.getX() && t.getY() == l.getY()) {
      g2.drawImage(t.tailLeft, t.getX(), t.getY(), gp.SIZE, gp.SIZE, null);
    } else if (t.getX() == l.getX() && t.getY() - gp.SIZE == l.getY()) {
      g2.drawImage(t.tailDown, t.getX(), t.getY(), gp.SIZE, gp.SIZE, null);
    } else if (t.getX() == l.getX() && t.getY() + gp.SIZE == l.getY()) {
      g2.drawImage(t.tailUp, t.getX(), t.getY(), gp.SIZE, gp.SIZE, null);
    }
  }

  private void drawBodyGraphic(Snake l, Snake c, Snake n, Graphics2D g2) {
    if (Math.abs(l.getX() - n.getX()) == Math.abs(l.getY() - n.getY())) {
      if (
        (c.getX() == l.getX() - gp.SIZE && c.getY() == n.getY() - gp.SIZE) ||
        (c.getX() == n.getX() - gp.SIZE && c.getY() == l.getY() - gp.SIZE)
      ) g2.drawImage(
        c.bodyBottomRight,
        c.getX(),
        c.getY(),
        gp.SIZE,
        gp.SIZE,
        null
      ); else if (
        (c.getX() == l.getX() + gp.SIZE && c.getY() == n.getY() + gp.SIZE) ||
        (c.getX() == n.getX() + gp.SIZE && c.getY() == l.getY() + gp.SIZE)
      ) {
        g2.drawImage(c.bodyTopLeft, c.getX(), c.getY(), gp.SIZE, gp.SIZE, null);
      } else if (
        (c.getX() == l.getX() + gp.SIZE && c.getY() == n.getY() - gp.SIZE) ||
        (c.getX() == n.getX() + gp.SIZE && c.getY() == l.getY() - gp.SIZE)
      ) {
        g2.drawImage(
          c.bodyBottomLeft,
          c.getX(),
          c.getY(),
          gp.SIZE,
          gp.SIZE,
          null
        );
      } else {
        g2.drawImage(
          c.bodyTopRight,
          c.getX(),
          c.getY(),
          gp.SIZE,
          gp.SIZE,
          null
        );
      }
    } else {
      if (l.getX() == n.getX()) g2.drawImage(
        c.bodyVertical,
        c.getX(),
        c.getY(),
        gp.SIZE,
        gp.SIZE,
        null
      ); else g2.drawImage(
        c.bodyHorizontal,
        c.getX(),
        c.getY(),
        gp.SIZE,
        gp.SIZE,
        null
      );
    }
  }
}
