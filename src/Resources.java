import java.io.File;

public class Resources {
    static final File vacuum = new File("resources/vacuum.png");

    static final File cat = new File("resources/cat.png");
    static final File dog = new File("resources/dog.png");
    
    static final File dirt = new File("resources/dirt.png");
    static final File poop = new File("resources/poop.png");

    // Rooms
    static final Room room1 = new Room("room1.txt", 10, 10, 0, 0);
    static final Room room2 = new Room("room2.txt", 10, 10, 1, 1);
}
