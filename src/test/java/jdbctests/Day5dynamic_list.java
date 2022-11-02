package jdbctests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.*;

public class Day5dynamic_list {
    String dbUrl = "jdbc:oracle:thin:@54.145.124.192:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test2() throws SQLException {
//1-firstly write these
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
       ResultSet resultSet = statement.executeQuery("SELECT first_name,last_name,salary,job_id\n" +
                "from employees\n" +
                "where rownum <6");
     //    ResultSet resultSet = statement.executeQuery("select * from departments");//all departments info
     //   ResultSet resultSet = statement.executeQuery("select * from employees");//all employees info

        //in order to get column names we need ResultSetMetaData
        ResultSetMetaData rsmd = resultSet.getMetaData();

//2nd    //list of maps to keep all information
        List<Map<String,Object>> queryData = new ArrayList<>();

//3rd        //number of columns
        int colCount = rsmd.getColumnCount();

// 4th       //while loop through each row>>how to move every row
        while(resultSet.next()){                 //create one map
            Map<String,Object> row = new LinkedHashMap<>();
            //some code to fill the dynamically
            for (int i = 1; i <= colCount; i++) {
                 row.put(rsmd.getColumnName(i),resultSet.getObject(i));
            }
             //add ready map row to the list
            queryData.add(row);
        }


 //5th       //print each row inside the list >>for each loop use>>
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }
/*
{FIRST_NAME=Steven, LAST_NAME=King, SALARY=24000, JOB_ID=AD_PRES}
{FIRST_NAME=Neena, LAST_NAME=Kochhar, SALARY=17000, JOB_ID=AD_VP}
{FIRST_NAME=Lex, LAST_NAME=De Haan, SALARY=17000, JOB_ID=AD_VP}
{FIRST_NAME=Alexander, LAST_NAME=Hunold, SALARY=9000, JOB_ID=IT_PROG}
{FIRST_NAME=Bruce, LAST_NAME=Ernst, SALARY=6000, JOB_ID=IT_PROG}
 */

        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }
}
