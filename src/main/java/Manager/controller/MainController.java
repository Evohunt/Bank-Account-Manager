package Manager.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MainController extends ShowScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label accountsButton;

    @FXML
    private Label transferButton;

    @FXML
    private Label payButton;

    @FXML
    private Label profileButton;

    @FXML
    void gotoAccountsScreen(MouseEvent event) {

        showScreen(profileButton, "/views/accounts.fxml");

    }

    @FXML
    void gotoPayScreen(MouseEvent event) {

        showScreen(profileButton, "/views/pay.fxml");

    }

    @FXML
    void gotoTransferScreen(MouseEvent event) {

        showScreen(profileButton, "/views/transfer.fxml");

    }

    @FXML
    void gotoProfileScreen(MouseEvent event) {

        showScreen(profileButton, "/views/profile.fxml");

    }

    @FXML
    void initialize() {

    }
}
