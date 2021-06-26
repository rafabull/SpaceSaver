package elements;

/**
 * Estrutura dos tiros gerados pelas naves
 * @author Rafaela Cristina Bull
 */
public class Tiro extends Elemento {
    /**
    * Velocidade do tiro, positiva sobe, negativa desce.
    */
    private final int velocidade;

    /**
     * Indica se o tiro foi feito pelo jogador
     */
    private final boolean tiroFeito;

    /**
     * Método construtor, define os atributos do objeto.
     * @param pX    posição inicial no eixo X
     * @param pY    posição inicial no eixo Y
     * @param f     forma do elemento (matrtix na qual 1 representa um pixel ocupado e 0 um pixel livre)
     * @param vel  valor da velocidade a ser atribuída ao objeto (valor não será mais alterado)
     */
    Tiro(int pX, int pY, int[][] f, int vel, boolean tiroFeito){
        super(pX,pY,f);

        this.tiroFeito = tiroFeito;
        this.velocidade = vel;
    }

    /**
    * Método get para a velocidade.
    * @return   valor da velocidade do objeto
    */
    public int getVelocidade() {
        return Math.abs(velocidade);
    }

    /**
     * Método para verificar se o tiro foi feito pelo jogar
     * @return true caso o tiro tenha sido feito pelo jogador e false caso contrário
     */
    public boolean isTiroFeito(){
        return tiroFeito;
    }

    /**
    * Método que guia o movimento de queda ou subida do tiro.
    */
    public void movimenta() {
        if (velocidade > 0)
            posicaoY += 1;
        if (velocidade < 0){
            posicaoY -= 1;
        }
    }
}