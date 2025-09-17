import org.mindrot.jbcrypt.BCrypt;
//This class just hashes the password before storing it in the database and checks the password at user log.
public class PasswordUtil {
    //Hashes password at user sign up
    public String hash(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    //Checks the password at user log in by sending a query to SQL for the password at specified email
    public static boolean checkPassword(String password, String hashed){
        return BCrypt.checkpw(password, hashed);
    }
}
