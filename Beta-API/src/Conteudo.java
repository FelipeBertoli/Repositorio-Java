public class Conteudo {
    private String titulo;
    private String url;
    private String elenco;
    private String nota;
    private String rank;

    public Conteudo(String titulo, String url, String elenco, String nota, String rank) {
        this.titulo = titulo;
        this.url = url;
        this.elenco = elenco;
        this.nota = nota;
        this.rank = rank;
    }

    public String Titulo() {
        return titulo;
    }

    public String Imagem() {
        return url;
    }

    public String Elenco() {
        return elenco;
    }
    public String Nota() {
        return nota;
    }
    public String Rank() {
        return rank;
    }

}
