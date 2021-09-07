import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*The main problems with the original singleton class was it never limited the class to a single instance
* which violated the primary principle of a singleton class. Now it limits it to only a single instance.*/
public class singletonFix {

    private static Connection conn = null;

    private static singletonFix instance = null;

    public static singletonFix getInstance() {
        if(instance == null){
            instance = new singletonFix();
            //conn = DriverManager();
            //this is where the connection would connect with the Database
        }
        return instance;
    }

}
