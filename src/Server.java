import java.net.*;

public class Server {

    public static void main(String args[]) throws Exception {
        // Default port number we are going to use for receiving messages
        int receivePort = 8002;
        // Default port number we are going to use for sending responses
        int sendPort = 8003;

        // Create a MulticastSocket for receiving messages
        MulticastSocket serverReceiveSocket = new MulticastSocket(receivePort);
        System.out.println("MulticastSocket for receiving is created at port " + receivePort);

        // Determine the IP address of a host, given the host name
        InetAddress receiveGroup = InetAddress.getByName("225.4.5.6");

        // Join the multicast group for receiving messages
        serverReceiveSocket.joinGroup(receiveGroup);
        System.out.println("joinGroup method is called for receiving...");

        // Create a DatagramSocket for sending responses
        DatagramSocket serverSendSocket = new DatagramSocket();
        System.out.println("DatagramSocket for sending is created...");

        boolean infinite = true;

        // Continually receives data and processes them
        while (infinite) {
            try {
                byte buf[] = new byte[1024];
                DatagramPacket data = new DatagramPacket(buf, buf.length);
                serverReceiveSocket.receive(data);
                String msg = new String(data.getData()).trim();
                System.out.println("Message received from client = " + msg);

                // Process the message and calculate the result
                String result = countSumOrProduct(msg);
                System.out.println("Sending result to client: " + result);

                // Send the result back to the client using the client's address and port
                DatagramPacket resultPacket = new DatagramPacket(result.getBytes(), result.length(), data.getAddress(), sendPort);
                serverSendSocket.send(resultPacket);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        serverReceiveSocket.close();
        serverSendSocket.close();
    }

    // Counts the sum or product of the message from the client
    static String countSumOrProduct(String msgFromClient) {

        }
    }
}
