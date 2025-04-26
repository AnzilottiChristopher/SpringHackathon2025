import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        // Make sure GUI creation is on the Event Dispatch Thread (best practice)
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        // Create the window (a JFrame)
        JFrame frame = new JFrame("Vacuum Game Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1200); // width x height
        frame.setLayout(new BorderLayout()); // Layout manager for easy placement

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

        // Make the window visible
        frame.setVisible(true);

        // Quit button action
        quitButton.addActionListener(e -> System.exit(0));

        // Start Game button action
        startButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.repaint();
            frame.revalidate();
        
            Room room = new Room(10, 10); // Create a 10x10 room
        
            // Example: Set some tiles
            room.getTile(2, 2).setColor("green");
            room.getTile(5, 5).setColor("brown");
        
            RoomPanel roomPanel = new RoomPanel(room);
            frame.add(roomPanel);
        
            frame.revalidate();
            frame.repaint();
        });
    }
}
