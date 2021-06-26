package screens.game;

import engine.Partida;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller da tela de jogo
 * @author Rafaela Cristina Bull
 */
public class GameController implements Initializable {

    private Partida partida;

    private Timeline timeline;

    @FXML
    private Button btnJogar;

    @FXML
    private Label lbSituacao;

    @FXML
    private AnchorPane pausePane;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Canvas telaPartida;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        restart();
    }

    /**
     * Função para start e restart do jogo
     */
    public void restart() {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        partida = new Partida(bounds.getWidth(), bounds.getHeight(), telaPartida);
        telaPartida.setWidth(bounds.getWidth());
        telaPartida.setHeight(bounds.getHeight());

        timeline = new Timeline(
                partida.getFRAMERATE(),
                new KeyFrame(
                        Duration.millis(partida.getFRAMERATE()),
                        event -> partida.runGame()
                )
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Função que trata o click do botão caixa de dialogo
     * @param e evento
     */
    @FXML
    protected void bntFecharAction (ActionEvent e){
        mainPane.requestFocus();
        pausePane.setVisible(false);
        partida.setPausa(false);
    }

    /**
     * Função que trata o click do botão voltar/jogar
     * @param e evento
     */
    @FXML
    protected void bntJogarAction (ActionEvent e){
        if(partida.isGameover()){
            timeline.stop();
            restart();
        }
        bntFecharAction(e);
    }

    /**
     * Função que trata o click do botão como jogar
     * @param e evento
     */
    @FXML
    protected void btnManualAction (ActionEvent e){
        partida.tooglePausa();
        engine.SpaceSaver.changeScene("manual");
        if(partida.isGameover()){
            restart();
        }
        partida.setStarted(false);
        bntFecharAction(e);
    }

    /**
     * Função que trata o click do botão sair
     * @param e evento
     */
    @FXML
    protected void bntSairAction(ActionEvent e){
        partida.tooglePausa();
        engine.SpaceSaver.changeScene("initial");
        restart();
        bntFecharAction(e);
    }

    /**
     * Função que trata as teclas pressionadas durante o jogo
     * @param eKey evento
     */
    @FXML
    public void keyClickHandle(KeyEvent eKey) {
        if(!partida.isStarted()){
            partida.startGame();
        }else
        if(eKey.getCode() == KeyCode.ESCAPE){
            pausePane.setVisible(!pausePane.isVisible());
            if(partida.isGameover()){
                lbSituacao.setText("Game Over");
                btnJogar.setText("Jogar");
            }else{
                lbSituacao.setText("Jogo Pausado");
                btnJogar.setText("Voltar");
            }
            if(partida.isPausado())
                pausePane.requestFocus();
            else
                mainPane.requestFocus();
            partida.tooglePausa();
        }else
        if(eKey.getCode() == KeyCode.LEFT){
            partida.clickLeft();
        }else
        if(eKey.getCode() == KeyCode.RIGHT){
            partida.clickRight();
        }else
        if(eKey.getCode() == KeyCode.SPACE){
            partida.clickSpace();
        }
    }
}
