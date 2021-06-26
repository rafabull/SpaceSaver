package elements;

/**
 * Estrutura comum das naves jogador (Canhao) e Invasoras.
 * @author Rafaela Cristina Bull
 */
public abstract class Nave extends Elemento {
    /**
     * Método construtor, define todos os atributos do objeto.
     * @param pX    posição inicial no eixo X
     * @param pY    posição inicial no eixo Y
     * @param f     forma do elemento (matrtix na qual 1 representa um pixel ocupado e 0 um pixel livre)
     */
    Nave(int pX, int pY, int[][] f){
        super(pX, pY, f);
    }

    /**
     * Método construtor, define os atributos do objeto menos a forma.
     * @param pX    posição inicial no eixo X
     * @param pY    posição inicial no eixo Y
     */
    Nave(int pX, int pY){
        super(pX, pY);
    }

    /**
     * Método que produz um tiro com base no dano que a nave causa.
     * @param vel       velocidade do tiro, vel maior que 0: tiro desce, vel menor que 0: tiro sobe
     * @param tiroFeito indica se o tiro foi feito pelo jogador
     * @return          tiro gerado pela nave
     */
    public Tiro atira(int vel, boolean tiroFeito) {
        int[][] f = {{1},{1}};
        return new Tiro(posicaoX+2, posicaoY+vel, f, vel, tiroFeito);
    }
}