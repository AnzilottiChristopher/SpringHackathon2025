import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class RoomPanel extends JPanel {
    private Room room;
    private int tileSize = 64; // Size of each tile in pixels
    private RobotVacuum robot;

    private List<Enemy> enemies = new ArrayList<>();

    public RoomPanel(Room room, RobotVacuum robot) {
        this.room = room;
        this.robot = robot;

        for (int y = 0; y < room.getRows(); y++) {
            for (int x = 0; x < room.getCols(); x++) {
                Tile tile = room.getTile(x, y);

                // If the tile is marked as an enemy starting point
                if (tile.isEnemy()) {
                    Enemy enemy = new Enemy(room, tile); // create an enemy at this tile
                    enemies.add(enemy);
                }
            }
        }

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

                // Draw tile background
                if (tile.isObstacle()) {
                    g.setColor(Color.GRAY);
                } else {
                    g.setColor(Color.WHITE);
                }
                
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
                } 

                 else if (tile.isDirty())
                {
                    // dirty image
                    try {
                        g.drawImage(ImageIO.read(Resources.dirt), col * tileSize, row * tileSize, tileSize, tileSize, this);
                    } catch (Exception e) {
                        g.drawString("*", col * tileSize + tileSize / 2 - 5, row * tileSize + tileSize / 2 + 5);
                    }
                }
                
                // draw enemies
                // Draw the enemies
                for (Enemy enemy : enemies) {
                    if (enemy != null) {
                        Point pos = enemy.getPosition();
                        try {
                            g.drawImage(ImageIO.read(Resources.dog), pos.x * tileSize, pos.y * tileSize, tileSize, tileSize, this);
                        } catch (Exception e) {
                            g.setColor(Color.RED);
                            g.drawString("E", pos.x * tileSize + tileSize / 2 - 5, pos.y * tileSize + tileSize / 2 + 5);
                        }
                    }
                }

                // draw the robot 
                if (robot != null)
                {
                    g.drawImage(robot.getSprite(), robot.getPosition().x * tileSize, robot.getPosition().y * tileSize, tileSize, tileSize, this);
                }
            }
        }
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

                // if it's a dirty tile, clean it
                if (nextTile.isDirty()) {
                    robot.cleanDirt();
                    nextTile.setDirty(false);
                }
                robot.setCurrentTile(nextTile); // update tile
                // robot.updatePathToClosestDirty(); // update path if a star is being used


                for (Enemy enemy : enemies) {
                    enemy.moveEnemy(nextTile);
                }
                repaint();

                // if robot dirt cleaned and room dirt amount is the same, go to next level
                if (robot.getDirtCleaned() == room.getDirt()) {
                    Main.nextLevel();
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


}
