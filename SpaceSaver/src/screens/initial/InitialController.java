package screens.initial;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Controller da tela inicial
 * @author Rafaela Cristina Bull
 */
public class InitialController {

    /**
     * Função que trata o click do botão como jogar
     * @param e evento
     */
    @FXML
    protected void btnManualAction(ActionEvent e){
        engine.SpaceSaver.changeScene("manual");
    }

    /**
     * Função que trata o click do botão jogar
     * @param e evento
     */
    @FXML
    protected void btnJogarAction(ActionEvent e){
        engine.SpaceSaver.changeScene("game");
    }
}
