package service;

import java.util.Scanner;
import java.io.File;
import java.util.List;

import dao.ColaboradorDAO;
import dao.TopicoDAO;
import model.Colaborador;
import model.Topico;
import spark.Request;
import spark.Response;
import util.Path;

public class TopicoService {
    private TopicoDAO topicoDAO = new TopicoDAO();
    private ColaboradorDAO colabDAO = new ColaboradorDAO();
    private String form;
    private final int FORM_INSERT = 1;
    private final int FORM_DETAIL = 2;
    private final int FORM_UPDATE = 3;
    private final int FORM_ORDERBY_ID = 1;
    private final int FORM_ORDERBY_NOME = 2;

    public TopicoService() {
        //makeForm();
    }
/* nao utilizado
    public void makeForm() {
        makeForm(FORM_INSERT, new Topico(), FORM_ORDERBY_ID);
    }

    public void makeForm(int tipo, Topico topico, int orderBy) {
        String nomeArquivo = "src/main/resources/html/linguagemTopicos.html";
        form = "";
        String list = "";
        try {
            Scanner entrada = new Scanner(new File(nomeArquivo));
            while (entrada.hasNext()) {
                form += (entrada.nextLine() + "\n");
            }
            entrada.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        list += "<h2 class=\"tituloLinguagemTopicos\">" + topicoDAO.getNomeLinguagem(532) + "</h2>\r\n"
                + "            <ul class=\"textoTopicos\">";

        List<Topico> topicos = topicoDAO.get();
        for (Topico t : topicos) {
            list += "<a href=\"conteudo.html?id=" + t.getId() + "\"><li>" + t.getNome() + "</li></a>";
        }
        list += "</ul>";

        form = form.replaceFirst("<TOPICOS>", list);
    }
*/
    
