package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.VoziloManager;
import ba.unsa.etf.rpr.dao.VoziloDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Vozilo;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Optional;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class VoziloController {
    private final VoziloManager voziloManager=new VoziloManager();


    public TextField naziv;
    public ComboBox<String> gorivo;
    public ComboBox<String> mjenjac;
    public ComboBox<Integer> maxbrputnika;
    public TextField cijenapodanu;
    public TextField brojregtablica;
    public ComboBox<String> tip;

    private Vozilo vozilo;

    public VoziloController(Vozilo vozilo) {
        this.vozilo=vozilo;
    }
@FXML
    public void initialize(){
       if(vozilo!=null){
           naziv.setText(vozilo.getNaziv());
           gorivo.setValue(vozilo.getGorivo());
           mjenjac.setValue(vozilo.getMjenjac());
           maxbrputnika.setValue(vozilo.getMaxbrputnika());
           cijenapodanu.setText(Integer.toString(vozilo.getCijenapodanu()));
           brojregtablica.setText(vozilo.getBrojregtablica());
           tip.setValue(vozilo.getTip());

       }

    }
    /*public void searchVozila(ActionEvent event){
        try {
            tabelaVozila.setItems(FXCollections.observableList(voziloManager.searchVozilo(search.getText())));
            tabelaVozila.refresh();
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    public void refreshVozila (){
        try{
            tabelaVozila.setItems(FXCollections.observableList(voziloManager.getAll()));
            tabelaVozila.refresh();
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }

    }*/

    /*public void actionObrisi(Integer id){
        try {
            Alert confirmation=new Alert(Alert.AlertType.CONFIRMATION, "Sigurno obrisati");
            Optional<ButtonType> result=confirmation.showAndWait();
            if(!result.get().getButtonData().isCancelButton()){
                voziloManager.delete(id);
                refreshVozila();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void actionIzmijeni(ActionEvent event){
        Vozilo vozilo=tabelaVozila.getSelectionModel().getSelectedItem();
        if(vozilo==null) return;

        Stage stage=new Stage();
        Parent root=null;
        try {
            //((Stage)voziloScreen.getScene().getWindow()).hide();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/vozilo.fxml"));
            VoziloController voziloController=new VoziloController();
            loader.setController(new AddUpdateVoziloController(id));
            Stage stage=new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Editovanje i dodavanje vozila za iznajmljivanje");
            stage.show();

        } catch (Exception e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }*/
    //public void actionDodaj(ActionEvent event){
public Vozilo getVozilo(){
        return vozilo;
}



}
