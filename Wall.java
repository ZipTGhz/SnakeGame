import javax.swing.ImageIcon;

public class Wall extends Entity {

  Wall(int x, int y) {
    super(x, y);
    initWall();
  }

  private void initWall() {
    ImageIcon icon = new ImageIcon(getClass().getResource("/img/brick.png"));
    setImage(icon.getImage());
  }
}
