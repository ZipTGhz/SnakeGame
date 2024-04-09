package Title;

import Main.GamePanel;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

public class TitleManager {

  GamePanel gp;
  public Title[] titles;
  public int map[][];

  public TitleManager(GamePanel gp) {
    this.gp = gp;
    titles = new Title[5];
    map = new int[12][16];
    initTilesImage();
    loadMap("/Map/map.txt");
  }

  private void initTilesImage() {
    try {
      titles[0] = new Title();
      titles[0].image =
        ImageIO.read(getClass().getResourceAsStream("/img/grass.png"));

      titles[1] = new Title();
      titles[1].image =
        ImageIO.read(getClass().getResourceAsStream("/img/brick.png"));
      titles[1].collision = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void loadMap(String path) {
    try {
      InputStream is = getClass().getResourceAsStream(path);
      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      int code, row = 0, col = 0;
      while ((code = br.read()) != -1) {
        if (code == ' ' || code == 10) continue;
        if (code == 13) {
          ++row;
          col = 0;
        } else {
          map[row][col++] = code - '0';
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void draw(Graphics2D g2) {
    for (int i = 0; i < map.length; ++i) {
      for (int j = 0; j < map[0].length; ++j) {
        g2.drawImage(
          titles[map[i][j]].image,
          j * gp.SIZE,
          i * gp.SIZE,
          gp.SIZE,
          gp.SIZE,
          null
        );
      }
    }
  }
}
