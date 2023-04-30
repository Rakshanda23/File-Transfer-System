import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.mysql.cj.jdbc.JdbcStatement;

public class JdbcExample {
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost/file_transfer";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";

    private static Connection conn = null;
    private static PreparedStatement stmt = null;
    private static ResultSet rs = null;

    public static void connectFunction() throws Exception{
         Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

 }
    public static String getTransaction(String username) {
        try {
            connectFunction();
            String selectQuery = "SELECT * FROM transaction WHERE sender_name = '" + username
                    + "' ORDER BY trans_id DESC LIMIT 5";
            stmt = conn.prepareStatement(selectQuery);
            rs = stmt.executeQuery();
            // String[] str = new String[5];
            String str = "";
            int i = 0;
            while (rs.next()) {
                String filename = rs.getString("file_name");
                String receiver = rs.getString("receiver_name");
                Date d = rs.getDate("time");
                System.out.println("File Name: " + filename + ", Receiver: " + receiver + ", Date : " + d);
                str += filename + "<-->" + receiver + "<-->" + d + "|--|";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    public static String getUser(String username) {
        try {
            connectFunction();

            String selectQuery = "SELECT * FROM user WHERE user_name = '" + username + "' ";
            stmt = conn.prepareStatement(selectQuery);
            rs = stmt.executeQuery();
            if (rs.next() == false){
                System.out.println("User does not exist");
                return("0");
            }
            else {
                String ip = rs.getString("ip_address");
                boolean status = rs.getBoolean("status");
                return(ip+"<-->"+status);
                // System.out.println("IP address: " + ip + ", Status : " + status);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    public static Boolean getStatus(String username) {
        try {
            connectFunction();
            String selectQuery = "SELECT * FROM user WHERE user_name = '" + username + "' ";
            stmt = conn.prepareStatement(selectQuery);
            rs = stmt.executeQuery();
            rs.next();
            boolean status = rs.getBoolean("status");
            if (status == false)
                return false;
            else
                return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getReceiver() {
        try {
            connectFunction();
            String selectQuery = "SELECT * FROM user WHERE status = '" + 1 + "' ";
            stmt = conn.prepareStatement(selectQuery);
            rs = stmt.executeQuery();
            String str = "";
            while (rs.next()) {
                String receiver = rs.getString("user_name");
                System.out.println(receiver);
                str += receiver + "<-->";
            }
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    public static Boolean updateIPaddress(String username, String ip) {
        try {
            connectFunction();
            String updateQuery = "UPDATE user SET ip_address = ? WHERE user_name = ?";
            stmt = conn.prepareStatement(updateQuery);
            stmt.setString(1, ip);
            stmt.setString(2, username);
            int count = stmt.executeUpdate();
            if (count > 0)
                return true; // System.out.println("Data updated successfully.");
            else
                return false; // System.out.println("Data Not updated .");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean updateStatus(String username, Boolean status) {
        try {
            connectFunction();
            String updateQuery = "UPDATE user SET Status = ? WHERE user_name = ?";
            stmt = conn.prepareStatement(updateQuery);
            stmt.setBoolean(1, status);
            stmt.setString(2, username);
            int count = stmt.executeUpdate();
            if (count > 0)
                return true; // System.out.println("Data updated successfully.");
            else
                return false; // System.out.println("Data Not updated .");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean insertUser(String username, String ip, String password) {
        try {
            connectFunction();
            System.out.println("Insertion");
            String insertQuery = "INSERT INTO user (user_name, ip_address, password) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(insertQuery);
            stmt.setString(1, username);
            stmt.setString(2, ip);
            stmt.setString(3, password);
            int count = stmt.executeUpdate();

            if (count > 0)
                return true; // System.out.println("Data updated successfully.");
            else
                return false; // System.out.println("Data Not updated .");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean addTransaction(String filename, String sender, String receiver) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        Date date = Date.valueOf(dtf.format(now));
        try {
            connectFunction();
            String insertQuery = "INSERT INTO transaction (file_name, sender_name, receiver_name, time) VALUES ( ?,?, ?, ?)";
            stmt = conn.prepareStatement(insertQuery);
            stmt.setString(1, filename);
            stmt.setString(2, sender);
            stmt.setString(3, receiver);
            stmt.setDate(4, date);
            int count = stmt.executeUpdate();

            if (count > 0)
                return true; // System.out.println("Data updated successfully.");
            else
                return false; // System.out.println("Data Not updated .");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ServerSocket serverSocket = null;
    public static void ServerConnection(){
          try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }

        System.out.println("Server started. Listening on port 4444...");

        while (true) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
                System.out.println("Accepted");
                CommunicationThread ct = new CommunicationThread(clientSocket);
                ct.start();
                break;
            } catch (Exception e) {
                System.err.println("Accept failed.");
                System.exit(1);
                // System.out.println(e);
            }
        }

           
            System.out.println("connection-----------");

            // getTransaction("abc");

            // getUser("abc");

            // Boolean status = getStatus("abc");
            // System.out.println(status);

            // getReceiver();

            // Boolean ip_updated = updateIPaddress("xyx", "39.84.7");
            // System.out.println(ip_updated);

            // Boolean status_updated = updateStatus("xyx",false);
            // System.out.println(status_updated);

            // Boolean user_inserted = insertUser("rsv","1.23.28","alwaysnforever");
            // System.out.println(user_inserted);

            // Boolean transaction_added = addTransaction("lovefile","sdv","rag");
            // System.out.println(transaction_added);
            //
            // System.out.println("finish");

       
        // finally //////////////// DB connection close
        // {
        // try {
        // if (rs != null) rs.close();
        // if (stmt != null) stmt.close();
        // if (conn != null) conn.close();
        // } catch (SQLException e) {
        // e.printStackTrace();
        // }
        // }
    }
    public static void main(String[] args) throws Exception {
        ServerConnection();
    }
}

class CommunicationThread extends Thread {
    Socket clientSocket;

    CommunicationThread(Socket clientSocket) {
        this.clientSocket = clientSocket;

    }

    public void run() {
        try {
            
            BufferedReader brin = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("Connection accepted from " + clientSocket.getRemoteSocketAddress());
            System.out.println("Listening for input...");

            PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;
            String str="";
            while ((inputLine = brin.readLine()) != null) {
                System.out.println("Client here");
                Boolean ip_updated = JdbcExample.updateIPaddress("rsv", "1819.84.7");
                System.out.println(ip_updated);
                String[] inputAr = inputLine.split("<-->");
                System.out.println("Hello : "+inputAr[0]);
                if (inputAr[0].equals("1")) {
                    
                str = JdbcExample.getTransaction(inputAr[1]);
                } else if (inputAr[0].equals("2")) {
                   str = JdbcExample.getUser(inputAr[1]);
                } else if (inputAr[0].equals("3")) {
                   Boolean b = JdbcExample.getStatus(inputAr[1]);
                    if(b==true){
                        str = "1";
                    }else{
                        str = "0";
                    }
                } else if (inputAr[0].equals("4")) {
                    str = JdbcExample.getReceiver();
                } else if (inputAr[0].equals("5")) {
                    Boolean b = JdbcExample.updateIPaddress(inputAr[1], inputAr[2]);
                    if(b==true){
                        str = "1";
                    }else{
                        str = "0";
                    }
                } else if (inputAr[0].equals("6")) {
                    if (inputAr[2].equals("0")) {
                        Boolean b = JdbcExample.updateStatus(inputAr[1], false);
                        if(b==true){
                            str = "1";
                        }else{
                            str = "0";
                        }
                    } else {
                        Boolean b =JdbcExample.updateStatus(inputAr[1], true);
                        if(b==true){
                            str = "1";
                        }else{
                            str = "0";
                        }
                    }
                } else if (inputAr[0].equals("7")) {
                    Boolean b = JdbcExample.insertUser(inputAr[1], inputAr[2], inputAr[3]);
                    if(b==true){
                        str = "1";
                    }else{
                        str="0";
                    }
                } else if (inputAr[0].equals("8")) {
                    Boolean b =JdbcExample.addTransaction(inputAr[1], inputAr[2], inputAr[3]);
                    if(b==true){
                        str = "1";
                    }else{
                        str = "0";
                    }
                }

                //System.out.println("Client: " + inputLine+"dkfjk;djfgkidrjg");

                /////////////////////////////////////////// input to server

                System.out.println("aaa");
                Scanner sc = new Scanner(System.in);

                // System.out.println("bbbb");
                // if (str.equals("Bbye")) {
                  System.out.println("bbbb");
                if (str.equals("Bbye")) {
                    System.out.println("cccc");
                    pw.println("Connection close");
                    pw.close();
                    System.out.println("Connection Close");
                    brin.close();
                    clientSocket.close();
                    JdbcExample.serverSocket.close();
                    break;

                } else {
                    pw.println(str);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//
// import com.sun.org.apache.xpath.internal.operations.Bool;
//
// import java.sql.*;
// import java.text.DateFormat;
// import java.text.SimpleDateFormat;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
//
// public class JdbcExample
// {
// private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
// private static final String DB_URL = "jdbc:mysql://localhost/file_transfer";
// private static final String USERNAME = "root";
// private static final String PASSWORD = "";
//
// private static Connection conn = null;
// private static PreparedStatement stmt = null;
// private static ResultSet rs = null;
// public static void getTransaction(String username)
// {
// try
// {
// String selectQuery = "SELECT * FROM transaction WHERE sender_name =
// '"+username+"' ORDER BY trans_id DESC LIMIT 5";
// stmt = conn.prepareStatement(selectQuery);
// rs = stmt.executeQuery();
// while (rs.next()) {
// String filename = rs.getString("file_name");
// String receiver = rs.getString("receiver_name");
// Date d = rs.getDate("time");
// System.out.println("File Name: " + filename + ", Receiver: " + receiver + ",
// Date : "+d );
// }
// }
// catch (SQLException e) {
// e.printStackTrace();
// }
//
// }
//
// public static void getUser(String username)
// {
// try
// {
// String selectQuery = "SELECT * FROM user WHERE user_name = '"+username+"' ";
// stmt = conn.prepareStatement(selectQuery);
// rs = stmt.executeQuery();
// if(rs.next() == false)
// System.out.println("User does not exist");
// else
// {
// String ip = rs.getString("ip_address");
// boolean status = rs.getBoolean("status");
// System.out.println("IP address: " + ip + ", Status : " + status);
// }
//
// }
// catch (SQLException e) {
// e.printStackTrace();
// }
// }
//
// public static Boolean getStatus(String username)
// {
// try
// {
// String selectQuery = "SELECT * FROM user WHERE user_name = '"+username+"' ";
// stmt = conn.prepareStatement(selectQuery);
// rs = stmt.executeQuery();
// rs.next() ;
// boolean status = rs.getBoolean("status");
// if(status==false)
// return false;
// else
// return true;
//
// }
// catch (SQLException e) {
// e.printStackTrace();
// }
// return false;
// }
//
// public static int getReceiver()
// {
// try
// {
// String selectQuery = "SELECT * FROM user WHERE status = '"+1+"' ";
// stmt = conn.prepareStatement(selectQuery);
// rs = stmt.executeQuery();
// while (rs.next())
// {
// String receiver = rs.getString("user_name");
// System.out.println(receiver);
// }
//
// }
// catch (SQLException e) {
// e.printStackTrace();
// }
// return 0;
// }
//
// public static Boolean updateIPaddress(String username, String ip)
// {
// try
// {
// String updateQuery = "UPDATE user SET ip_address = ? WHERE user_name = ?";
// stmt = conn.prepareStatement(updateQuery);
// stmt.setString(1, ip);
// stmt.setString(2, username);
// int count = stmt.executeUpdate();
// if(count > 0)
// return true; //System.out.println("Data updated successfully.");
// else
// return false; //System.out.println("Data Not updated .");
//
// }
// catch (SQLException e) {
// e.printStackTrace();
// }
// return false;
// }
//
// public static Boolean updateStatus(String username, Boolean status)
// {
// try
// {
// String updateQuery = "UPDATE user SET Status = ? WHERE user_name = ?";
// stmt = conn.prepareStatement(updateQuery);
// stmt.setBoolean(1, status);
// stmt.setString(2, username);
// int count = stmt.executeUpdate();
// if(count > 0)
// return true; //System.out.println("Data updated successfully.");
// else
// return false ; //System.out.println("Data Not updated .");
//
// }
// catch (SQLException e) {
// e.printStackTrace();
// }
// return false;
// }
//
// public static Boolean insertUser(String username, String ip, String password)
// {
// try
// {
// String insertQuery = "INSERT INTO user (user_name, ip_address, password)
// VALUES (?, ?, ?)";
// stmt = conn.prepareStatement(insertQuery);
// stmt.setString(1, username);
// stmt.setString(2, ip);
// stmt.setString(3, password);
// int count = stmt.executeUpdate();
//
// if(count > 0)
// return true; //System.out.println("Data updated successfully.");
// else
// return false ; //System.out.println("Data Not updated .");
//
// }
// catch (SQLException e) {
// e.printStackTrace();
// }
// return false ;
// }
//
// public static Boolean addTransaction(String filename, String sender, String
// receiver)
// {
// DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
// LocalDateTime now = LocalDateTime.now();
// Date date=Date.valueOf(dtf.format(now));
// try
// {
//
// String insertQuery = "INSERT INTO transaction (file_name, sender_name,
// receiver_name, time) VALUES ( ?,?, ?, ?)";
// stmt = conn.prepareStatement(insertQuery);
// stmt.setString(1, filename);
// stmt.setString(2, sender);
// stmt.setString(3, receiver);
// stmt.setDate(4, date);
// int count = stmt.executeUpdate();
//
// if(count > 0)
// return true; //System.out.println("Data updated successfully.");
// else
// return false ; //System.out.println("Data Not updated .");
//
// }
// catch (SQLException e) {
// e.printStackTrace();
// }
// return false ;
// }
//
// public static void main(String[] args) {
//
//
// try {
//
// Class.forName(JDBC_DRIVER);
// conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
//
//
// System.out.println(" \n Transaction details : ");
// getTransaction("abc");
//
// System.out.println(" \n Details of 'abc' : ");
// getUser("abc");
//
// System.out.println(" \n Receiving Status of 'rag' : ");
// Boolean status = getStatus("abc");
// System.out.println(status);
//
// System.out.println(" \n All Receivers : ");
// getReceiver();
//
// System.out.println(" \n Update IP address of 'xyz' : ");
// Boolean ip_updated = updateIPaddress("xyx","39.84.7");
// System.out.println(ip_updated);
//
// System.out.println(" \n Update Status address of 'xyz' : ");
// Boolean status_updated = updateStatus("xyx",false);
// System.out.println(status_updated);
//
// System.out.println(" \n Inserrt user 'rsv' : ");
// Boolean user_inserted = insertUser("rsv","1.23.28","alwaysnforever");
// System.out.println(user_inserted);
//
// System.out.println(" \n Transaction done by 'sdv': ");
// Boolean transaction_added = addTransaction("file10","sdv","rag");
// System.out.println(transaction_added);
//
// System.out.println("finish");
//
// }
// catch (ClassNotFoundException | SQLException e) {
// e.printStackTrace();
// } finally {
// try {
// if (rs != null) rs.close();
// if (stmt != null) stmt.close();
// if (conn != null) conn.close();
// } catch (SQLException e) {
// e.printStackTrace();
// }
// }
// }
// }