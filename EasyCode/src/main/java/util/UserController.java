package util;

//import java.util.ArrayList;
import java.util.List;


import dao.ColaboradorDAO;
import model.Colaborador;
import org.jasypt.util.password.StrongPasswordEncryptor;

import spark.*;

//final class User {
//    public String email;
//    public String password;
//    
//}

public class UserController {
    private static ColaboradorDAO colabDAO = new ColaboradorDAO();
    
    private static List<Colaborador> colabs;

    /**
     * Retorna a página de perfil do usuário
     * @param request
     * @param response
     * @return html
     */
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
            if ((password.equals(c.getSenha()) || checkPassword(password, c.getSenha())) && username.equals(c.getNome())) achou = true;
        });
        return achou;
            
    }
    public static boolean checkPassword(String inputPassword, String encryptedStoredPassword) {
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        try {
        return encryptor.checkPassword(inputPassword, encryptedStoredPassword);
        } catch(Exception e) { return false; }
    }
    

}