import java.io.*;
import java.net.*;

public class SimpleClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8080;
        System.out.println("Client connecting to " + host + ":" + port);

        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String message = "I am a Java_Student";
            System.out.println("Sending to server: " + message);
            out.println(message);

            String response = in.readLine();
            if (response != null) {
                System.out.println("Response from server: " + response);
            } else {
                System.out.println("No response from server (null).");
            }

        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
