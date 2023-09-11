package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.controllers.GlavnaController;
import ba.unsa.etf.rpr.controllers.VoziloController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

/**
 * Hello world!
 *
 */
public class App extends Application
{
    public static void main( String[] args )
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/glavna.fxml"));
        GlavnaController glavnaController=new GlavnaController();
        loader.setController(glavnaController);
        Parent root=loader.load();

        stage.setTitle("Home");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);//login forme da nisu promjenljive
        stage.show();

    }
}
