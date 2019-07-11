package com.topica.socket;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	static final String LOCALHOST = "localhost";
	static final int PORT_SERVER = 15064;
	static final int SUB_FILE_SIZE = 64 * 1024;
	static final String FILE_INPUT = "1G.mp4";

	public static void main(String[] args) throws Exception {
		Socket sock = new Socket(LOCALHOST, PORT_SERVER);
		FileInputStream fileInputStream = new FileInputStream(FILE_INPUT);
		OutputStream out = sock.getOutputStream();

		byte[] buffer = new byte[SUB_FILE_SIZE];
		int bytesRead = 0;
		long totalSent = 0;
		long startTime = System.currentTimeMillis();
		while ((bytesRead = fileInputStream.read(buffer)) != -1) {
			if (bytesRead > 0) {
				out.write(buffer, 0, bytesRead);
				totalSent += bytesRead;
				System.out.println("sent " + totalSent);
			}
		}

		sock.close();
		fileInputStream.close();
		System.out.println(System.currentTimeMillis() - startTime);

	}
}
