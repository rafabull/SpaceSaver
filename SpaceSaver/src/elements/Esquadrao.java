package elements;

/**
 * Estrutura da matriz de invasores
 * @author Rafaela Cristina Bull
 */
public class Esquadrao extends Elemento {

    /**
     * Quantidade de pixels que um inimigo ocupa no esqudrao
     */
    private final int ENIMESIZE = 8;

    /**
    * Matriz de invasores
    */
    private Invasor[][] invasores;

    /**
     * Quantidade de inimigos vivos no esquadrão.
     */
    private int inimigosRestantes;

    /**
    * Velocidade que todos os invasores se deslocam, velocidade maior que 0: direita; velocidade menor que 0: esquerda.
    */
    private int velocidade;

    /**
     * Método construtor, cria todas as naves invasoras pertencentes ao esquadrão e define os demais atributos.
     * @param partida   indica em qual partida o jogo se encontra
     */
    public Esquadrao(int partida){
        super(0,10+(partida%5)*8);
        velocidade = 1;
        inimigosRestantes = 11*5;
        invasores = new Invasor[5][11];

        //Definindo possíveis formas de nave
        int[][] f1 = {{0,0,1,1,0,0}, {0,1,0,0,1,0}, {1,1,1,1,1,1}, {1,0,1,1,0,1}, {1,0,1,1,0,1}, {0,1,0,0,1,0}};
        int[][] f2 = {{0,1,1,1,1,0}, {1,1,0,0,1,1}, {1,1,1,1,1,1}, {1,0,1,0,1,1}, {1,1,0,1,0,1}, {0,1,1,1,1,0}};
        int[][] f3 = {{0,0,1,1,0,0},{1,1,1,1,1,1},{1,0,0,0,0,1},{1,1,1,1,1,1},{1,0,1,1,0,1},{0,1,1,1,1,0}};

        //Criando as naves do esquadrao
        int x = 0, y = 10+partida*ENIMESIZE;
            for(int c = 0; c < 11; c++){
                    invasores[0][c] = new Invasor(x,y,f1, 1);
                    y += ENIMESIZE;
                    invasores[1][c] = new Invasor(x,y,f2, 1);
                    y += ENIMESIZE;
                    invasores[2][c] = new Invasor(x,y,f2, 1);
                    y += ENIMESIZE;
                    invasores[3][c] = new Invasor(x,y,f3, 1);
                    y += ENIMESIZE;
                    invasores[4][c] = new Invasor(x,y,f3, 1);
                x += ENIMESIZE;
                y = 10+(partida%5)*8;
            }
        this.atualizaForma();
    }

    /**
     * Método get para velocidade do esquadrao
     * @return velocidade do esquadrao
     */
    public int getVelocidade() {
        return velocidade;
    }

    /**
     * Método get para a direção que o esquadrão está se movendo
     * @return direção que o esquadrão está se movendo -1: para esquerda 1:para direita
     */
    public int getDirection(){
        return velocidade/Math.abs(velocidade);
    }

    /**
     * Método get para a quantidade de inimigos restantes
     * @return quantidade de inimigos restantes
     */
    public int getInimigosRestantes(){
        return inimigosRestantes;
    }

    /**
     * Método responsável por atualizar a forma do esquadrão a partir das naves invasoras.
     */
    public void atualizaForma(){
        forma = new int[ENIMESIZE*5][ENIMESIZE*11];
        int i, e; //Indices da matriz forma
        for(int l = 0; l < 5; l++){
            for(int c = 0; c < 11; c++){
                //Setando o início daquela forma
                i = ENIMESIZE*l;
                e = ENIMESIZE*c;
                int[][] f;
                if(invasores[l][c] == null){
                    //Caso o invasor não exista
                    f = new int[ENIMESIZE][ENIMESIZE];
                }else {
                    //Obtendo forma de um invasor existente
                    f = invasores[l][c].getForma();
                }
                //Inserindo na matriz do esquadrao
                for (int[] vet : f) {
                    for (int el : vet) {
                        forma[i][e] = el;
                        e++;
                    }
                    i++;
                    e = ENIMESIZE*c;
                }
            }
        }
    }

    /**
     * Metodo set para velocidade do esquadrão.
     * @param velocidade  velocidade na qual o esquadrao ira se move mover
     */
    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    /**
     * Método get para uma nave inimiga específica do esquadrão (matriz[5][11]
     * @param x coluna em que a nave se encontra (entre 0 e 10)
     * @param y linha em que a nave se enconta (entre 0 e 4)
     * @return  nave inimiga que esta na posição solicitado, caso não exista retorna null
     */
    public Invasor getNave(int x, int y){
        return invasores[y][x];
    }

    /**
    * Método que faz a movimentação do esquadrão de acordo com as regras de colisão com as laterais.
     * @param sWidth    largura da tela do jogo
    */
    public void movimenta(int sWidth) {
        int desce = 0;
        //Verificando as bordas no sentido esperado
        int i, soma;
        if(velocidade > 0) {
            i = 10;
            soma = -1;
        }else{
            i = 0;
            soma = 1;
        }
        boolean flag = false;
        for (int j = i; j >= 0 && j < 11; j += soma) {
            for (Invasor[] vet : invasores) {
                //Verficando se há invasores
                if(vet[j] != null){
                    flag = true;
                    break;
                }
            }
            if(flag){
                break;
            }else{
                //Caso a última coluna verificada seja nula
                if(velocidade > 0) {
                    i--;    //Existe uma coluna a menos na direita
                }else{
                    i++;    //Existe uma coluna a menos na esquerda
                }
            }
        }

        //Verificando se as bordas colidem
        if(velocidade > 0) {
            if(posicaoX + i*ENIMESIZE >= sWidth-ENIMESIZE){
                desce = 4;
            }
        }else{
            if(posicaoX + i*ENIMESIZE <= 0){
                desce = 4;
            }
        }

        //Movimentando o esquadrão
        posicaoX += velocidade/Math.abs(velocidade);
        posicaoY += desce;

        //Movimentando naves invasoras individualmente
        for (Invasor[] vet : invasores) {
            for (Invasor el : vet) {
                if(el != null) {
                    el.movimenta(velocidade/Math.abs(velocidade), 0);
                    el.movimenta(0, desce);
                }
            }
        }

        //Invertendo a velocidade se nescessário
        if(desce == 4){
            velocidade = -velocidade;
        }

        //Atualizando forma do esquadrão
        this.atualizaForma();
    }

    /**
     * Elimina a nave invasora que ocupa um determinado pixel.
     * @param cX    coordenada X do pixel ocupado pela nave
     * @param cY    coordenada Y do pixel ocupado pela nave
     * @return      retorna os pontos que aquela nave vale
     */
    public int mataNave(int cX, int cY){
        int px = (cX - posicaoX)/ENIMESIZE;
        int py = (cY - posicaoY)/ENIMESIZE;
        try {
            int ponto = invasores[py][px].getPontos();
            invasores[py][px] = null;
            atualizaForma();
            inimigosRestantes--;
            return ponto;
        }catch(Exception e){
            System.out.println("Bug na morte" + e);
            return 0;
        }

    }
}