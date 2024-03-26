import javax.swing.ImageIcon;

public class Food extends Entity {

  private final int width = 640, height = 480;

  Food() {
    initFood();
    randomFood();
  }

  //   Food(int x, int y) {
  //     super(x, y);
  //     initFood();
  //   }

  private void initFood() {
    ImageIcon icon = new ImageIcon(getClass().getResource("/img/food.png"));
    setImage(icon.getImage());
  }

  public void randomFood() {
    int currX = (int) (Math.random() * 1000) % width;
    int cuurY = (int) (Math.random() * 1000) % height;
    setX(currX / 32 * 32);
    setY(cuurY / 32 * 32);
  }
}
