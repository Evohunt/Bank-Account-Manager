package Manager.controller;

import Manager.animations.Shaker;
import Manager.database.DatabaseHandler;
import Manager.enums.ServiceProvider;
import Manager.model.User;
import Manager.utility.ValidationUtility;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PayController extends ShowScreenController {

    @FXML
    private Label payButton;

    @FXML
    private Label accountsButton;

    @FXML
    private Label transferButton;

    @FXML
    private Label profileButton;

    @FXML
    private Label logoutButton;

    @FXML
    private TextField amountToPay;

    @FXML
    private ComboBox<String> userAcccountsComboBox;

    @FXML
    private ComboBox<String> servicesComboBox;
    @FXML
    private Pane payPopUpPane;

    @FXML
    private Label okButton;


    private DatabaseHandler databaseHandler;

    @FXML
    public void logoutUser() {

        showScreen(logoutButton, "/views/startup.fxml");

    }

    @FXML
    public void gotoAccountsScreen() {

        showScreen(accountsButton, "/views/accounts.fxml");

    }

    @FXML
    public void gotoHomeScreen() {

        showScreen(accountsButton, "/views/main.fxml");

    }

    @FXML
    public void gotoProfileScreen() {

        showScreen(accountsButton, "/views/profile.fxml");

    }

    @FXML
    public void gotoTransferScreen() {

        showScreen(accountsButton, "/views/transfer.fxml");

    }

    @FXML
    void payToService() throws SQLException, ClassNotFoundException {

        if (ValidationUtility.checkIfStringIsMoneyFormat(amountToPay.getText())) {
            databaseHandler = new DatabaseHandler();
            String accountName = userAcccountsComboBox.getSelectionModel().getSelectedItem();
            ResultSet currentAccount = databaseHandler.getAccountsByName(accountName);
            double accountBalance = 0;
            if (currentAccount.next()) {
                accountBalance = Double.parseDouble(currentAccount.getString("accountBalance"));
            }

            if (Double.parseDouble(amountToPay.getText()) <= accountBalance) {
                databaseHandler.updateAccountBalance(accountName, -1.0 * Double.parseDouble(amountToPay.getText()));
                amountToPay.setText("");
                payPopUpPane.setVisible(true);
                payButton.setVisible(false);
            } else {
                Shaker shaker = new Shaker(amountToPay);
                shaker.shake();
            }
        } else {
            Shaker shaker = new Shaker(amountToPay);
            shaker.shake();
        }

    }

    @FXML
    void okPressed(MouseEvent event) {
        payPopUpPane.setVisible(false);
        payButton.setVisible(true);
    }

    @FXML
    public void initialize() throws SQLException {

        User user = new User();
        databaseHandler = new DatabaseHandler();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/login.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LoginController loginController = loader.getController();
        ResultSet currentUser = databaseHandler.getUser(loginController.getUser());

        if(currentUser.next()) {
            user.setUserId(Integer.parseInt(currentUser.getString("userid")));
            user.setUserName(currentUser.getString("userName"));
            user.setFirstName(currentUser.getString("firstName"));
            user.setLastName(currentUser.getString("lastName"));
            user.setAddress(currentUser.getString("address"));
            user.setEmail(currentUser.getString("email"));
        }

        populateUserAccountsComboBox(user);
        populateServicesComboBox();

    }

    private void populateUserAccountsComboBox(User user) throws SQLException {

        userAcccountsComboBox.setStyle("-fx-font-family: Arial");
        userAcccountsComboBox.setStyle("-fx-font-size: 17");
        databaseHandler = new DatabaseHandler();
        ResultSet userAccounts = databaseHandler.getAccountsByUser(user);
        userAcccountsComboBox.getItems().clear();
        while(userAccounts.next()) {
            userAcccountsComboBox.getItems().add(userAccounts.getString("accountName"));
        }

    }

    private void populateServicesComboBox() {

        servicesComboBox.setStyle("-fx-font-family: Arial");
        servicesComboBox.setStyle("-fx-font-size: 17");
        servicesComboBox.getItems().clear();
        servicesComboBox.getItems().addAll(
                ServiceProvider.values()[0].toString(),
                ServiceProvider.values()[1].toString(),
                ServiceProvider.values()[2].toString(),
                ServiceProvider.values()[3].toString(),
                ServiceProvider.values()[4].toString()
        );

    }

    private void initUser(ResultSet resultSet) throws SQLException {



    }

}
