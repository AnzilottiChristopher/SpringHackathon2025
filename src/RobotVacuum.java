import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RobotVacuum
{
    private int movesTaken;
    private Point position;
    private BufferedImage sprite;

    public RobotVacuum()
    {
        movesTaken = 0;
        position = new Point(0, 0);

        try {
            sprite = ImageIO.read(new File("resources/vacuum.png")); // or wherever your robot image is
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getSprite()
    {
        return sprite;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point p)
    {
        position = p;
    }

    public void setX(Point p)
    {
        position.x = p.x;
    }

    public void setY(Point p)
    {
        position.y = p.y;
    }
    public int getMovesTaken()
    {
        return movesTaken;
    }
    public void increaseMoves()
    {
        movesTaken++;
    }

    public Point aStar()
    {
        return null;
    }
}
