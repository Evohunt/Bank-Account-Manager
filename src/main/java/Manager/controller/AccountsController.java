package Manager.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class AccountsController extends ShowScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label homeButton;

    @FXML
    private Label transferButton;

    @FXML
    private Label payButton;

    @FXML
    private Label profileButton;

    @FXML
    void gotoHomePage(MouseEvent event) {

        showScreen(homeButton, "/views/main.fxml");

    }

    @FXML
    void gotoPayPage(MouseEvent event) {

        showScreen(homeButton, "/views/pay.fxml");

    }

    @FXML
    void gotoProfilePage(MouseEvent event) {

        showScreen(homeButton, "/views/profile.fxml");

    }

    @FXML
    void gotoTransferPage(MouseEvent event) {

        showScreen(homeButton, "/views/transfer.fxml");

    }

    @FXML
    void initialize() {

    }
}
