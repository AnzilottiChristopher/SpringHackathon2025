import javax.swing.*;
import java.awt.*;

public class RoomPanel extends JPanel {
    private Room room;
    private int tileSize = 64; // Size of each tile in pixels

    public RoomPanel(Room room) {
        this.room = room;
        setPreferredSize(new Dimension(room.getCols() * tileSize, room.getRows() * tileSize));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < room.getRows(); row++) {
            for (int col = 0; col < room.getCols(); col++) {
                Tile tile = room.getTile(row, col);

                // Determine tile color
                Color color = getColorFromString(tile.getColor());

                // Draw tile background
                g.setColor(color);
                g.fillRect(col * tileSize, row * tileSize, tileSize, tileSize);

                // Draw a border
                g.setColor(Color.BLACK);
                g.drawRect(col * tileSize, row * tileSize, tileSize, tileSize);

                // Optional: mark enemies, obstacles, dirty tiles with symbols
                g.setColor(Color.BLACK);
                if (tile.isObstacle()) {
                    g.drawString("X", col * tileSize + tileSize / 2 - 5, row * tileSize + tileSize / 2 + 5);
                } else if (tile.isEnemy()) {
                    g.drawString("E", col * tileSize + tileSize / 2 - 5, row * tileSize + tileSize / 2 + 5);
                } else if (tile.isDirty()) {
                    g.drawString("*", col * tileSize + tileSize / 2 - 5, row * tileSize + tileSize / 2 + 5);
                }
            }
        }
    }

    private Color getColorFromString(String colorName) {
        // A simple helper to map strings to actual colors
        switch (colorName.toLowerCase()) {
            case "white":
                return Color.WHITE;
            case "gray":
                return Color.GRAY;
            case "green":
                return Color.GREEN;
            case "brown":
                return new Color(139, 69, 19); // brown RGB
            default:
                return Color.LIGHT_GRAY; // default if unknown
        }
    }
}
