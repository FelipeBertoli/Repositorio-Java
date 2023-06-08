import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        int option = 0;
        int j = 0;
        String rank = "";
        String url = null;
        var http = new ClienteHttp();
        var extrator = new Extrator();
        var geradora = new GeradorDeCartazes();

        do {
            Scanner sc = new Scanner(System.in);
            System.out.println();
            System.out.println(
                    "O que você deseja acessar?\n----- 1. Conferir o top 10 melhores filmes\n----- 2. Conferir os filmes mais populares"
                            +
                            "\n----- 3. Conferir o top 3 melhores séries\n----- 4. Conferir as séries mais populares\n----- 5. Encerrar programa");
            System.out.println("____________________________________________");
            System.out.print("Digite aqui sua opção: ");
            option = Integer.parseInt(sc.nextLine());

            System.out.println();

            if (option == 1) {
                url = "https://api.mocki.io/v2/549a5d8b";
                rank = "1.";
                j = 10;
            } else if (option == 2) {
                url = "https://api.mocki.io/v2/549a5d8b/MostPopularMovies";
                rank = "2.";
                j = 3;
            } else if (option == 3) {
                url = "https://api.mocki.io/v2/549a5d8b/Top250TVs";
                rank = "3.";
                j = 10;
            } else if (option == 4) {
                url = "https://api.mocki.io/v2/549a5d8b/MostPopularTVs";
                rank = "4.";
                j = 3;
            } else if (option == 5) {
                System.out.println();
                System.exit(0);
            }
            String json = http.buscaDados(url);
            List<Conteudo> conteudos = extrator.extraiConteudos(json);
            for (int i = 0; i < j; i++) {
                Conteudo conteudo = conteudos.get(i);
                InputStream inputStream = new URL(conteudo.Imagem()).openStream();
                String nomeArquivo = rank + conteudo.Rank() + ". " + conteudo.Titulo().replace(":", "-") + ".png";
                geradora.cria(inputStream, nomeArquivo);
                System.out.println(conteudo.Rank() + ". " + "\u001b[1m" + conteudo.Titulo() + "\u001b[0m (" + conteudo.Elenco() + ")");
                System.out.println("Classificação IMDB:" + conteudo.Nota());
                System.out.println();
            }
            System.out.println("-> CARTAZES GERADOS!");

            if (option == 5) {
                sc.close();
            }
        } while (option != 5);
  
  
    }
    
}
