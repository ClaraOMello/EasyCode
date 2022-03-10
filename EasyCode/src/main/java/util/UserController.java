package util;

import java.util.ArrayList;
// Talvez isso daqui deveria ser o colaborador?

import spark.*;

final class User {
    public String email;
    public String password;
    
}

public class UserController {
    private static ArrayList<User> mockUsers = new ArrayList<User>();

    public static Route serveUserPage = (Request request, Response response) -> {
        LoginController.ensureUserIsLoggedIn(request, response);
        return ViewUtil.render(request, null, Path.toFile(Path.Web.PERFIL));
    };
    
    public static void register(String username, String password) {
        User u = new User();
        u.password = password;
        u.email = username;
        mockUsers.add(u);
    }

    static boolean achou;
    public static boolean authenticate(String username, String password) {
        if (username == null || password == null) return false;
        if (username.isEmpty() || password.isEmpty()) {
            return false;
        }
        User user = new User();
        user.password = password;
        user.email = username;
        achou = false;
        mockUsers.forEach(u -> {
            if (user.password.equals(u.password) && user.email.equals(u.email)) achou = true;
        });
        return achou;
            
    }

//    public static void setPassword(String username, String oldPassword, String newPassword) {
//        if (authenticate(username, oldPassword)) {
//            String newSalt = BCrypt.gensalt();
//            String newHashedPassword = BCrypt.hashpw(newSalt, newPassword);
//            // Update the user salt and password
//        }
//    }
}