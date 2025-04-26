import javax.swing.*;
import java.awt.*;

public class Main {
    private static JFrame frame;
    private static int currentLevel = 1;
    public static void main(String[] args) {
        // Make sure GUI creation is on the Event Dispatch Thread (best practice)
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        // Create the window (a JFrame)
        frame = new JFrame("Vacuum Game Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700); // width x height
        frame.setLayout(new BorderLayout()); // Layout manager for easy placement

        showMainMenu();

        frame.setVisible(true);
    }

    private static void showMainMenu() {
        frame.getContentPane().removeAll();

        // Create a label
        JLabel label = new JLabel("Welcome to Vacuum Cleaner Game!", SwingConstants.CENTER);
        frame.add(label, BorderLayout.NORTH); // Add label to top

        // Create a panel for buttons
        JPanel panel = new JPanel();
        JButton startButton = new JButton("Start Game");
        JButton quitButton = new JButton("Quit");

        panel.add(startButton);
        panel.add(quitButton);

        frame.add(panel, BorderLayout.CENTER); // Add panel to center

        frame.revalidate();
        frame.repaint();

        // Quit button action
        quitButton.addActionListener(e -> System.exit(0));

        // Start Game button action
        startButton.addActionListener(e -> startLevel(currentLevel));
    }

    public static void startLevel(int level) {
        frame.getContentPane().removeAll();
        Room room;
        switch (level) {
            case 1:
                room = Resources.room1;
                break;
            case 2:
                room = Resources.room2;
                break;
            default:
                showWinScreen();
                return;
        }
        room.loadRoom();

        RobotVacuum player = new RobotVacuum(room); // create player

        RoomPanel roomPanel = new RoomPanel(room, player);
        frame.add(roomPanel);

        player.setCurrentTile(room.getTile(room.getStartX(),room.getStartZ()));


        frame.revalidate();
        frame.repaint();
    }

    public static void nextLevel() {
        currentLevel++;
        startLevel(currentLevel);
    }

    private static void showWinScreen() {
        frame.getContentPane().removeAll();

        JLabel winLabel = new JLabel("Congratulations! You have cleaned all the rooms!", SwingConstants.CENTER);
        winLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(winLabel, BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();
    }
}
