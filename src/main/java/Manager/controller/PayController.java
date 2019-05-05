package Manager.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class PayController extends ShowScreenController {

    @FXML
    private Label payButton;

    @FXML
    private Label accountsButton;

    @FXML
    private Label transferButton;

    @FXML
    private Label profileButton;

    @FXML
    private Label logoutButton;

    @FXML
    public void logoutUser() {

        showScreen(logoutButton, "/views/startup.fxml");

    }

    @FXML
    public void gotoAccountsScreen() {

        showScreen(accountsButton, "/views/accounts.fxml");

    }

    @FXML
    public void gotoHomeScreen() {

        showScreen(accountsButton, "/views/main.fxml");

    }

    @FXML
    public void gotoProfileScreen() {

        showScreen(accountsButton, "/views/profile.fxml");

    }

    @FXML
    public void gotoTransferScreen() {

        showScreen(accountsButton, "/views/transfer.fxml");

    }

    @FXML
    public void initialize() {


    }
}
