package Manager.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Manager.animations.Shaker;
import Manager.database.DatabaseHandler;
import Manager.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController extends ShowScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label loginButton;

    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;

    private DatabaseHandler databaseHandler;
    private static User user = new User();

    @FXML
    void loginUser(MouseEvent event) {

        databaseHandler = new DatabaseHandler();

        if (loginUsername.getText().equals("")) {
            Shaker usernameShaker = new Shaker(loginUsername);
            usernameShaker.shake();
        } else if (loginPassword.getText().equals("")) {
            Shaker passwordShaker = new Shaker(loginPassword);
            passwordShaker.shake();
        } else {
            String loginText = loginUsername.getText().trim();
            String loginPwd = loginPassword.getText().trim();

            user.setUserName(loginText);
            user.setPassword(loginPwd);

            ResultSet userRow = databaseHandler.getUser(user);
            try {
                if (userRow.next()) {
                    showScreen(loginUsername, "/views/main.fxml");
                } else {
                    Shaker usernameShaker = new Shaker(loginUsername);
                    Shaker passwordShaker = new Shaker(loginPassword);
                    passwordShaker.shake();
                    usernameShaker.shake();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void initialize() {


    }
}
