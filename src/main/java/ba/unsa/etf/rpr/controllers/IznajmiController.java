package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.IznajmljivanjeManager;
import ba.unsa.etf.rpr.dao.IznajmljivanjeDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Iznajmljivanje;
import ba.unsa.etf.rpr.domain.Vozilo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class IznajmiController {
    private IznajmljivanjeManager iznajmljivanjeManager=new IznajmljivanjeManager();

    private Vozilo selectedVozilo;
    public TextField idvozila;
    
    public TextField ukupno;
    public Button btncancel;
    public Button btniznajmi;
    public DatePicker preuzimanje;
    public DatePicker vracanje;
    public Button izracunajCijenu;
    private IznajmljivanjeDaoSQLImpl dao;

    public double ukupnacijena=0;

    public IznajmiController() throws Exception{
        dao= IznajmljivanjeDaoSQLImpl.getInstance();

    }
    void setSelectedVozilo(Vozilo vozilo){
        this.selectedVozilo=vozilo;
        idvozila.setText(String.valueOf(vozilo.getId()));
        initialize();
    }
    @FXML
    public void initialize(){
        if(selectedVozilo!=null){
            idvozila.setText(String.valueOf(selectedVozilo.getId()));
        }
    }
    public void iznajmiForm(ActionEvent event) throws Exception {

        try {
        Iznajmljivanje iznajmljivanje=new Iznajmljivanje();
        iznajmljivanje.setPreuzimanje(preuzimanje.getValue());
        iznajmljivanje.setVracanje(vracanje.getValue());
        iznajmljivanje.setIdvozila(selectedVozilo.getId());
        iznajmljivanje.setCijena((int) ukupnacijena);
        Iznajmljivanje addedIznajmljivanje=iznajmljivanjeManager.add(iznajmljivanje);
        System.out.println("Iznajmljivanje added "+addedIznajmljivanje);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void cancelForm(ActionEvent event) {
        Stage stage=(Stage) btncancel.getScene().getWindow();
        stage.close();
        
    }
    public void izracacunajCijenu(ActionEvent event) {
        LocalDate preuzimanjeDate=preuzimanje.getValue();
        LocalDate vracanjeDate=vracanje.getValue();
        if(vracanjeDate.isBefore(preuzimanjeDate)){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Invalid Date Selection");
            alert.setContentText("Vracanje date cannot be before prezuimanje date");
            alert.showAndWait();
            return;
        }
        double pricePerDay=selectedVozilo.getCijenapodanu();
        long numberOfDays= (ChronoUnit.DAYS.between(preuzimanjeDate, vracanjeDate) + 1);;
        double totalPrice= (pricePerDay*numberOfDays); //proba
        ukupno.setText(String.format("%.2f",totalPrice));
        ukupnacijena=totalPrice;
    }
}
