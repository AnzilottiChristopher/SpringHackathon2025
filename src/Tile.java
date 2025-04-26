public class Tile
{
    private boolean isDirty;
    private boolean isObstacle;
    private boolean isEnemy;
    private boolean isPlayer;


    //Dirty = brown, Vacuum Sprite = Player, Grey = wall, Dog Sprite = enemy
    private String color;

    public Tile(boolean dirty, boolean obstacle, boolean enemy)
    {
        this.isDirty = dirty;
        this.isObstacle = obstacle;
        this.isEnemy = enemy;
    }

    public boolean isDirty()
    {
        return isDirty;
    }

    public void setDirty(boolean dirty) {
        this.isDirty = dirty;
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
}
