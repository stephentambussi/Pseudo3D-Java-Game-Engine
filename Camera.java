import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Camera implements KeyListener {
  public double xPos, yPos, xDir, yDir, xPlane, yPlane;
  public boolean left, right, forward, back, fire, reload;
  // move and turn speed
  public final double MOVE_SPEED = .08;
  public final double ROTATION_SPEED = .05;

  public Camera(double x, double y, double xd, double yd, double xp, double yp) {
    xPos = x;// initial value sets starting point on map
    yPos = y;// initial value sets starting point on map
    xDir = xd;
    yDir = yd;
    xPlane = xp;
    yPlane = yp;// effectively sets FOV
  }

  public void keyPressed(KeyEvent key) {
    if ((key.getKeyCode() == KeyEvent.VK_LEFT))
      left = true;
    if ((key.getKeyCode() == KeyEvent.VK_RIGHT))
      right = true;
    if ((key.getKeyCode() == KeyEvent.VK_UP))
      forward = true;
    if ((key.getKeyCode() == KeyEvent.VK_DOWN))
      back = true;
    if ((key.getKeyCode() == KeyEvent.VK_SPACE))
      fire = true;
    if ((key.getKeyCode() == KeyEvent.VK_R))
      reload = true;
  }

  public void keyReleased(KeyEvent key) {
    if ((key.getKeyCode() == KeyEvent.VK_LEFT))
      left = false;
    if ((key.getKeyCode() == KeyEvent.VK_RIGHT))
      right = false;
    if ((key.getKeyCode() == KeyEvent.VK_UP))
      forward = false;
    if ((key.getKeyCode() == KeyEvent.VK_DOWN))
      back = false;
    if ((key.getKeyCode() == KeyEvent.VK_SPACE))
      fire = false;
    if ((key.getKeyCode() == KeyEvent.VK_R))
      reload = false;
  }

  public void update(int[][] map) {
    if (forward) {
      if (map[(int) (xPos + xDir * MOVE_SPEED)][(int) yPos] == 0)
        xPos += xDir * MOVE_SPEED;
      if (map[(int) xPos][(int) (yPos + yDir * MOVE_SPEED)] == 0)
        yPos += yDir * MOVE_SPEED;
    }
    if (back) {
      if (map[(int) (xPos - xDir * MOVE_SPEED)][(int) yPos] == 0)
        xPos -= xDir * MOVE_SPEED;
      if (map[(int) xPos][(int) (yPos - yDir * MOVE_SPEED)] == 0)
        yPos -= yDir * MOVE_SPEED;
    }
    if (right) {
      double oldxDir = xDir;
      xDir = xDir * Math.cos(-ROTATION_SPEED) - yDir * Math.sin(-ROTATION_SPEED);
      yDir = oldxDir * Math.sin(-ROTATION_SPEED) + yDir * Math.cos(-ROTATION_SPEED);
      double oldxPlane = xPlane;
      xPlane = xPlane * Math.cos(-ROTATION_SPEED) - yPlane * Math.sin(-ROTATION_SPEED);
      yPlane = oldxPlane * Math.sin(-ROTATION_SPEED) + yPlane * Math.cos(-ROTATION_SPEED);
    }
    if (left) {
      double oldxDir = xDir;
      xDir = xDir * Math.cos(ROTATION_SPEED) - yDir * Math.sin(ROTATION_SPEED);
      yDir = oldxDir * Math.sin(ROTATION_SPEED) + yDir * Math.cos(ROTATION_SPEED);
      double oldxPlane = xPlane;
      xPlane = xPlane * Math.cos(ROTATION_SPEED) - yPlane * Math.sin(ROTATION_SPEED);
      yPlane = oldxPlane * Math.sin(ROTATION_SPEED) + yPlane * Math.cos(ROTATION_SPEED);
    }
  }

  // blaster and reload sensor
  public boolean blaster(boolean firestate) {
    if (fire == true) {
      firestate = true;
    } else {
      firestate = false;
    }
    return firestate;
  }

  public boolean reload(boolean reloadstate) {
    if (reload == true) {
      reloadstate = true;
    } else {
      reloadstate = false;
    }
    return reloadstate;
  }

  public void keyTyped(KeyEvent arg0) {

  }
}
