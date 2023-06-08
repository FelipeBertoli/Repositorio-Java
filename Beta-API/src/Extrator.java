import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Extrator {

    public List<Conteudo> extraiConteudos(String json) {

        // Extrair só os dados que interessam 
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);
        List<Conteudo> conteudos = new ArrayList<>();

        // Popular a lista de conteudos
        for (Map<String, String> atributos : listaDeAtributos) {
            String titulo = atributos.get("title");
            String url= atributos.get("image");
            String elenco = atributos.get("crew");
            String nota = atributos.get("imDbRating");
            String rank = atributos.get("rank");
            var conteudo = new Conteudo(titulo, url, elenco, nota, rank);
            conteudos.add(conteudo);
        }

        return conteudos;
    }
}