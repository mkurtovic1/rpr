package ba.unsa.etf.rpr.controllers;



import ba.unsa.etf.rpr.business.VoziloManager;
import ba.unsa.etf.rpr.domain.Vozilo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class AddUpdateVoziloController {
    private Vozilo modifiedVozilo;
    private VoziloManager voziloManager;

    @FXML
    private TextField naziv;
    ObservableList<String> goriva= FXCollections.observableArrayList("dizel", "benzin", "plin");
    @FXML
    private ComboBox<String> gorivo;
    ObservableList<String> mjenjaci=FXCollections.observableArrayList("automatic", "manuel");
    @FXML
    private ComboBox<String> mjenjac;
    ObservableList<String> putnici=FXCollections.observableArrayList("1", "2", "3", "4", "5");
    @FXML
    private ComboBox<String> maxbrputnika;
    @FXML
    private TextField cijenapodanu;
    @FXML
    private TextField brojregtablica;
    @FXML
    private ComboBox<String> tip;
    @FXML
    private Button btnsave;
    @FXML
    private Button btncancel;


    public AddUpdateVoziloController(){

    }
    @FXML
    public void initialize(){
        gorivo.setItems(goriva);
        mjenjac.setItems(mjenjaci);
        maxbrputnika.setItems(putnici);
        System.out.println("Add and update vozilo initialized.");
        naziv.clear();


    }
    public void setVozilo(Vozilo vozilo){
        if(vozilo!=null){
            this.modifiedVozilo=vozilo;
            naziv.setText(vozilo.getNaziv());
            gorivo.setValue(vozilo.getGorivo());
            mjenjac.setValue(vozilo.getMjenjac());
            maxbrputnika.setValue(String.valueOf(vozilo.getMaxbrputnika()));
            cijenapodanu.setText(String.valueOf(vozilo.getCijenapodanu()));
            brojregtablica.setText(vozilo.getBrojregtablica());
            tip.setValue(vozilo.getTip());
        }
    }
    public Vozilo getModifiedVozilo(){
        if(modifiedVozilo!=null){
            modifiedVozilo.setNaziv(naziv.getText());
            modifiedVozilo.setGorivo(gorivo.getValue());
            modifiedVozilo.setMjenjac(gorivo.getValue());
            modifiedVozilo.setMaxbrputnika(Integer.parseInt(maxbrputnika.getValue()));
            modifiedVozilo.setCijenapodanu(Integer.parseInt(cijenapodanu.getText()));
            modifiedVozilo.setBrojregtablica(brojregtablica.getText());
            modifiedVozilo.setTip(tip.getValue());
            return modifiedVozilo;
        }
        return null;
    }
    @FXML
    private void saveForm(ActionEvent event){
        Vozilo modifiedVozilo=getModifiedVozilo();
        if(modifiedVozilo!=null){
            try {
                voziloManager.add(modifiedVozilo);
                showAlert(Alert.AlertType.INFORMATION, "Succes", "Vozilo added successfully");
            }catch (Exception e){
                showAlert(Alert.AlertType.ERROR, "Error", "Faild to add Vozilo"+e.getMessage());
            }
        }
    }
    @FXML
    private void cancelForm(ActionEvent event){
        System.out.println("Cancel button clicked.");
        Stage stage=(Stage) btncancel.getScene().getWindow();
        stage.close();
    }
    private void showAlert(Alert.AlertType alertType,String title, String contentText){
        Alert alert=new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }


}
