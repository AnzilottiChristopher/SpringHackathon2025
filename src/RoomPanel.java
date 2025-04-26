import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RoomPanel extends JPanel {
    private Room room;
    private int tileSize = 64; // Size of each tile in pixels
    private RobotVacuum robot;

    public RoomPanel(Room room, RobotVacuum robot) {
        this.room = room;
        this.robot = robot;
        setPreferredSize(new Dimension(room.getCols() * tileSize, room.getRows() * tileSize));
        setupKeyBindings();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < room.getRows(); row++) {
            for (int col = 0; col < room.getCols(); col++)
            {
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
                if (tile.isObstacle())
                {
                    // obstacle
                    //g.drawString("X", col * tileSize + tileSize / 2 - 5, row * tileSize + tileSize / 2 + 5);
                } else if (tile.isEnemy())
                {
                    // enemy
                    try {
                        g.drawImage(ImageIO.read(Resources.dog), col * tileSize, row * tileSize, tileSize, tileSize, this);
                    } catch (Exception e) {
                        g.drawString("E", col * tileSize + tileSize / 2 - 5, row * tileSize + tileSize / 2 + 5);
                    }
                } else if (tile.isDirty())
                {
                    // dirty image
                    try {
                        g.drawImage(ImageIO.read(Resources.dirt), col * tileSize, row * tileSize, tileSize, tileSize, this);
                    } catch (Exception e) {
                        g.drawString("*", col * tileSize + tileSize / 2 - 5, row * tileSize + tileSize / 2 + 5);
                    }
                }

                // draw the robot 
                if (robot != null)
                {
                    g.drawImage(robot.getSprite(), robot.getPosition().x * tileSize, robot.getPosition().y * tileSize, tileSize, tileSize, this);
                } else
                {
                    g.setColor(Color.BLUE);
                    g.fillOval(robot.getPosition().x * tileSize, robot.getPosition().y * tileSize, tileSize, tileSize);
                }
            }
        }
    }

    private void setupKeyBindings() {
        // Get input and action maps
        InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getActionMap();
    
        // Move Up (W or UP)
        inputMap.put(KeyStroke.getKeyStroke("W"), "moveUp");
        inputMap.put(KeyStroke.getKeyStroke("UP"), "moveUp");
        actionMap.put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveRobot(0, -1);
            }
        });
    
        // Move Down (S or DOWN)
        inputMap.put(KeyStroke.getKeyStroke("S"), "moveDown");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
        actionMap.put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveRobot(0, 1);
            }
        });
    
        // Move Left (A or LEFT)
        inputMap.put(KeyStroke.getKeyStroke("A"), "moveLeft");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        actionMap.put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveRobot(-1, 0);
            }
        });
    
        // Move Right (D or RIGHT)
        inputMap.put(KeyStroke.getKeyStroke("D"), "moveRight");
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
        actionMap.put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveRobot(1, 0);
            }
        });
    }

    private void moveRobot(int dx, int dy) {
        Point pos = robot.getPosition();
        int newX = pos.x + dx;
        int newY = pos.y + dy;
    
        // Check bounds
        if (newX >= 0 && newX < room.getCols() && newY >= 0 && newY < room.getRows()) {
    
            // Get the tile BEFORE moving
            Tile nextTile = room.getTile(newY, newX); // NOTE: newY first, then newX
            
            // If tile is not obstacle or enemy, move
            if (!(nextTile.isObstacle() || nextTile.isEnemy())) {
                robot.setPosition(new Point(newX, newY));
                robot.increaseMoves();
                repaint();
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
            default:
                return Color.LIGHT_GRAY; // default if unknown
        }
    }
}
