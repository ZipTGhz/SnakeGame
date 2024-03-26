import javax.swing.ImageIcon;

public class Food extends Entity {

  private int boardWidth, boardHeight;

  // Food() {
  //   initFood();
  //   randomFood();
  // }

  Food(int boardWidth, int boardHeight) {
    this.boardWidth = boardWidth;
    this.boardHeight = boardHeight;
    initFood();
    randomFood();
  }

  private void initFood() {
    ImageIcon icon = new ImageIcon(getClass().getResource("/img/food.png"));
    setImage(icon.getImage());
  }

  public void randomFood() {
    int currX = (int) (Math.random() * 10000) % boardWidth;
    int cuurY = (int) (Math.random() * 10000) % boardHeight;
    setX(currX / 32 * 32);
    setY(cuurY / 32 * 32);
  }
}
