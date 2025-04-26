public class Tile
{
    private boolean dirty;
    private boolean obstacle;
    private boolean enemy;
    private String color;

    public Tile(boolean dirty, boolean obstacle, boolean enemy, String color)
    {
        this.dirty = dirty;
        this.obstacle = obstacle;
        this.enemy = enemy;
        this.color = color;
    }

    public boolean isDirty()
    {
        return dirty;
    }

    public boolean isEnemy()
    {
        return enemy;
    }

    public boolean isObstacle()
    {
        return obstacle;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }
}
