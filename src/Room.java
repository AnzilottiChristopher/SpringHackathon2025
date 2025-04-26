import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private Tile[][] grid; // 2D array of tiles
    private String filename;
    private int rows;
    private int cols;
    private int dirtCount;

    // Constructor where you specify size
    public Room(String filename, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.filename = "rooms/" + filename;
        grid = new Tile[rows][cols];
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

    // get number of dirt
    public int getDirt() {
        return dirtCount;
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
                grid[r][c] = new Tile(false, false, false, r, c);
            }
        }
    }

     public void loadRoom() {
        List<String> lines = new ArrayList<>();
        dirtCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null && lines.size() < rows) {
                lines.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int r = 0; r < rows; r++) {
            if (r >= lines.size()) {
                // If the file has fewer rows than expected, fill with empty tiles
                for (int c = 0; c < cols; c++) {
                    grid[r][c] = new Tile(false, false, false, r, c);
                }
                continue;
            }

            String[] tokens = lines.get(r).split("\\s+");
            for (int c = 0; c < cols; c++) {
                if (c >= tokens.length) {
                    // If line has fewer columns, fill with empty tiles
                    grid[r][c] = new Tile(false, false, false, r, c);
                } else {
                    int value = Integer.parseInt(tokens[c]);
                    switch (value) {
                        case 0:
                            grid[r][c] = new Tile(false, false, false, r ,c); // Clean floor
                            break;
                        case 1:
                            grid[r][c] = new Tile(false, true, false, r, c); // Obstacle
                            break;
                        case 2:
                            grid[r][c] = new Tile(false, false, true, r, c); // Enemy
                            break;
                        case 3:
                            grid[r][c] = new Tile(true, false, false, r, c); // Dirty
                            dirtCount++;
                            break;
                        default:
                            grid[r][c] = new Tile(false, false, false, r, c); // Default clean
                            break;
                    }
                }
            }
        }
    }



}
