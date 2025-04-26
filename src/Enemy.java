import java.util.List;
import java.util.Random;
import java.awt.Point;

public class Enemy extends RobotVacuum {

    private List<Tile> currentPath;
    private int pathStep;
    private int stepsBeforeForget;
    private boolean wandering;
    private Random random;
    private Tile tile;

    public Enemy(Room room, Tile tile) {
        super(room);
        this.tile = tile;
        currentPath = null;
        pathStep = 0;
        random = new Random();
        stepsBeforeForget = 5 + random.nextInt(5); // Move 5–9 steps before forgetting
        wandering = false;
    }

    public void moveEnemy() {
        if (wandering) {
            wanderRandomly();
            stepsBeforeForget--;
            if (stepsBeforeForget <= 0) {
                wandering = false;
                stepsBeforeForget = 5 + random.nextInt(5);
                findNewPath();
            }
        } else {
            if (currentPath == null || pathStep >= stepsBeforeForget || pathStep >= currentPath.size()) {
                wandering = true;
                stepsBeforeForget = 3 + random.nextInt(4); // Wander for 3–6 moves
            } else {
                followPath();
            }
        }
    }

    private void followPath() {
        if (currentPath != null && pathStep < currentPath.size()) {
            Tile nextTile = currentPath.get(pathStep);
            setPosition(new Point(nextTile.getX(), nextTile.getY()));
            setCurrentTile(nextTile);
            pathStep++;
            increaseMoves();
        }
    }

    private void findNewPath() {
        Tile closestDirt = findClosestDirty();
        if (closestDirt != null) {
            currentPath = aStar(closestDirt);
            pathStep = 0;
        }
    }

    private void wanderRandomly() {
        List<Tile> neighbors = getNeighbors(getCurrentTile());
        if (!neighbors.isEmpty()) {
            Tile randomNeighbor = neighbors.get(random.nextInt(neighbors.size()));
            setPosition(new Point(randomNeighbor.getX(), randomNeighbor.getY()));
            setCurrentTile(randomNeighbor);
            increaseMoves();
        }
    }

    private Tile getCurrentTile() {
        return room.getTile(getPosition().x, getPosition().y);
    }
}
