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
    void logoutUser(MouseEvent event) {

        showScreen(logoutButton, "/views/startup.fxml");

    }

    @FXML
    void gotoAccountsScreen(MouseEvent event) {

        showScreen(accountsButton, "/views/accounts.fxml");

    }

    @FXML
    void gotoHomeScreen(MouseEvent event) {

        showScreen(accountsButton, "/views/main.fxml");

    }

    @FXML
    void gotoPayScreen(MouseEvent event) {

        showScreen(accountsButton, "/views/pay.fxml");

    }

    @FXML
    void gotoProfileScreen(MouseEvent event) {

        showScreen(accountsButton, "/views/profile.fxml");

    }

    @FXML
    void initialize() {


    }

}
