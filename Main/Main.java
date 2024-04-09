package Main;

public class Main {

  public static void main(String[] args) {
    GameFrame window = new GameFrame();
    GamePanel gamePanel = new GamePanel();
    window.add(gamePanel);
    // window.pack();
    gamePanel.startGameThread();
    window.setVisible(true);
  }
}
