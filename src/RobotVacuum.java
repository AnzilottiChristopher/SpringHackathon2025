import java.awt.*;

public class RobotVacuum
{
    private int movesTaken;
    private Point position;

    public RobotVacuum()
    {
        movesTaken = 0;
        position = new Point(0, 0);
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
