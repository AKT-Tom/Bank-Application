import java.util.Scanner;
//This is used to log in users into the app, so calls verification to confirm their email and password matches up
public class login {

    public static void LogUser(){
        Users user = new Users();

        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the Bank of Dreams!");
        System.out.println("Please enter your email and password to continue.");
        System.out.print("\nEmail: ");
        String email = input.nextLine();
        System.out.print("\nPassword: ");
        String password = input.nextLine();

        user.setEmail(email);

        Verification.CheckLogIn(user,password);
    }

}
