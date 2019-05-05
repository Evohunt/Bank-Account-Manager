package Manager.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Manager.animations.Shaker;
import Manager.database.DatabaseHandler;
import Manager.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController extends ShowScreenController {

    private static final String ACCEPTED_SPECIAL_CHARACTERS = "!@#$%^&*~?.";

    private static final int MIN_NUMBER_OF_PASSWORD_CHARS = 8;

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final int MAX_FIRST_NAME_LENGTH = 30;
    private static final int MIN_FIRST_NAME_LENGTH = 2;

    private static final int MAX_LAST_NAME_LENGTH = 30;
    private static final int MIN_LAST_NAME_LENGTH = 2;

    private static final int MAX_USERNAME_LENGTH = 20;
    private static final int MIN_USERNAME_LENGTH = 5;

    private static final int MAX_ADDRESS_LENGTH = 150;
    private static final int MIN_ADDRESS_LENGTH = 10;

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
    public void cancelLogin() {

        showScreen(cancelLoginButton, "/views/startup.fxml");

    }

    @FXML
    public void registerUser() {

        databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.validateUser(new User(registerUsername.getText()));
        try {
            if (!resultSet.next()) {
                if ( ! meetsFirstNameFormat()) {
                    Shaker shaker = new Shaker(registerFirstName);
                    shaker.shake();
                } else if ( ! meetsLastNameFormat()) {
                    Shaker shaker = new Shaker(registerLastName);
                    shaker.shake();
                } else if ( ! meetsUsernameFormat()) {
                    Shaker shaker = new Shaker(registerUsername);
                    shaker.shake();
                } else if ( ! meetsEmailFormat()) {
                    Shaker shaker = new Shaker(registerEmail);
                    shaker.shake();
                } else if ( ! meetsAddressFormat()) {
                    Shaker shaker = new Shaker(registerAddress);
                    shaker.shake();
                } else if ( ! meetsPasswordFormat()) {
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

    @FXML
    public void initialize() {


    }

    private boolean meetsPasswordFormat() {
        String password = registerPassword.getText();

        boolean hasSpecialChar = false;
        boolean hasDigit = false;
        boolean hasLetter = false;
        boolean hasEnoughCharacters = false;

        char[] specialCharArray = ACCEPTED_SPECIAL_CHARACTERS.toCharArray();
        List<Character> specialCharList = new ArrayList<>();

        for (char ch: specialCharArray) {
            specialCharList.add(ch);
        }

        if (password.length() > MIN_NUMBER_OF_PASSWORD_CHARS) {
            hasEnoughCharacters = true;
        }

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isDigit(ch)) {
                hasDigit = true;
            }
            if (Character.isLetter(ch)) {
                hasLetter = true;
            }
            if (specialCharList.contains(ch)) {
                hasSpecialChar = true;
            }
        }

        return hasSpecialChar && hasDigit &&
                hasLetter && hasEnoughCharacters;
    }

    private boolean meetsEmailFormat() {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(registerEmail.getText());
        return matcher.find();
    }

    private boolean meetsUsernameFormat() {
        return registerUsername.getText().length() >= MIN_USERNAME_LENGTH &&
                registerUsername.getText().length() <= MAX_USERNAME_LENGTH;
    }

    private boolean meetsFirstNameFormat() {
        return registerFirstName.getText().length() >= MIN_FIRST_NAME_LENGTH &&
                registerFirstName.getText().length() <= MAX_FIRST_NAME_LENGTH;
    }

    private boolean meetsLastNameFormat() {
        return registerLastName.getText().length() >= MIN_LAST_NAME_LENGTH &&
                registerLastName.getText().length() <= MAX_LAST_NAME_LENGTH;
    }

    private boolean meetsAddressFormat() {
        return registerAddress.getText().length() >= MIN_ADDRESS_LENGTH &&
                registerAddress.getText().length() <= MAX_ADDRESS_LENGTH;
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
}
