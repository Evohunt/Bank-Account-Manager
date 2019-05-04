package Manager.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class StartupController extends ShowScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label loginButton;

    @FXML
    private Label registerButton;

    @FXML
    void gotoLoginScreen(MouseEvent event) {
        showScreen(loginButton, "/views/login.fxml");
    }

    @FXML
    void gotoRegisterScreen(MouseEvent event) {
        showScreen(loginButton, "/views/register.fxml");
    }

    @FXML
    void initialize() {

    }
}
