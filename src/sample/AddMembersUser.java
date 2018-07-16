package sample;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AddMembersUser {

    private  final SimpleStringProperty  nameCol;
    private  final SimpleStringProperty emailCol;
    private  final SimpleStringProperty addressCol;
    private  final SimpleIntegerProperty  mobileNumberCol;

    public AddMembersUser(String name, String email, String address, Integer mobile) {
        super();
        this.nameCol = new SimpleStringProperty( name );
        this.emailCol = new SimpleStringProperty( email);
        this.addressCol = new SimpleStringProperty(address);
        this.mobileNumberCol = new SimpleIntegerProperty( mobile );
    }


    public String getName() {
        return nameCol.get();
    }

    public String getEmail() {
        return emailCol.get();
    }

    public String getAddress() {
        return addressCol.get();
    }

    public int getPhone() {
        return mobileNumberCol.get();
    }



    public void setName(String name) {

        this.nameCol.set( name );
    }

    public void setEmail(String email) {
        this.emailCol.set(  email);
    }

    public void setAddress(String address) {
        this.addressCol.set( address );
    }

    public void setPhone(int mobile) {
        mobileNumberCol.set( mobile );
    }
}
































