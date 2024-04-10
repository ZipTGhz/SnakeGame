package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandle implements KeyListener {

  private final int LEFT = 1, RIGHT = 2, UP = 3, DOWN = 4;
  public int direction = LEFT;
  public boolean isPaused = false;

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyPressed(KeyEvent e) {
    int keyCode = e.getKeyCode();
    if ((keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT)) {
      direction = LEFT;
    } else if ((keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT)) {
      direction = RIGHT;
    } else if ((keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP)) {
      direction = UP;
    } else if ((keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN)) {
      direction = DOWN;
    } else if (keyCode == KeyEvent.VK_P) {
      isPaused = true;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int keyCode = e.getKeyCode();
    if (keyCode == KeyEvent.VK_P) {
      isPaused = false;
    }
  }
}
