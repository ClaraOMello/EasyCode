package service;

import java.util.Scanner;
import org.jasypt.util.password.StrongPasswordEncryptor;
//import java.time.LocalDate;
import java.io.File;
import java.time.format.DateTimeFormatter;
//import java.time.LocalDateTime;
import java.util.List;
import dao.ColaboradorDAO;
import dao.LinguagemDAO;
import dao.TopicoDAO;
import model.Colaborador;
import model.Linguagem;
import model.Topico;
import spark.Request;
import spark.Response;
import util.Path;

public class ColaboradorService {
	private ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
	private LinguagemDAO linguagemDAO = new LinguagemDAO();
	private TopicoDAO topicoDAO = new TopicoDAO();
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
	
    // personalizar perfil para cada usuario
	private void makePerfil(Colaborador colaborador) {
	    String file = "";
        try{
            Scanner entrada = new Scanner(new File("src/main/resources/html/perfilUser.html"));
            while(entrada.hasNext()){
                file += (entrada.nextLine() + "\n");
            }
            entrada.close();
        }  catch (Exception e) { System.out.println(e.getMessage()); }
        makePerfil(file, colaborador);
	}
	public String makePerfil(String file, Colaborador user) {
	    // file eh a pagina de perfil sem personalizacao por usuario
	    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	    String texto = "";
	    form = file;
	    
	    String tipoUser = "Colaborador";
	    if (user != null) {
    	    if (user.getAdm()) {
    	        tipoUser = "Administrador";
    	        // somente adm pode gerenciar outros usuarios
    	        List<Colaborador> colabs = colaboradorDAO.get();
    	        texto += "<button type=\"button\" id=\"start-btn\" class=\"btn\" data-bs-toggle=\"modal\"\r\n"
    	                + "                            data-bs-target=\"#configColab\">\r\n"
    	                + "                            Configurações de colaboradores\r\n"
    	                + "                </button>";
    	        texto += "<div class=\"modal\" tabindex=\"-1\" id=\"configColab\">\r\n"
    	                + "                  <div class=\"modal-dialog modal-lg\">\r\n"
    	                + "                    <div class=\"modal-content\">\r\n"
    	                + "                      <div class=\"modal-header\">\r\n"
    	                + "                        <h5 class=\"modal-title\">Configurações dos Colaboradores</h5>\r\n"
    	                + "                        <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\r\n"
    	                + "                      </div>\r\n"
    	                + "                      <div class=\"modal-body\">\r\n"
    	                + "                        <table>\r\n"
    	                + "                            <thead>\r\n"
    	                + "                                <tr>\r\n"
    	                + "                                    <th scope=\"col\" class=\"col-8\"> Usuário: </th>\r\n"
    	                + "                                    <th scope=\"col\" > Adm </th>\r\n"
    	                + "                                </tr>\r\n"
    	                + "                            </thead>";
    	        for (Colaborador c : colabs) {
    	            texto += "<tr class=\"card-text\"> \r\n"
    	                    + "                                <td>"+c.getNome()+"</td>\r\n"
    	                    + "                                <td>\r\n"
    	                    + "                                    <a href=\"/colaboradorAdm/update/"+c.getId()+"\" style=\"border:none\" class=\"btn\" id=\"adm\"><u>Tornar Adm</u></a>\r\n"
    	                    + "                                </td>                       \r\n"
    	                    + "                            </tr>";
    	            if(c.getAdm()) texto = texto.replace("                                    <a href=\"/colaboradorAdm/update/" + c.getId() + "\" style=\"border:none\" class=\"btn\" id=\"adm\"><u>Tornar Adm</u></a>\r\n", "já é administrador\r\n");
    	        }
    	        texto += "</table>\r\n"
    	                + "                      </div>\r\n"
    	                + "                      <div class=\"modal-footer\">\r\n"
    	                + "                        <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Fechar</button>\r\n"
    	                + "                      </div>\r\n"
    	                + "                    </div>\r\n"
    	                + "                  </div>\r\n"
    	                + "                </div>\r\n";
    	        
    	        form = form.replaceFirst("<CONFIG-COLABORADOR-PARA-ADM>", texto);
    	    }
    	    
    	    // preencher tabela topico
    	    texto = "";
    	    List<Topico> topicos = topicoDAO.getTopicosByColab(user.getId(), user.getAdm());
    	    for (Topico t : topicos) {
    	        texto += "<tr class=\"conteudoTop\" style=\"color:white\"><th scope=\"col\" class=\"fs-4\">"+ t.getId() +"</th>\r\n"
    	                + "        <td class=\"tituloTabela\">"+ t.getNome() +"</td>\r\n"
    	                + "        <td class=\"autorTabela\"> "+ topicoDAO.getNomeColabByTopicoId(t.getId()) +" </td>\r\n"
    	                + "        <td class=\"linguaTabela\">"+ topicoDAO.getNomeLinguagem(t.getLing()) +"</td>\r\n"
    	                + "        <td class=\"text-muted linkTabela\"><a href=\"/conteudo/"+t.getId()+"\"><p> Publicado </p></a></td>\r\n"
    	                + "        <td class=\"botoesTabela>\r\n"
    	                + "        <div class=\"form-group\">\r\n"
    	                + "        <button type=\"button\" onclick=editarTopico("+t.getId()+") id=\"btnEditar\"\r\n"
    	                + "        class=\"btn\">\r\n"
    	                + "        <i class=\"fa-solid fa-pen\"></i> Editar\r\n"
    	                + "        </button>\r\n"
    	                + "        </div>\r\n"
    	                + "        <div class=\"form-group\">\r\n"
    	                + "        <button type=\"button\" onclick=confirmarDeletarTopico("+t.getId()+ ") id=\"btnDeletar\"\r\n"
    	                + "        class=\"btn\">\r\n"
    	                + "        <i class=\"fa-solid fa-trash\"></i> Excluir\r\n"
    	                + "        </button>\r\n"
    	                + "        </div></td></tr>";
    	    }
    	    form = form.replaceFirst("<LISTA-TOPICOS>", (texto == "" ? "<h5 style=\"color:gray; text-align:center\"> Sem tópicos publicados </h5>" : texto));
    	    
    	    form = form.replaceFirst("<input id=\"autor\" data-index=\"new\" type=\"text\" class=\"form-control cad\" placeholder=\"Autor\" name=\"autor\" value=\"\" />", "<input id=\"autor\" data-index=\"new\" type=\"text\" class=\"form-control cad\" placeholder=\"Autor\" name=\"autor\" value=\""+user.getId()+"\" />");
    	    
    	    List<Linguagem> linguagens = linguagemDAO.get();
    	    texto = "";
    	    for (Linguagem lg : linguagens) {
    	        texto += "<option value=\""+ lg.getId() +"\"> "+ lg.getNome() +" </option>\r\n";
    	    }
    	    
    	    form = form.replaceFirst("<option value=\"xxx\"> XXX </option>", texto);
    	    
    	    if (user.getDescricao() != null && user.getDescricao() != "") texto = user.getDescricao();
    	    else texto = "Olá! Estou usando o EasyCode";
    	    form = form.replaceFirst("<DESCRICAO>", texto);
    	    
    	    form = form.replaceFirst("<EXCLUSAO-PERFIL>", "<button type=\"button\" id=\"start-btn\" class=\"btn\" onclick=\"confirmarDeletePerfil("+user.getId()+", '"+ user.getNome()+"')\">\r\n"
    	            + "<i class=\"fa-solid fa-trash\"></i>\r\n"
                    + "Excluir Conta</button>");
    	    
    	    // para preencher modal de atualizacao
    	    form = form.replaceFirst("<form class=\"form\" action=\"/perfilUser/update\" method=\"post\" id=\"atualizarPerfil\">", "<form class=\"form\" action=\"/perfilUser/update/"+user.getId()+"\" method=\"post\" id=\"atualizarPerfil\">");
    	    form = form.replaceFirst("<input type=\"text\" autofocus id=\"editaDescricao\" name=\"editaDescricao\" class=\"inputForm\" value=\"\">", "<input type=\"text\" autofocus id=\"editaDescricao\" name=\"editaDescricao\" class=\"inputForm\" value=\""+ texto +"\">");
    	    form = form.replaceFirst("<input type=\"text\" autofocus id=\"editaNome\" name=\"editaNome\" class=\"inputForm\" value=\"\">", "<input type=\"text\" autofocus id=\"editaNome\" name=\"editaNome\" class=\"inputForm\" value=\""+user.getNome()+"\">");
    	    form = form.replaceFirst("<input type=\"text\" autofocus id=\"editaEmail\" name=\"editaEmail\" class=\"inputForm\" value=\"\">", "<input type=\"text\" autofocus id=\"editaEmail\" name=\"editaEmail\" class=\"inputForm\" value=\""+user.getEmail()+"\">");
    	    
            form = form.replaceFirst("<span class=\"black\">Nome:</span> <span id=\"nomeUser\">Standro</span> <br>", "<span class=\"black\">Nome:</span> <span id=\"nomeUser\">"+user.getNome()+"</span> <br>");
            form = form.replaceFirst("<span class=\"black\">Tipo de Usuário:</span> <span id=\"tipoUser\"> xxx </span> <br>", "<span class=\"black\">Tipo de Usuário:</span> <span id=\"tipoUser\"> "+ tipoUser +" </span> <br>");
            form = form.replaceFirst("<span class=\"black\">Data de adesão:</span> <span id=\"anoUser\">2022</span><br>", "<span class=\"black\">Data de adesão:</span> <span id=\"anoUser\">"+user.getAdesao().format(df) +"</span><br>"); // tempo aparece a hora atual
	    
			if (tipoUser.equals("Administrador")) {
				form = form.replaceFirst("<BOTAO-LINGUAGEM>",
						"<button type=\"button\" id=\"start-btn lingBtn\" class=\"btn\"> <a id=\"btnLing\">Linguagens</a> </button>");

				texto = "<div class=\"row\">\r\n"
				        + "                        <span class=\"lingLinha\">\r\n"
				        + "                            <button type=\"button\" id=\"start-btn\" class=\"btn\">\r\n"
						+ "                            <a id=\"btnTop\">Tópicos</a>\r\n"
						+ "                            </button>\r\n"
				        + "                            <button type=\"button\" id=\"start-btn\" class=\"btn btnTestinho\" data-bs-toggle=\"modal\"data-bs-target=\"#staticBackdropLing\">\r\n"
				        + "                                Nova Linguagem\r\n"
				        + "                            </button>\r\n"
				        + "                        </span>\r\n"
				        + "                        <h1>Linguagens Atuais:\r\n"
				        + "                        </h1>\r\n"
				        + "                    </div>";
				form = form.replaceFirst("<TITULING>", texto);
				texto = "<tbody>\r\n";
				List<Linguagem> lings = linguagemDAO.getOrderById();
				for (Linguagem l : lings) {
					texto += "	<tr id=\"conteudotable\" class=\"table-hover\">\r\n";
					texto += "		<th scope=\"col\" class=\"fs-4\">" + l.getId() + "</th>\r\n";
					texto += "		<td id=\"nome" + l.getId() + "\" class=\"tituloTabela\">" + l.getNome()
							+ "</td>\r\n";
					texto += "		<td id=\"img" + l.getId()
							+ "\" class=\"autorTabela\"><img class=\"imgTabelaL\" src=\"" + l.getImg()
							+ "\" alt=\"\"></td>\r\n";
					texto += "		<td class=\"text-muted linkTabela\"><button type=\"button\" value=\"editarL-"
							+ l.getId()
							+ "\" id=\"btnEditar\"class=\"btn\" data-bs-toggle=\"modal\" data-bs-target=\"#staticBackdropLing\"><i class=\"fa-solid fa-pen\"></i> Editar</button></td>\r\n";
					texto += "		<td class=\"text-muted linkTabela\"><button type=\"button\" value=\"deletarL-"
							+ l.getId() + "\"id=\"btnDeletar\"class=\"btn\" onclick=\"confirmarDeleteLing(" + l.getId()
							+ ", '" + l.getNome() + "')\"<i class=\"fa-solid fa-trash\"></i> Excluir</button></td>\r\n";
					texto += "	</tr>\r\n";
				}
				texto += "</tbody>\r\n";
				form = form.replaceFirst("<R-DE-LING>", texto);
			}
		}
        return form;
        
	}
	
	
	public static String encryptPassword(String inputPassword) {
	    StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor(); // SHA-256.

	    return encryptor.encryptPassword(inputPassword);
	}
	/**
	 * Cria um login para o usuário na database
	 * @param request
	 * @param response
	 */
	public Object insert(Request request, Response response) {
		String nome = request.queryParams("nome");
		String email = request.queryParams("email");
		String senha = request.queryParams("senha");
		senha = encryptPassword(senha);
		// String confirmaSenha = request.queryParams("confirmaSenha");
		int id = colaboradorDAO.getLastId() + 1;
		List<Colaborador> colabs = colaboradorDAO.get();
		boolean existe = false;
		
		String resp = "";
		
		Colaborador colaborador = new Colaborador(id, false, nome, email, senha);
		
		for(Colaborador c : colabs) {
		    if (c.getNome().equals(colaborador.getNome())) existe = true;
		}
		System.out.println("Existe? " + existe);
		
		if(!existe && colaboradorDAO.insert(colaborador) == true) {
            resp = "Usuário (" + nome + ") criado!";
            response.status(201); // 201 Created
		} else if (existe){
		    resp = "Nome de usuário '" + nome + "' já existente!";
		} else {
			resp = "Falha na criação do usuário (" + nome + ")!";
			response.status(404); // 404 Not found
		}
		System.out.println(resp);
		// makeLogin();
		response.redirect(Path.Web.LOGIN);
//		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
		return form;
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
		
		form.replace("<span class=\"black\">Tipo de Usuário:</span> <span id=\"tipoUser\"> xxx </span> <br>", "<span class=\"black\">Tipo de Usuário:</span> <span id=\"tipoUser\"> HEEEEYYYYY </span> <br>");
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
	
	/**
	 * Atualize quem é o administrador
	 * @param request
	 * @param response
	 */
	public Object updateAdm(Request request, Response response) {
	    int id = Integer.parseInt(request.params(":id"));
	    String resp = "";
	    Colaborador colaborador = colaboradorDAO.get(id);
	    
	    if (colaborador != null) {
	        colaborador.setAdm(true);
            colaboradorDAO.update(colaborador);
            response.status(200); // success
            resp = "Usuário (ID " + colaborador.getId() + ") agora é administrador!";
        } else {
            response.status(404); // 404 Not found
            resp = "Usuário (ID " + colaborador.getId() + ") não encontrado!";
        }
	    response.redirect(Path.Web.PERFIL);
	    return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
	
	/**
	 * Atualiza infos pessoais do colaborador
	 * @param request
	 * @param response
	 */
	public Object updateInfoPessoal(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        String resp = "";
        Colaborador colaborador = colaboradorDAO.get(id);
        
        if (colaborador != null) {
            colaborador.setNome(request.queryParams("editaNome"));
            colaborador.setEmail(request.queryParams("editaEmail"));
            colaborador.setDescricao(request.queryParams("editaDescricao"));
            colaboradorDAO.update(colaborador);
            response.status(200); // success
            resp = "Usuário (ID " + colaborador.getId() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Usuário (ID " + colaborador.getId() + ") não encontrado!";
        }
        makePerfil(colaborador);
        form = form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
        response.redirect(Path.Web.PERFIL);
        return form;
    }
	
	/**
	 * Atualiza os dados do colaborador
	 * @param request
	 * @param response
	 */
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
            resp = "Usuário (ID " + colaborador.getId() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Usuário (ID " + colaborador.getId() + ") não encontrado!";
        }
		// makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	

	/**
	 * Deleta o colaborador
	 * @param request
	 * @param response
	 */
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
        
        request.session().removeAttribute("currentUser");
        request.session().attribute("loggedOut", true);
        response.redirect(Path.Web.LOGIN);
		makeLogin();
        form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
		return form;
	}
}
