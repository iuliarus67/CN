import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class RelayNode {

    private String ipAddress;
    private Integer portNumber;
    private ServerSocket serverSocket1;
    private Socket clientSocket;
    private Client moveForwardClient;
    private PrintWriter out;
    private BufferedReader in;

    public RelayNode(String ipAddress, Integer portNumber) throws IOException {
        this.ipAddress = ipAddress;
        this.portNumber = portNumber;
        serverSocket1 = new ServerSocket(portNumber);
    }

    public void start() throws IOException {

        while (true) {
            clientSocket = serverSocket1.accept(); // the server waits for a client to make a connection request. It blocks here until a client requests a connection

            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String message = in.readLine();
            if (message.equalsIgnoreCase("bye")) {
                break;
            }
            if (message.substring(0, 9).equalsIgnoreCase(ipAddress)) {
                System.out.println("Message stopped at " + message.substring(0, 9));
            } else {
                int p = portNumber + 1;
                int c = Integer.parseInt(ipAddress.charAt(8) + "");
                c++;
                String copy = ipAddress.substring(0, 8) + String.valueOf(c);
                System.out.println("Go on to " + p + " " + copy + " from " + ipAddress);

                moveForwardClient = new Client(copy, p);
                moveForwardClient.startConnection(message);
            }
        }
    }


    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket1.close();
    }

}
