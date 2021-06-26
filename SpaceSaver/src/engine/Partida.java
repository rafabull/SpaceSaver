package engine;

import elements.Canhao;
import elements.Elemento;
import elements.Esquadrao;

import java.util.*;
import elements.*;
import javafx.scene.canvas.Canvas;
import screens.game.GameCanvas;


/**
 * Classe que coordena o andamento da partida
 * @author Rafaela Cristina Bull
 */
public class Partida {
    /**
     * Controla a taxa de atualização do jogo
     */
    private final int FRAMERATE =  20;

    /**
     * Indica quantos frames foram exibidos
     */
    private long FRAMECOUNT = 1;

    /**
     * Indica em qual partida o jogo esta
     */
    private int partida = 1;

    /**
    * Quantidade de vidas disponiveis para o jogador.
    */
    private int vidas;

    /**
    * Pontos acumulados pelo jogador.
    */
    private int pontos;

    /**
     * Sinaliza se o jogo esta em pausa.
     */
    private boolean pausa;

    /**
     * Sinaliza se o jogo foi finalizado por morte do jogaror
     */
    private boolean gameover;

    /**
     * Indica se o jogo já foi iniciado
     */
    private boolean started;

    /**
     * Indica se houve um click do jogador para movimentação
     */
    private int click;

    /**
     * Indica se houve um click do jogador para atirar
     */
    private long spaceClicked = -10;

    /**
     * Vetor com todos os elementos que estão fazendo parte do jogo.
     */
    private final ArrayList<Elemento> elementos;

    /**
     * Objeto que possui as funções de manipulação da tela do jogo
     */
    private final GameCanvas tela;

    /**
     * Método construtor, define os atributos do objeto.
     */
    public Partida(double w, double h, Canvas t){
        vidas = 3;
        pontos = 0;
        pausa = false;
        gameover = false;
        started = false;
        int PIXELSIZE = 7;
        tela = new GameCanvas(t, (int) w/ PIXELSIZE, (int) h/ PIXELSIZE, PIXELSIZE, this);

        //Preenchendo a matriz tela
        elementos = new ArrayList<>();
        elementos.add(new Canhao(tela.getWidth() /2, tela.getHeight()-22, 5));
        elementos.add(new Esquadrao(0));
        elementos.add(new Barreira((tela.getWidth()/15), (tela.getHeight()/8)*6));
        elementos.add(new Barreira((tela.getWidth()/15)*3, (tela.getHeight()/8)*6));
        elementos.add(new Barreira((tela.getWidth()/15)*5, (tela.getHeight()/8)*6));
        elementos.add(new Barreira((tela.getWidth()/15)*7, (tela.getHeight()/8)*6));
        elementos.add(new Barreira((tela.getWidth()/15)*9, (tela.getHeight()/8)*6));
        elementos.add(new Barreira((tela.getWidth()/15)*11, (tela.getHeight()/8)*6));
        elementos.add(new Barreira((tela.getWidth()/15)*13, (tela.getHeight()/8)*6));

    }

    /**
     * Método get para o FRAMERATE do jogo
     * @return o FRAMERATE do jogo
     */
    public int getFRAMERATE() {
        return FRAMERATE;
    }

    /**
     * Método get para o vetor de elementos
     * @return o vetor de elementos da partida
     */
    public ArrayList<Elemento> getElementos() {
        return elementos;
    }

    /**
     * Método get para a pontuação da partida.
     * @return  o valor inteiro da pontuação
     */
    public int getPontos() {
        return pontos;
    }

    /**
     * Método get para as vidas restantes.
     * @return  o valor inteiro das vidas restantes
     */
    public int getVidas() {
        return vidas;
    }

    /**
     * Método que verifica se o jogo está pausado
     * @return retorna se o jogo está pausado (true = esta pausado, false = não está pausado)
     */
    public boolean isPausado(){
        return pausa;
    }

    /**
     * Método que verifica se o jogo terminou por morte do jogador
     * @return true = jogo encerrado, false = jogo em andamento
     */
    public boolean isGameover(){
        return gameover;
    }

