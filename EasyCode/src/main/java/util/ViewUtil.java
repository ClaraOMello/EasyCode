package util;

import spark.*;

import java.io.File;
import java.util.*;
import static util.RequestUtil.*;
import dao.ColaboradorDAO;
import service.ColaboradorService;



public class ViewUtil {
    private static ColaboradorDAO colabDAO = new ColaboradorDAO();
    private static ColaboradorService colabService = new ColaboradorService();

    /**
     * Troca campos no templatePath por campos no model
     * @param request
     * @param model
     * @param templatePath
     * @return String
     */
    public static String render(Request request, Map<String, Object> model, String templatePath) {
        if (model == null) model = new HashMap<>();
        model.put("currentUser", getSessionCurrentUser(request));
        return replaceHtmlWithModel(model, templatePath);
    }
    
    static String aux;
    private static String replaceHtmlWithModel(Map<String, Object> model, String htmlPath) {
        aux = readFile(htmlPath);
        model.forEach((key, value) -> {
            // Pegar, por exemplo, <TOPICO> e substituir por conteúdo que foi colocado no model
            if (value != null) {
                aux = aux.replaceAll("<" + key.toLowerCase() + ">", value.toString());
            }
        });
        if (model.get("currentUser") != null) {
            aux = colabService.makePerfil(aux, colabDAO.getByNome((String)(model.get("currentUser"))));
        }
        
        return aux;
    }
    
    private static String readFile(String htmlPath) {
        String content = "";
		try{
			Scanner entrada = new Scanner(new File(htmlPath));
		    while(entrada.hasNext()){
		    	content += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		return content;
    }

}
