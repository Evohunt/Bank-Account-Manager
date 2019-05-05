package Manager.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class TransferController extends ShowScreenController {

    @FXML
    private Label homeButton;

    @FXML
    private Label accountsButton;

    @FXML
    private Label payButton;

    @FXML
    private Label profileButton;

    @FXML
    private Label logoutButton;

    @FXML
    public void logoutUser(MouseEvent event) {

        showScreen(logoutButton, "/views/startup.fxml");

    }

    @FXML
    public void gotoAccountsScreen(MouseEvent event) {

        showScreen(accountsButton, "/views/accounts.fxml");

    }

    @FXML
    public void gotoHomeScreen(MouseEvent event) {

        showScreen(accountsButton, "/views/main.fxml");

    }

    @FXML
    public void gotoPayScreen(MouseEvent event) {

        showScreen(accountsButton, "/views/pay.fxml");

    }

    @FXML
    public void gotoProfileScreen(MouseEvent event) {

        showScreen(accountsButton, "/views/profile.fxml");

    }

    @FXML
    public void initialize() {


    }

}
