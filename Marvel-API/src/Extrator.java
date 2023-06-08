import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Extrator {

    public List<Content> extraiConteudos(String json) {

        // Extrair só os dados que interessam 
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);
        List<Content> conteudos = new ArrayList<>();

        // Popular a lista de conteudos
        for (Map<String, String> atributos : listaDeAtributos) {
            String name = atributos.get("status");
            var conteudo = new Content(name);
            conteudos.add(conteudo);
        }

        return conteudos;
    }
}