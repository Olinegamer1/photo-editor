package com.example.photoeditor;

import com.example.photoeditor.CaptureWindow;
import com.example.photoeditor.imageFilters.*;
import com.example.photoeditor.imageTransformation.BilinearInterpolationTransformation;
import com.example.photoeditor.imageTransformation.NearestNeighborTransformation;
import com.example.photoeditor.imageTransformation.BicubicInterpolationTransformation;
import com.example.photoeditor.imageTransformation.Rotate;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;


public class Controller {
    private final Menu manager = Menu.getInstance();
    private final CaptureWindow captureWindow = CaptureWindow.getInstance();

    @FXML
    private Button openFolderButton;
    @FXML
    private Button saveImageButton;
    @FXML
    private ImageView imageField;
    @FXML
    private ImageView imageFiledResizable;
    @FXML
    private Canvas canvas;
    @FXML
    private TextField scaleValue;
    @FXML
    private Slider sobelSize;
    @FXML
    private Canvas histogram;
    @FXML
    private Slider brightnessValue;
    @FXML
    private Slider negativeValue;
    @FXML
    private Slider binaryValue;
    @FXML
    private Slider decreaseQ1;
    @FXML
    private Slider decreaseQ2;
    @FXML
    private Slider increaseQ1;
    @FXML
    private Slider increaseQ2;
    @FXML
    private Slider gammaSlider;
    @FXML
    private Slider quantonisationSlider;
    @FXML
    private Slider lowHighFilterSlider;
    @FXML
    private Slider medianSlider;
    @FXML
    private ColorPicker q1color;
    @FXML
    private ColorPicker q2color;
    @FXML
    private ColorPicker q3Color;
    @FXML
    private ColorPicker q4Color;
    @FXML
    private ColorPicker q5Color;
    @FXML
    private ColorPicker q6Color;
    @FXML
    private Slider q1Border;
    @FXML
    private Slider q2Border;
    @FXML
    private Slider q5Border;
    @FXML
    private Slider q3Border;
    @FXML
    private Slider q4Border;
    @FXML
    private ProgressIndicator indicator;
    @FXML
    private Slider bilinearInterpolationScaleSlider;
    @FXML
    private Slider bicubicInterpolationScaleSlider;
    @FXML
    private TextField rotateValue;

    @FXML
    void handleNearestNeighbor() {
        NearestNeighborTransformation.applyTransformation(imageFiledResizable, scaleValue);
    }

    @FXML
    void handleKirschFilter() {
        Kirsch.applyFilter(imageField, indicator);
    }

    @FXML
    void handleEmbossingFilter() {
        Embossing.applyFilter(imageField, indicator);
    }

    @FXML
    void handleOpenFolder() {
        manager.openImage(openFolderButton, imageField); }

    @FXML
    void handleSaveImage() throws IOException {
        manager.saveImage(saveImageButton);
    }

    @FXML
    void handleLofHighFilter() {
        LowAndHighFilter.applyFilter(imageField, lowHighFilterSlider, indicator);
    }

    @FXML
    void handleChangeColor() {
        PseudoColoring.applyFilter(imageField, q1color, q2color, q3Color, q4Color, q5Color, q6Color, indicator, q1Border, q2Border, q3Border, q4Border, q5Border);
    }

    @FXML
    void handleSepia() {
        Sepia.applyFilter(imageField, indicator);
    }

    @FXML
    void handleBinClustering() {
        BinClustering.applyFilter(imageField, indicator);
    }

    @FXML
    private void handleHistogram() {
        Histogram.drawHistogram(histogram);
    }

    @FXML
    void handleQuantonisation() {
        Quantonisation.applyFilter(quantonisationSlider, imageField, indicator);
    }

    @FXML
    void handleBinarization() {
        Binarization.applyFilter(binaryValue, imageField, indicator);
    }

    @FXML
    private void handleNegative() {
        Negative.applyFilter(negativeValue, imageField, indicator);
    }

    @FXML
    private void handleBrightness() {
        Brightness.applyFilter(brightnessValue, imageField, indicator);
    }

    @FXML
    void handleDecreaseContrast() {
        IncreaseDecreaseContrast.applyFilter(decreaseQ2, decreaseQ1, imageField, true, indicator);
    }

    @FXML
    void handleMedianFilter() {
        Median.applyFilter(imageField, medianSlider, indicator);
    }

    @FXML
    void handleGammaConvertor() {
        GammaConverter.applyFilter(gammaSlider, imageField, indicator);
    }

    @FXML
    void handleIncreaseContrast() {
        IncreaseDecreaseContrast.applyFilter(increaseQ2, increaseQ1, imageField, false, indicator);
    }

    @FXML
    private void handleGrayScale() {
        GrayScale.applyFilter(imageField, indicator);
    }


    @FXML
    void handleSelectArea(){
        canvas.setDisable(!canvas.isDisable());
        captureWindow.showWindow(canvas, imageFiledResizable);
    }

    @FXML
    void handleSobelFilter() {
        Sobel.applyFilter(imageField, sobelSize, indicator);
    }

    @FXML
    void handleSolar() {
        Solar.applyFilter(imageField, indicator);
    }

    @FXML
    void handleBilinearInterpolationScale(){
        BilinearInterpolationTransformation.applyTransformation(imageFiledResizable, bilinearInterpolationScaleSlider);
    }
    @FXML
    void handleBicubicInterpolationScale() {
        BicubicInterpolationTransformation.applyTransformation(imageFiledResizable, bicubicInterpolationScaleSlider);
    }

    @FXML
    void handleRotateImage() {
        Rotate.applyTransformation(0, 0, imageFiledResizable, rotateValue);
    }



    @FXML
    void handleImageDelete() {
        indicator.setVisible(false);
        indicator.progressProperty().unbind();
        indicator.setProgress(0);
        canvas.setDisable(true);
        manager.clearImageField(imageFiledResizable);
        manager.clearImageField(imageField);
    }

    @FXML
    public void handleApplyChanges() {
        manager.applyChanges(imageField);
    }

    @FXML
    public void handleCancelChanges() {
        manager.cancelChanges(imageField);
    }

    public void handleAlignText() {
        AlignText.applyFilter(imageField, indicator);
    }
}
