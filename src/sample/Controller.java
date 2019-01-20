package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private ObservableList<User> usersData = FXCollections.observableArrayList();

    @FXML
    TextArea textArea;

    @FXML
    private TableView<User> tableUsers;

    @FXML
    private TableColumn<User, String> fioColumn;

    @FXML
    private TableColumn<User, Date> dateColumn;

    @FXML
    TextField fioDelete;

    @FXML
    TextField fioCreate;

    @FXML
    DatePicker dateBirthdayCreate;

    @FXML
    TextField fioUpdate;

    @FXML
    TextField newFioUpdate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BaseService.connect();
        usersData.addAll(BaseService.getUsers());
        fioColumn.setCellValueFactory(new PropertyValueFactory<User, String>("fio"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<User, Date>("birthDate"));
        tableUsers.setItems(usersData);
    }

    public void create(){
        LocalDate locald = dateBirthdayCreate.getValue();
        java.sql.Date date = java.sql.Date.valueOf(locald);
        BaseService.createRecord(fioCreate.getText(), date);
        refresh();
    }

    public void update(){
        BaseService.updateRecord(fioUpdate.getText(), newFioUpdate.getText());
        refresh();
    }

    public void delete(){
        BaseService.deleteRecord(fioDelete.getText());
        refresh();
    }

    public void refresh(){
        usersData.clear();
        usersData.addAll(BaseService.getUsers());
    }
}
