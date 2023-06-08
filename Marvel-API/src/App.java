
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        String url = "http://gateway.marvel.com/v1/public/characters/";
        String id = "";
        int option = 0;
        var http = new HTTPClient();
        var extrator = new Extrator();
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println();
            System.out
                    .println("Bem vindo a Marvel, para acessar as informações do seu personagem favorito, digite o ID "
                            + "\nCapitão América(1009220) \nHomem Aranha(1009610) \n.Homem de Ferro(1009368) \nThor(1009664)"
                            + "\nEncerrar o programa(0)");
            System.out.print("-> ");
            option = Integer.parseInt(sc.nextLine());

            if (option == 1009220) {
                id = "1009220";
            } else if (option == 1009610) {
                id = "1009610";
            } else if (option == 1009368) {
                id = "1009368";
            } else if (option == 1009664) {
                id = "1009664";
            } else if (option == 5) {
                sc.close();
            }
            String NUrl = url + id + "?ts=1&apikey=6212697f5287d4e5a2cbd7b7275ae3e0&hash=cd377f4036e09a386a98ebada4c114e9";
            String json = http.searchData(NUrl);
            List<Content> conteudos = extrator.extraiConteudos(json);
            for (int i = 0; i < 100; i++) {
                Content _content = conteudos.get(i);
                System.out.println(_content.Name());
                System.out.println();
            }

        } while (option != 0);
        System.out.println("!---PROGRAMA FINALIZADO---! ");
    }
    
}
