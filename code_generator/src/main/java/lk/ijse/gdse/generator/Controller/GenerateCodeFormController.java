package lk.ijse.gdse.generator.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.ijse.gdse.generator.util.Navigation;
import lk.ijse.gdse.generator.util.Routes;

import java.io.IOException;

public class GenerateCodeFormController  {

    @FXML
    private JFXButton btngenerate;

    @FXML
    private JFXButton btnsave;

    @FXML
    private ImageView imgchange;

    @FXML
    private Pane inPane;

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXRadioButton rbBarCode;

    @FXML
    private JFXRadioButton rbQrCode;

    @FXML
    private JFXTextArea txtInput;

    @FXML
    private JFXButton btnExit;

    @FXML
    public void BarcodeOnAction(ActionEvent event) {

    }

    @FXML
    public void QrcodeOnAction(ActionEvent event) {

    }

    @FXML
    public void generateCodeOnAction(ActionEvent event) {

    }

    @FXML
    public void saveOnAction(ActionEvent event) {

    }

    @FXML
    void BackOnAction(ActionEvent event) {
        try {
            Navigation.navigate(Routes.MAIN,pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
