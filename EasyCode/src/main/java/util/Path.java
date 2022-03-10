package util;

public class Path {
    public class Web {
        public final static String INDEX = "/";
        public final static String LINGUAGENS = "/linguagens";
        public final static String TOPICOS = "/topicos/:id";
        public final static String CONTEUDO = "/conteudo/:id";
        public final static String PERFIL = "/perfilUser";
        public final static String LOGIN = "/login";
        public final static String DESLOGAR = "/deslogar";
    }

    public static String toFile(String webName) {
        if (webName == "/")
            webName = "/index";

        webName = webName.split("/")[1]; // pra ignorar coisas tipo :id
        return "src/main/resources/html/" + webName + ".html";
    }
}
