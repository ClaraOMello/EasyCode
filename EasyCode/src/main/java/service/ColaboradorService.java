package service;

import java.util.Scanner;
//import java.time.LocalDate;
import java.io.File;
//import java.time.LocalDateTime;
import java.util.List;
import dao.ColaboradorDAO;
import model.Colaborador;
import spark.Request;
import spark.Response;

public class ColaboradorService {
	private ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_NOME = 2;
	private final int FORM_ORDERBY_EMAIL = 3;

	public ColaboradorService() {
		//makeForm();
	}

	// makeForm's somente sao visiveis a administradores
/*
	public void makeForm() {
		makeForm(FORM_INSERT, new Colaborador(), FORM_ORDERBY_ID);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Colaborador(), orderBy);
	}

	public void makeForm(int tipo, Colaborador colaborador, int orderBy) {
		String nomeArquivo = "src/main/resources/contribua.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umServico = "";
		String novoServico = "";
		if(tipo != FORM_INSERT) {
			novoServico += "<font size=\"+1.5\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/colaborador/list/1\" id=\"addColaborador\"> Novo colaborador"; 
			novoServico += "<i class=\"fa-regular fa-square-plus\"></i></a></b></font>";

			form = form.replaceFirst("<NOVO-SERVICO>", novoServico);		
		}		
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/colaborador/";
			String name, servico, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir colaborador";
				servico = "massagem";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + colaborador.getId();
				name = "Atualizar Serviço (ID " + colaborador.getId() + ")";
				servico = colaborador.getNome();
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
			umServico += "\t\t\t<td>&nbsp;Serviço: <input class=\"input--register\" type=\"text\" name=\"servico\" value=\""+ servico +"\"></td>";
			umServico += "\t\t\t<td>Preço: <input class=\"input--register\" type=\"text\" name=\"preco\" value=\""+ colaborador.getPreco() +"\"></td>";
			umServico += "\t\t\t<td>Duração: <input class=\"input--register\" type=\"text\" name=\"duracao\" value=\""+ colaborador.getDuracao() +"\"></td>";
			umServico += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\" id=\"botaoPrinc\"></td>";
			umServico += "\t\t</tr>";
			umServico += "\t</table>";
			umServico += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umServico += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umServico += "\t\t<tr>";
			umServico += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Serviço (ID " + colaborador.getId() + ")</b></font></td>";
			umServico += "\t\t</tr>";
			umServico += "\t\t<tr>";
			umServico += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umServico += "\t\t</tr>";
			umServico += "\t\t<tr>";
			umServico += "\t\t\t<td>&nbsp;Serviço: "+ colaborador.getServico() +"</td>";
			umServico += "\t\t\t<td>Preço: "+ colaborador.getPreco() +"</td>";
			umServico += "\t\t\t<td>Duração: "+ colaborador.getDuracao() + " minutos</td>";
			umServico += "\t\t</tr>";
			umServico += "\t\t<tr>";
			//umServico += "\t\t\t<td>&nbsp;Data de fabricação: "+ produto.getDataFabricacao().toString() + "</td>";
			//umServico += "\t\t\t<td>Data de validade: "+ produto.getDataValidade().toString() + "</td>";
			umServico += "\t\t\t<td>&nbsp;</td>";
			umServico += "\t\t</tr>";
			umServico += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-SERVICO>", umServico);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\" id=\"tableListar\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Serviços</b></font>" +
				"<font size=\\\"+1.5\\\"><b>&nbsp;&nbsp;&nbsp;<a href=\\\"/colaborador/list/1\\\" id=\\\"addServico\\\"> Novo serviço "
				+ "<i class=\\\"fa-regular fa-square-plus\\\"></i></a></b></font> </td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/colaborador/list/" + FORM_ORDERBY_ID + "\"><b>ID</b></a></td>\n" +
        		"\t<td><a href=\"/colaborador/list/" + FORM_ORDERBY_NOME + "\"><b>Serviço</b></a></td>\n" +
        		"\t<td><a href=\"/colaborador/list/" + FORM_ORDERBY_EMAIL + "\"><b>Preço</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Colaborador> servicos;
		if (orderBy == FORM_ORDERBY_ID) {                 	servicos = colaboradorDAO.getOrderById();
		} else if (orderBy == FORM_ORDERBY_NOME) {		servicos = colaboradorDAO.getOrderByServico();
		} else if (orderBy == FORM_ORDERBY_EMAIL) {			servicos = colaboradorDAO.getOrderByPreco();
		} else {											servicos = colaboradorDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Colaborador s : servicos) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr class=\"listagem\" bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + s.getId() + "</td>\n" +
            		  "\t<td>" + s.getServico() + "</td>\n" +
            		  "\t<td>" + s.getPreco() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/colaborador/" + s.getId() + "\"><i class=\"fa-solid fa-magnifying-glass-plus\"></i></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/colaborador/update/" + s.getId() + "\"><i class=\"fa-solid fa-pen-to-square\"></i></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteServico('" + s.getId() + "', '" + s.getServico() + "', '" + s.getPreco() + "');\"><i class=\"fa-solid fa-trash\"></i></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-SERVICO>", list);				
	}
*/	
	
