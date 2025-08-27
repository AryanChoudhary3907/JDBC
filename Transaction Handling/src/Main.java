import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
       String url = "jdbc:mysql://localhost:3306/mydatabase";
       String username = "root";
       String password = "Aryan#2807";
       String withdrawQuery = "update accounts set balance = balance - ? where account_number = ?";
       String depositeQuery = "update accounts set balance = balance + ? where account_number = ?";


       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           System.out.println("Drivers load Successfully!!");
       }catch (Exception e){
           System.out.println(e.getMessage());
       }

       try{
           Connection connection = DriverManager.getConnection(url,username,password);
           System.out.println("Connected!!!");
           connection.setAutoCommit(false);
           PreparedStatement withdrawStatement = connection.prepareStatement(withdrawQuery);
           PreparedStatement depositeStatement = connection.prepareStatement(depositeQuery);
           withdrawStatement.setDouble(1,500);
           withdrawStatement.setString(2,"124");
           depositeStatement.setDouble(1,500);
           depositeStatement.setString(2,"123");

           int rowsAffected1 = withdrawStatement.executeUpdate();
           int rowsAffected2 = depositeStatement.executeUpdate();

           if(rowsAffected2>0 && rowsAffected1 >0){
               connection.commit();
               System.out.println("Transaction Successful!");
           }
           else{
               connection.rollback();
               System.out.println("Transaction failed!");
           }



       }catch (SQLException e){
           System.out.println(e.getMessage());
       }


    }
}