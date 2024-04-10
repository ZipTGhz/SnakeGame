package Main;

import Entity.Food;
import Entity.Snake;
import java.awt.Graphics2D;

public class FoodSpawner {

  GamePanel gp;
  Food food;

  public FoodSpawner(GamePanel gp) {
    this.gp = gp;
    food = new Food();
  }

  public void spawn() {
    int x, y;
    do {
      x = gp.SIZE + (int) (Math.random() * 896) / gp.SIZE * gp.SIZE;
      y = gp.SIZE + (int) (Math.random() * 640) / gp.SIZE * gp.SIZE;
      food.setPosition(x, y);
    } while (gp.cChecker.checkFoodCollision(food));
  }

  public void draw(Graphics2D g2) {
    g2.drawImage(food.image, food.getX(), food.getY(), gp.SIZE, gp.SIZE, null);
  }

  public boolean isAteFood() {
    Snake head = gp.snake.snakes.getFirst();
    return head.getX() == food.getX() && head.getY() == food.getY();
  }
}
