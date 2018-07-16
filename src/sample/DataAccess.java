package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    //check user name and password
    public boolean adminCheckLogin(String email, String password){

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/login","root","");
            PreparedStatement pst = conn.prepareStatement( "Select *from adminlogin where usertestfield =? and password=? " );

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
    //when you login store your email
    public void loginData(String email){
        try{
            System.out.println("email store");
            String sql = "insert into loginmail(email) values(?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,email);
            pst.execute();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    ArrayList<String> seatId = new ArrayList<String>();

    //ticket javafx<hasib-rahul project>
    public void ticket(String serial){

        try{
            System.out.println("ticket store");
            String sql = "insert into seat(fillup) values(?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,serial);
            pst.execute();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    ArrayList<String> seatQuery = new ArrayList<String>();
    String s = new String("-fx-padding: 2 5 2 5;-fx-border-color: white;-fx-border-width: 2;-fx-background-radius: 0;-fx-background-color: #32CD32;-fx-font-family: Helvetica, Arial, sans-serif;-fx-font-size: 8pt;-fx-font-weight: bold;-fx-text-fill: white;-fx-background-insets: 0 0 0 0, 0, 1, 2;");

    public void seatSelect(ActionEvent e){

        Button btn = (Button) e.getSource();
        String id = btn.getId();

        if(seatId.contains(id)){
            seatQuery.remove(seatId.indexOf(id));
            seatId.remove(id);
            btn.setStyle(s);

            //print(seatId);
            //print(seatQuery);

        }

    }


    public void addBookTable(String Bookid,String BookName,String Author, String TotalCopy, String AvailableCopy,String Shelf){
        JDBC_DRIVER = "com.mysql.jdbc.Driver";
        DB_URL = "jdbc:mysql://localhost:3306/login";
        USER = "root";
        PASS = "";

            try{
                Class.forName( JDBC_DRIVER );
                System.out.println("connecting user add book");
                conn = DriverManager.getConnection(DB_URL,USER,PASS);

                String sql = "insert into addbook(bookid, bookname, authorname, totalcopy, availablecopy,shelf) values(?,?,?,?,?,?)";
                PreparedStatement pst = null;
                pst = conn.prepareStatement( sql );
                pst.setString( 1,Bookid );
                pst.setString( 2,BookName );
                pst.setString( 3,Author );
                pst.setString( 4,TotalCopy );
                pst.setString( 5,AvailableCopy );
                pst.setString( 6,Shelf );
                pst.execute();


            }catch(Exception e){
                System.out.println(e);
            }
    }


   public List<AddBookUser> getBookInfo(){
       Connection conn;
       List l1 = new LinkedList();
       Statement st;
       ResultSet rs;
       String url = "jdbc:mysql://localhost/login";
       String user = "root";
       String pass = "";
       String driver = "com.mysql.jdbc.Driver";
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
            st = conn.createStatement();
            String recordQuery = ("Select * from addbook");
            rs = st.executeQuery(recordQuery);

            while (rs.next()) {
                //get string from db,whichever way
                //  data.add(new AddBookUser(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6)));
                Integer bookid = rs.getInt( "bookid" );
                String bookName = rs.getString( "bookname" );
                String bookAuthor = rs.getString(  "authorname" );
                Integer totalCopy = rs.getInt( "totalcopy" );
                Integer availableCopy = rs.getInt(  "availablecopy" );
                String shelf = rs.getString(  "shelf" );

                l1.add( new AddBookUser(  bookid, bookName, bookAuthor, totalCopy, availableCopy, shelf ));

                System.out.println(bookid+ "," + bookName + "," + bookAuthor + "," +totalCopy+ "," +availableCopy+ "," +shelf);


                }

        }  catch (ClassNotFoundException | SQLException ex){
           Logger.getLogger(AddBook.class.getName()).log( Level.SEVERE, null, ex);
        }

        return l1;
    }
    //get borrow history

    public List<AddBorrowHistory> getBorrowHistory(){
        Connection conn;
        List l1 = new LinkedList();
        Statement st;
        ResultSet rs;
        String url = "jdbc:mysql://localhost/login";
        String user = "root";
        String pass = "";
        String driver = "com.mysql.jdbc.Driver";
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
            st = conn.createStatement();
            String recordQuery = ("Select * from borrowhistory");
            rs = st.executeQuery(recordQuery);

            while (rs.next()) {
                String email = rs.getString("email");
                Integer bookid = rs.getInt( "bookid" );
                String bookname = rs.getString( "bookname" );
                String borrowdate = rs.getString(  "borrowdate" );
                String expectdate = rs.getString( "expectdate" );
                Integer fine = rs.getInt( "fine" );
//                String returndate = rs.getString( "returndate" );
//                Integer fine = rs.getInt( "fine" );

                l1.add( new AddBorrowHistory(  email,bookid, bookname, borrowdate, expectdate,fine));

               System.out.println(bookid+ "," + bookname + "," + expectdate + "," +expectdate);


            }

        }  catch (ClassNotFoundException | SQLException ex){
            Logger.getLogger(AddBorrowHistory.class.getName()).log( Level.SEVERE, null, ex);
        }

        return l1;
    }

    //show Member list in admin

    public List<AddMembersUser> getMemberInfo(){
        Connection conn;
        List l1 = new LinkedList();
        Statement st;
        ResultSet rs;
        String url = "jdbc:mysql://localhost/login";
        String user = "root";
        String pass = "";
        String driver = "com.mysql.jdbc.Driver";
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
            st = conn.createStatement();
            String recordQuery = ("Select * from signup");
            rs = st.executeQuery(recordQuery);

            while (rs.next()) {
                //get string from db,whichever way
                String name = rs.getString( "name" );
                String email = rs.getString(  "email" );
                String address = rs.getString( "address" );
                Integer mobile = rs.getInt(  "mobilenumber" );

                l1.add( new AddMembersUser(  name, email, address, mobile ));

                //System.out.println(name+ "," + email + "," + address + "," +mobile);


            }

        }  catch (ClassNotFoundException | SQLException ex){
            Logger.getLogger(AddBook.class.getName()).log( Level.SEVERE, null, ex);
        }

        return l1;
    }
    //Delete members list in admin
    public void getMemberDelete(){
        Connection conn= null;
        List l1 = new LinkedList();
        Statement stmt= null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost/login";
        String user = "root";
        String pass = "";
        String driver = "com.mysql.jdbc.Driver";
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
            //conn = getConnection();
            String query = "select name, email,address, mobilenumber from signup";
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString(1);
                String email = rs.getString(2);
                String address = rs.getString(3);
                int  mobilenumber = rs.getInt(4);
                System.out.println("name=" + name + "  email=" + email+ " address "+ address+ " mobile"+ mobilenumber);
            }

            rs.first();
           rs.deleteRow();
//            rs.beforeFirst();
//            while (rs.next()) {
//                String name = rs.getString(1);
//                String email = rs.getString(2);
//                String address = rs.getString(3);
//                int  mobilenumber = rs.getInt(4);
//                System.out.println("name=" + name + "  email=" + email+ " address "+ address+ " mobile"+ mobilenumber);
//            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    //User Registration
    public void borrowhistory(String email, String bookid, String bookname, String borrowdate, String expectdate) {

        JDBC_DRIVER = "com.mysql.jdbc.Driver";
        DB_URL = "jdbc:mysql://localhost:3306/login";
        USER = "root";
        PASS = "";


        try{
            Class.forName( JDBC_DRIVER );
            System.out.println("connecting borrow history");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);


            String sql = "insert into borrowhistory (email, bookid, bookname, borrowdate, expectdate) values(?,?,?,?,?)";
            PreparedStatement pst = null;
            pst = conn.prepareStatement( sql );

            pst.setString( 1, email );
            pst.setString( 2, bookid );
            pst.setString( 3, bookname );
            pst.setString( 4, borrowdate );
            pst.setString( 5, expectdate );

            pst.execute();
            //System.out.println(sql);


        }catch(Exception e){ System.out.println(e);}

    }

    //Delete members list in admin
    public void getBookDelete(){
        Connection conn= null;
        List l1 = new LinkedList();
        Statement stmt= null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost/login";
        String user = "root";
        String pass = "";
        String driver = "com.mysql.jdbc.Driver";
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
            //conn = getConnection();
            String query = "select * from addbook";
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int bookid = rs.getInt( "bookid" );
                String bookName = rs.getString( "bookname" );
                String bookAuthor = rs.getString(  "authorname" );
                int totalCopy = rs.getInt( "totalcopy" );
                int availableCopy = rs.getInt(  "availablecopy" );
                String shelf = rs.getString(  "shelf" );
                System.out.println("bookid=" + bookid + "  bookName=" + bookName+ " bookAuthor "+ bookAuthor+ " totalCopy"+ totalCopy+" available copy "+availableCopy+" Shelf"+ shelf);

            }
            rs.first();
            rs.deleteRow();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}


