package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManagerFlightConditionController {

    @FXML
    private TableColumn<?, ?> rPriceCol;

    @FXML
    private TableColumn<?, ?> rLeaveDateCol;

    @FXML
    private Button signOut;

    @FXML
    private TextField nameField;

    @FXML
    private TableColumn<?, ?> rArriveSrcDateCol;

    @FXML
    private Button rescheduleReservation;

    @FXML
    private TableColumn<?, ?> rAirlineCol;

    @FXML
    private TableColumn<?, ?> rReturnDateCol;

    @FXML
    private TextField resevationField;

    @FXML
    private Button home;

    @FXML
    private Button search;

    @FXML
    private TableColumn<?, ?> rArriveDateCol;

    @FXML
    private Button cancelReservation;

    @FXML
    private TableView<?> rSearchResults;

    @FXML
    private Button myAccount;

    @FXML
    void Home(ActionEvent event) {

    }

    @FXML
    void MyAccount(ActionEvent event) {

    }

    @FXML
    void SignOut(ActionEvent event) {

    }

    @FXML
    void Search(ActionEvent event) {

    }

    @FXML
    void CancelReservation(ActionEvent event) {

    }

    @FXML
    void RescheduleReservation(ActionEvent event) {

    }

}
