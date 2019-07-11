package com.topica.socket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	static final int PORT_SERVER = 15064;
	static final String FILE_OUTPUT = "output.mp4";
	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		ServerSocket servsock = new ServerSocket(PORT_SERVER);
		Socket sock = servsock.accept();

		InputStream fileInputStream = sock.getInputStream();
		FileOutputStream fileOutputStream = new FileOutputStream(FILE_OUTPUT);
		byte[] buffer = new byte[64 * 1024];
		int bytesRead = 0;

		while ((bytesRead = fileInputStream.read(buffer)) != -1)
			fileOutputStream.write(buffer, 0, bytesRead);
		sock.close();
		fileOutputStream.close();

	}
}
