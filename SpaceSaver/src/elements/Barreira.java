package elements;

/**
 * Estrutura da barreira estática que fica entre  o canhão e os invasores.
 * @author Rafaela Cristina Bull
 */
public class Barreira extends Elemento {
    /**
     * Método construtor, define os atributos do objeto.
     * @param pX    posição inicial no eixo X
     * @param pY    posição inicial no eixo Y
     */
    public Barreira(int pX, int pY){
        super(pX,pY);
        this.forma = new int[][]{{0,0,1,1,1,1,1,1,1,1,1,1,1,0,0},{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                 {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,0,0,0,0,0,0,0,1,1,1,1},{1,1,1,1,0,0,0,0,0,0,0,1,1,1,1},
                                 {1,1,0,0,0,0,0,0,0,0,0,0,0,1,1},{1,1,0,0,0,0,0,0,0,0,0,0,0,1,1},{1,1,0,0,0,0,0,0,0,0,0,0,0,1,1}};
    }
    /**
    * Ao receber dano em um pixel esse pixel irá desaparecer, deixando de fazer parte da forma da estrutura.
    * @param cX coordenada x que recebeu o dano
    * @param cY coordenada y que rebeu o dano 
    */
    public void recebeDano(int cX, int cY) {
        int x = cX - posicaoX;
        int y = cY - posicaoY;

        forma[y][x] = 0;
    }
}