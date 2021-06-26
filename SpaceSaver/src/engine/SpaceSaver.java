package engine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Classe executável
 * @author Rafaela Cristina Bull
 */
public class SpaceSaver extends Application {

    private static Stage stage;

    private static Scene initialScene;
    private static Scene manualScene;
    private static Scene gameScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        stage.setTitle("Space Saver");

        Parent initialFxml = FXMLLoader.load(getClass().getResource("../screens/initial/Initial.fxml"));
        initialScene = new Scene(initialFxml, 800, 500);

        Parent manualFxml = FXMLLoader.load(getClass().getResource("../screens/manual/Manual.fxml"));
        manualScene = new Scene(manualFxml, 800, 500);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        Parent gameFxml = FXMLLoader.load(getClass().getResource("../screens/game/Game.fxml"));
        gameScene = new Scene(gameFxml, bounds.getWidth(), bounds.getHeight());

        stage.setScene(initialScene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Função para alterar a cena que esta sendo exibida pela aplicação
     * @param str cena a ser exibida (opções: "inital", "manual", "game")
     */
    public static void changeScene(String str) {
        switch(str){
            case "initial":
                stage.close();
                stage.setScene(initialScene);
                stage.show();
                break;
            case "manual":
                stage.close();
                stage.setScene(manualScene);
                stage.show();
                break;
            case "game":
                stage.close();
                stage.setScene(gameScene);
                stage.show();
                gameScene.getRoot().requestFocus();
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
