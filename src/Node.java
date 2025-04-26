public class Node implements Comparable<Node> {
    Tile tile;         // Tile reference instead of x, y
    int gCost;         // Actual moves taken to reach this tile
    int hCost;         // Heuristic estimate to goal
    Node parent;       // Where you came from

    public Node(Tile tile, int gCost, int hCost, Node parent) {
        this.tile = tile;
        this.gCost = gCost;
        this.hCost = hCost;
        this.parent = parent;
    }

    public int getFCost() {
        return gCost + hCost;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.getFCost(), other.getFCost());
    }
}
