import java.awt.*;

public class RobotVacuum
{
    private int movesTaken;
    private Point position;
    private int dirtCleaned;

    public RobotVacuum()
    {
        movesTaken = 0;
        position = new Point(0, 0);
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

    public void cleanDirt() {
        dirtCleaned++;
    }

    public int getDirtCleaned() {
        return dirtCleaned;
    }

    public Point aStar()
    {
        return null;
    }
}
