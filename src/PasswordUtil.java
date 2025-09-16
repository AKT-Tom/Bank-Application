import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    public String hash(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
}
