import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class RobotVacuum
{
    private int movesTaken;
    private Point position;
    private BufferedImage sprite;
    private Tile currentTile;

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

    public void setCurrentTile(Tile t)
    {
        currentTile = t;
    }

    public int heuristic(Tile start, Tile goal)
    {
        return Math.abs(start.getX() - goal.getX()) + Math.abs(start.getY() - goal.getY());
    }

    public Point aStar(Tile GoalTile)
    {
        PriorityQueue<Node> openList = new PriorityQueue<>();
        Set<Node> closedList = new HashSet<>();

       // Node startNode = new Node(currentTile)
        return null;
    }
}
