import java.math.BigDecimal;
import java.util.Scanner;
//Handles user sign up logic, so calls verification as well to check if everything is valid or if the user is already registered before adding them to the database
public class SignUp {
    public static void signUp(){
        Scanner input = new Scanner(System.in);
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

        input.close();

        Users User = new Users(new BigDecimal("0.00"), ID, firstname, lastname, password, city,address, email);

        if(Verification.CheckSignUp(User)){
                UserOperations.AddUser(User);
        }
        //Do not need an else because Verification already handles all of the statements to the user on why they might not have been able to sign in such as invalid ID, already existing in the database, etc
    }
}
