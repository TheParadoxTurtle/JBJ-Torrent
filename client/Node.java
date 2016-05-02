// Node.java
//
// Detail the requirements of a node for p2p communcation

public interface Node {
    // Tells server I want to seed this file
    public void seed(String fileName);

    // Asks the server for a list of neighbors for the file
    // titled filename
    //
    // Must return false if:
    //  fails to connect to server
    //  there are no peers with the file
    public boolean getNeighbors(String fileName);

    // Tries to connect with a neighboring node
    //
    // Must return null if:
    //  fails to connect
    //
    //  Otherwise returns bitmap of file
    //
    public boolean[] connect(Node neighbor, String filename);

    // Tells neighbor that this node now has the piece at index
    public void have(Node neighbor, String fileName, int index);

    // Tells neighbor that this node is interested 
    public void interested(Node neighbor, String fileName);

    // Tells neighbor they may request
    public void unchoke(Node neighbor, String fileName);

    // Asks for specific piece
    public void request(Node neighbor, String fileName, int index);

    // Sends specific piece
    public void send(Node neighbor, String fileName, int index);

    // Tells peer no longer need this piece
    public void cancel(Node neighbor, String fileName, int index);
}