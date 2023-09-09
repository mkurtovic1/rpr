package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SampleController {
    public Label labela1;
    public Button dugmeid;
    public TextField textid;

    public void nekaAkcija(ActionEvent actionEvent) {
        textid.setText("neki tekst");
    }
}
