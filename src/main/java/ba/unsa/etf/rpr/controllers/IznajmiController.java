package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.IznajmljivanjeManager;
import ba.unsa.etf.rpr.business.KorisnikManager;
import ba.unsa.etf.rpr.business.VoziloManager;
import ba.unsa.etf.rpr.dao.IznajmljivanjeSQLImpl;
import ba.unsa.etf.rpr.domain.Iznajmljivanje;
import ba.unsa.etf.rpr.domain.Korisnik;
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
    private VoziloManager voziloManager;
    private KorisnikManager korisnikManager;
    private Vozilo selectedVozilo;
    @FXML
    public TextField idIznajmljivanja;
    public TextField idkorisnika;
    public TextField idvozila;
    
    public TextField ukupno;
    public Button btncancel;
    public Button btniznajmi;
    public DatePicker preuzimanje;
    public DatePicker vracanje;
    public Button izracunajCijenu;
    private IznajmljivanjeSQLImpl dao;

    public IznajmiController() throws Exception{
        dao=IznajmljivanjeSQLImpl.getInstance();
    }
    public void setKorisnikManager(KorisnikManager korisnikManager){
        this.korisnikManager=korisnikManager;
    }
    public void setVoziloManager(VoziloManager voziloManager){
        this.voziloManager=voziloManager;
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

        iznajmljivanje.setKlijentId(1);
        iznajmljivanje.setPreuzimanje(preuzimanje.getValue());
        iznajmljivanje.setVracanje(vracanje.getValue());
        iznajmljivanje.setVoziloId(selectedVozilo.getId());
        Iznajmljivanje addedIznajmljivanje=iznajmljivanjeManager.add(iznajmljivanje);
        idIznajmljivanja.setText(String.valueOf(addedIznajmljivanje.getId()));

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
        long numberOfDays=  (ChronoUnit.DAYS.between(preuzimanjeDate, vracanjeDate) + 1);;
        double totalPrice=pricePerDay*numberOfDays; //proba
        ukupno.setText(String.format("%.2f",totalPrice));
    }
}
