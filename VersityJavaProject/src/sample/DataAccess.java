package sample;
import java.sql.*;

public class DataAccess{
    Connection conn;
    String JDBC_DRIVER;
    String DB_URL;
    Statement stmt;
    String USER;
    String PASS;
    ResultSet rs;
    public DataAccess(){
        JDBC_DRIVER = "com.mysql.jdbc.Driver";
        DB_URL = "jdbc:mysql://localhost:3306/login";
        USER = "root";
        PASS = "";

        try{
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    //User Registration
    public void userReg(String Name, String Email, String Address, String Mobilenumber, String Password, String confirmpassword) {

        JDBC_DRIVER = "com.mysql.jdbc.Driver";
        DB_URL = "jdbc:mysql://localhost:3306/login";
        USER = "root";
        PASS = "";


        try{
            Class.forName( JDBC_DRIVER );
            System.out.println("connecting user regitration");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);


           // stmt=con.createStatement();

           // stmt.executeUpdate(sql1);
            /*PreparedStatement pst= conn.prepareStatement("INSERT INTO "
                    +"signup"
                    + "(name, email, address, mobilenumber, password)"
                    +"VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);*/
            String sql = "insert into signup (name, email, address, mobilenumber, password, confirmpassword) values(?,?,?,?,?,?)";
            PreparedStatement pst = null;
            pst = conn.prepareStatement( sql );

            pst.setString( 1, Name );
            pst.setString( 2, Email );
            pst.setString( 3, Address );
            pst.setString( 4, Mobilenumber );
            pst.setString( 5, Password);
            pst.setString( 6, confirmpassword);

            pst.execute();


        }catch(Exception e){ System.out.println(e);}

    }

    //check user name and password
    public boolean userCheckLogin(String email, String password){

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/login","root","");
            PreparedStatement pst = conn.prepareStatement( "Select *from signup where email =? and password=? " );

            pst.setString( 1,email );
            pst.setString( 2,password );
            ResultSet rs = pst.executeQuery();

            if(rs.next())
                return true;
            else
                return false;


        }catch(Exception e){ System.out.println(e);}

        return false;

    }


}