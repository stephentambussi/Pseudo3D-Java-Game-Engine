import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {
  public int[] pixels;
  private String loc;
  public final int SIZE;

  public Texture(String location, int size) {
    loc = location;
    SIZE = size;
    pixels = new int[SIZE * SIZE];
    load();
  }

  private void load() {
    try {
      // System.out.println(new File(loc).getAbsolutePath());
      BufferedImage image = ImageIO.read(new File(loc));
      int w = image.getWidth();
      int h = image.getHeight();
      image.getRGB(0, 0, w, h, pixels, 0, w);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static Texture vent = new Texture("textures/vent.png", 64);
  public static Texture metal = new Texture("textures/metal.png", 64);
  public static Texture window = new Texture("textures/window.png", 64);
  public static Texture console = new Texture("textures/console.png", 64);
  public static Texture alienGrunt = new Texture("textures/AlienGrunt.png", 64);
}
