import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends RobotVacuum {

    private List<Tile> currentPath;
    private int pathStep;
    private Tile tile;

    private int behaviorCounter = 0; // Counts moves under current behavior
    private boolean useAStar = true; // true = use A*, false = wander randomly
    private final Random random = new Random();
    private int randomMoveCounter = 0; // Counter for random moves

    public Enemy(Room room, Tile tile) {
        super(room);
        this.tile = tile;
        setPosition(new Point(tile.getY(), tile.getX()));
        setCurrentTile(tile);
        currentPath = null;
        pathStep = 0;
        findNewPath();
    }

    public void moveEnemy(Tile avoidTile) {
        if (useAStar) {
            moveEnemyAStar(avoidTile);
        } else {
            moveEnemyRandom(avoidTile);
        }

    }
    
    private void moveEnemyAStar(Tile avoidTile) {
        if (currentPath == null || pathStep >= currentPath.size()) {
            findNewPath();
        }
    
        if (currentPath != null && pathStep < currentPath.size()) {
            Tile nextTile = currentPath.get(pathStep);
    
            // Check if the next tile is the one we need to avoid
            if (nextTile.equals(avoidTile)) {
                // If the next tile is the avoidTile, stay at the current tile
                return;
            }
    
            // Check if the next tile is too close to a dirty tile (within 1 space)
            if (nextTile.isDirty() || isNextTileCloseToDirty(nextTile)) {
                // If the next tile is dirty or too close to a dirty tile, move randomly
                moveEnemyRandom(avoidTile);
                randomMoveCounter++; // Increment the random move counter
                return;
            }
    
            // Clear current tile
            this.tile.setEnemy(false);
    
            // Move to next tile
            setPosition(new Point(nextTile.getY(), nextTile.getX()));
    
            // Update tile reference
            this.tile = nextTile;
    
            // Mark new tile as occupied by enemy
            this.tile.setEnemy(true);
    
            setCurrentTile(this.tile);
    
            pathStep++;
            increaseMoves();
        }
    }
    
    private void moveEnemyRandom(Tile avoidTile) {
        List<Tile> neighbors = getNeighbors(tile);
        List<Tile> availableMoves = new ArrayList<>();
    
        for (Tile neighbor : neighbors) {
            if (!neighbor.isEnemy() && !neighbor.isObstacle() && !neighbor.isDirty() && !neighbor.equals(avoidTile)) {
                availableMoves.add(neighbor);
            }
        }
    
        if (!availableMoves.isEmpty()) {
            Tile nextTile = availableMoves.get(random.nextInt(availableMoves.size()));
    
            // Clear current tile
            this.tile.setEnemy(false);
    
            // Move to next tile
            setPosition(new Point(nextTile.getY(), nextTile.getX()));
    
            // Update tile reference
            this.tile = nextTile;
    
            // Mark new tile as occupied by enemy
            this.tile.setEnemy(true);
    
            setCurrentTile(this.tile);
            increaseMoves();
        }
    
        // After 2 random moves, switch back to A*
        if (randomMoveCounter >= 2) {
            randomMoveCounter = 0;
            useAStar = true; // Switch back to A* after 2 random moves
            findNewPath(); // Recalculate the path
        }
    }
    

    private boolean isNextTileCloseToDirty(Tile nextTile) {
        // Check the adjacent tiles for dirty tiles
        List<Tile> neighbors = getNeighbors(nextTile);
        for (Tile neighbor : neighbors) {
            if (neighbor.isDirty()) {
                return true; // If there's a dirty tile next to the tile, return true
            }
        }
        return false; // No dirty tiles next to the tile
    }

    private void findNewPath() {
        Tile closestDirt = findClosestDirty();
        if (closestDirt != null) {
            currentPath = aStar(closestDirt);
            // printDirtPath(currentPath);
            pathStep = 0;
        } else {
            currentPath = null; // No dirt left
        }
    }
}
