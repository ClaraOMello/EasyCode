package util;

//import java.util.ArrayList;
import java.util.List;
import dao.ColaboradorDAO;
import model.Colaborador;

import spark.*;

//final class User {
//    public String email;
//    public String password;
//    
//}

public class UserController {
    private static ColaboradorDAO colabDAO = new ColaboradorDAO();
    
    private static List<Colaborador> colabs;

    public static Route serveUserPage = (Request request, Response response) -> {
        LoginController.ensureUserIsLoggedIn(request, response);
        return ViewUtil.render(request, null, Path.toFile(Path.Web.PERFIL));
    };
    
    // Cadastro feito no ColaboradorService.insert()
//    public static void register(String username, String password) {
//        User u = new User();
//        u.password = password;
//        u.email = username;
//        mockUsers.add(u);
//    }

    static boolean achou;
    public static boolean authenticate(String username, String password) {
        colabs = colabDAO.get();
        if (username == null || password == null) return false;
        if (username.isEmpty() || password.isEmpty()) {
            return false;
        }
        
        achou = false;
        colabs.forEach(c -> {
            if (password.equals(c.getSenha()) && username.equals(c.getNome())) achou = true;
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