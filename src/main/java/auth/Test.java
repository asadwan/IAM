package auth;

import model.User;
import model.UserDAO;

public class Test {

    public static void main(String[] args) {
        UserDAO.createUser(new User("aaaaa","aaaaa","aaaaa", "aaaaa"));
        System.out.println(UserDAO.getUser("asadwan").getUserType());

    }
}
