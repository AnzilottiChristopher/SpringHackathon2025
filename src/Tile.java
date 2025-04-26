public class Tile
{
    private boolean isDirty;
    private boolean isObstacle;
    private boolean isEnemy;

    public Tile(boolean dirty, boolean obstacle, boolean enemy) {
        this.isDirty = dirty;
        this.isObstacle = obstacle;
        this.isEnemy = enemy;
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

    public boolean isObstacle() {
        return isObstacle;
    }
}
