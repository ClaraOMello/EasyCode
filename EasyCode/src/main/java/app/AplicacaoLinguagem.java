package app;

import static spark.Spark.*;
import service.LinguagemService;

public class AplicacaoLinguagem {
	
	private static LinguagemService lingService = new LinguagemService();
	
    public static void main(String[] args) {
        port(6789);
        
        staticFiles.location("");
        
        post("/ling/insert", (request, response) -> lingService.insert(request, response));

        get("/ling/:id", (request, response) -> lingService.get(request, response));
        
        get("/ling/list/:orderby", (request, response) -> lingService.getAll(request, response));

        get("/ling/update/:id", (request, response) -> lingService.getToUpdate(request, response));
        
        post("/ling/update/:id", (request, response) -> lingService.update(request, response));
           
        get("/ling/delete/:id", (request, response) -> lingService.delete(request, response));
        
        
        //mine
        get("/linguagens", (request, response) -> lingService.mostra(request, response));

             
    }
	
}
