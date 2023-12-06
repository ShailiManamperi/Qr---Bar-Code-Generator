package lk.ijse.gdse.generator.Controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import lk.ijse.gdse.generator.util.Navigation;
import lk.ijse.gdse.generator.util.Routes;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GenerateCodeFormController   {

    @FXML
    private JFXButton btngenerate;

    @FXML
    private JFXButton btnsave;

    @FXML
    private ImageView imgchange;

    @FXML
    private AnchorPane imgCodePane;

    @FXML
    private AnchorPane imgpane;

    @FXML
    private Pane inPane;

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXRadioButton rbBarCode;

    @FXML
    private JFXRadioButton rbQrCode;

    @FXML
    private JFXRadioButton rbDouble;

    @FXML
    private JFXRadioButton rbSingle;

    @FXML
    private TextField txtinput1;

    @FXML
    private TextField txtinput2;

    @FXML
    private JFXButton btnExit;

    private String type;
    private String flag;
    Image fxImageBarCode;
    private Image generatedCodeImage;
    ImageView imageView;
    public void initialize(){
        txtinput1.setDisable(true);
        txtinput2.setDisable(true);
    }
    @FXML
    void CheckDoubleOnAction(ActionEvent event) {
        flag = "Double";
        txtinput1.setDisable(false);
        txtinput2.setDisable(false);
    }
    @FXML
    void CheckSingleOnAction(ActionEvent event) {
        flag = "Single";
        txtinput1.setDisable(false);
        txtinput2.setDisable(true);
    }
    @FXML
    public void generateCodeOnAction(ActionEvent event) {
        if (rbBarCode.isSelected()) {
            type = "Bar Code";
            generateBarCode();
        } else if (rbQrCode.isSelected()) {
            type = "Qr Code";
            generateQRCode();
        }
        updatePreview();
        btngenerate.setDisable(false);
    }

    private void generateBarCode() {
        String input1 = txtinput1.getText();
        String input2 = txtinput2.getText();
        System.out.println(flag);
        if (flag.equals("Single")){
            if (input1 != null){
                try {
                    Barcode barcode = BarcodeFactory.createCode128(input1);
                    barcode.setDrawingText(false);
                    BufferedImage image = BarcodeImageHandler.getImage(barcode);

                    // Resize the image
                    int targetWidth = 180; // Set your desired width
                    int targetHeight = 70; // Set your desired height

                    // Convert the BufferedImage to JavaFX Image
                    fxImageBarCode = SwingFXUtils.toFXImage(image, null);

                    // Create an ImageView to scale the image
                    imageView = new ImageView(fxImageBarCode);
                    imageView.setFitWidth(targetWidth);
                    imageView.setFitHeight(targetHeight);

                    imgchange.setVisible(false);
                    imageView.setVisible(true);

                    // Set the alignment of the ImageView within the AnchorPane
                    AnchorPane.setTopAnchor(imageView, (imgCodePane.getHeight() - imageView.getFitHeight()) / 2);
                    AnchorPane.setLeftAnchor(imageView, (imgCodePane.getWidth() - imageView.getFitWidth()) / 2);

                    imgCodePane.getChildren().add(imageView);
                } catch (Exception e) {
                    e.printStackTrace();
                    // Handle any exceptions that may occur during barcode generation
                }
            }else {
                throw new RuntimeException("\n Input filed is empty");
            }
        }else if (flag.equals("Double")){
            if (input1 != null && input2 != null){
                try {
                    // First Barcode
                    Barcode barcode1 = BarcodeFactory.createCode128(input1);
                    barcode1.setDrawingText(false);
                    BufferedImage image1 = BarcodeImageHandler.getImage(barcode1);
                    // Resize the image
                    int targetWidth = 100; // Set your desired width
                    int targetHeight = 100; // Set your desired height

                    // Second Barcode
                    Barcode barcode2 = BarcodeFactory.createCode128(input2);
                    barcode2.setDrawingText(false);
                    BufferedImage image2 = BarcodeImageHandler.getImage(barcode2);

                    // Combine the two images (You may want to adjust this based on your requirements)
                    BufferedImage combinedImage = new BufferedImage(targetWidth * 2, targetHeight, BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2d = combinedImage.createGraphics();
                    g2d.drawImage(image1, 0, 0, null);
                    g2d.drawImage(image2, targetWidth, 0, null);
                    g2d.dispose();

                    // Convert the combined BufferedImage to JavaFX Image
                    fxImageBarCode = SwingFXUtils.toFXImage(combinedImage, null);

                    // Create an ImageView to scale the combined image
                    imageView = new ImageView(fxImageBarCode);
                    imageView.setFitWidth(targetWidth * 2); // Adjust the width based on your needs
                    imageView.setFitHeight(targetHeight);

                    imgchange.setVisible(false);
                    imageView.setVisible(true);

                    // Set the alignment of the ImageView within the AnchorPane
                    AnchorPane.setTopAnchor(imageView, (imgCodePane.getHeight() - imageView.getFitHeight()) / 2);
                    AnchorPane.setLeftAnchor(imageView, (imgCodePane.getWidth() - imageView.getFitWidth()) / 2);

                    imgCodePane.getChildren().add(imageView);
                } catch (Exception e) {
                    e.printStackTrace();
                    // Handle any exceptions that may occur during barcode generation
                }
            }else {
                throw new RuntimeException("\n input filed are empty");
            }
        }
    }

    private void generateQRCode() {
        String input1 = txtinput1.getText();
        String input2 = txtinput2.getText();
        System.out.println(flag);
        int width = 200;
        int height = 200;
        if (flag.equals("Single")){
            if (input1 != null){
                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                BitMatrix bitMatrix;
                try {
                    bitMatrix = qrCodeWriter.encode(input1, BarcodeFormat.QR_CODE, width, height);
                    generatedCodeImage = SwingFXUtils.toFXImage(MatrixToImageWriter.toBufferedImage(bitMatrix), null);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }else {
                throw new RuntimeException("\n Input filed is empty");
            }
        }else if (flag.equals("Double")){
            if (input1 != null && input2 != null){
                String combined = input1 + " | "+ input2;
                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                BitMatrix bitMatrix;
                try {
                    bitMatrix = qrCodeWriter.encode(combined, BarcodeFormat.QR_CODE, width, height);
                    generatedCodeImage = SwingFXUtils.toFXImage(MatrixToImageWriter.toBufferedImage(bitMatrix), null);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }else {
                throw new RuntimeException("\n input filed are empty");
            }
        }
    }

    private void updatePreview() {
        if (generatedCodeImage != null) {
            imgchange.setImage(generatedCodeImage);
        }
    }

    @FXML
    public void saveOnAction(ActionEvent event) {
        if (rbBarCode.isSelected()) {
            saveBarCode();
        } else if (rbQrCode.isSelected()) {
            saveQrCode();
        }
    }

    private void saveQrCode() {

    }

    private void saveBarCode() {
        if (fxImageBarCode != null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save " + type);
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"));
            File fileToSave = fileChooser.showSaveDialog(null);

            if (fileToSave != null) {
                try {
                    // Ensure the selected file has .png extension
                    if (!fileToSave.getName().toLowerCase().endsWith(".png")) {
                        fileToSave = new File(fileToSave.getAbsolutePath() + ".png");
                    }

                    ImageIO.write(SwingFXUtils.fromFXImage(fxImageBarCode, null), "png", fileToSave);

                    // Show a success message
                    System.out.println(type + " saved successfully!");
                    txtinput1.setText("");
                    txtinput2.setText("");
                    txtinput1.setDisable(true);
                    txtinput2.setDisable(true);
                    imgchange.setVisible(true);
                    imageView.setVisible(false);

                    rbQrCode.setSelected(false);
                    rbBarCode.setSelected(false);
                    rbDouble.setSelected(false);
                    rbSingle.setSelected(false);

                    imgchange.setImage(new Image("asstes/qr1.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                    // Show an error message
                    System.out.println("Error saving " + type);
                }
            }
        }
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
