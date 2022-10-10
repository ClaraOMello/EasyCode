package app;

import static spark.Spark.*;
import service.LinguagemService;
import service.TopicoService;

public class Aplicacao {
	
	private static LinguagemService lingService = new LinguagemService();
	private static TopicoService topService = new TopicoService();
	
    public static void main(String[] args) {
        port(6789);
        
        staticFiles.location("");
        
        get("/linguagens", (request, response) -> lingService.mostra(request, response));
        get("/topicos/:ling", (request, response) -> topService.getAllLing(request, response));
        get("/conteudo/:id", (request, response) -> topService.getConteudo(request, response));

             
    }
	
}
