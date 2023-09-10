package ba.unsa.etf.rpr.controllers;


import ba.unsa.etf.rpr.business.VoziloManager;
import ba.unsa.etf.rpr.domain.Vozilo;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;


public class AddUpdateVoziloController {
    @FXML
    public GridPane gridPane;
    private final VoziloManager voziloManager=new VoziloManager();
    //private  final IznajmljivanjeManager iznajmljivanjeManager=new IznajmljivanjeManager();
    private VoziloModel model=new VoziloModel();
    private Integer voziloId; //id koji se edit/add

    public TextField  columnVoziloNaziv;
    public ComboBox columnVoziloGorivo;
    public ComboBox columnVoziloMjenjac;
    public ComboBox  columnVoziloMaxBrPutnika;
    public TextField  columnVoziloCijenaPoDanu;
    public TextField  columnVoziloBrojRegTablica;
    public ComboBox columnVoziloTip;

    public AddUpdateVoziloController() {
    }

    public AddUpdateVoziloController(Integer voziloId){
        this.voziloId=voziloId;
    }
    public void initialize(){
        try {
            columnVoziloNaziv.textProperty().bindBidirectional(model.vozilo);
            columnVoziloGorivo.valueProperty().bindBidirectional(model.vozilo);
            columnVoziloMjenjac.valueProperty().bindBidirectional(model.vozilo);
            columnVoziloMaxBrPutnika.valueProperty().bindBidirectional(model.vozilo);
            columnVoziloCijenaPoDanu.textProperty().bindBidirectional(model.vozilo);
            columnVoziloBrojRegTablica.textProperty().bindBidirectional(model.vozilo);
            columnVoziloTip.valueProperty().bindBidirectional(model.vozilo);

        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    public void cancelForm(ActionEvent event){
        gridPane.getScene().getWindow().hide();
    }
    public void saveForm(ActionEvent event){
        try{
            Vozilo vozilo=model.toVozilo();
            if(voziloId!=null){
                vozilo.setId(voziloId);
                voziloManager.update(vozilo);
            }
            else{
                voziloManager.add(vozilo);
            }
            gridPane.getScene().getWindow().hide();
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * Helper Model class that supports 2 way data binding with form for Quote management
     *
     */
    public class VoziloModel{
        public SimpleStringProperty vozilo=new SimpleStringProperty("");

        public Vozilo toVozilo(){
            Vozilo vozilo=new Vozilo();
            vozilo.setNaziv(this.vozilo.getValue());
            vozilo.setMaxbrputnika(Integer.parseInt(this.vozilo.getValue()));
            vozilo.setCijenapodanu(Integer.parseInt(this.vozilo.getValue()));
            vozilo.setBrojregtablica(this.vozilo.getValue());
            return vozilo;
        }


    }

}
