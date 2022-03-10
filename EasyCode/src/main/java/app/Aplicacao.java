package app;


import spark.*;
import static spark.Spark.*;

import service.LinguagemService;
import service.TopicoService;
import spark.Response;
import util.Path;
import util.UserController;
import util.ViewUtil;
import util.LoginController;



public class Aplicacao {
    

    private static LinguagemService lingService = new LinguagemService();
    private static TopicoService topService = new TopicoService();

    public static void main(String[] args) {
        port(6789);

        staticFiles.location("/"); // src/main/resources/

        before("*", removeTrailingSlashes);
        before("*", removeHtmlFromPath);
        before("*", noIndex);
        before("*", contribuaToLogin);


        get(Path.Web.INDEX, (request, response) -> ViewUtil.render(request, null,  Path.toFile(Path.Web.INDEX)));
        get(Path.Web.LINGUAGENS, (request, response) -> lingService.mostra(request, response));
        get(Path.Web.CONTEUDO, (request, response) -> topService.getConteudo(request, response));

        get(Path.Web.LOGIN, LoginController.serveLoginPage); // forma diferente de fazer a mesma coisa dos de cima
        post(Path.Web.LOGIN, LoginController.handleLoginPost);
        get(Path.Web.DESLOGAR, LoginController.handleLogoutPost);

        get(Path.Web.PERFIL, UserController.serveUserPage); // Talvez colaborador?


        // Isso aqui está errado. Deveria ser /linguagem/:ling e o lingService.getLing
        // Nem tenho certeza
        get("/topicos/:ling", (request, response) -> topService.getAllLing(request, response));


    }


    public static Filter removeTrailingSlashes = (Request request, Response response) -> {
        if (request.pathInfo().endsWith("/") && request.pathInfo().length() > 1) {
            String s = request.pathInfo();
            
            response.redirect(s.substring(0, s.length() -1));
        }
    };
    public static Filter removeHtmlFromPath = (Request request, Response response) -> {
        
        if (request.pathInfo().contains(".html")) {
            response.redirect(request.pathInfo().replace(".html", ""));
        }
    };
    public static Filter noIndex = (Request request, Response response) -> {
        if (request.pathInfo().equals("/index")) {
            response.redirect("/");
        }
    };
    public static Filter contribuaToLogin = (Request request, Response response) -> {
        if (request.pathInfo().equals("/contribua")) {
            response.redirect("/login");
        }
    };

}
