package com.java.ServerConnect;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.java.DatabaseConnection.RequestActivity;
import com.java.Zcode.Account;

public class Server {
	
	public static final int port = 2001;
	
	public static void main(String[] args) {	
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			// wait for client
			Socket socket = serverSocket.accept();
			
			DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
//			String s = dataInputStream.readUTF();
			String request = dataInputStream.readUTF();
			DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
			if(request.equals("login")) {
				String userId = dataInputStream.readUTF();
				Account account = (new RequestActivity()).getAccount(userId);
				if(account == null) {
					dataOutputStream.writeUTF("null");
					return;
				}
				dataOutputStream.writeUTF(account.getPassword());
				
			}
			
			dataInputStream.close();
			dataOutputStream.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
