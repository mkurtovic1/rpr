package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.VoziloManager;
import ba.unsa.etf.rpr.dao.VoziloDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Vozilo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class GlavnaController {
    private VoziloManager voziloManager=new VoziloManager();
    private Vozilo selectedVozilo;
    public void setVoziloManager(VoziloManager voziloManager){
        this.voziloManager=voziloManager;
    }
    public void setSelectedVozilo(Vozilo selectedVozilo){
        this.selectedVozilo=selectedVozilo;
        initialize();
    }

    public TableView<Vozilo> tabelaVozila;
    public TextField search;
    public TableColumn<Vozilo, Integer> id;
    public TableColumn<Vozilo, String> naziv;
    public TableColumn<Vozilo, String> gorivo;
    public TableColumn<Vozilo, String> mjenjac;
    public TableColumn<Vozilo, Integer> maxbrputnika;
    public TableColumn<Vozilo, Integer> cijenapodanu;
    public TableColumn<Vozilo, String> brojregtablica;
    public TableColumn<Vozilo, String> tip;
    private VoziloDaoSQLImpl dao;
    private ObservableList<Vozilo> listVozilo;

    public GlavnaController() throws Exception {
        dao=VoziloDaoSQLImpl.getInstance();
        listVozilo=FXCollections.observableArrayList(dao.getAll());
    }

    @FXML
    public void initialize(){
        tabelaVozila.setItems(listVozilo);
        id.setCellValueFactory(new PropertyValueFactory<Vozilo, Integer>("id"));
        naziv.setCellValueFactory(new PropertyValueFactory<Vozilo, String>("naziv"));
        gorivo.setCellValueFactory(new PropertyValueFactory<Vozilo, String>("gorivo"));
        mjenjac.setCellValueFactory(new PropertyValueFactory<Vozilo, String>("mjenjac"));
        maxbrputnika.setCellValueFactory(new PropertyValueFactory<Vozilo, Integer>("maxbrputnika"));
        cijenapodanu.setCellValueFactory(new PropertyValueFactory<Vozilo, Integer>("cijenapodanu"));
        brojregtablica.setCellValueFactory(new PropertyValueFactory<Vozilo, String>("brojregtablica"));
        tip.setCellValueFactory(new PropertyValueFactory<Vozilo, String>("tip"));

        refreshVozila();

    }
    public void searchVozila(ActionEvent event){
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

    }

    //ne treba metoda, jer necu brisat preko id nego cijelu kolonu
    /*public void obrisiVozilo(Integer id){
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
    }*/

    public void izmijeniVozilo(ActionEvent actionEvent){
        Vozilo vozilo=tabelaVozila.getSelectionModel().getSelectedItem();
        if(vozilo==null) return;
        try {

            //((Stage)voziloScreen.getScene().getWindow()).hide();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/vozilo.fxml"));
            //get the controller associated with fxml

            Parent root=loader.load();
            AddUpdateVoziloController voziloController=loader.getController();
           if(voziloController!=null){
               voziloController.setVozilo(vozilo);
               Stage stage=new Stage();
               stage.setTitle("Izmijeni");
               stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
               stage.showAndWait();

           }
           else
               System.err.println("Controller is null");
            //loader.setController(voziloController);


           // selectedVozilo=voziloController.getModifiedVozilo();



            //refreshVozila();


        } catch (Exception e) {
            new Alert(Alert.AlertType.NONE, "Error loading vozilo.fxml", ButtonType.OK).show();
        }
    }
    public void dodajVozilo(ActionEvent actionEvent){

        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/vozilo.fxml"));
            Stage stage=new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Editovanje i dodavanje vozila za iznajmljivanje");
            stage.show();

            Vozilo vozilo= new Vozilo();
            vozilo.setNaziv(naziv.getText());
            vozilo.setGorivo(gorivo.getText());
            vozilo.setMjenjac(mjenjac.getText());
            vozilo.setMaxbrputnika(Integer.parseInt(maxbrputnika.getText()));
            vozilo.setCijenapodanu(Integer.parseInt(cijenapodanu.getText()));
            vozilo.setBrojregtablica(brojregtablica.getText());
            vozilo.setTip(tip.getText());
            voziloManager.add(vozilo);
            refreshVozila();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void obrisiVozilo(ActionEvent actionEvent) throws Exception {
        Vozilo vozilo=tabelaVozila.getSelectionModel().getSelectedItem();
        if(vozilo==null) return;

        Stage stage=new Stage();
        Parent root=null;

        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potrvrda brisanja");
        alert.setHeaderText("Brisanje vozila "+vozilo.getNaziv());
        alert.setContentText("Da li ste sigurni?");
        Optional<ButtonType> result=alert.showAndWait();
        if(result.get()==ButtonType.OK){
            dao.delete(vozilo.getId());
            listVozilo.setAll(dao.getAll());
        }
        refreshVozila();
    }

    private void showAlert(Alert.AlertType alertType, String title, String contentText){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

}
