public class Verification {
    boolean isValid(Users user){
        if (user.getID() != null && user.getID().matches("\\d{13}") && user.getID().matches("\\d+")){
            return true;
        }
        else{
            return false;
        }
    }
}
