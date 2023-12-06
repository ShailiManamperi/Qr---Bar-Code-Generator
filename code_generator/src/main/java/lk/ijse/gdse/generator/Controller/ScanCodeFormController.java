package lk.ijse.gdse.generator.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.ijse.gdse.generator.util.Navigation;
import lk.ijse.gdse.generator.util.Routes;

import java.io.IOException;

public class ScanCodeFormController {

    @FXML
    private JFXButton btnDecode;

    @FXML
    private JFXButton btnExit;

    @FXML
    private JFXButton btnOpen;

    @FXML
    private ImageView imgchange;

    @FXML
    private Pane inPane;

    @FXML
    private AnchorPane pane;
    @FXML
    private TextField txtinput1;

    @FXML
    private TextField txtinput2;

    @FXML
    void BackOnAction(ActionEvent event) {
        try {
            Navigation.navigate(Routes.MAIN,pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void openCodeOnAction(ActionEvent event) {

    }

    @FXML
    void scanCodeOnAction(ActionEvent event) {

    }

}
