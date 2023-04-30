// import java.io.*;
// import java.net.*;

// public class Server
// {
// 	public static void main(String args[]) throws Exception
// 	{
// 		ServerSocket ss = new ServerSocket(4444);
// 		while(true)
// 		{
// 			try
// 			{
// 				Socket s = ss.accept();
// 				CommunicationThread ct = new CommunicationThread(s);
// 				ct.start();
// 			}
// 			catch(Exception e)
// 			{
// 				System.out.println(e);
// 			}
// 		}		
// 	}
// }

// class CommunicationThread extends Thread
// {
// 	Socket s;
// 	CommunicationThread(Socket s)
// 	{
// 		this.s = s;
// 	}
// 	public void run()
// 	{
// 		try(BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream())))
// 		{
// 			System.out.println("Connection accepted from " + s.getRemoteSocketAddress());
// 			while(true)
// 			{
// 				String msg = br.readLine();
// 				System.out.println(msg);
// 				if(msg.equals("exit"))
// 					break;
// 			}
// 		}
// 		catch(Exception e)
// 		{
// 			System.out.println(e);
// 		}
// 	}
// }


import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server
{
	public static ServerSocket serverSocket = null;
	public static void main(String args[]) throws Exception
	{
		try {
			serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }

		System.out.println("Server started. Listening on port 4444...");

		while(true)
		{Socket clientSocket = null;
			try
			{
				clientSocket = serverSocket.accept();
				System.out.println("Accepted");
				CommunicationThread ct = new CommunicationThread( clientSocket);
				ct.start();
				break;
			}
			catch(Exception e)
			{
				System.err.println("Accept failed.");
            	System.exit(1);
				//System.out.println(e);
			}
		}		
	}
}

class CommunicationThread extends Thread
{
	Socket clientSocket;
	CommunicationThread(Socket clientSocket)
	{
		this.clientSocket = clientSocket;
		
	}
	public void run()
	{
		try
		{
			BufferedReader brin = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			System.out.println("Connection accepted from " + clientSocket.getRemoteSocketAddress());
			System.out.println("Listening for input...");

			PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);
			

			String inputLine;
	
			while ((inputLine = brin.readLine()) != null) {
				/////////////////////////////////////////// output from client
				System.out.println("Client: " + inputLine);
				
				/////////////////////////////////////////// input to server

				Scanner sc= new Scanner(System.in);  
				String str = sc.nextLine();   

				if (str.equals("Bbye"))
				{
					pw.println("Connection close");					
					pw.close();
					System.out.println("Connection Close");
					brin.close();
					clientSocket.close();
					Server.serverSocket.close();
					break;

				}
				else
				{
					pw.println(str);
				}
				
				/////////////////////////////////////////// input from server //////
				// Scanner sc = new Scanner(System.in);
				// String msg = sc.nextLine();
				// pw.println(msg); 
				// String msg = sc.nextLine();
				// out.println(msg);
	
				
			}
	
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}