    /**
     * Inverte o estado de pausa do jogo
     */
    public void tooglePausa() {
        pausa = !pausa;
    }

    /**
     * Define o estado de pausa do jogo
     * @param p valor para a pausa (boolean)
     */
    public void setPausa(boolean p){
        pausa = p;
    }

    /**
     * Método que verifica se o jogo já foi iniciado
     * @return true = já foi iniciado, false = ainda não foi
     */
    public boolean isStarted() {
        return started;
    }

    /**
     * Método get para a propriedade starter
     * @param started novo valor (boolean)
     */
    public void setStarted(boolean started) {
        this.started = started;
    }

    /**
     * Método usado para iniciar o jogo
     */
    public void startGame() {
        started = true;
    }

    /**
     * Método que coordena a movimentação dos elementos do jogo.
     */
    public void movimentos(){
        for(Elemento el: elementos){
            if (el instanceof Esquadrao){
                Esquadrao esq = (Esquadrao) el;
                if(FRAMECOUNT % (6 - Math.abs(esq.getVelocidade())) == 0)
                    esq.movimenta(tela.getWidth());
            }else
            if (el instanceof Canhao && click != 0) {
                Canhao c = (Canhao) el;
                if(FRAMECOUNT % (6 - c.getVelocidade()) == 0) {
                    if (c.getPosicaoX() >= 0 && click < 0)
                        c.movimenta(click);
                    if (c.getPosicaoX() < tela.getWidth()-4 && click > 0)
                        c.movimenta(click);
                    click = 0;
                }
            }else
            if(el instanceof Tiro){
                Tiro t = (Tiro) el;
                if(FRAMECOUNT % (6 - t.getVelocidade()) == 0)
                    t.movimenta();
            }else
            if (el instanceof InvasorEspecial){
                InvasorEspecial ie = (InvasorEspecial) el;
                if(FRAMECOUNT % (6 - ie.getVelocidade()) == 0)
                    ie.movimenta();
            }
        }
        tela.refreshTela();
    }

    /**
     * Método que verifica se um elemento potencial (Invasor) foi atingido por um tiro
     * @param e1    elemento potencial
     * @param t     tiro
     * @param x     coordenada X
     * @param y     coordenada Y
     */
    public void verificaInvasor(Elemento e1, Tiro t, int x, int y){
        if(e1 instanceof Esquadrao)
            if(!(t != null && !t.isTiroFeito())) {
                pontos += ((Esquadrao) e1).mataNave(x, y);
                elementos.remove(t);
            }
    }

    /**
     * Método que verifica se um elemento potencial (Invasor Especial) foi atingido por um tiro
     * @param e1    elemento potencial
     * @param t     tiro
     */
    public void verificaInvasorEspecial(Elemento e1, Tiro t){
        if(e1 instanceof InvasorEspecial){
            if(!(t != null && !t.isTiroFeito())) {
                InvasorEspecial ie = (InvasorEspecial) e1;
                pontos += ie.getPontos();
                elementos.remove(e1);
                elementos.remove(t);
            }
        }
    }

    /**
     * Método que verifica se um elemento potencial (Jogador) foi atingido por um tiro
     * @param e1    elemento potencial
     * @param t     tiro
     */
    public void verificaJogador(Elemento e1, Tiro t) {
        if(e1 instanceof Canhao){
            if(!(t != null && t.isTiroFeito())) {
                elementos.remove(e1);
                elementos.add(0, new Canhao(tela.getWidth() / 2, tela.getHeight() - 22, 5));
                vidas -= 1;
                elementos.remove(t);
            }
        }
    }

    /**
     * Método que verifica se um elemento potencial (Barreira) foi atingido por um tiro
     * @param e1    elemento potencial
     * @param t     tiro
     * @param x     coordenada X
     * @param y     coordenada Y
     */
    public void vericaBarreira(Elemento e1, Tiro t, int x, int y) {
        if(e1 instanceof Barreira){
            Barreira b = (Barreira) e1;
            b.recebeDano(x,y);
            elementos.remove(t);
        }
    }

