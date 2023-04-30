package db;
import java.sql.*;
import javax.swing.JOptionPane;
public class DbConnect {
    
    public static Connection c= null;
    public static Statement st= null;
    
    static{
        try{
             Class.forName("com.mysql.cj.jdbc.Driver");
             c=DriverManager.getConnection("jdbc:mysql://localhost:3306/spendingdb?zeroDateTimeBehavior=CONVERT_TO_NULL&user=root");
             st=c.createStatement();
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Hello :"+ex);
        }          
    
}
    
}