	void makeLogin() {
	    String nomeArquivo = "src/main/resources/html/login.html";
        form = "";
        try{
            Scanner entrada = new Scanner(new File(nomeArquivo));
            while(entrada.hasNext()){
                form += (entrada.nextLine() + "\n");
            }
            entrada.close();
        }  catch (Exception e) { System.out.println(e.getMessage()); }
	}
	
	public Object insert(Request request, Response response) {
		String nome = request.queryParams("nome");
		String email = request.queryParams("email");
		String senha = request.queryParams("senha");
		// String confirmaSenha = request.queryParams("confirmaSenha");
		int id = colaboradorDAO.getLastId() + 1;
		
		String resp = "";
		
		Colaborador colaborador = new Colaborador(id, false, nome, email, senha);
		
		if(colaboradorDAO.insert(colaborador) == true) {
            resp = "Usuário (" + nome + ") criado!";
            response.status(201); // 201 Created
		} else {
			resp = "Falha na criação do usuário (" + nome + ")!";
			response.status(404); // 404 Not found
		}
		
		makeLogin();
		
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Colaborador colaborador = (Colaborador) colaboradorDAO.get(id);
		
		if (colaborador != null) {
			response.status(200); // success
			// makeForm(FORM_DETAIL, colaborador, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Serviço " + id + " não encontrado.";
    		// makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Colaborador colaborador= (Colaborador) colaboradorDAO.get(id);
		
		if (colaborador != null) {
			response.status(200); // success
			// makeForm(FORM_UPDATE, colaborador, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Serviço " + id + " não encontrado.";
    		// makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		// makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
		Colaborador colaborador = colaboradorDAO.get(id);
        String resp = "";       

        if (colaborador != null) {
        	colaborador.setAdm(Boolean.parseBoolean(request.queryParams("adm")));
        	colaborador.setNome(request.queryParams("nome"));
        	colaborador.setEmail(request.queryParams("email"));
        	colaborador.setSenha(request.queryParams("senha"));
        	//produto.setDataFabricacao(LocalDateTime.parse(request.queryParams("dataFabricacao")));
        	//produto.setDataValidade(LocalDate.parse(request.queryParams("dataValidade")));
        	colaboradorDAO.update(colaborador);
        	response.status(200); // success
            resp = "Serviço (ID " + colaborador.getId() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Serviço (ID " + colaborador.getId() + ") não encontrado!";
        }
		// makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Colaborador colaborador = colaboradorDAO.get(id);
        String resp = "";       

        if (colaborador != null) {
            colaboradorDAO.delete(id);
            response.status(200); // success
            resp = "Usuário (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Usuário (" + id + ") não encontrado!";
        }
		// makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}
