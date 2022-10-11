package util;

import spark.*;

import java.util.*;

// Importando assim pra não precisar fazer RequestUtil.metodo()
// Só metodo()
import static util.RequestUtil.*; 

public class LoginController {
    public final static String LOGIN_FAILED = "Usuário e senha inválido";
    public final static String LOGIN_SUCCESS = "Login feito com sucesso";

    public static Route serveLoginPage = (Request request, Response response) -> {
        if (userIsLoggedIn(request)) response.redirect(Path.Web.PERFIL);
        System.out.println("Is user logged in?: " + userIsLoggedIn(request));
        Map<String, Object> model = new HashMap<>();
        model.put("loggedOut", removeSessionAttrLoggedOut(request));
        model.put("loginRedirect", removeSessionAttrLoginRedirect(request));
        return ViewUtil.render(request, model, Path.toFile(Path.Web.LOGIN));
    };


    public static Route handleLoginPost = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        String user = null;
        String pass = null;
        // String name = null;
        try {
        user = getQueryUsername(request);
        pass = getQueryPassword(request);
        // name = getQueryName(request);
        } catch(Exception e) {}

//        boolean tryingToRegister = name != null;
//        if (tryingToRegister) {
//            UserController.register(user, pass);
//        } else 
        
        if (!UserController.authenticate(user, pass)) {
            model.put("authenticationFailed", LOGIN_FAILED);
            return ViewUtil.render(request, model, Path.toFile(Path.Web.LOGIN));
        }
        model.put("authenticationSucceeded", LOGIN_SUCCESS);
        request.session().attribute("currentUser", user);

//        if (getQueryLoginRedirect(request) != null) { // Isso daqui n funciona pq o form lá n tem esse camo
//            response.redirect(getQueryLoginRedirect(request));
//        }
        response.redirect(Path.Web.PERFIL);
        return null;
    };


    public static Route handleLogoutPost = (Request request, Response response) -> {
        request.session().removeAttribute("currentUser");
        request.session().attribute("loggedOut", true);
        response.redirect(Path.Web.INDEX);
        return null;
    };


    public static boolean userIsLoggedIn(Request request) {
        return request.session().attribute("currentUser") != null;
        
    }
    // A origem da requisição (request.pathInfo()) é salva na sessão para o
    // usuário poder ser redirecionado depois de fazer o login
    public static void ensureUserIsLoggedIn(Request request, Response response) {
        System.out.println("Current user: " + request.session().attribute("currentUser"));
        if (!userIsLoggedIn(request)) {
            request.session().attribute("loginRedirect", request.pathInfo());
            response.redirect(Path.Web.LOGIN);
        }
    };

}
