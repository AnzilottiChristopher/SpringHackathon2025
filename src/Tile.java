import java.awt.*;

public class Tile
{
    private boolean isDirty;
    private boolean isObstacle;
    private boolean isEnemy;

    private Point position;


    public Tile(boolean dirty, boolean obstacle, boolean enemy, int x, int y)
    {
        this.isDirty = dirty;
        this.isObstacle = obstacle;
        this.isEnemy = enemy;
        this.position = new Point(x, y);
    }

    public boolean isDirty() {
        return isDirty;
    }

    public void setDirty(boolean dirty) {
        this.isDirty = dirty;
    }

    public boolean isEnemy() {
        return isEnemy;
    }

    public void setEnemy(boolean enemy) {
        this.isEnemy = enemy;
    }

    public boolean isObstacle() {
        return isObstacle;
    }
    public int getX()
    {
        return position.x;
    }

    public int getY()
    {
        return position.y;
    }
}
