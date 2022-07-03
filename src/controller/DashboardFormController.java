package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DashboardFormController {

    public AnchorPane dashboardformContext;
    public AnchorPane sidepaneContext;
    public AnchorPane loadpaneContext;
    public Label lblTime;
    public Label lblDate;

    public void initialize(){
        initClock();
    }



    public void btnCustomer(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("../view/customer.fxml");

        assert resource != null;

        Parent load = (Parent) FXMLLoader.load(resource);
        this.loadpaneContext.getChildren().clear();
        this.loadpaneContext.getChildren().add(load);
    }

    public void btnExit(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage)this.loadpaneContext.getScene().getWindow();
        window.setScene(new Scene((Parent)FXMLLoader.load(this.getClass().getResource("../view/loginform.fxml"))));
    }
    private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            lblTime.setText(LocalDateTime.now().format(formatter));

            SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            lblDate.setText(formatter2.format(date));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void btnItem(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("../view/item.fxml");

        assert resource != null;

        Parent load = (Parent) FXMLLoader.load(resource);
        this.loadpaneContext.getChildren().clear();
        this.loadpaneContext.getChildren().add(load);
    }

    public void btnOrder(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("../view/order.fxml");

        assert resource != null;

        Parent load = (Parent) FXMLLoader.load(resource);
        this.loadpaneContext.getChildren().clear();
        this.loadpaneContext.getChildren().add(load);

    }
}