    // gerar pagina com listagem dos topicos
    public void makeForm(int ling) {
        String nomeArquivo = "src/main/resources/html/linguagemTopicos.html";
        form = "";
        String list = "";
        try {
            Scanner entrada = new Scanner(new File(nomeArquivo));
            while (entrada.hasNext()) {
                form += (entrada.nextLine() + "\n");
            }
            entrada.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

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
        String nomeArquivo = "src/main/resources/html/conteudo.html";
        form = "";
        String txtLinguagem = "";
        try {
            Scanner entrada = new Scanner(new File(nomeArquivo));
            while (entrada.hasNext()) {
                form += (entrada.nextLine() + "\n");
            }
            entrada.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        txtLinguagem = "<h1 id=\"tituloPagina\">" + topicoDAO.getNomeLinguagem(topico.getLing()) + " - "
                + topico.getNome() + " </h1>";

        form = form.replaceFirst("<TITULO-CONTEUDO>", txtLinguagem);

        txtLinguagem = "<div class=\"tituloModeloLing\">\r\n"
                + "                    <span> " + topico.getNome() + "</span>\r\n"
                + "                    </div>";
        txtLinguagem += "<div class=\"conteudoModeloLing\">" + topico.getConteudo() + "</div>";

        form = form.replaceFirst("<CONTEUDO-LINGUAGEM>", txtLinguagem);
    }

    public String makeEdicao(Topico topico) {
        String nomeArquivo = "src/main/resources/html/edicao.html";
        form = "";
        String txtEdicao = "";
        try {
            Scanner entrada = new Scanner(new File(nomeArquivo));
            while (entrada.hasNext()) {
                form += (entrada.nextLine() + "\n");
            }
            entrada.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        txtEdicao += "<form id=\"edicaoTopico\" action=\"/topico/update/"+topico.getId()+"\">\r\n"
                + "            <textarea name=\"nomeTopico\" id=\"nomeTopico\" form=\"edicaoTopico\" maxlength=\"100\"> "+topico.getNome()+" </textarea>\r\n"
                + "            <textarea name=\"conteudoTopico\" id=\"conteudoTopico\" form=\"edicaoTopico\" onkeydown=\"autoResize()\"> "+topico.getConteudo()+" </textarea>\r\n"
                + "            <div id=\"divBtnEdicao\" ><button type=\"btn\" class=\"btn\" id=\"edicaoCancelaBtn\" onClick=\"voltar()\"> Cancelar </button>\r\n"
                + "            <button type=\"submit\" class=\"btn\" id=\"edicaoBtn\"> Salvar </button> </div>\r\n"
                + "        </form>";
        //txtEdicao += topico.getId() + " - " + topico.getNome() + "<br>" + topico.getConteudo() + "<br>"; 
        form = form.replaceFirst("<EDICAO-CORPO>", txtEdicao);
        return form;
       
    }

    /**
     *  Insere novo topico
     * @param request
     * @param response
     * @return Formulário de inserção. Mas deve redirecionar para o perfil.
     */
    public Object insert(Request request, Response response) {
        int ling = Integer.parseInt(request.queryParams("linguagens"));
        int id = (topicoDAO.getMaiorIdTopicoByLing(ling) > 0) ? (topicoDAO.getMaiorIdTopicoByLing(ling)+ 1) : Integer.parseInt(Integer.toString(ling) + "01");
        String nome = request.queryParams("titulo");
        int userId = Integer.parseInt(request.queryParams("autor"));
        
        String resp = "";

        Topico topico = new Topico(id, ling, nome, "");

        if (topicoDAO.insert(topico) == true) {
            topicoDAO.insertGerenciaTopico(topico, userId);
            resp = "Topico (" + nome + ") inserido!";
            response.status(201); // 201 Created
        } else {
            resp = "Topico (" + nome + ") não inserido!";
            response.status(404); // 404 Not found
        }
        response.redirect(Path.Web.PERFIL);
        //makeForm();
        //form = form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
        return form;
    }

    /**
     * Retorna todos os tópicos de uma linguagem
     * @param request
     * @param response
     * @return form é o html da página a ser mostrada
     */
    public Object getAllTopicosFromLing(Request request, Response response) {
        int ling = Integer.parseInt(request.params(":ling"));
        makeForm(ling);
        response.header("Content-Type", "text/html");
        response.header("Content-Encoding", "UTF-8");
        return form;
    }

    /**
     * Retorna o conteúdo do tópico escolhido
     * @param request
     * @param response
     * @return form que é o html da página a ser mostrada
     */
    public Object getConteudo(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Topico topico = (Topico) topicoDAO.get(id);

        if (topico != null) {
            response.status(200); // success
            makeForm(topico);
        } else {
            response.status(404); // 404 Not found
            String resp = "Tópico da linguagem " + id + " não encontrado.";
            makeForm(topico);
            form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">",
                    "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
        }

        return form;
    }

    
    public Object get(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Topico topico = (Topico) topicoDAO.get(id);

        if (topico != null) {
            response.status(200); // success
            //makeForm(FORM_DETAIL, topico, FORM_ORDERBY_ID);
        } else {
            response.status(404); // 404 Not found
            String resp = "Tópico da linguagem " + id + " não encontrado.";
            //makeForm();
            form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">",
                    "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
        }

        return form;
    }

    /**
     * Obtem topico para depois ser atualizado
     * @param request
     * @param response
     * @return form Formulário que serve para atualizar topico
     */
    public Object getToUpdate(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Topico topico = (Topico) topicoDAO.get(id);

        if (topico != null) {
            response.status(200); // success
            makeEdicao(topico);
        } else {
            response.status(404); // 404 Not found
            String resp = "Tópico " + id + " não encontrado.";
            makeEdicao(topico);
            form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">",
                    "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
        }

        return form;
    }

    public Object getAll(Request request, Response response) {
        int orderBy = Integer.parseInt(request.params(":orderby"));
        //makeForm(orderBy);
        response.header("Content-Type", "text/html");
        response.header("Content-Encoding", "UTF-8");
        return form;
    }

    /**
     * Atualizar topico
     * @param request
     * @param response
     * @return Retorna um form mas deve redirecionar para o perfil.
     */
    public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Topico topico = topicoDAO.get(id);
        String resp = "";

        if (topico != null) {
            topico.setNome(request.queryParams("nomeTopico"));
            topico.setConteudo(request.queryParams("conteudoTopico"));
            //topico.setLing(Integer.parseInt(request.queryParams("ling")));
            topicoDAO.update(topico);
            response.status(200); // success
            resp = "Tópico (ID " + topico.getId() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Tópico (ID " + topico.getId() + ") não encontrado!";
        }
        
        response.redirect(Path.Web.PERFIL);
        //makeForm();
        return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">",
                "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
    }

    /**
     * Deleta um topico
     * @param request
     * @param response
     * @return  formulário com resultado
     */
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
        response.redirect(Path.Web.PERFIL);
        return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">",
                "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
    }
}
