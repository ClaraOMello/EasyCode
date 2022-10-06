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
        
    //************************ Linguagem  **************************//
//        post("/ling/insert", (request, response) -> lingService.insert(request, response));
//
//        get("/ling/:id", (request, response) -> lingService.get(request, response));
//        
//        get("/ling/list/:orderby", (request, response) -> lingService.getAll(request, response));
//
//        get("/ling/update/:id", (request, response) -> lingService.getToUpdate(request, response));
//        
//        post("/ling/update/:id", (request, response) -> lingService.update(request, response));
//           
//        get("/ling/delete/:id", (request, response) -> lingService.delete(request, response));
        
        
        //meus
        get("/linguagens", (request, response) -> lingService.mostra(request, response));
        
        
    //************************ Topico  **************************//
//        post("/top/insert", (request, response) -> topService.insert(request, response));
//
//        get("/top/:id", (request, response) -> topService.get(request, response));
//        
//        get("/top/list/:orderby", (request, response) -> topService.getAll(request, response));
//
//        get("/top/update/:id", (request, response) -> topService.getToUpdate(request, response));
//        
//        post("/top/update/:id", (request, response) -> topService.update(request, response));
//           
//        get("/top/delete/:id", (request, response) -> topService.delete(request, response));
        
        
        //meus
        get("/topicos/:ling", (request, response) -> topService.getAllLing(request, response));
        
        get("/conteudo/:id", (request, response) -> topService.getConteudo(request, response));

             
    }
	
}
