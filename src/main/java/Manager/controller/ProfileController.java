package Manager.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Manager.database.DatabaseHandler;
import Manager.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ProfileController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label editProfileButton;

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
    private Label transactionHistoryButton;

    @FXML
    private Label changePasswordButton;

    private DatabaseHandler databaseHandler;


    @FXML
    void promptChangePassword(MouseEvent event) {

    }

    @FXML
    void cancelEditProfile(MouseEvent event) {

        profileLabelsVisible(true);
        profileEditTextFieldsVisible(false);

        editProfileButton.setVisible(true);
        transactionHistoryButton.setVisible(true);
        changePasswordButton.setVisible(true);

        cancelEditProfileButton.setVisible(false);
        saveEditProfileButton.setVisible(false);

    }

    @FXML
    void saveEditProfile(MouseEvent event) throws SQLException, ClassNotFoundException {

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

    @FXML
    void promptEditProfile(MouseEvent event) {

        profileLabelsVisible(false);
        profileEditTextFieldsVisible(true);
        initEditTextFields();

        editProfileButton.setVisible(false);
        transactionHistoryButton.setVisible(false);
        changePasswordButton.setVisible(false);

        cancelEditProfileButton.setVisible(true);
        saveEditProfileButton.setVisible(true);

    }

    private void populateLabel(Label label, String string) {

        label.setText(string);

    }

    @FXML
    void initialize() throws SQLException {

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

}
