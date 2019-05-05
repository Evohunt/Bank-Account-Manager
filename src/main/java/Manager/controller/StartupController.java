package Manager.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StartupController extends ShowScreenController {

    @FXML
    private Label loginButton;

    @FXML
    private Label registerButton;

    @FXML
    public void gotoLoginScreen() {
        showScreen(loginButton, "/views/login.fxml");
    }

    @FXML
    public void gotoRegisterScreen() {
        showScreen(loginButton, "/views/register.fxml");
    }

    @FXML
    public void initialize() {

    }
}
