package service;

import java.util.Scanner;
import java.io.File;
import java.util.List;
import dao.LinguagemDAO;
import model.Linguagem;
import spark.Request;
import spark.Response;

public class LinguagemService {
	private LinguagemDAO lingDAO = new LinguagemDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_NOME = 2;

	public LinguagemService() {
		makeForm();
	}

	public void makeForm() {
		makeForm(FORM_INSERT, new Linguagem(), FORM_ORDERBY_ID);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Linguagem(), orderBy);
	}

	
	public void makeForm(int tipo, Linguagem ling, int orderBy) {
		String nomeArquivo = "src/main/resources/linguagens.html";
		form = "";
		String list = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		List <Linguagem> lings = lingDAO.get();;
		for (Linguagem lgs : lings) {
			list += "<a href=\"/topicos/" + lgs.getId() + "\"><img src=\" "+ lgs.getImg() +"\" alt=\"card " 
					+ lgs.getNome() + " \" class=\"cardsLinguagens\"></a>\r\n";
		}
		form = form.replaceFirst("<CARDS-LINGUAGEM>", list);
		
		/*
		String umServico = "";		
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/ling/";
			String name, nome, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Linguagem";
				nome = "massagem";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + ling.getId();
				name = "Atualizar Linguagem (ID " + ling.getId() + ")";
				nome = ling.getNome();
				buttonLabel = "Atualizar";
			}
			umServico += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umServico += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umServico += "\t\t<tr>";
			umServico += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umServico += "\t\t</tr>";
			umServico += "\t\t<tr>";
			umServico += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umServico += "\t\t</tr>";
			umServico += "\t\t<tr>";
			umServico += "\t\t\t<td>&nbsp;Nome: <input class=\"input--register\" type=\"text\" name=\"nome\" value=\""+ nome +"\"></td>";
			umServico += "\t\t\t<td>Endereço Imagem: <input class=\"input--register\" type=\"text\" name=\"img\" value=\""+ ling.getImg() +"\"></td>";
			umServico += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\" id=\"botaoPrinc\"></td>";
			umServico += "\t\t</tr>";
			umServico += "\t</table>";
			umServico += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umServico += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umServico += "\t\t<tr>";
			umServico += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Serviço (ID " + ling.getId() + ")</b></font></td>";
			umServico += "\t\t</tr>";
			umServico += "\t\t<tr>";
			umServico += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umServico += "\t\t</tr>";
			umServico += "\t\t<tr>";
			umServico += "\t\t\t<td>&nbsp;Nome: "+ ling.getNome() +"</td>";
			umServico += "\t\t\t<td>Endereço da Imagem: "+ ling.getImg() +"</td>";
			umServico += "\t\t</tr>";
			umServico += "\t\t<tr>";
			umServico += "\t\t\t<td>&nbsp;</td>";
			umServico += "\t\t</tr>";
			umServico += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-SERVICO>", umServico);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\" id=\"tableListar\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Linguagens</b></font>" +
				"<font size=\\\"+1.5\\\"><b>&nbsp;&nbsp;&nbsp;<a href=\\\"/ling/list/1\\\" id=\\\"addLinguagem\\\"> Nova linguagem "
				+ "<i class=\\\"fa-regular fa-square-plus\\\"></i></a></b></font> </td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/ling/list/" + FORM_ORDERBY_ID + "\"><b>ID</b></a></td>\n" +
        		"\t<td><a href=\"/ling/list/" + FORM_ORDERBY_NOME + "\"><b>Linguagem</b></a></td>\n" +
        		"\t<td ><b>&Img</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Linguagem> lings;
		if (orderBy == FORM_ORDERBY_ID) {                 	lings = lingDAO.getOrderById();
		} else if (orderBy == FORM_ORDERBY_NOME) {		lings = lingDAO.getOrderByNome();
		} else {											lings = lingDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Linguagem lgs : lings) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr class=\"listagem\" bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + lgs.getId() + "</td>\n" +
            		  "\t<td>" + lgs.getNome() + "</td>\n" +
            		  "\t<td>" + lgs.getImg() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/ling/" + lgs.getId() + "\"><i class=\"fa-solid fa-magnifying-glass-plus\"></i></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/ling/update/" + lgs.getId() + "\"><i class=\"fa-solid fa-pen-to-square\"></i></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteLinguagem('" + lgs.getId() + "', '" + lgs.getNome() + "', '" + lgs.getImg() + "');\"><i class=\"fa-solid fa-trash\"></i></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-SERVICO>", list);
		*/				
	}
	
	
	//meus
	
	public Object mostra(Request request, Response response) {
		makeForm(1);
		response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;		
	}
	
	
	
	
	
	
	
	
	////// prontos ///////
	
	
	public Object insert(Request request, Response response) {
		String nome = request.queryParams("nome");
		String img = request.queryParams("img");
		int id = Integer.parseInt(request.queryParams("id"));
		
		String resp = "";
		
		Linguagem ling = new Linguagem(id, nome, img);
		
		if(lingDAO.insert(ling) == true) {
            resp = "Linguagem (" + nome + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Linguagem (" + nome + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Linguagem ling = (Linguagem) lingDAO.get(id);
		
		if (ling != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, ling, FORM_ORDERBY_ID);
        } else {
            response.status(404); // 404 Not found
            String resp = "Linguagem " + id + " não encontrada.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Linguagem ling= (Linguagem) lingDAO.get(id);
		
		if (ling != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, ling, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Linguagem " + id + " não encontrada.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
		Linguagem ling = lingDAO.get(id);
        String resp = "";       

        if (ling != null) {
        	ling.setNome(request.queryParams("nome"));
        	ling.setImg(request.queryParams("img"));
        	lingDAO.update(ling);
        	response.status(200); // success
            resp = "Linguagem (ID " + ling.getId() + ") atualizada!";
        } else {
            response.status(404); // 404 Not found
            resp = "Linguagem (ID " + ling.getId() + ") não encontrada!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Linguagem ling = lingDAO.get(id);
        String resp = "";       

        if (ling != null) {
            lingDAO.delete(id);
            response.status(200); // success
            resp = "Linguagem (" + id + ") excluída!";
        } else {
            response.status(404); // 404 Not found
            resp = "Linguagem (" + id + ") não encontrada!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}
