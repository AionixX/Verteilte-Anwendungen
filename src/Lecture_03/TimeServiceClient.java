package Lecture_03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TimeServiceClient {

	public static String url = "127.0.0.1";
	public static int port = 75;

	public static void main(String[] args) {
		Boolean run = true;
		while (run) {
			System.out.println("Type \'time\' for time and \'date\' for date. Type anything other to close.");

			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				String action = reader.readLine();
				String answer = fetchData(action, url, port);
				System.out.println(answer);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Client closed");
	}

	private static String fetchData(String _action, String _url, int _port) {

		String data = "Not found";

		try (Socket socket = new Socket(_url, _port);) {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			WriteMessage(writer, _action);

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			data = reader.readLine();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

	public static void WriteMessage(BufferedWriter writer, String message) throws IOException {
		writer.write(message);
		writer.newLine();
		writer.flush();
	}

}
