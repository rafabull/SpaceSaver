package elements;

/**
 * Estrutura básica dos elementos do jogo, todos eles estão em alguma posição na tela e apresentam um formato que
 * pode ser definido por uma matriz quadrada de pixels.
 * @author Rafaela Cristina Bull
 */

public abstract class Elemento {
    /**
     * Indica a posição em relação ao eixo X que o elemento se encontra na tela.
     */
    protected int posicaoX;

    /**
     * Indica a posição em relação ao eixo Y que o elemento se encontra na tela.
     */
    protected int posicaoY;

    /**
     * Indica o formato do elemento, matriz quadrada onde 1 indica um pixel ocupado e 0 um pixel livre.
     */
    protected int[][] forma;

    /**
     * Método construtor, define os atributos do objeto, com exceção da forma.
     * @param pX    posição inicial no eixo X
     * @param pY    posição inicial no eixo Y
     */
    Elemento(int pX, int pY){
        this.posicaoX = pX;
        this.posicaoY = pY;
    }

    /**
     * Método construtor, define todos os atributos do objeto.
     * @param pX    posição inicial no eixo X
     * @param pY    posição inicial no eixo Y
     * @param f     forma do elemento (matrtix na qual 1 representa um pixel ocupado e 0 um pixel livre)
     */
    Elemento(int pX, int pY, int[][] f){
        this.posicaoX = pX;
        this.posicaoY = pY;
        this.forma = f;
    }

    /**
     * Metodo get para posição do elemento no eixo X.
     * @return  posição do elemento no eixo X
     */
    public int getPosicaoX() {
        return posicaoX;
    }

    /**
     * Método get para posição do elemento no eixo Y.
     * @return  posição do elemento no eixo Y
     */
    public int getPosicaoY() {
        return posicaoY;
    }

    /**
     * Método get para forma do elemento.
     * @return  forma do elemento
     */
    public int[][] getForma() {
        return forma;
    }

    /**
     * Função responsável por exibr o elemento na tela, irá receber o pixel atual a ser exebido se o elemento ocupar aquele pixel  ele será colorido.
     * @param atualX posição x que a exibição se encontra
     * @param atualY posição y que a exibição se encontra
     * @return  retorna true caso tenho ocorrido uma exibição e false caso contário
     */
    public boolean exibe(int atualX, int atualY) {
        int fx = atualX - posicaoX;
        int fy = atualY - posicaoY;
        if(fy >= 0 && fy < forma.length){
            if(fx >= 0 && fx < forma[0].length){
                return forma[fy][fx] == 1;
            }
        }
        return false;
    }

    /**
     * Verifica se um elemento está ocupando a coordenada recebida, para estar ocupando uma coordenada ele deve
     * possuir sua matriz forma sobre aquela coordenada e possuir o valor correspondente 1.
     * @param cX    valor da coordenada no eixo X
     * @param cY    valor da coordenada no eixo Y
     * @return      true: o elemento está naquela coordenada, false: o elemento não está naquela coordenada
     */
    public boolean estaNaCoordenada(int cX, int cY){
        int px = cX - posicaoX;
        int py = cY - posicaoY;

        if(py < forma.length && px < forma[0].length)
            return forma[py][px] == 1;
        else
            return false;
    }
}