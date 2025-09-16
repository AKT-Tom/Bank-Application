import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;
import java.math.BigDecimal;



public class Main{

    public static void main(String [] args){
        Scanner input = new Scanner(System.in);
        AddUser add = new AddUser();
        Verification validate = new Verification();

        System.out.print("Enter your ID: ");
        String ID = input.nextLine();

        System.out.print("\nEnter your first name: ");
        String firstname = input.nextLine();

        System.out.print("\nEnter your last name: ");
        String lastname = input.nextLine();

        System.out.print("\nEnter your current address: ");
        String address = input.nextLine();

        System.out.print("\nEnter your current city: ");
        String city = input.nextLine();

        System.out.print("\nEnter your current email: ");
        String email = input.nextLine();

        System.out.print("\nEnter your password: ");
        String password = input.nextLine();

        Users User = new Users(new BigDecimal("0.00"), ID, firstname, lastname, password, city,address, email);

        if (validate.isValid(User)){
            add.addUser(User);
        }
        else{
            System.out.println("Error, invalid ID.");
        }




    }
}