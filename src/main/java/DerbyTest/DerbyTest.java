package DerbyTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DerbyTest {

    public DerbyTest() throws Exception {
        try {

            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            Connection conn = DriverManager
                    .getConnection("jdbc:derby:/home/thomas/MyDB;create=true","thunderbirds","thunderbirds");
            Statement stmt =  conn.createStatement();
            ResultSet rst = stmt.executeQuery("select * from perso");
            while (rst.next()) {
                System.out.println(rst.getInt(1));
                System.out.println(rst.getString(2));
            }
        } catch (Exception e) {
            System.out.println(e);;
        }
    }
    public static void main(String[] args) throws Exception {
        DerbyTest dao = new DerbyTest();
    }
}