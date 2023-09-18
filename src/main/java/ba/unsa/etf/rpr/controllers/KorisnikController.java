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
    @FXML
    public Button btncancel;

    private int loggedInUserId=-1;
    @FXML
    public void cancelAction(ActionEvent event) {
        Stage stage = (Stage) btncancel.getScene().getWindow();
        stage.close();
    }

    public void loginAction(ActionEvent event) {
        String mail=email.getText();
        String password=pass.getText();
        Integer userId=validateLogin(mail, password);

        if(userId!=null){
            setLoggedInUserId(userId);
            closeLoginWindow();
        }else {
            showAlert(Alert.AlertType.ERROR, "Invalid login", "Invalid email or password");
        }
    }
    public int getLoggedInUserId(){
        return loggedInUserId;
    }
    private void setLoggedInUserId(int userId){
        this.loggedInUserId=userId;
    }

    private void showAlert(Alert.AlertType alertType, String title, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    private void closeLoginWindow() {

        Stage stage = (Stage) btncancel.getScene().getWindow();
        stage.close();
    }
    public Integer getId(){
        String emailText=email.getText();
        String passText=pass.getText();
        return validateLogin(emailText, passText);
    }



    public Integer validateLogin(String mail, String password) {
        try {
            KorisnikDaoSQLImpl dao=KorisnikDaoSQLImpl.getInstance();
            Korisnik korisnik=dao.getByEmail(mail);
            if(korisnik!=null){
                String storedPassword=korisnik.getLozinka();
                System.out.println("Stored password: "+storedPassword);
                boolean isPasswordValid= password.equals(storedPassword);
                System.out.println("Is password valid? "+isPasswordValid);
                System.out.println("Id of user "+korisnik.getId());
                return isPasswordValid? korisnik.getId() : null;
            }else {
                System.out.println("User with email "+mail+" not found.");
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
