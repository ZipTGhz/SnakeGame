import java.awt.EventQueue;
import javax.swing.JFrame;

public class Game extends JFrame {

  public Game() {
    initGame();
  }

  public void initGame() {
    Board b = new Board();
    add(b);
    setSize(640, 480);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      Game game = new Game();
      game.setVisible(true);
    });
  }
}
