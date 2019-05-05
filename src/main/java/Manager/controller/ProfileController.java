package Manager.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import Manager.animations.Shaker;
import Manager.database.DatabaseHandler;
import Manager.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ProfileController extends ShowScreenController {

    @FXML
    private Label homeButton;

    @FXML
    private Label accountsButton;

    @FXML
    private Label transferButton;

    @FXML
    private Label payButton;

    @FXML
    private Label editProfileButton;

    @FXML
    private Label transactionHistoryButton;

    @FXML
    private Label changePasswordButton;

    @FXML
    private Label profileUsername;

    @FXML
    private Label profileFirstName;

    @FXML
    private Label profileLastName;

    @FXML
    private Label profileAddress;

    @FXML
    private Label profileEmail;

    @FXML
    private TextField profileLastNameEdit;

    @FXML
    private TextField profileFirstNameEdit;

    @FXML
    private TextField profileAddressEdit;

    @FXML
    private Label saveEditProfileButton;

    @FXML
    private Label cancelEditProfileButton;

    @FXML
    private Pane popUpPane;

    @FXML
    private PasswordField txtOldPassword;

    @FXML
    private PasswordField txtNewPassword;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private Label passwordChangeSave;

    @FXML
    private Label passwordChangeCancel;

    @FXML
    private Label logoutButton;

    private DatabaseHandler databaseHandler;

    @FXML
    public void logoutUser() {

        showScreen(logoutButton, "/views/startup.fxml");

    }

    @FXML
    public void gotoHomeScreen() {

        showScreen(accountsButton, "/views/main.fxml");

    }

    @FXML
    public void gotoPayScreen() {

        showScreen(accountsButton, "/views/pay.fxml");

    }

    @FXML
    public void gotoTransferScreen() {

        showScreen(accountsButton, "/views/transfer.fxml");

    }

    @FXML
    public void gotoAccountsScreen() {

        showScreen(accountsButton, "/views/accounts.fxml");

    }

    @FXML
    public void cancelPasswordChange() {

        popUpPane.setVisible(false);
        changePasswordButton.setVisible(true);
        transactionHistoryButton.setVisible(true);
        editProfileButton.setVisible(true);
        resetPasswordFields();

    }

    @FXML
    public void savePasswordChange() throws SQLException, ClassNotFoundException {

        String userPassword = "";
        ResultSet userResult = databaseHandler.getUserByUsername(new User(profileUsername.getText()));

        while (userResult.next()) {
            userPassword = userResult.getString("password");
        }

        if (!txtOldPassword.getText().equals("") && !txtNewPassword.getText().equals("") && !txtConfirmPassword.getText().equals("")) {

            if (userPassword.equals(txtOldPassword.getText()) && txtNewPassword.getText().equals(txtConfirmPassword.getText())) {

                databaseHandler.editUserPassword(profileUsername.getText(), txtConfirmPassword.getText());

                popUpPane.setVisible(false);
                editProfileButton.setVisible(true);
                transactionHistoryButton.setVisible(true);
                changePasswordButton.setVisible(true);

                showScreen(txtOldPassword, "/views/login.fxml");

            } else if (!userPassword.equals(txtOldPassword.getText())) {

                Shaker shaker = new Shaker(txtOldPassword);
                shaker.shake();

            } else if (!txtNewPassword.getText().equals(txtConfirmPassword.getText())) {

                Shaker newPasswordShaker = new Shaker(txtNewPassword);
                Shaker confirmPasswordShaker = new Shaker(txtConfirmPassword);
                newPasswordShaker.shake();
                confirmPasswordShaker.shake();

            }

        } else {

            Shaker oldPasswordShaker = new Shaker(txtOldPassword);
            Shaker newPasswordShaker = new Shaker(txtNewPassword);
            Shaker confirmPasswordShaker = new Shaker(txtConfirmPassword);
            oldPasswordShaker.shake();
            newPasswordShaker.shake();
            confirmPasswordShaker.shake();

        }

    }

    @FXML
    public void promptChangePassword() {

        popUpPane.setVisible(true);
        changePasswordButton.setVisible(false);
        transactionHistoryButton.setVisible(false);
        editProfileButton.setVisible(false);
        resetPasswordFields();

    }

    @FXML
    public void cancelEditProfile() {

        profileLabelsVisible(true);
        profileEditTextFieldsVisible(false);

        editProfileButton.setVisible(true);
        transactionHistoryButton.setVisible(true);
        changePasswordButton.setVisible(true);

        cancelEditProfileButton.setVisible(false);
        saveEditProfileButton.setVisible(false);

    }

    @FXML
    public void saveEditProfile() throws SQLException, ClassNotFoundException {

        User user = new User();
        ResultSet userResult = databaseHandler.getUserByUsername(new User(profileUsername.getText()));

        while (userResult.next()) {
            user.setFirstName(userResult.getString("firstName"));
            user.setLastName(userResult.getString("lastName"));
            user.setAddress(userResult.getString("address"));
            user.setUserName(userResult.getString("userName"));
        }

        user.setFirstName(profileFirstNameEdit.getText());
        user.setLastName(profileLastNameEdit.getText());
        user.setAddress(profileAddressEdit.getText());

        databaseHandler.editUser(user);

        profileLabelsVisible(true);
        profileEditTextFieldsVisible(false);

        editProfileButton.setVisible(true);
        transactionHistoryButton.setVisible(true);
        changePasswordButton.setVisible(true);

        cancelEditProfileButton.setVisible(false);
        saveEditProfileButton.setVisible(false);

        initialize();

    }

    @FXML
    public void promptEditProfile() {

        profileLabelsVisible(false);
        profileEditTextFieldsVisible(true);
        initEditTextFields();

        editProfileButton.setVisible(false);
        transactionHistoryButton.setVisible(false);
        changePasswordButton.setVisible(false);

        cancelEditProfileButton.setVisible(true);
        saveEditProfileButton.setVisible(true);

    }

    @FXML
    public void initialize() throws SQLException {

        databaseHandler = new DatabaseHandler();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/login.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LoginController loginController = loader.getController();
        ResultSet user = databaseHandler.getUser(loginController.getUser());

        while(user.next()) {
            populateLabel(profileUsername, user.getString("username"));
            populateLabel(profileFirstName, user.getString("firstName"));
            populateLabel(profileLastName, user.getString("lastName"));
            populateLabel(profileAddress, user.getString("address"));
            populateLabel(profileEmail, user.getString("email"));
        }

    }

    private void profileLabelsVisible(boolean state) {

        profileFirstName.setVisible(state);
        profileLastName.setVisible(state);
        profileAddress.setVisible(state);

    }

    private void profileEditTextFieldsVisible(boolean state) {

        profileFirstNameEdit.setVisible(state);
        profileLastNameEdit.setVisible(state);
        profileAddressEdit.setVisible(state);

    }

    private void initEditTextFields() {

        profileFirstNameEdit.setText(profileFirstName.getText());
        profileLastNameEdit.setText(profileLastName.getText());
        profileAddressEdit.setText(profileAddress.getText());

    }

    private void populateLabel(Label label, String string) {

        label.setText(string);

    }

    private void resetPasswordFields() {

        txtOldPassword.setText("");
        txtNewPassword.setText("");
        txtConfirmPassword.setText("");

    }
}
