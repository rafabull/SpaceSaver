package screens.manual;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Controller da tela como jogar
 * @author Rafaela Cristina Bull
 */
public class ManualController {

    /**
     * Função que trata o click do botão cancelar
     * @param e evento
     */
    @FXML
    protected void btnCancelarAction(ActionEvent e){
        engine.SpaceSaver.changeScene("initial");
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
