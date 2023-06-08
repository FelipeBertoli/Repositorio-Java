
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class GeradorDeCartazes {
    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {
        // public void cria(InputStream inputStream, String nomeArquivo, String nota)
        // throws Exception {

        // Leitura da imagem
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        imagemOriginal = resizeImage(imagemOriginal, 750, 1200);

        // Configurar largura e altura
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        // int novaAltura = altura + 125;
        BufferedImage novaImagem = new BufferedImage(largura, altura, BufferedImage.TRANSLUCENT);

        // Copiar imagem original para nova imagem
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar a fonte
        // var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 48);
        // graphics.setColor(Color.WHITE);
        // graphics.setFont(fonte);

        // escrever uma frase na nova imagem
        // graphics.drawString(nota, 275, novaAltura - 50);

        // Salvar Imagem
        ImageIO.write(novaImagem, "png", new File("./output/" + nomeArquivo));

    }

    BufferedImage resizeImage(BufferedImage imagemOriginal, int larguraD, int alturaD) throws IOException {
        Image posImagem = imagemOriginal.getScaledInstance(larguraD, alturaD, Image.SCALE_SMOOTH);
        BufferedImage resultado = new BufferedImage(larguraD, alturaD, BufferedImage.TYPE_INT_RGB);
        resultado.getGraphics().drawImage(posImagem, 0, 0, null);
        return resultado;
    }

}
