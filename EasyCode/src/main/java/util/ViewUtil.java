package util;

import spark.*;

import java.io.File;
import java.util.*;
import static util.RequestUtil.*;



public class ViewUtil {

    // Renders a template given a model and a request
    // The request is needed to check the user session for language settings
    // and to see if the user is logged in
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
