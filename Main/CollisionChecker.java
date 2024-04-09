package Main;

import Entity.Food;
import Entity.Snake;

public class CollisionChecker {

  private GamePanel gp;

  CollisionChecker(GamePanel gp) {
    this.gp = gp;
  }

  public boolean checkBlockCollision() {
    Snake head = gp.snake.snakes.getFirst();
    int direction = gp.snake.snakeDirection;
    int blockRow = head.getY() / gp.SIZE;
    int blockCol = head.getX() / gp.SIZE;
    if (direction == gp.LEFT) {
      int blockNum = gp.tm.map[blockRow][blockCol - 1];
      if (gp.tm.titles[blockNum].collision == true) return true;
    } else if (direction == gp.RIGHT) {
      int blockNum = gp.tm.map[blockRow][blockCol + 1];
      if (gp.tm.titles[blockNum].collision == true) return true;
    } else if (direction == gp.UP) {
      int blockNum = gp.tm.map[blockRow - 1][blockCol];
      if (gp.tm.titles[blockNum].collision == true) return true;
    } else if (direction == gp.DOWN) {
      int blockNum = gp.tm.map[blockRow + 1][blockCol];
      if (gp.tm.titles[blockNum].collision == true) return true;
    }
    return false;
  }

  public boolean checkSnakeCollision() {
    Snake head = gp.snake.snakes.getFirst();
    int direction = gp.snake.snakeDirection;
    int x = head.getX(), y = head.getY();
    if (direction == gp.LEFT) {
      x -= gp.SIZE;
    } else if (direction == gp.RIGHT) {
      x += gp.SIZE;
    } else if (direction == gp.UP) {
      y -= gp.SIZE;
    } else if (direction == gp.DOWN) {
      y += gp.SIZE;
    }
    for (int i = 1; i < gp.snake.snakes.size(); ++i) {
      Snake s = gp.snake.snakes.get(i);
      if (s.getX() == x && s.getY() == y) return true;
    }
    return false;
  }

  public boolean checkFoodCollision(Food food) {
    for (Snake s : gp.snake.snakes) {
      if (s.getX() == food.getX() && s.getY() == food.getY()) return true;
    }
    return false;
  }


}
