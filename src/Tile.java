import java.awt.*;

public class Tile
{
    private boolean isDirty;
    private boolean isObstacle;
    private boolean isEnemy;
    private boolean isPlayer;

    private Point position;


    //Dirty = brown, Vacuum Sprite = Player, Grey = wall, Dog Sprite = enemy
    private String color;

    public Tile(boolean dirty, boolean obstacle, boolean enemy, int x, int y)
    {
        this.isDirty = dirty;
        this.isObstacle = obstacle;
        this.isEnemy = enemy;
        if (isDirty())
        {
            this.color = "brown";
        }
        else if (isObstacle())
        {
            this.color = "grey";
        }
        else if (isPlayer)
        {
            this.color = "spritePlayer";
        }
        else if (isEnemy())
        {
            this.color = "spriteDog";
        } else
        {
            this.color = "white";
        }
        this.position = new Point(x, y);
    }

    public boolean isDirty()
    {
        return isDirty;
    }

    public boolean isEnemy()
    {
        return isEnemy;
    }

    public boolean isObstacle()
    {
        return isObstacle;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
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
