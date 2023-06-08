import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(System.in)) {
            int opcao = 0;
            System.out.println();
            System.out.println(
                    "Boas-vindas, o que você deseja executar? \n--- 1. Catálogo de filmes e séries \n--- 2. Gerador de Cartazes" + 
                    "\n--- 3. Mostrar foto astronômica do dia da Nasa");
            System.out.println("____________________________________________");
            System.out.print("Digite aqui sua opção: ");
            opcao = Integer.parseInt(sc.nextLine());
            System.out.println();

            if (opcao == 1) {
                try (Scanner sc2 = new Scanner(System.in)) {
                    int resposta = 0;
                    String url = null;
                    do {
                        System.out.println();
                        System.out.println(
                                "O que você deseja acessar?\n----- 1. Conferir o top 10 melhores filmes\n----- 2. Conferir os filmes mais populares" + 
                                "\n----- 3. Conferir o top 3 melhores séries\n----- 4. Conferir as séries mais populares\n----- 5. Encerrar programa");
                        System.out.println("____________________________________________");
                        System.out.print("Digite aqui sua opção: ");
                        resposta = Integer.parseInt(sc2.nextLine());
                        System.out.println();

                        if (resposta == 1) {
                            url = "https://api.mocki.io/v2/549a5d8b";
                        } else if (resposta == 2) {
                            url = "https://api.mocki.io/v2/549a5d8b/MostPopularMovies";
                        } else if (resposta == 3) {
                            url = "https://api.mocki.io/v2/549a5d8b/Top250TVs";
                        } else if (resposta == 4) {
                            url = "https://api.mocki.io/v2/549a5d8b/MostPopularTVs";
                        } else if (resposta == 5) {
                            System.out.println();
                            System.exit(0);
                        }

                        // Conexão HTTP
                        URI endereco = URI.create(url);
                        var client = HttpClient.newHttpClient();
                        var request = HttpRequest.newBuilder(endereco).GET().build();

                        // Busca
                        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
                        String body = response.body();

                        // Extrair dados que interessam (Rank, título, elenco, classificação)
                        JsonParser parser = new JsonParser();
                        List<Map<String, String>> listaDeConteudos = parser.parse(body);

                        // Exibir dados
                        for (Map<String, String> item : listaDeConteudos) {
                            System.out.println(item.get("rank") + ". " + "\u001b[1m" + item.get("title") + "\u001b[0m ("
                                    + item.get("crew") + ")");
                            System.out.println("Classificação IMDB:" + item.get("imDbRating"));
                            System.out.println();
                        }

                    } while (resposta != 5);
                }
            } else if (opcao == 2) {

                try (Scanner sc2 = new Scanner(System.in)) {
                    int resposta = 0;
                    String rank = "";
                    String url = null;
                    do {
                        System.out.println();
                        System.out.println(
                                "Quais cartazes você deseja gerar?\n----- 1. Top 10 melhores filmes\n----- 2. Filmes mais populares\n----- 3. Top 3 melhores séries" +
                                "\n----- 4. Séries mais populares\n----- 5. Encerrar programa");
                        System.out.println("____________________________________________");
                        System.out.print("Digite aqui sua opção: ");
                        resposta = Integer.parseInt(sc2.nextLine());
                        System.out.println();

                        if (resposta == 1) {
                            url = "https://api.mocki.io/v2/549a5d8b";
                            rank = "1.";
                        } else if (resposta == 2) {
                            url = "https://api.mocki.io/v2/549a5d8b/MostPopularMovies";
                            rank = "2.";
                        } else if (resposta == 3) {
                            url = "https://api.mocki.io/v2/549a5d8b/Top250TVs";
                            rank = "3.";
                        } else if (resposta == 4) {
                            url = "https://api.mocki.io/v2/549a5d8b/MostPopularTVs";
                            rank = "4.";
                        } else if (resposta == 5) {
                            System.out.println();
                            System.exit(0);
                        }
                        // Conexão HTTP                        
                        URI endereco = URI.create(url);
                        var client = HttpClient.newHttpClient();
                        var request = HttpRequest.newBuilder(endereco).GET().build();

                        // Busca
                        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
                        String body = response.body();

                        // Extrair só os dados que interessam (titulo, poster)
                        var parser = new JsonParser();
                        List<Map<String, String>> listaDeConteudos = parser.parse(body);

                        // Exibir e manipular os dados
                        var geradora = new GeradorDeCartazes();
                        for (Map<String, String> filme : listaDeConteudos) {

                            String urlImagem = filme.get("image");
                            String titulo = filme.get("title");
                            String nota = ("Nota:" + filme.get("imDbRating"));
                            InputStream inputStream = new URL(urlImagem).openStream();
                            String nomeArquivo = rank + filme.get("rank") + ". " + titulo.replace(":", "-") + ".png";
                            geradora.cria(inputStream, nomeArquivo, nota);
                            System.out.println(titulo);
                        }

                    } while (resposta != 5);
                }
            } 
        }
    }
}