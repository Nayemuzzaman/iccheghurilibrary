package sample;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AddBookUser {

    private final SimpleIntegerProperty bookidCol;
    private  final SimpleStringProperty  bookNameCol;
    private  final SimpleStringProperty authorNameCol;
    private  final SimpleIntegerProperty totalCopyCol;
    private  final SimpleIntegerProperty availableCopyCol;
    private  final SimpleStringProperty  shelfCol;

    public AddBookUser(Integer bookid, String bookName, String authorName, Integer totalCopy, Integer availableCopy, String shelf) {
        super();
        this.bookidCol = new SimpleIntegerProperty( bookid );
        this.bookNameCol = new SimpleStringProperty( bookName);
        this.authorNameCol = new SimpleStringProperty(authorName);
        this.totalCopyCol = new SimpleIntegerProperty(totalCopy);
        this.availableCopyCol = new SimpleIntegerProperty(availableCopy);
        this.shelfCol = new SimpleStringProperty( shelf );
    }


    public int getBookid() {
        return bookidCol.get();
    }

    public String getBookName() {
        return bookNameCol.get();
    }

    public String getAuthorName() {
        return authorNameCol.get();
    }

    public int getTotalCopy() {
        return totalCopyCol.get();
    }

    public int getAvailableCopy() {
        return availableCopyCol.get();
    }

    public String getShelf() {
        return shelfCol.get();
    }

    public void setBookid(int bookid) {

        this.bookidCol.set( bookid );
    }

    public void setBookName(String bookName) {
        this.bookNameCol.set(  bookName);
    }

    public void setAuthorName(String authorName) {
        this.authorNameCol.set( authorName );
    }

    public void setTotalCopy(int totalCopy) {
        this.totalCopyCol.set( totalCopy );
    }

    public void setAvailableCopy(int availableCopy) {
        this.availableCopyCol.set( availableCopy );
    }

    public void setShelf(String shelf) {
        shelfCol.set( shelf );
    }
}
