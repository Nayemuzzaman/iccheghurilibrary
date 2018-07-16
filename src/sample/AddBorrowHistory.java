package sample;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AddBorrowHistory {


    private final SimpleStringProperty emailCol;
    private final SimpleIntegerProperty bookidCol;
    private  final SimpleStringProperty  bookNameCol;
    private  final SimpleStringProperty BorrowedOnCol;
    private  final SimpleStringProperty ExpectedReturnCol;
    private  final SimpleIntegerProperty fineCol;



    public AddBorrowHistory(String emailCol,Integer bookid, String bookName, String borrowdate, String expectdate,Integer fineCol) {
        super();
        this.emailCol = new SimpleStringProperty( emailCol );
        this.bookidCol = new SimpleIntegerProperty( bookid );
        this.bookNameCol = new SimpleStringProperty( bookName);
        this.BorrowedOnCol = new SimpleStringProperty(borrowdate);
        this.ExpectedReturnCol = new SimpleStringProperty(expectdate);
        this.fineCol = new SimpleIntegerProperty(fineCol);

    }
    public String getemailCol(){
        return emailCol.get();
    }
    public int getBookidCol() {
        return bookidCol.get();
    }
    public String getBookNameCol() {
        return bookNameCol.get();
    }

    public String getBorrowedOnCol() {
        return BorrowedOnCol.get();
    }

    public String getExpectedReturnCol() {
        return ExpectedReturnCol.get();
    }
    public int getFineCol(){
        return fineCol.get();
    }
    public void setEmailCol(String emailCol){this.emailCol.set(emailCol);}

    public void setBookidCol(int bookidCol) {
        this.bookidCol.set(bookidCol);
    }

    public void setBookNameCol(String bookNameCol) {
        this.bookNameCol.set(bookNameCol);
    }

    public void setBorrowedOnCol(String BorrowedOnCol) {
        this.BorrowedOnCol.set(BorrowedOnCol);
    }


    public void setExpectedReturnCol(String ExpectedReturnCol) {
        this.ExpectedReturnCol.set(ExpectedReturnCol);
    }

    public void setFineCol(int fineCol){
        this.fineCol.set(fineCol);
    }



}

