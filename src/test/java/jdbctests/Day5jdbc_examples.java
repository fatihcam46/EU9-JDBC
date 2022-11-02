package jdbctests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;
//day5
public class Day5jdbc_examples {

    String dbUrl = "jdbc:oracle:thin:@54.145.124.192:1521:XE";//I added my IP address:port number(same)
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);//added exception
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");

//1stWAY>>     //move to first row
//        resultSet.next();
//        System.out.println(resultSet.getString(2));
//        //display departments table in 10 - Administration - 200 - 1700 format dynamically lets do

//2ndWAY>>        //code for iterating for each row
        while(resultSet.next()){   // //next() --> move pointer to first row

            System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2)
            +" - "+resultSet.getString(3)+" - "+resultSet.getInt(4));
            //some of them is zero>>thats why null>>String we made it
            //10 - Administration - 200 - 1700  and so on all table>>>
        }
System.out.println("======================================================");

        resultSet = statement.executeQuery("SELECT * FROM regions");
        while(resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));
/*
1 - Europe
2 - Americas
3 - Asia
4 - Middle East and Africa
 */
        }
        //Copy and paste from day4 Connection
        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }

    @DisplayName("ResultSet Methods")
    @Test
    public void test2() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");

        //how to find how many rows we have for the query
        //move to last row
        //test
        resultSet.last();

        //get the row count
        int rowCount = resultSet.getRow();
        System.out.println(rowCount);//27

        //to move before first row after we use .last method
        resultSet.beforeFirst();

        //print all second column information
        while (resultSet.next()){
            System.out.println(resultSet.getString(2));//Administration,Marketing,Purchasing...
        }
        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }
    @Test
    public void test3() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");

        //get the database related data inside the dbMetedata object
        DatabaseMetaData dbMetadata = connection.getMetaData();

        System.out.println("dbMetadata.getUserName() = " + dbMetadata.getUserName());
        System.out.println("dbMetadata.getDatabaseProductName() = " + dbMetadata.getDatabaseProductName());
        System.out.println("dbMetadata.getDatabaseProductVersion() = " + dbMetadata.getDatabaseProductVersion());
        System.out.println("dbMetadata.getDriverName() = " + dbMetadata.getDriverName());
        System.out.println("dbMetadata.getDriverVersion() = " + dbMetadata.getDriverVersion());

        //get the resultsetmetadata //rsmd
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //how many columns we have ?
        int colCount = rsMetadata.getColumnCount();
        System.out.println(colCount);

        //getting column names
        System.out.println(rsMetadata.getColumnName(1));
        System.out.println(rsMetadata.getColumnName(2));

        //rsMetadata.getColumnName(i) --> gets column name
        //rsMetadata.getColumnCount() --> total number of columns
        //print all the column names dynamically
        for (int i = 1; i <= colCount; i++) {
            System.out.println(rsMetadata.getColumnName(i));
        }

        //BREAK UNTIL 12:22
        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }

}
