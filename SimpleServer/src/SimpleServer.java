import java.io.*;
import java.net.*;

public class SimpleServer {
    public static void main(String[] args) {
        int port = 8080;
        System.out.println("Starting server on port " + port + " ...");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Waiting for a connection...");

            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                System.out.println("New connection accepted from " + clientSocket.getInetAddress().getHostAddress()
                        + ":" + clientSocket.getPort());

                String line = in.readLine();
                if (line != null) {
                    System.out.println("Received from client (port " + clientSocket.getPort() + "): " + line);
                    out.println(String.format("Hi %s, your port is %d", line, clientSocket.getPort()));
                } else {
                    System.out.println("Client sent no data (readLine returned null).");
                }
            }

            System.out.println("Connection handled. Server will now exit.");
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
