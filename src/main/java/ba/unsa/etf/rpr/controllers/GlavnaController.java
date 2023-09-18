package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.KorisnikManager;
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

import java.io.IOException;
import java.util.Optional;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class GlavnaController {
    public Button btnDodaj;
    public Button btnIzmijeni;
    public Button btnObrisi;
    public Button btnIznajmi;
    public Button btnlogin;
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

    private int loggedInUserId=1;
    public void setLoggedInUserId(int userId){
        this.loggedInUserId=userId;
    }
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

    public void refreshVozila (){
        try{
            tabelaVozila.setItems(FXCollections.observableList(voziloManager.getAll()));
            tabelaVozila.refresh();
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }

    }



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
        refreshVozila();
    }
    public void dodajVozilo(ActionEvent actionEvent){
        Vozilo novovozilo=new Vozilo();
        try {

            //((Stage)voziloScreen.getScene().getWindow()).hide();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/vozilo.fxml"));
            //get the controller associated with fxml

            Parent root=loader.load();
            AddUpdateVoziloController voziloController=loader.getController();
            if(voziloController!=null){
                voziloController.setVozilo(novovozilo);
                Stage stage=new Stage();
                stage.setTitle("Dodaj");
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
        refreshVozila();
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
    public void iznajmiVozilo(ActionEvent actionEvent) throws  Exception{
        Vozilo vozilo=tabelaVozila.getSelectionModel().getSelectedItem();
        if(vozilo==null) return;
        try {

            //((Stage)voziloScreen.getScene().getWindow()).hide();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/iznajmi.fxml"));
            //get the controller associated with fxml

            Parent root=loader.load();
            IznajmiController iznajmiController=loader.getController();

            if(iznajmiController!=null){
                iznajmiController.setSelectedVozilo(vozilo);

                Stage stage=new Stage();
                stage.setTitle("Iznajmi vozilo");
                stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                stage.showAndWait();

            }
            else
                System.err.println("Controller is null");
            //loader.setController(voziloController);


            // selectedVozilo=voziloController.getModifiedVozilo();



            //refreshVozila();


        } catch (Exception e) {
            new Alert(Alert.AlertType.NONE, "Error loading iznajmi.fxml", ButtonType.OK).show();
        }

    }


    private void showAlert(Alert.AlertType alertType, String title, String contentText){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public void loginAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/loginforma.fxml"));
            //get the controller associated with fxml
            Parent root = loader.load();
            KorisnikController korisnikController=loader.getController();
            if(korisnikController!=null){

                Stage stage = new Stage();


                stage.setTitle("Login form");
                stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                stage.showAndWait();

                int userId=korisnikController.getLoggedInUserId();
                if(userId!=-1){
                    setLoggedInUserId(userId);
                }else {
                    System.err.println("Korisnik Controller is null");
                }

            }



        }catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.NONE, "Error loading loginforma.fxml", ButtonType.OK).show();
        }
    }
}
