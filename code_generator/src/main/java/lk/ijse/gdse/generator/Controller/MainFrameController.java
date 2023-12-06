package lk.ijse.gdse.generator.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.generator.util.Navigation;
import lk.ijse.gdse.generator.util.Routes;

import java.io.IOException;

public class MainFrameController {
    @FXML
    private JFXButton btnscan;

    @FXML
    private JFXButton btngenerate;

    @FXML
    private AnchorPane pane;

    @FXML
    void ScanCodeFormOnAction(ActionEvent event) {
        try {
            Navigation.navigate(Routes.SCAN,pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void generateCodeFormOnAction(ActionEvent event) {
        try {
            Navigation.navigate(Routes.GENERATE,pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
