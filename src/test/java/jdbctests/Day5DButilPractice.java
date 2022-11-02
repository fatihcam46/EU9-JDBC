package jdbctests;

import org.junit.jupiter.api.Test;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class Day5DButilPractice {
    @Test
    public void test1(){
        //create connection
        DBUtils.createConnection();
        String query = "SELECT first_name,last_name,salary,job_id\n" +
                "from employees\n" +
                "where rownum <6";

        List<Map<String, Object>> queryData = DBUtils.getQueryResultMap(query);

        //print the result
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }
        /*
        {JOB_ID=AD_PRES, SALARY=24000, LAST_NAME=King, FIRST_NAME=Steven}
{JOB_ID=AD_VP, SALARY=17000, LAST_NAME=Kochhar, FIRST_NAME=Neena}
{JOB_ID=AD_VP, SALARY=17000, LAST_NAME=De Haan, FIRST_NAME=Lex}
{JOB_ID=IT_PROG, SALARY=9000, LAST_NAME=Hunold, FIRST_NAME=Alexander}
{JOB_ID=IT_PROG, SALARY=6000, LAST_NAME=Ernst, FIRST_NAME=Bruce}
         */
        //close the connection
        DBUtils.destroy();
    }
    @Test
    public void test2(){
        //create connection
        DBUtils.createConnection();
        String query = "SELECT first_name,last_name,salary,job_id\n" +
                "from employees\n" +
                "where rownum <2";

        Map<String, Object> rowMap = DBUtils.getRowMap(query);

        System.out.println(rowMap.toString());
//{JOB_ID=AD_PRES, SALARY=24000, LAST_NAME=King, FIRST_NAME=Steven}
        //close the connection
        DBUtils.destroy();
    }
}
