package lk.ijse.gdse.generator.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;
    private static AnchorPane subpane;

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (route) {
            case GENERATE:
                window.setTitle("Generate Codes");
                initUI("generateCodeForm.fxml");
                break;
            case SCAN:
                window.setTitle("Scan Codes");
                initUI("scanCodeForm.fxml");
                break;
            case MAIN:
                window.setTitle("Main Frame");
                initUI("Mainframe.fxml");
        }
    }

    public static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/view/" + location)));
    }

    public static void init(String location) throws IOException {
        Stage window = (Stage) pane.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(Navigation.class.getResource("/view/" + location))));
    }

}
