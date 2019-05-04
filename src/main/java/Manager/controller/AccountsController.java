package Manager.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Manager.Triplet.SimpleTriplet;
import Manager.animations.Fader;
import Manager.animations.Shaker;
import Manager.database.DatabaseHandler;
import Manager.enums.Currency;
import Manager.model.Account;
import Manager.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

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
    private Label createAccountButton;

    @FXML
    private Pane accountPane1;

    @FXML
    private Label accountBalance1;

    @FXML
    private Label accountName1;

    @FXML
    private Label accountCurrency1;

    @FXML
    private ImageView addBalanceButton1;

    @FXML
    private Pane accountPane2;

    @FXML
    private Label accountBalance2;

    @FXML
    private Label accountName2;

    @FXML
    private Label accountCurrency2;

    @FXML
    private ImageView addBalanceButton2;

    @FXML
    private Pane accountPane3;

    @FXML
    private Label accountBalance3;

    @FXML
    private Label accountName3;

    @FXML
    private Label accountCurrency3;

    @FXML
    private ImageView addBalanceButton3;

    @FXML
    private Pane accountPane4;

    @FXML
    private Label accountBalance4;

    @FXML
    private Label accountName4;

    @FXML
    private Label accountCurrency4;

    @FXML
    private ImageView addBalanceButton4;

    @FXML
    private Pane accountPane5;

    @FXML
    private Label accountBalance5;

    @FXML
    private Label accountName5;

    @FXML
    private Label accountCurrency5;

    @FXML
    private ImageView addBalanceButton5;

    @FXML
    private Label confirmCreateAccountButton;

    @FXML
    private Label cancelCreateAccountButton;

    @FXML
    private TextField accountBalanceAdd;

    @FXML
    private ComboBox<String> accountCurrencyAdd;

    @FXML
    private Pane createAccountPopUp;

    @FXML
    private Label logoutButton;

    @FXML
    private Label lblMaxAccountsReached;

    @FXML
    private Label deleteButton1;

    @FXML
    private Label deleteButton2;

    @FXML
    private Label deleteButton3;

    @FXML
    private Label deleteButton4;

    @FXML
    private Label deleteButton5;

    private DatabaseHandler databaseHandler;
    private User user;

    @FXML
    void deleteAccount1(MouseEvent event) throws SQLException, ClassNotFoundException {
        databaseHandler.deleteAccount(accountName1.getText());
        initialize();
    }

    @FXML
    void deleteAccount2(MouseEvent event) throws SQLException, ClassNotFoundException {
        databaseHandler.deleteAccount(accountName2.getText());
        initialize();
    }

    @FXML
    void deleteAccount3(MouseEvent event) throws SQLException, ClassNotFoundException {
        databaseHandler.deleteAccount(accountName3.getText());
        initialize();
    }

    @FXML
    void deleteAccount4(MouseEvent event) throws SQLException, ClassNotFoundException {
        databaseHandler.deleteAccount(accountName4.getText());
        initialize();
    }

    @FXML
    void deleteAccount5(MouseEvent event) throws SQLException, ClassNotFoundException {
        databaseHandler.deleteAccount(accountName5.getText());
        initialize();
    }

    @FXML
    void logoutUser(MouseEvent event) {

        showScreen(logoutButton, "/views/startup.fxml");

    }

    private void populateCurrencyComboBox() {

        accountCurrencyAdd.setStyle("-fx-font-family: Arial");
        accountCurrencyAdd.setStyle("-fx-font-size: 17");
        accountCurrencyAdd.getItems().clear();
        accountCurrencyAdd.getItems().addAll(
                Currency.values()[0].toString(),
                Currency.values()[1].toString(),
                Currency.values()[2].toString(),
                Currency.values()[3].toString()
        );

    }

    @FXML
    void promptCreateAccount(MouseEvent event) throws SQLException {

        int numberOfAccounts = 0;
        ResultSet accountsResult = databaseHandler.getAccountsByUser(user);
        while (accountsResult.next()) {
            numberOfAccounts++;
        }

        if (numberOfAccounts == 5) {

            Shaker shaker = new Shaker(lblMaxAccountsReached);
            shaker.shake();

        } else {

            createAccountButton.setVisible(false);
            createAccountPopUp.setVisible(true);
            populateCurrencyComboBox();

        }

    }

    @FXML
    void cancelCreateAccount(MouseEvent event) {

        createAccountButton.setVisible(true);
        createAccountPopUp.setVisible(false);

    }

    @FXML
    void confirmCreateAccount(MouseEvent event) throws SQLException {

        if (accountBalanceAdd.getText().equals("")) {

            Shaker shaker = new Shaker(accountBalanceAdd);
            shaker.shake();

        } else if (accountCurrencyAdd.getSelectionModel().getSelectedIndex() == -1) {

            Shaker shaker = new Shaker(accountCurrencyAdd);
            shaker.shake();

        } else {

            String currency = accountCurrencyAdd.getSelectionModel().getSelectedItem();
            Account account = new Account(user, Double.parseDouble(accountBalanceAdd.getText()), Currency.valueOf(currency));
            databaseHandler.createAccount(user, account);

            createAccountButton.setVisible(true);
            createAccountPopUp.setVisible(false);

            initialize();

        }

    }

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
    void promptAddBalance1(MouseEvent event) {

    }

    @FXML
    void promptAddBalance2(MouseEvent event) {

    }

    @FXML
    void promptAddBalance3(MouseEvent event) {

    }

    @FXML
    void promptAddBalance4(MouseEvent event) {

    }

    @FXML
    void promptAddBalance5(MouseEvent event) {

    }

    private void initAccountLabel(Pane pane, Label nameLabel, Label balanceLabel,
                                  Label currencyLabel, SimpleTriplet data) {

        pane.setVisible(true);
        nameLabel.setText(data.getFirst().toString());
        balanceLabel.setText(data.getSecond().toString());
        currencyLabel.setText(data.getThird().toString());

    }

    private void resetAccountLabels() {

        accountPane1.setVisible(false);
        accountPane2.setVisible(false);
        accountPane3.setVisible(false);
        accountPane4.setVisible(false);
        accountPane5.setVisible(false);

    }

    @FXML
    void initialize() throws SQLException {

        user = new User();
        databaseHandler = new DatabaseHandler();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/login.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LoginController loginController = loader.getController();
        ResultSet userResult = databaseHandler.getUser(loginController.getUser());

        if (userResult.next()) {
            user.setUserId(Integer.parseInt(userResult.getString("userid")));
            user.setUserName(userResult.getString("userName"));
            user.setFirstName(userResult.getString("firstName"));
            user.setLastName(userResult.getString("lastName"));
            user.setEmail(userResult.getString("email"));
            user.setAddress(userResult.getString("address"));
        }

        accountBalanceAdd.setText("");

        int numberOfAccounts = 0;
        SimpleTriplet<String, String, String> triplet = new SimpleTriplet<>();
        SimpleTriplet[] arrayOfTriplets = new SimpleTriplet[6];
        ResultSet accountsResult = databaseHandler.getAccountsByUser(user);
        while (accountsResult.next()) {

            triplet.setFirst(accountsResult.getString("accountName"));
            triplet.setSecond(accountsResult.getString("accountBalance"));
            triplet.setThird(accountsResult.getString("accountCurrency"));
            arrayOfTriplets[numberOfAccounts] = new SimpleTriplet<>(triplet);
            numberOfAccounts++;

        }

        resetAccountLabels();
        if (numberOfAccounts == 5) {

            lblMaxAccountsReached.setVisible(true);

        } else {

            lblMaxAccountsReached.setVisible(false);

        }

        for (int i = 0; i < numberOfAccounts; i++) {
            switch (i) {

                case 0:
                    initAccountLabel(accountPane1, accountName1, accountBalance1, accountCurrency1, arrayOfTriplets[i]);
                    break;
                case 1:
                    initAccountLabel(accountPane2, accountName2, accountBalance2, accountCurrency2, arrayOfTriplets[i]);
                    break;
                case 2:
                    initAccountLabel(accountPane3, accountName3, accountBalance3, accountCurrency3, arrayOfTriplets[i]);
                    break;
                case 3:
                    initAccountLabel(accountPane4, accountName4, accountBalance4, accountCurrency4, arrayOfTriplets[i]);
                    break;
                case 4:
                    initAccountLabel(accountPane5, accountName5, accountBalance5, accountCurrency5, arrayOfTriplets[i]);
                    break;

            }

        }

    }
}
