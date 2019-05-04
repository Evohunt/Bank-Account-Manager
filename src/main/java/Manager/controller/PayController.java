package Manager.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class PayController extends ShowScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label payButton;

    @FXML
    private Label accountsButton;

    @FXML
    private Label transferButton;

    @FXML
    private Label profileButton;

    @FXML
    void gotoAccountsScreen(MouseEvent event) {

        showScreen(accountsButton, "/views/accounts.fxml");

    }

    @FXML
    void gotoHomeScreen(MouseEvent event) {

        showScreen(accountsButton, "/views/main.fxml");

    }

    @FXML
    void gotoProfileScreen(MouseEvent event) {

        showScreen(accountsButton, "/views/profile.fxml");

    }

    @FXML
    void gotoTransferScreen(MouseEvent event) {

        showScreen(accountsButton, "/views/transfer.fxml");

    }

    @FXML
    void initialize() {


    }
}
