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

public class RegisterController extends ShowScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label registerButton;

    @FXML
    private TextField registerUsername;

    @FXML
    private PasswordField registerPassword;

    @FXML
    private TextField registerFirstName;

    @FXML
    private TextField registerEmail;

    @FXML
    private TextField registerAddress;

    @FXML
    private TextField registerLastName;

    @FXML
    private Label cancelLoginButton;

    private DatabaseHandler databaseHandler;

    @FXML
    void cancelLogin(MouseEvent event) {

        showScreen(cancelLoginButton, "/views/startup.fxml");

    }

    @FXML
    void registerUser(MouseEvent event) {

        databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.validateUser(new User(registerUsername.getText()));
        try {
            if (!resultSet.next()) {
                if (registerFirstName.getText().equals("")) {
                    Shaker shaker = new Shaker(registerFirstName);
                    shaker.shake();
                } else if (registerLastName.getText().equals("")) {
                    Shaker shaker = new Shaker(registerLastName);
                    shaker.shake();
                } else if (registerUsername.getText().equals("")) {
                    Shaker shaker = new Shaker(registerUsername);
                    shaker.shake();
                } else if (registerEmail.getText().equals("")) {
                    Shaker shaker = new Shaker(registerEmail);
                    shaker.shake();
                } else if (registerAddress.getText().equals("")) {
                    Shaker shaker = new Shaker(registerAddress);
                    shaker.shake();
                } else if (registerPassword.getText().equals("")) {
                    Shaker shaker = new Shaker(registerPassword);
                    shaker.shake();
                } else {
                    createUser();
                    showScreen(registerFirstName, "/views/login.fxml");
                }
            } else {
                Shaker shaker = new Shaker(registerUsername);
                shaker.shake();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void createUser() {

        databaseHandler = new DatabaseHandler();
        String firstName = registerFirstName.getText();
        String lastName = registerLastName.getText();
        String userName = registerUsername.getText();
        String password = registerPassword.getText();
        String email = registerEmail.getText();
        String address = registerAddress.getText();

        User user = new User(firstName, lastName, userName, password, email, address);
        databaseHandler.registerUser(user);

    }

    @FXML
    void initialize() {


    }
}
