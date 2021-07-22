package Lecture_03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import Lecture_02.Clock;

public class TimeServiceMultithreaded extends Thread {

	public static int port = 75;

	private Socket socket;

	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(port);) {
			while (true) {
				try {
					Socket socket = serverSocket.accept();
					new TimeServiceMultithreaded(socket);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public TimeServiceMultithreaded(Socket _socket) {
		this.socket = _socket;
		this.start();
	}

	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			while (true) {
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
						return;
					}
				} else {
					socket.close();
					return;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void HandleInput(ServerSocket serverSocket) throws IOException {

	}

	public static void WriteMessage(BufferedWriter writer, String message) throws IOException {
		writer.write(message);
		writer.newLine();
		writer.flush();
	}

}
