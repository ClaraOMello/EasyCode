package service;

import java.util.Scanner;
import java.io.File;
import java.util.List;
import dao.TopicoDAO;
import model.Topico;
import spark.Request;
import spark.Response;

public class TopicoService {
	private TopicoDAO topicoDAO = new TopicoDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_NOME = 2;

	public TopicoService() {
		makeForm();
	}

	public void makeForm() {
		makeForm(FORM_INSERT, new Topico(), FORM_ORDERBY_ID);
	}

	
//	public void makeForm(int orderBy) {
//		makeForm(FORM_INSERT, new Topico(), orderBy);
//	}

	
	public void makeForm(int tipo, Topico topico, int orderBy) {
		String nomeArquivo = "src/main/resources/linguagemTopicos.html";
		form = "";
		String list="";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		list += "<h2 class=\"tituloLinguagemTopicos\">" + topicoDAO.getNomeLinguagem(532) + "</h2>\r\n"
				+ "            <ul class=\"textoTopicos\">";
		
		List<Topico> topicos = topicoDAO.get();
		for (Topico t : topicos) {
			list += "<a href=\"conteudoDaLinguagem.html?id=" + t.getId() + "\"><li>" + t.getNome() + "</li></a>";
		}
		list += "</ul>";
		
		form = form.replaceFirst("<TOPICOS>", list);
		//String umServico = "";
		/*String novoServico = "";
		if(tipo != FORM_INSERT) {
			novoServico += "<font size=\"+1.5\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/topico/list/1\" id=\"addServico\"> Novo serviço"; 
			novoServico += "<i class=\"fa-regular fa-square-plus\"></i></a></b></font>";

			form = form.replaceFirst("<NOVO-SERVICO>", novoServico);		
		}*/
		/*
		
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/topico/";
			String name, servico, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Serviço";
				servico = "massagem";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + topico.getId();
				name = "Atualizar Serviço (ID " + topico.getId() + ")";
				servico = topico.getServico();
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
			umServico += "\t\t\t<td>Preço: <input class=\"input--register\" type=\"text\" name=\"preco\" value=\""+ topico.getPreco() +"\"></td>";
			umServico += "\t\t\t<td>Duração: <input class=\"input--register\" type=\"text\" name=\"duracao\" value=\""+ topico.getDuracao() +"\"></td>";
			umServico += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\" id=\"botaoPrinc\"></td>";
			umServico += "\t\t</tr>";
			umServico += "\t</table>";
			umServico += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umServico += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umServico += "\t\t<tr>";
			umServico += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Serviço (ID " + topico.getId() + ")</b></font></td>";
			umServico += "\t\t</tr>";
			umServico += "\t\t<tr>";
			umServico += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umServico += "\t\t</tr>";
			umServico += "\t\t<tr>";
			umServico += "\t\t\t<td>&nbsp;Serviço: "+ topico.getServico() +"</td>";
			umServico += "\t\t\t<td>Preço: "+ topico.getPreco() +"</td>";
			umServico += "\t\t\t<td>Duração: "+ topico.getDuracao() + " minutos</td>";
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
				"<font size=\\\"+1.5\\\"><b>&nbsp;&nbsp;&nbsp;<a href=\\\"/topico/list/1\\\" id=\\\"addServico\\\"> Novo serviço "
				+ "<i class=\\\"fa-regular fa-square-plus\\\"></i></a></b></font> </td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/topico/list/" + FORM_ORDERBY_ID + "\"><b>ID</b></a></td>\n" +
        		"\t<td><a href=\"/topico/list/" + FORM_ORDERBY_NOME + "\"><b>Serviço</b></a></td>\n" +
        		"\t<td><a href=\"/topico/list/" + FORM_ORDERBY_PRECO + "\"><b>Preço</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Topico> servicos;
		if (orderBy == FORM_ORDERBY_ID) {                 	servicos = topicoDAO.getOrderById();
		} else if (orderBy == FORM_ORDERBY_NOME) {		servicos = topicoDAO.getOrderByServico();
		} else if (orderBy == FORM_ORDERBY_PRECO) {			servicos = topicoDAO.getOrderByPreco();
		} else {											servicos = topicoDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Topico s : servicos) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr class=\"listagem\" bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + s.getId() + "</td>\n" +
            		  "\t<td>" + s.getServico() + "</td>\n" +
            		  "\t<td>" + s.getPreco() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/topico/" + s.getId() + "\"><i class=\"fa-solid fa-magnifying-glass-plus\"></i></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/topico/update/" + s.getId() + "\"><i class=\"fa-solid fa-pen-to-square\"></i></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteServico('" + s.getId() + "', '" + s.getServico() + "', '" + s.getPreco() + "');\"><i class=\"fa-solid fa-trash\"></i></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-SERVICO>", list);
		*/				
	}
	
	
	// gerar pagina com listagem dos topicos
	public void makeForm(int ling) {
		String nomeArquivo = "src/main/resources/linguagemTopicos.html";
		form = "";
		String list="";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		list += "<h1 id=\"tituloPagina\">" + topicoDAO.getNomeLinguagem(ling) + "</h1>";
		form = form.replaceFirst("<TITULO-TOPICO>", list);
		
		list = "<h2 class=\"tituloLinguagemTopicos\">" + topicoDAO.getNomeLinguagem(ling) + "</h2>\r\n"
				+ "            <ul class=\"textoTopicos\">";
		
		List<Topico> topicos = topicoDAO.getTopicos(ling);
		for (Topico t : topicos) {
			list += "<a href=\"/conteudo/" + t.getId() + "\"><li>" + t.getNome() + "</li></a>";
		}
		list += "</ul>";
		
		form = form.replaceFirst("<TOPICOS>", list);
	}
	
