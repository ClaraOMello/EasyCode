package service;

import java.util.Scanner;
import java.io.File;
import java.util.List;
import dao.LinguagemDAO;
import model.Linguagem;
import spark.Request;
import spark.Response;
import util.Path;

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
        String nomeArquivo = Path.toFile(Path.Web.LINGUAGENS);
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
        List<Linguagem> lings = lingDAO.get();
        ;
        for (Linguagem lgs : lings) {
            list += "<a href=\"/topicos/" + lgs.getId() + "\"><img src=\" " + lgs.getImg() + "\" alt=\"card "
                    + lgs.getNome() + " \" class=\"cardsLinguagens\"></a>\r\n";
        }
        form = form.replaceFirst("<CARDS-LINGUAGEM>", list);

    }

    // meus

    public Object mostra(Request request, Response response) {
        makeForm(1);
        response.header("Content-Type", "text/html");
        response.header("Content-Encoding", "UTF-8");
        return form;
    }

    ////// prontos ///////

    public Object insert(Request request, Response response) {
        int id = Integer.parseInt(request.queryParams("IDLing"));
        String nome = request.queryParams("nomeLing");
        String img = request.queryParams("imgLing");

        String resp = "";

        Linguagem ling = new Linguagem(id, nome, img);

        if (lingDAO.insert(ling) == true) {
            resp = "Linguagem (" + nome + ") inserido!";
            response.status(201); // 201 Created
        } else {
            resp = "Linguagem (" + nome + ") não inserido!";
            response.status(404); // 404 Not found
        }

        // makeForm();
        form = form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">",
                "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
        response.redirect(Path.Web.PERFIL);
        return form;
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
            form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">",
                    "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
        }

        return form;
    }

    public Object getToUpdate(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Linguagem ling = (Linguagem) lingDAO.get(id);

        if (ling != null) {
            response.status(200); // success
            makeForm(FORM_UPDATE, ling, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Linguagem " + id + " não encontrada.";
            makeForm();
            form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">",
                    "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
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
            ling.setNome(request.queryParams("nomeLing"));
            ling.setImg(request.queryParams("imgLing"));
            lingDAO.update(ling);
            response.status(200); // success
            resp = "Linguagem (ID " + ling.getId() + ") atualizada!";
        } else {
            response.status(404); // 404 Not found
            resp = "Linguagem (ID " + ling.getId() + ") não encontrada!";
        }
        makeForm();
        response.redirect(Path.Web.PERFIL);
        return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">",
                "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
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
        response.redirect(Path.Web.PERFIL);
        return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">",
                "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
    }
}
