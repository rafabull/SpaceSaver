package elements;

/**
 * Invasor que aparece aleatoriamente
 * @author Rafaela Cristina Bull
 */
public class InvasorEspecial extends Invasor {
    /**
     * Velocidade definida como  positiva para direita e negativa para esquerda.
     */
    private final int velocidade;

    /**
     * Método construtor, define os atributos do objeto.
     * @param pX    posição inicial no eixo X
     * @param pY    posição inicial no eixo Y
     * @param p     quantos pontos a nave irá valer
     * @param vel   velocidade que o invasor irá se mover
     */
    public InvasorEspecial(int pX, int pY, int p, int vel){
        super(pX, pY, p);
        forma = new int[][]{{0,0,1,1,1,1,1,1,0,0}, {0,1,0,0,1,1,0,0,1,0}, {1,0,0,0,1,1,0,0,0,1},
                            {1,1,1,1,1,1,1,1,1,1}, {0,1,1,1,0,0,1,1,1,0}, {0,1,0,1,0,0,1,0,1,0}};
        velocidade = vel;
    }

    /**
     * Método get para velocidade.
     * @return  velocidade do invasor
     */
    public int getVelocidade() {
        return velocidade;
    }

    /**
     * Método que realiza o movimento de acordo com a sua velocidade.
     */
    public void movimenta() {
        posicaoX = posicaoX + velocidade/Math.abs(velocidade);
    }
}