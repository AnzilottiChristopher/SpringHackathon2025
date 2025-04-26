import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class RobotVacuum
{
    private int movesTaken;
    private Point position;
    private BufferedImage sprite;
    private Tile currentTile;
    private int dirtCleaned;
    private Room room;

    public RobotVacuum(Room room)
    {
        movesTaken = 0;
        position = new Point(0, 0);
        this.room = room;

        try {
            sprite = ImageIO.read(Resources.vacuum); // or wherever your robot image is
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
        this.currentTile = t;
    }

    public int heuristic(Tile start, Tile goal)
    {
        return Math.abs(start.getX() - goal.getX()) + Math.abs(start.getY() - goal.getY());
    }
    public void cleanDirt() {
        dirtCleaned++;
    }

    public int getDirtCleaned() {
        return dirtCleaned;
    }

    public void resetDirtCleaned() {
        dirtCleaned = 0;
    }

    public List<Tile> aStar(Tile goalTile)
    {
        PriorityQueue<Node> openList = new PriorityQueue<>();
        Set<String> closedList = new HashSet<>();
        Node startNode = new Node(currentTile, 0, heuristic(currentTile, goalTile), null);
        openList.add(startNode);

        while(!openList.isEmpty())
        {
            Node currentNode = openList.poll();

            if (currentNode.tile.equals(goalTile))
            {
                return reconstructPath(currentNode);
            }
            closedList.add(currentNode.toString());

            for(Tile neighbor : getNeighbors(currentNode.tile))
            {
                if (neighbor.isEnemy() || neighbor.isObstacle() || closedList.contains(neighbor.toString()))
                {
                    continue;
                }
                int newGCost = currentNode.gCost + 1;
                Node neighborNode = new Node(neighbor, newGCost, heuristic(neighbor, goalTile), currentNode);
                openList.add(neighborNode);
            }

        }
        return new ArrayList<>();
    }


    // Reconstruct the path from goal to start
    public List<Tile> reconstructPath(Node goalNode) {
        List<Tile> path = new ArrayList<>();
        Node currentNode = goalNode;

        while (currentNode != null) {
            path.add(0, currentNode.tile); // Add at the beginning of the list
            currentNode = currentNode.parent;
        }

        return path;
    }

    private List<Tile> getNeighbors(Tile tile) {
        List<Tile> neighbors = new ArrayList<>();

        if (tile.getX() > 0) neighbors.add(room.getTile(tile.getX() - 1, tile.getY()));
        if (tile.getX() < room.getCols() - 1) neighbors.add(room.getTile(tile.getX() + 1, tile.getY()));
        if (tile.getY() > 0) neighbors.add(room.getTile(tile.getX(), tile.getY() - 1));
        if (tile.getY() < room.getRows() - 1) neighbors.add(room.getTile(tile.getX(), tile.getY() + 1));

        return neighbors;
    }
}
