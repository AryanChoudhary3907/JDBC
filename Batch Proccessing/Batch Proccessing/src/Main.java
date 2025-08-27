import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
       String url = "jdbc:mysql://localhost:3306/mydatabase";
       String username = "root";
       String password = "Aryan#2807";

       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           System.out.println("Drivers load Successfully!!");
       }catch (ClassNotFoundException e){
           System.out.println(e.getMessage());
       }

       try{
           Connection connection = DriverManager.getConnection(url,username,password);
           System.out.print("Connected");
           connection.setAutoCommit(false);
           for (int i=0;i<5;i++){
               Thread.sleep(500);
               System.out.print("!");
           }
           System.out.println();
           String sql = "insert into employees(id ,name, job_title, salary) values( ? ,? ,? ,?)";
           PreparedStatement preparedStatement = connection.prepareStatement(sql);
           Scanner scanner = new Scanner(System.in);
           while(true){
               System.out.print("Enter id: ");
               int id = scanner.nextInt();

               scanner.nextLine();
               System.out.print("Enter name: ");
               String name  = scanner.nextLine();

               System.out.print("Enter job_title: ");
               String job_title = scanner.nextLine();

               System.out.print("Enter salary: ");
               double salary = scanner.nextDouble();

               preparedStatement.setInt(1,id);
               preparedStatement.setString(2,name);
               preparedStatement.setString(3,job_title);
               preparedStatement.setDouble(4,salary);
               scanner.nextLine();
               preparedStatement.addBatch();

               System.out.println("Exit (Y/N): ");
               String choice  = scanner.nextLine();

               if(choice.equalsIgnoreCase("Y")) {
                   break;
               }
           }

           preparedStatement.executeBatch();
           connection.commit();
           System.out.println("Batch Execute Successfully!!!");

       }catch (SQLException | InterruptedException e){
           System.out.println(e.getMessage());
       }

    }
}