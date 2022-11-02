package jdbctests;

import java.sql.*;

public class Day4TestConnection {
    //day4
//firstly we made this class: we created JAVA to AWS EC2 computer connection, we connected
    public static void main(String[] args) throws SQLException {
        String dbUrl = "jdbc:oracle:thin:@54.145.124.192:1521:XE";//I added my IP address:port number(same)
        String dbUsername = "hr";
        String dbPassword = "hr";

        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);//added exception
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM regions");
                                                     //from SQL we are writing these codes:

        //once you set up connection default pointer looks for 0
      //1-  //next() --> move pointer to first row
        resultSet.next();

      //2-  //getting information with column name
        System.out.println(resultSet.getString("region_name"));//Europe

      //3- //getting information with column index(starts 1)
        System.out.println(resultSet.getString(2));//Europe
        System.out.println(resultSet.getInt(1));//1  it is integer, and we took column number

      //4-print  // 1 - Europe   integer  "-" string
                // 2 - Americas
        System.out.println(resultSet.getInt(1)+ " - " +resultSet.getString(2) );//1 - Europe

        //move to second row  //bir asagi satira gecmesi icin >>resultSet.next();
        resultSet.next();
        System.out.println(resultSet.getInt(1)+ " - " +resultSet.getString(2) );//2 - Americas

        //move to third row
        resultSet.next();
        System.out.println(resultSet.getInt(1)+ " - " +resultSet.getString(2) );//3 - Asia

        //5- lets do loop>> every row look write and pass under row  --automatically
   //     while (resultSet.next()){
   //        System.out.println(resultSet.getInt(1)+ " - " +resultSet.getString(2) );
   //     }
        //1 - Europe
        //2 - Americas
        //3 - Asia
        //4 - Middle East and Africa

        //close connection
        resultSet.close();
        statement.close();
        connection.close();


    }
}