	// gerar pagina com conteudo do topico
	public void makeForm(Topico topico) {
		String nomeArquivo = "src/main/resources/conteudoDaLinguagem.html";
		form = "";
		String txtLinguagem="";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		txtLinguagem = "<h1 id=\"tituloPagina\">"+topicoDAO.getNomeLinguagem(topico.getLing())+" - "+topico.getNome()+" </h1>";
		
		form = form.replaceFirst("<TITULO-CONTEUDO>", txtLinguagem);
		
		txtLinguagem = "<div class=\"tituloModeloLing\">\r\n"
				+ "                    <span> " + topico.getNome() + "</span>\r\n"
				+ "                    </div>";
		txtLinguagem += "<div class=\"conteudoModeloLing\">"+ topico.getConteudo() +"</div>";
		
		form = form.replaceFirst("<CONTEUDO-LINGUAGEM>", txtLinguagem);
	}
	
	public Object insert(Request request, Response response) {
		int id = Integer.parseInt(request.queryParams("id"));
		int ling = Integer.parseInt(request.queryParams("ling"));
		String nome = request.queryParams("nome");		
		String conteudo = request.queryParams("conteudo");		
		
		String resp = "";
		
		Topico topico = new Topico(id, ling, nome, conteudo);
		
		if(topicoDAO.insert(topico) == true) {
            resp = "Topico (" + nome + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Topico (" + nome + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	// apresenta topicos de cada ling
	public Object getAllLing(Request request, Response response) {
		int ling = Integer.parseInt(request.params(":ling"));
		makeForm(ling);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}		
	
	// apresenta conteudo do topico escolhido
	public Object getConteudo(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Topico topico = (Topico) topicoDAO.get(id);
		
		if (topico != null) {
			response.status(200); // success
			makeForm(topico);
        } else {
            response.status(404); // 404 Not found
            String resp = "Tópico da linguagem " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Topico topico = (Topico) topicoDAO.get(id);
		
		if (topico != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, topico, FORM_ORDERBY_ID);
        } else {
            response.status(404); // 404 Not found
            String resp = "Tópico da linguagem " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Topico topico= (Topico) topicoDAO.get(id);
		
		if (topico != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, topico, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Tópico " + id + " não encontrado.";
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
		Topico topico = topicoDAO.get(id);
        String resp = "";       

        if (topico != null) {
        	topico.setNome(request.queryParams("nome"));
        	topico.setConteudo(request.queryParams("conteudo"));
        	topico.setLing(Integer.parseInt(request.queryParams("ling")));
        	topicoDAO.update(topico);
        	response.status(200); // success
            resp = "Tópico (ID " + topico.getId() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Tópico (ID " + topico.getId() + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Topico topico = topicoDAO.get(id);
        String resp = "";       

        if (topico != null) {
            topicoDAO.delete(id);
            response.status(200); // success
            resp = "Tópico (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Tópico (" + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}
