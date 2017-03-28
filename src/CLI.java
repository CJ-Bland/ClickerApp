import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Andrew McD on 3/10/2017.
 */
public class CLI {
    public CLI(){

    }

    public void runCLI(){
        String breaker = "something";
        Scanner scan = new Scanner(System.in);
        while(breaker.equals("quit")){
            System.out.println("--> ");
            breaker = scan.nextLine();
            switch(breaker){
                case "quit":
                    break;

                case "connect":
                    connect();
                    break;


                case "addToD":
                    addToD();
                    break;

                case "getFromD":
                    getFromD();
                    break;
            }
        }
    }

    public void addToD(){

    }

    public void  removeFromD(){

    }

    public void getFromD(){

    }

    public void connect(){
        try {
            Connection con = getConnection();
            System.out.println(con.getSchema());

            //While loop for actually doing stuff on the database
            //Prompting user for input
            //Printing proper output

            con.close();
            //Closing connection properly
        }
            catch(Exception e){

        }
    }

    private static Connection getConnection() throws URISyntaxException, SQLException {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        return DriverManager.getConnection(dbUrl);
    }


}
