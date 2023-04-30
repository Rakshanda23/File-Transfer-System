	import java.net.*;
	import java.io.*;
	import java.util.*;


	public class Client {
		public static Socket s=null;
		public static PrintWriter pw ;
		public static BufferedReader in ;
		public static Boolean sendUserData(String username, String ip, String password) {
			String inputLine;
			try {
				if(s==null)
				{ s = new Socket("192.168.1.117", 4444);
				 in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				 pw = new PrintWriter(s.getOutputStream(), true);
			}
				System.out.println("Connection established with " + s.getRemoteSocketAddress());
				String msg = "7<-->" + username + "<-->" + ip + "<-->" + password;
				if (msg.equals("Bbye")) {
					pw.println("Connection Close");
					System.out.println("Connection Close");
					s.close();
				} else {
					pw.println(msg);
				}


				inputLine = in.readLine();
				System.out.println("Server: " + inputLine);
				 
				// System.out.println("Connection Close");
				// s.close();

				if(inputLine.equals("0")){
					return false;
				}else{
					return true;
				}
				
				
			} catch (Exception e) {
				System.out.println(e);
			}
			return false;
		}

		public static Boolean sendTransactionData(String filename, String sender, String receiver) {
			String inputLine;
			try {
				if(s==null)
				{ s = new Socket("192.168.1.117", 4444);
				 in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				 pw = new PrintWriter(s.getOutputStream(), true);
			}
			System.out.println("Connection established with " + s.getRemoteSocketAddress());
				//////////////////////////////////////////// msg from client
				// String msg = sc.nextLine();
				String msg = "8<-->" + filename + "<-->" + sender + "<-->" + receiver;
				if (msg.equals("Bbye")) {
					pw.println("Connection Close");
					System.out.println("Connection Close");
					s.close();
				} else {
					pw.println(msg);
				}

				/////////////////////////////////////////////////////////// output from server

				inputLine = in.readLine();
				System.out.println("Server: " + inputLine);

				// System.out.println("Connection Close");
				// s.close();

				if(inputLine.equals("0")){
					return false;
				}else{
					return true;
				}


				/////////////////////////////////////////////////////////// output from
				/////////////////////////////////////////////////////////// server/////
				// Socket clientSocket = null;
				// BufferedReader inn = new BufferedReader(new
				/////////////////////////////////////////////////////////// InputStreamReader(clientSocket.getInputStream()));
				// PrintWriter pww = new PrintWriter(clientSocket.getOutputStream(), true);

				// inputLine = inn.readLine();
				// System.out.println("Server: " + inputLine);
				// pww.println(inputLine);
			} catch (Exception e) {
				System.out.println(e);
			}
			return false;
		}

		// public static Boolean collectIpAddress(String username, String ip, String password) {
		// 	String inputLine;
		// 	try {
		// 		Socket s = new Socket("192.168.1.117", 4444);
		// 					BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		// 		PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
		// 		System.out.println("Connection established with " + s.getRemoteSocketAddress());
		// 		//////////////////////////////////////////// msg from client
		// 		// String msg = sc.nextLine();
		// 		String msg = "7<-->" + username + "<-->" + ip + "<-->" + password;
		// 		if (msg.equals("Bbye")) {
		// 			pw.println("Connection Close");
		// 			System.out.println("Connection Close");
		// 			s.close();
		// 		} else {
		// 			pw.println(msg);
		// 		}

		// 		/////////////////////////////////////////////////////////// output from server

		// 		inputLine = in.readLine();
		// 		System.out.println("Server: " + inputLine);

		// 		/////////////////////////////////////////////////////////// output from
		// 		/////////////////////////////////////////////////////////// server/////
		// 		// Socket clientSocket = null;
		// 		// BufferedReader inn = new BufferedReader(new
		// 		/////////////////////////////////////////////////////////// InputStreamReader(clientSocket.getInputStream()));
		// 		// PrintWriter pww = new PrintWriter(clientSocket.getOutputStream(), true);

		// 		// inputLine = inn.readLine();
		// 		// System.out.println("Server: " + inputLine);
		// 		// pww.println(inputLine);
		// 	} catch (Exception e) {
		// 		System.out.println(e);
		// 	}
		// 	return true;
		// }

		public static String collectReciever() {
			String inputLine;
			try {
				if(s==null)
				{ s = new Socket("192.168.1.117", 4444);
				 in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				 pw = new PrintWriter(s.getOutputStream(), true);
			}
			System.out.println("Connection established with " + s.getRemoteSocketAddress());
				//////////////////////////////////////////// msg from client
				// String msg = sc.nextLine();
				String msg = "4";
				if (msg.equals("Bbye")) {
					pw.println("Connection Close");
					System.out.println("Connection Close");
					s.close();
				} else {
					pw.println(msg);
				}

				/////////////////////////////////////////////////////////// output from server

				inputLine = in.readLine();
				System.out.println("Server: " + inputLine);

				// System.out.println("Connection Close");
				// s.close();
				return inputLine;
				

				/////////////////////////////////////////////////////////// output from
				/////////////////////////////////////////////////////////// server/////
				// Socket clientSocket = null;
				// BufferedReader inn = new BufferedReader(new
				/////////////////////////////////////////////////////////// InputStreamReader(clientSocket.getInputStream()));
				// PrintWriter pww = new PrintWriter(clientSocket.getOutputStream(), true);

				// inputLine = inn.readLine();
				// System.out.println("Server: " + inputLine);
				// pww.println(inputLine);
			} catch (Exception e) {
				System.out.println(e);
			}
			return "";
		}

		public static String collectTransaction(String username) {
			String inputLine;
			try {
				if(s==null)
				{ s = new Socket("192.168.1.117", 4444);
				 in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				 pw = new PrintWriter(s.getOutputStream(), true);
			}
			System.out.println("Connection established with " + s.getRemoteSocketAddress());
				//////////////////////////////////////////// msg from client
				// String msg = sc.nextLine();
				String msg = "1<-->" + username;
				if (msg.equals("Bbye")) {
					pw.println("Connection Close");
					System.out.println("Connection Close");
					s.close();
				} else {
					pw.println(msg);
				}

				/////////////////////////////////////////////////////////// output from server

				inputLine = in.readLine();
				System.out.println("Server: " + inputLine);

				// System.out.println("Connection Close");
				// s.close();

				return inputLine;
				/////////////////////////////////////////////////////////// output from
				/////////////////////////////////////////////////////////// server/////
				// Socket clientSocket = null;
				// BufferedReader inn = new BufferedReader(new
				/////////////////////////////////////////////////////////// InputStreamReader(clientSocket.getInputStream()));
				// PrintWriter pww = new PrintWriter(clientSocket.getOutputStream(), true);

				// inputLine = inn.readLine();
				// System.out.println("Server: " + inputLine);
				// pww.println(inputLine);
			} catch (Exception e) {
				System.out.println(e);
			}
			return "";
		}

		public static Boolean changeStatus(String username, String status) {
			String inputLine;
			try {
				if(s==null)
				{ s = new Socket("192.168.1.117", 4444);
				 in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				 pw = new PrintWriter(s.getOutputStream(), true);
			}
			System.out.println("Connection established with " + s.getRemoteSocketAddress());
				//////////////////////////////////////////// msg from client
				// String msg = sc.nextLine();
				String msg = "6<-->" + username + "<-->" + status;
				if (msg.equals("Bbye")) {
					pw.println("Connection Close");
					System.out.println("Connection Close");
					s.close();
				} else {
					pw.println(msg);
				}

				/////////////////////////////////////////////////////////// output from server

				inputLine = in.readLine();
				System.out.println("Server: " + inputLine);

					// System.out.println("Connection Close");
					// s.close();
				if(inputLine.equals("0")){
					return false;
				}else{
					return true;
				}

				/////////////////////////////////////////////////////////// output from
				/////////////////////////////////////////////////////////// server/////
				// Socket clientSocket = null;
				// BufferedReader inn = new BufferedReader(new
				/////////////////////////////////////////////////////////// InputStreamReader(clientSocket.getInputStream()));
				// PrintWriter pww = new PrintWriter(clientSocket.getOutputStream(), true);

				// inputLine = inn.readLine();
				// System.out.println("Server: " + inputLine);
				// pww.println(inputLine);
			} catch (Exception e) {
				System.out.println(e);
			}
			return false;
		}

		public static Boolean sendFile(String filePath) {
			return true;
		}
		public static void main(String args[]) {
			// String inputLine;
			//sendUserData("Swarup", "123", "Raksha");
			Scanner sc = new Scanner(System.in);
			int i;
			do {
			System.out.println("JDBC and CLIENT");
			System.out.print("Enter the choice : ");
			System.out.println("1. Insert User");
			System.out.println("2. Send Transaction Data");
			System.out.println("3. Collect Reciever");
			System.out.println("4. Collect Transaction");
			System.out.println("5. Change Status");
			System.out.println("6. Exit");

			i = sc.nextInt();
			switch (i) {
				case 1:
					System.out.println(sendUserData("Raksha", "127.0.0.1", "Shiv"));
					break;
				case 2:
					System.out.println(sendTransactionData("MyHeart.obj", "Swarup", "Raksha"));
					break;
				case 3:
					System.out.println(collectReciever());
					break;
				case 4:
					System.out.println(collectTransaction("Swarup"));
					break;
			case 5:
					
					System.out.println(changeStatus("Swarup", "0"));
					break;
				default:
					break;
			}
			} while (i != 10);
			changeStatus("Swarup", "0");
			// try {
			// Socket s = new Socket("192.168.1.4", 4444);
			// BufferedReader in = new BufferedReader(new
			// InputStreamReader(s.getInputStream()));
			// PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
			// Scanner sc = new Scanner(System.in);

			// System.out.println("Connection established with " +
			// s.getRemoteSocketAddress());
			// while (true) {
			// //////////////////////////////////////////// msg from client
			// String msg = sc.nextLine();

			// if (msg.equals("Bbye")) {
			// pw.println("Connection Close");
			// System.out.println("Connection Close");
			// s.close();
			// break;
			// } else {
			// pw.println(msg);
			// }

			// /////////////////////////////////////////////////////////// output from
			// server

			// inputLine = in.readLine();
			// System.out.println("Server: " + inputLine);

			// /////////////////////////////////////////////////////////// output from
			// /////////////////////////////////////////////////////////// server/////
			// // Socket clientSocket = null;
			// // BufferedReader inn = new BufferedReader(new
			// ///////////////////////////////////////////////////////////
			// InputStreamReader(clientSocket.getInputStream()));
			// // PrintWriter pww = new PrintWriter(clientSocket.getOutputStream(), true);

			// // inputLine = inn.readLine();
			// // System.out.println("Server: " + inputLine);
			// // pww.println(inputLine);

			// }
			// } catch (Exception e) {
			// System.out.println(e);
			// }
		}
	}