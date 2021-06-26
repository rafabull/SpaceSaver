package elements;

/**
 * Estrutura do canhão controlado pelo jogador
 * @author Rafaela Cristina Bull
 */
public class Canhao extends Nave {
    /**
     * Velocidade na qual o canhão pode se mover.
     */
    private final int velocidade;

    /**
     * Método construtor, define os atributos do objeto.
     * @param pX    posição inicial no eixo X
     * @param pY    posição inicial no eixo Y
     * @param vel   velocidade a ser atribuida ao objeto
     */
    public Canhao(int pX, int pY, int vel){
        super(pX, pY);
        this.velocidade = vel;
        this.forma = new int[][]{{0,0,1,1,0,0},{0,0,1,1,0,0},{0,1,1,1,1,0},{0,1,1,1,1,0},{1,1,1,1,1,1},{1,1,1,1,1,1}};
    }

    /**
     * Método get para velocidade.
     * @return  velocidade daquele canhão
     */
    public int getVelocidade() {
        return velocidade;
    }

    /**
     * Função de movimentação, altera a posicaoX do canhao conforme a direção dada pelo usuário.
     * @param dir    indica a direção do movimento -1= esquerda +1=direita
     */
    public void movimenta(int dir) {
        posicaoX = posicaoX + dir;
    }
}