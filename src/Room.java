public class Room {
    private Tile[][] grid; // 2D array of tiles
    private int rows;
    private int cols;

    public Room(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new Tile[rows][cols];

        // Initialize the grid with default tiles
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = new Tile(false, false, false); // All tiles start clean and empty
            }
        }
    }

    // Get a specific Tile
    public Tile getTile(int row, int col) {
        if (isInBounds(row, col)) {
            return grid[row][col];
        }
        return null;
    }

    // Set a specific Tile
    public void setTile(int row, int col, Tile tile) {
        if (isInBounds(row, col)) {
            grid[row][col] = tile;
        }
    }

    // Check if coordinates are inside the grid
    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    // Getters for dimensions
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    // Optional: Reset the whole room
    public void resetRoom() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = new Tile(false, false, false);
            }
        }
    }
}
