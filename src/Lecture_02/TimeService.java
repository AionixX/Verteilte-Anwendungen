package Lecture_02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeService {

    public static int port = 6001;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            while (true) {
                try {
                	HandleInput(serverSocket);
                }
                catch(IOException e) {
                	e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void HandleInput(ServerSocket serverSocket) throws IOException {
    	
    	Socket socket = serverSocket.accept();

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        
        while(!socket.isClosed()) {
            String input = reader.readLine();
            
            if (input != null) {
                switch (input) {
                case "time":
                    WriteMessage(writer, Clock.time());
                    break;
                case "date":
                    WriteMessage(writer, Clock.date());
                    break;
                default:
                    socket.close();
                    break;
                }
            }
        }
    }

    public static void WriteMessage(BufferedWriter writer, String message) throws IOException {
        writer.write(message);
        writer.newLine();
        writer.flush();
    }
}
