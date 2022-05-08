import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {

        Client client = new Client("127.0.0.15", 1234);

        final String IPD1 = "127.0.0.1";
        final String IPD2 = "127.0.0.2";
        final String IPD3 = "127.0.0.3";
        Integer sentInteger = 0;

        for (int i = 0; i < 100; i++) {
            Random rand = new Random(); //instance of random class
            int upperbound = 3;
            //generate random values from 0-2
            int int_random = rand.nextInt(upperbound);
            switch (int_random) {
                case 0:
                    client.startConnection(IPD1 + sentInteger);
                    sentInteger++;
                    break;
                case 1:
                    client.startConnection(IPD2 + sentInteger);
                    sentInteger++;
                    break;
                case 2:
                    client.startConnection(IPD3 + sentInteger);
                    sentInteger++;
                    break;
                default:
                    break;
            }

        }
        System.out.println("Sent integer is " + sentInteger);
        client.startConnection("Bye");
    }
}
