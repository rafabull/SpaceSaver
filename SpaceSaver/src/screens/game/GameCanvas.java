package screens.game;

import elements.Elemento;
import elements.Tiro;
import engine.Partida;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

/**
 * Funções de exibição do jogo
 * @author Rafaela Cristina Bull
 */
public class GameCanvas{

    /**
     * Canvas usado para renderização do jogo
     */
    private final Canvas tela;

    /**
     * Largura da tela do jogo
     */
    private final int width;

    /**
     * Altura da tela do jogo
     */
    private final int height;

    /**
     * Tamanho do pixel a ser exibido
     */
    private final int PIXELSIZE;

    /**
     * Objeto para acesso das funções e elementos da partida
     */
    private final Partida partida;

    /**
     * Método construtor, define todos os atributos necessáros
     * @param t     Canvas que será usado para renderização do jogo
     * @param w     largura da tela
     * @param h     altura da tela
     * @param pixel tamanho do pixel
     * @param part  objeto da partida
     */
    public GameCanvas(Canvas t, int w, int h, int pixel, Partida part){
        tela = t;
        width = w;
        height = h;
        PIXELSIZE = pixel;
        partida = part;
    }

    /**
     * Método get para a largura do jogo
     * @return largura do jogo
     */
    public int getWidth() {
        return width;
    }

    /**
     * Método get para a altura do jogo
     * @return altura do jogo
     */
    public int getHeight() {
        return height;
    }

    /**
     * Método para exibição dos elementos na tela durante o jogo
     */
    public void refreshTela(){
        ArrayList<Elemento> elementos = partida.getElementos();
        GraphicsContext gc = tela.getGraphicsContext2D();
        int xtela = 0, ytela = 0;
        boolean verifica = false;
        Elemento last = null;
        gc.clearRect(0, 0, tela.getWidth(), tela.getHeight());
        //Exibindo a tela do jogo
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                try {
                    for (Elemento e : elementos) {
                        if (e.exibe(x, y)) {
                            if(e instanceof Tiro)
                                gc.setFill(Color.ORANGE);
                            else
                                gc.setFill(Color.WHITE);
                            gc.fillRect(xtela, ytela, PIXELSIZE, PIXELSIZE);
                            //Caso mais de um elemento tenha sido exibido naquele pixel
                            if (verifica) {
                                //Verificar se houve colisão
                                partida.handleColison(e, last, x, y);
                            }
                            verifica = true;
                            last = e;
                        }
                    }
                }catch (Exception err){ }
                xtela += PIXELSIZE;
                verifica = false;
                last = null;
            }
            ytela += PIXELSIZE;
            xtela = 0;
        }
        //Exibindo barra final
        gc.setFill(new Color(0, 0,0, 0.7));
        gc.fillRect(0, (height-15)*PIXELSIZE-5, width*PIXELSIZE, PIXELSIZE*15);
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("Comic Sans MS", FontWeight.SEMI_BOLD, 30));
        gc.setTextAlign(TextAlignment.LEFT);
        gc.fillText("Pontos: " + partida.getPontos(), 10, (height-15)*PIXELSIZE+30);
        gc.fillText("Vidas Restantes: " + partida.getVidas(), 10, (height-15)*PIXELSIZE+65);
    }

    /**
     * Exibe a tela de Game Over no Canvas do jogo
     */
    public void telaGameOver(){
        GraphicsContext gc = tela.getGraphicsContext2D();
        gc.clearRect(0,0, width*PIXELSIZE, height*PIXELSIZE);
        gc.setFont(Font.font("Comic Sans MS", FontWeight.SEMI_BOLD, 60));
        gc.setFill(Color.WHITE);

        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText(
                "Game Over :(",
                Math.round((float) width*PIXELSIZE / 2)+10,
                Math.round((float) height*PIXELSIZE / 2)-60
        );
        gc.setFont(Font.font("Comic Sans MS", FontWeight.SEMI_BOLD, 30));
        gc.fillText(
                "Total de pontos: " + partida.getPontos(),
                Math.round((float) width*PIXELSIZE / 2)+10,
                Math.round((float) height*PIXELSIZE / 2)
        );
        gc.setFont(Font.font("Comic Sans MS", FontWeight.SEMI_BOLD, 20));
        gc.fillText(
                "Presione ESC",
                Math.round((float) width*PIXELSIZE / 2)+10,
                Math.round((float) height*PIXELSIZE / 2)+40
        );
    }

    /**
     * Exibe a mensagem de jogo não iniciado na tela
     */
    public void telaNotStarted(){
        GraphicsContext gc = tela.getGraphicsContext2D();
        gc.setFont(Font.font("Comic Sans MS", FontWeight.SEMI_BOLD, 25));
        gc.setFill(Color.WHITE);

        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText(
                "Aperte qualquer tecla para iniciar!",
                Math.round((float) width*PIXELSIZE / 2)+10,
                Math.round((float) height*PIXELSIZE / 2)-25
        );
    }

}
