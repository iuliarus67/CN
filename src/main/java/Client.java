import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private String ipAddress;
    private Integer portNumber;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public Client(String ipAddress, Integer portNumber) {
        this.ipAddress = ipAddress;
        this.portNumber = portNumber;
    }

    public void startConnection(String input) throws IOException {

        clientSocket = new Socket(ipAddress, portNumber);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out.println(input);
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