    /**
     * Método responsável por lidar com a possível colião de dois elementos que exibiram no mesmo pixel,
     * verificando se eles colidiram ou não, se necessário exclui esses elementos,
     * podendo também interferir na pontuação ou na quantidade de vidas.
     * @param e1    elemento numero 1 a ser verificado
     * @param e2    elemento numero 2 a ser verificado
     * @param cX    coordenada X do pixel redundante
     * @param cY    coordenada Y do pixel redundante
     */
    public void handleColison(Elemento e1, Elemento e2, int cX, int cY){
        if(e1.estaNaCoordenada(cX,cY) && e2.estaNaCoordenada(cX,cY)){
            //ScYstem.out.println("colidiu");
            Tiro t = null;
            if(e1 instanceof Tiro)
                t = (Tiro) e1;
            if(e2 instanceof Tiro)
                t = (Tiro) e2;

            verificaInvasor(e1, t, cX, cY);
            verificaInvasor(e2, t, cX, cY);

            verificaInvasorEspecial(e1, t);
            verificaInvasorEspecial(e2, t);

            verificaJogador(e1, t);
            verificaJogador(e2, t);

            vericaBarreira(e1, t, cX, cY);
            vericaBarreira(e2, t, cX, cY);
        }
    }

    /**
     * Altera o click para movimento para esquerda
     */
    public void clickLeft(){
        click = -1;
    }

    /**
     * Altera o click para a direita
     */
    public void clickRight(){
        click = 1;
    }

    /**
     * Altera o click space para ativado
     */
    public void clickSpace(){
        if(FRAMECOUNT-spaceClicked > 30) {
            spaceClicked = FRAMECOUNT;
            Canhao c = (Canhao) elementos.get(0);
            elementos.add(c.atira(-3, true));
        }
    }

    /**
     * Coordena as funções para o jogo funcionar (a função deve ser usada em um timeloop)
     */
    public void runGame(){
        if(gameover){
            tela.telaGameOver();
        }else
        if(!started){
            tela.refreshTela();
            tela.telaNotStarted();
        }else
        if(!pausa) {
            if(vidas <= 0){
                pausa = true;
                gameover = true;
            }
            if(FRAMECOUNT%(1200) == 0){
                elementos.add(new InvasorEspecial(-5,1, 5,2));
            }
            try {
                for(Elemento e: elementos){
                    if(e instanceof Tiro){
                        if(e.getPosicaoY() > tela.getHeight() || e.getPosicaoY() < 0){
                            elementos.remove(e);
                        }
                    }
                    if( e instanceof InvasorEspecial)
                        if(e.getPosicaoX() > tela.getWidth())
                            elementos.remove(e);
                    if(e instanceof Esquadrao){
                        Esquadrao esq = (Esquadrao) e;
                        if (esq.getInimigosRestantes() == 0){
                            partida++;
                            esq = new Esquadrao(partida);
                            elementos.set(1, esq);
                            elementos.removeIf(el -> el instanceof Tiro);
                        }else {
                            if (esq.getInimigosRestantes() < 15)
                                esq.setVelocidade(esq.getDirection()*4);
                            else if (esq.getInimigosRestantes() < 30)
                                esq.setVelocidade(esq.getDirection()*3);
                            else if (esq.getInimigosRestantes() < 40)
                                esq.setVelocidade(esq.getDirection()*2);
                        }
                        if(FRAMECOUNT%(4*(10-partida)) == 0) {
                            Invasor n = esq.getNave((int) (Math.random() * 10), (int) (Math.random() * 4));
                            for (int i = 0; i < 10; i++) {
                                if (n != null)
                                    break;
                                n = esq.getNave((int) (Math.random() * 10), (int) (Math.random() * 4));
                            }
                            elementos.add(n.atira(3, false));
                        }
                    }
                }
            }catch (Exception ex){ }
            movimentos();
            FRAMECOUNT++;
        }
    }
}