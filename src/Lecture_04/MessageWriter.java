package Lecture_04;

import java.io.BufferedWriter;
import java.io.IOException;

public class MessageWriter {
	public static void WriteMessage(BufferedWriter writer, String message) throws IOException {
		writer.write(message);
		writer.newLine();
		writer.flush();
	}
}
