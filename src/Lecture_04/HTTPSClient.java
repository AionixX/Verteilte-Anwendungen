package Lecture_04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class HTTPSClient {

	public static void main(String[] args) {
		String url = "https://www.bundestag.de/presse";
		try {
			if (urlExists(url)) {
				get(url);
				System.out.println(getContentAsString(url));
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	private static void get(String _url) throws MalformedURLException {
		URL url = new URL(_url);

		String host = url.getHost();
		int port = url.getPort() == -1 ? 443 : url.getPort();
		String path = url.getFile();
		String action = "GET " + path + " HTTP/1.0";

		System.out.println(action);
		System.out.println("Host: " + host);

		SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		try (SSLSocket socket = (SSLSocket) factory.createSocket(host, port);) {

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			WriteMessage(writer, action, host);

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String data;
			while ((data = reader.readLine()) != null) {
				System.out.println(data);
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getContentAsString(String _url) throws MalformedURLException {
		URL url = new URL(_url);

		String host = url.getHost();
		int port = url.getPort() == -1 ? 443 : url.getPort();
		String path = url.getFile();
		String action = "GET " + path + " HTTP/1.0";

		System.out.println(action);
		System.out.println("Host: " + host);

		SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		try (SSLSocket socket = (SSLSocket) factory.createSocket(host, port);) {

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			WriteMessage(writer, action, host);

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			return getContent(reader);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Boolean urlExists(String _url) throws MalformedURLException {
		URL url = new URL(_url);

		String host = url.getHost();
		String path = url.getFile();
		int port = url.getPort() == -1 ? 443 : url.getPort();
		String action = "HEAD " + path + " HTTP/1.1";

		SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		try (SSLSocket socket = (SSLSocket) factory.createSocket(host, port);) {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			WriteMessage(writer, action, host);

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String data = reader.readLine();
			if (data.equals("HTTP/1.1 200 OK"))
				return true;
			else
				return false;
		} catch (UnknownHostException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
	}
	
	private static String getContent(BufferedReader _reader) throws IOException {
		while (!_reader.readLine().isEmpty());

		StringBuffer data = new StringBuffer();
		String tmp = "";
		while ((tmp = _reader.readLine()) != null) {
			data.append(tmp);
		}
		return data.toString();
	}
	
	private static void WriteMessage(BufferedWriter _writer, String _action, String _host) throws IOException {
		_writer.write(_action);
		_writer.newLine();
		_writer.write("Host: " + _host);
		_writer.newLine();
		_writer.newLine();
		_writer.flush();
	}
}
