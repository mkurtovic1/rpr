package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.IznajmljivanjeManager;
import ba.unsa.etf.rpr.business.VoziloManager;
import ba.unsa.etf.rpr.dao.IznajmljivanjeSQLImpl;
import ba.unsa.etf.rpr.domain.Vozilo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class VoziloController {
    private final VoziloManager voziloManager=new VoziloManager();
    @FXML
    public BorderPane voziloScreen;


    public TableView tableViewVozila;
    public TextField search;
    public TableColumn<Vozilo, Integer> columnVoziloId;
    public TableColumn<Vozilo, String> columnVoziloNaziv;
    public TableColumn<Vozilo, String> columnVoziloGorivo;
    public TableColumn<Vozilo, String> columnVoziloMjenjac;
    public TableColumn<Vozilo, Integer> columnVoziloMaxBrPutnika;
    public TableColumn<Vozilo, Integer> columnVoziloCijenaPoDanu;
    public TableColumn<Vozilo, String> columnVoziloBrojRegTablica;
    public TableColumn<Vozilo, String> columnVoziloTip;

    public void initialize(){
        columnVoziloId.setCellValueFactory(new PropertyValueFactory<Vozilo, Integer>("id"));
        columnVoziloNaziv.setCellValueFactory(new PropertyValueFactory<Vozilo, String>("Naziv"));
        columnVoziloGorivo.setCellValueFactory(new PropertyValueFactory<Vozilo, String>("gorivo"));
        columnVoziloMjenjac.setCellValueFactory(new PropertyValueFactory<Vozilo, String>("mjenjac"));
        columnVoziloMaxBrPutnika.setCellValueFactory(new PropertyValueFactory<Vozilo, Integer>("putnici"));
        columnVoziloCijenaPoDanu.setCellValueFactory(new PropertyValueFactory<Vozilo, Integer>("cijena"));
        columnVoziloBrojRegTablica.setCellValueFactory(new PropertyValueFactory<Vozilo, String>("tablice"));
        columnVoziloTip.setCellValueFactory(new PropertyValueFactory<Vozilo, String>("tip"));

        refreshVozila();

    }
    public void searchVozila(ActionEvent event){
        try {
            tableViewVozila.setItems(FXCollections.observableList(voziloManager.searchVozilo(search.getText())));
            tableViewVozila.refresh();
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    public void refreshVozila (){
        try{
            tableViewVozila.setItems(FXCollections.observableList(voziloManager.getAll()));
            tableViewVozila.refresh();
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }

    }
    public void delete(Integer id){
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

    public void updateScene(Integer id){
        try {
            ((Stage)voziloScreen.getScene().getWindow()).hide();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/addupdatevozilo.fxml"));
            loader.setController(new AddUpdateVoziloController(id));
            Stage stage=new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Editovanje i dodavanje vozila za iznajmljivanje");
            stage.show();
            stage.setOnHiding(event->{
                ((Stage)voziloScreen.getScene().getWindow()).show();
                refreshVozila();
            });
        } catch (Exception e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    public void add(ActionEvent event){
        updateScene(null);
    }


}
