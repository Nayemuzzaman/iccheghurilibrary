package sample;

import javafx.scene.control.Label;

import javax.swing.*;


public class Calculator extends JFrame{

    JLabel lF,lS,lT;
    JTextField firstTxtfield,secondTextField,thirdTextField;
    JButton btnAdd,btnSub,btnMult,btnDiv;

    Calculator(){
        JFrame f =new JFrame( "Calculator" );
        this.setSize( 290,200 );
        this.setResizable( false );
        this.setLocation( 300,250 );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setLayout(null);
        addComponents();
        addBounds();
    }
    public void addComponents()
    {
        this.lF = new JLabel("1st Number:");
        this.lS = new JLabel("2nd Number:");
        this.lT = new JLabel("Result:");

        this.firstTxtfield = new JTextField(  );
        this.secondTextField = new JTextField(  );
        this.thirdTextField = new JTextField(  );

        this.btnAdd = new JButton( "+" );
        this.btnSub = new JButton( "-" );
        this.btnMult = new JButton( "*" );
        this.btnDiv = new JButton( "/" );

        this.add( lF );
        this.add( lS );
        this.add( lT );

        this.add( firstTxtfield );
        this.add( secondTextField );
        this.add( thirdTextField );

        this.add( btnAdd );
        this.add( btnSub );
        this.add( btnMult );
        this.add( btnDiv );
    }

    public void addBounds(){
        this.lF.setBounds(10,10,140,20);
        this.firstTxtfield.setBounds(160,10,80,20);

        this.lS.setBounds(10,40,140,20);
        this.secondTextField.setBounds(160,40,80,20);

        this.lT.setBounds(10,70,140,20);
        this.thirdTextField.setBounds(160,70,80,20);

        this.btnAdd.setBounds(10,100,50,20);
        this.btnSub.setBounds(70,100,50,20);
        this.btnMult.setBounds(130,100,50,20);
        this.btnDiv.setBounds(190,100,50,20);
    }
    public static void main(String[] args)
    {
        Calculator calculator = new Calculator();

        calculator.setVisible( true );
    }

}
