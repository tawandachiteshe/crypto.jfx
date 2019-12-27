package com.dickensdev.cryptojfx.controller;

import com.dickensdev.cryptojfx.CryptojfxApplication;
import com.dickensdev.cryptojfx.model.CryptoModel;
import com.dickensdev.cryptojfx.model.PayloadModel;
import com.dickensdev.cryptojfx.service.CryptoPriceService;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.svg.SVGGlyph;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {
    private String baseUrl = "https://api.cryptoapis.io";
    @FXML
    private Label symbol;
    @FXML
    private Label change;
    @FXML
    private Label change1;
    @FXML
    private Label change7;
    @FXML
    private Label coinName;
    @FXML
    private Label price;
    @FXML
    private JFXListView listView;
    @FXML
    private ImageView Logo;
    @FXML
    private SVGGlyph da;

    public void initialize(URL location, ResourceBundle resources) {
        getCryptoData();

    }

    private void getCryptoData(){
        CryptoPriceService priceService = getData(baseUrl).create(CryptoPriceService.class);
        Call<PayloadModel> payloadCall = priceService.getCoinData();
        payloadCall.enqueue(new Callback<PayloadModel>() {
            public void onResponse(Call<PayloadModel> call, final Response<PayloadModel> response) {
                Platform.runLater(new Runnable() {
                    public void run() {
                       coinName.setText(response.body().getPayload().get(1).getName());
                       price.setText(response.body().getPayload().get(1).getPrice() + "");
                       change7.setText(response.body().getPayload().get(1).getChange1Weekly() + "");
                       change1.setText(response.body().getPayload().get(1).getChange1Hour() + "");
                       change.setText(response.body().getPayload().get(1).getChange() + "");
                        symbol.setText(response.body().getPayload().get(1).getOriginalSymbol() + "");
                        for (final CryptoModel cryptoModel: response.body().getPayload()) {
                            listView.getItems().add(new Label(cryptoModel.getName()));
                            listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                public void handle(MouseEvent event) {
                                    Label label = (Label) listView.getSelectionModel().getSelectedItem();
                                    CryptoModel clickM = response.body().getPayload().get(listView.getSelectionModel().getSelectedIndex());
                                    coinName.setText(clickM.getName());
                                    price.setText(clickM.getPrice() + "");
                                    if(clickM.getChange() < 0){
                                        change.setTextFill(Paint.valueOf("#ff0000"));
                                    }else{
                                        change.setTextFill(Paint.valueOf("#2aff05"));
                                    }
                                    if(clickM.getChange1Hour() < 0){
                                        change1.setTextFill(Paint.valueOf("#ff0000"));
                                    }else{
                                        change1.setTextFill(Paint.valueOf("#2aff05"));
                                    }
                                    if(clickM.getChange1Weekly() < 0){
                                        change7.setTextFill(Paint.valueOf("#ff0000"));
                                    }else{
                                        change7.setTextFill(Paint.valueOf("#2aff05"));
                                    }
                                    change1.setText(clickM.getChange1Hour() + "");
                                    change7.setText(clickM.getChange1Weekly() + "");
                                    change.setText(clickM.getChange() + "");
                                    symbol.setText(clickM.getOriginalSymbol());

                                }
                            });
                        }
                    }
                });
            }

            public void onFailure(Call<PayloadModel> call, Throwable throwable) {

            }
        });
    }

    public void onRefresh(){
        getCryptoData();
    }

    public Retrofit getData(String baseUrl){
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(baseUrl).
                addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }
}
