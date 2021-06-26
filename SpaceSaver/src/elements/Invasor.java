package elements;

/**
 * Estrutura de cada nave invasora
 * @author Rafaela Cristina Bull
 */

public class Invasor extends Nave {

    /**
     * Quantos pontos aquela nave vale.
     */
    protected int pontos;

    /**
     * Método construtor, define os atributos do objeto.
     * @param pX    posição inicial no eixo X
     * @param pY    posição inicial no eixo Y
     * @param f     forma do elemento (matrtix na qual 1 representa um pixel ocupado e 0 um pixel livre)
     * @param p     quantos pontos a nave irá valer
     */
    Invasor(int pX, int pY, int[][] f, int p){
        super(pX, pY, f);
        this.pontos = p;
    }

    /**
     * Método construtor, define os atributos do objeto (com exeção da forma).
     * @param pX    posição inicial no eixo X
     * @param pY    posição inicial no eixo Y
     * @param p     quantos pontos a nave irá valer
     */
    Invasor(int pX, int pY, int p){
        super(pX, pY);
        this.pontos = p;
    }

    /**
     * Função get pontos que a nave vale.
     * @return  pontos que a nave vale
     */
    public int getPontos() {
        return pontos;
    }

    @Override
    public Tiro atira(int vel, boolean tiroFeito) {
        int[][] f = {{1},{1}};
        return new Tiro(posicaoX+4, posicaoY+2*vel, f, vel, tiroFeito);
    }
    /**
     * Função que realiza a movimentação de cada invasor, recebe a nova posição que a nave deve ocupar.
     * @param vX variação da posição do elemento no eixo X
     * @param vY variação da posição do elemento no eixo Y
     */
    public void movimenta(int vX, int vY) {
        posicaoX += vX;
        posicaoY += vY;
    }
}