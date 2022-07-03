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
import model.Item;
import tm.itemTM;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemController implements Initializable {
    public JFXTextField txtCode;
    public JFXTextField txtsize;
    public JFXTextField txtDesc;
    public JFXTextField txtPrice;
    public JFXTextField txtType;
    public JFXTextField txtQty;
    public TableView<itemTM> tblItem;
    public TableColumn colCode;
    public TableColumn colDesc;
    public TableColumn colSize;
    public TableColumn colQty;
    public TableColumn colPrice;
    public TableColumn colType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCode.setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("packsize"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitprice"));
        colType.setCellValueFactory(new PropertyValueFactory<>("itemtype"));

        getAllItems();

        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            txtCode.setText(newValue.getItemcode());
            txtDesc.setText(newValue.getDescription());
            txtsize.setText(newValue.getPacksize());
            txtQty.setText(String.valueOf(newValue.getQty()));
            txtPrice.setText(String.valueOf(newValue.getUnitprice()));
            txtType.setText(newValue.getItemtype());
        });
    }

     private void getAllItems(){
         ArrayList<Item>items = new ArrayList<>();
         ObservableList<itemTM>itemTMS = FXCollections.observableArrayList();
         try {
             Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM item");
             ResultSet resultSet = preparedStatement.executeQuery();
             while (resultSet.next()){
                 items.add(new Item(resultSet.getString(1), resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4 ),resultSet.getDouble(5),resultSet.getString(6)));
             }
             for (Item item : items)
             {
                 itemTMS.add(new itemTM(item.getItemcode(),item.getDescription(),item.getPacksize(),item.getQty(),item.getUnitprice(),item.getItemtype()));
             }
             tblItem.setItems(itemTMS);
         } catch (SQLException throwables) {
             throwables.printStackTrace();
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }
     }

    public void btnAdd(ActionEvent actionEvent) {


        Item item = new Item(txtCode.getText(),txtDesc.getText(),txtsize.getText(),Integer.parseInt(txtQty.getText()),Double.parseDouble(txtPrice.getText()),txtType.getText());
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO item  "+" VALUES (?,?,?,?,?,?)");
            preparedStatement.setObject(1,item.getItemcode());
            preparedStatement.setObject(2,item.getDescription());
            preparedStatement.setObject(3,item.getPacksize());
            preparedStatement.setObject(4,item.getQty());
            preparedStatement.setObject(5,item.getUnitprice());
            preparedStatement.setObject(6,item.getItemtype());
            int add = preparedStatement.executeUpdate();
            if (add>0){
                new Alert(Alert.AlertType.CONFIRMATION,"Saved", ButtonType.OK).show();

            }else {
                new Alert(Alert.AlertType.WARNING,"Try Again", ButtonType.OK).show();
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
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM item WHERE item_code = ?");
            preparedStatement.setObject(1,txtCode.getText());
            int delete = preparedStatement.executeUpdate();
            if (delete>0){
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Try Again",ButtonType.OK).show();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnUpdate(ActionEvent actionEvent) {
        Item item = new Item(txtCode.getText(),txtDesc.getText(),txtsize.getText(),Integer.parseInt(txtQty.getText()),Double.parseDouble(txtPrice.getText()),txtType.getText());
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE item SET "+"description=?,packSize=?,qtyOnHand=?,unitPrice=?,item_type=? WHERE item_code=?");

            preparedStatement.setObject(1,txtDesc.getText());
            preparedStatement.setObject(2,txtsize.getText());
            preparedStatement.setObject(3,txtQty.getText());
            preparedStatement.setObject(4,txtPrice.getText());
            preparedStatement.setObject(5,txtType.getText());
            preparedStatement.setObject(6,txtCode.getText());
            int update = preparedStatement.executeUpdate();
            if (update>0){
                new Alert(Alert.AlertType.CONFIRMATION,"Updated", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Try Again",ButtonType.OK).show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnSearch(ActionEvent actionEvent) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM item WHERE item_code = ? ");
            preparedStatement.setObject(1,txtCode.getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                txtDesc.setText(resultSet.getString(2));
                txtsize.setText(resultSet.getString(3));
                txtQty.setText(resultSet.getString(4));
                txtPrice.setText(resultSet.getString(5));
                txtType.setText(resultSet.getString(6));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



    public void codeOnAction(ActionEvent actionEvent) {

        txtDesc.requestFocus();
    }

    public void sizeOnAction(ActionEvent actionEvent) {
        txtQty.requestFocus();

    }

    public void qtyOnAction(ActionEvent actionEvent) {
        txtPrice.requestFocus();
    }

    public void descOnAction(ActionEvent actionEvent) {
        txtsize.requestFocus();
    }

    public void priceOnAction(ActionEvent actionEvent) {
        txtType.requestFocus();
    }

    public void typeOnAction(ActionEvent actionEvent) {
    }

    private void clearText(){
        txtType.clear();
        txtsize.clear();
        txtPrice.clear();
        txtDesc.clear();
        txtCode.clear();
        txtQty.clear();
    }

    public void  setItemAfterOnAction(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("packsize"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitprice"));
        colType.setCellValueFactory(new PropertyValueFactory<>("itemtype"));

        getAllItems();
        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            txtCode.setText(newValue.getItemcode());
            txtDesc.setText(newValue.getDescription());
            txtsize.setText(newValue.getPacksize());
            txtQty.setText(String.valueOf(newValue.getQty()));
            txtPrice.setText(String.valueOf(newValue.getUnitprice()));
            txtType.setText(newValue.getItemtype());
        });

    }



}
