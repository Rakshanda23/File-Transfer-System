import java.net.*;
import java.io.*;
import java.util.*;

public class Client
{
	public static void main(String args[])
	{String inputLine;
		try	
		{
			Socket s = new Socket("192.168.1.117", 4444);
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
			Scanner sc = new Scanner(System.in);

			System.out.println("Connection established with " + s.getRemoteSocketAddress());
			while(true)
			{
				//////////////////////////////////////////// msg from client
				String msg = sc.nextLine();
				
				if (msg.equals("Bbye"))
					{
						pw.println("Connection Close"); 
						System.out.println("Connection Close");
						s.close();
						break;						
					}
				else{
					pw.println(msg); 
				}

				/////////////////////////////////////////////////////////// output from server

				inputLine = in.readLine();
					System.out.println("Server: " + inputLine);

				/////////////////////////////////////////////////////////// output from server/////
				// Socket clientSocket = null;
				// BufferedReader inn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				// PrintWriter pww = new PrintWriter(clientSocket.getOutputStream(), true);
				
				// inputLine = inn.readLine();
				// System.out.println("Server: " + inputLine);
				// pww.println(inputLine);

					
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}