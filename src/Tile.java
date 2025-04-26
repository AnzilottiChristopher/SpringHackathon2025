public class Tile
{
    private boolean dirty;
    private boolean obstacle;
    private boolean enemy;
    private boolean isPlayer;


    //Dirty = brown, Vacuum Sprite = Player, Grey = wall, Dog Sprite = enemy
    private String color;

    public Tile(boolean dirty, boolean obstacle, boolean enemy)
    {
        this.dirty = dirty;
        this.obstacle = obstacle;
        this.enemy = enemy;
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
