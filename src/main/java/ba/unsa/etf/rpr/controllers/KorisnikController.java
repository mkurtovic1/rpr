package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.KorisnikDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Korisnik;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class KorisnikController {
    public Button btnlogin;
    @FXML
    public TextField pass;
    @FXML
    public TextField email;
    public Button btncancel;

    public void cancelAction(ActionEvent event) {
    }

    public void loginAction(ActionEvent event) {
        String mail=email.getText();
        String password=pass.getText();
        boolean isLoginValid=validateLogin(mail, password);
        if(isLoginValid){
            closeLoginWindow();
        }else {
            showAlert(Alert.AlertType.ERROR, "Invalid login", "Invalid email or password");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    private void closeLoginWindow() {
        Stage stage = (Stage) email.getScene().getWindow();
        stage.close();
    }

    private boolean validateLogin(String mail, String password) {
        try {
            KorisnikDaoSQLImpl dao=KorisnikDaoSQLImpl.getInstance();
            Korisnik korisnik=dao.getByEmail(mail);
            if(korisnik!=null){
                String storedPassword=korisnik.getLozinka();
                System.out.println("Stored password: "+storedPassword);
                boolean isPasswordValid= password.equals(storedPassword);
                System.out.println("Is password valid? "+isPasswordValid);
                return isPasswordValid;
            }else {
                System.out.println("User with email "+mail+" not found.");
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
