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
    private Label profileButton;

    @FXML
    void gotoProfileScreen(MouseEvent event) {

        showScreen(profileButton, "/views/profile.fxml");

    }

    @FXML
    void initialize() {

    }
}
