package controller;

import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import tm.customerTM;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    public AnchorPane customerformContext;

    public JFXTextField txtid;
    public JFXTextField txtTitle;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostalcode;
    public TableView<customerTM> tblCustomer;
    public TableColumn colId;
    public TableColumn colTitle;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colCity;
    public TableColumn colProvince;
    public TableColumn colPostalCode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set Values to Table

        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("custid"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("title"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblCustomer.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("city"));
        tblCustomer.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("province"));
        tblCustomer.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("postalcode"));

        //set values to customerTM from Customer model
        getAllCustomer();
        //selecting values from table
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            txtid.setText(newValue.getCustid());
            txtTitle.setText(newValue.getTitle());
            txtName.setText(newValue.getName());
            txtAddress.setText(newValue.getAddress());
            txtCity.setText(newValue.getCity());
            txtProvince.setText(newValue.getProvince());
            txtPostalcode.setText(newValue.getPostalcode());
        });


    }

    private void getAllCustomer() {
        ArrayList<Customer> customers = new ArrayList<>();
        ObservableList<customerTM> customerTMS = FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customers.add(new Customer(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7)));

            }
            for (Customer customer : customers) {
                customerTMS.add(new customerTM(customer.getCustid(), customer.getTitle(), customer.getName(), customer.getAddress(), customer.getCity(), customer.getProvince(), customer.getPostalcode()));
            }
            tblCustomer.setItems(customerTMS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnAdd(ActionEvent actionEvent) {
        Customer customer = new Customer(txtid.getText(), txtTitle.getText(), txtName.getText(), txtAddress.getText(), txtCity.getText(), txtProvince.getText(), txtPostalcode.getText());
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customer " + " VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setObject(1, customer.getCustid());
            preparedStatement.setObject(2, customer.getTitle());
            preparedStatement.setObject(3, customer.getName());
            preparedStatement.setObject(4, customer.getAddress());
            preparedStatement.setObject(5, customer.getCity());
            preparedStatement.setObject(6, customer.getProvince());
            preparedStatement.setObject(7, customer.getPostalcode());
            int add = preparedStatement.executeUpdate();
            if (add > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again", ButtonType.OK).show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        clearText();
    }

    public void btnDelete(ActionEvent actionEvent) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customer WHERE cust_id=? ");
            preparedStatement.setObject(1, txtid.getText());
            int delete = preparedStatement.executeUpdate();
            if (delete > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again", ButtonType.OK).show();
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clearText();

    }

    public void btnUpdate(ActionEvent actionEvent) {
        Customer customer = new Customer(txtid.getText(), txtTitle.getText(), txtName.getText(), txtAddress.getText(), txtCity.getText(), txtProvince.getText(), txtPostalcode.getText());
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE customer SET " + "cust_title=?,cust_name=?,cust_address=?,city=?,province=?,postalcode=? WHERE cust_id = ?");

            preparedStatement.setObject(1, txtTitle.getText());
            preparedStatement.setObject(2, txtName.getText());
            preparedStatement.setObject(3, txtAddress.getText());
            preparedStatement.setObject(4, txtCity.getText());
            preparedStatement.setObject(5, txtProvince.getText());
            preparedStatement.setObject(6, txtPostalcode.getText());
            preparedStatement.setObject(7, txtid.getText());
            int update = preparedStatement.executeUpdate();
            if (update > 0) {

                new Alert(Alert.AlertType.CONFIRMATION, "Updated", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again", ButtonType.OK).show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnSearch(ActionEvent actionEvent) {
        Customer customer = new Customer(txtid.getText(), txtTitle.getText(), txtName.getText(), txtAddress.getText(), txtCity.getText(), txtProvince.getText(), txtPostalcode.getText());

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer WHERE cust_id = ?");
            preparedStatement.setObject(1, txtid.getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                txtTitle.setText(resultSet.getString(2));
                txtName.setText(resultSet.getString(3));
                txtAddress.setText(resultSet.getString(4));
                txtCity.setText(resultSet.getString(5));
                txtProvince.setText(resultSet.getString(6));
                txtPostalcode.setText(resultSet.getString(7));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void idOnAction(ActionEvent actionEvent) {
        txtTitle.requestFocus();
    }

    public void titleOnAction(ActionEvent actionEvent) {
        txtName.requestFocus();
    }

    public void nameOnAction(ActionEvent actionEvent) {
        txtAddress.requestFocus();
    }

    public void addressOnACtion(ActionEvent actionEvent) {
        txtCity.requestFocus();
    }

    public void cityOnAction(ActionEvent actionEvent) {
        txtProvince.requestFocus();
    }

    public void provinceOnAction(ActionEvent actionEvent) {
        txtPostalcode.requestFocus();
    }


    private void clearText() {
        txtid.clear();
        txtTitle.clear();
        txtName.clear();
        txtAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostalcode.clear();

    }




    }


