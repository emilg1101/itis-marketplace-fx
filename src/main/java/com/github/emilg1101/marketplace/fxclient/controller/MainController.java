package com.github.emilg1101.marketplace.fxclient.controller;

import com.github.emilg1101.marketplace.fxclient.api.MarketplaceApi;
import com.github.emilg1101.marketplace.fxclient.api.pojo.ProductResponse;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

@Component
public class MainController extends Controller {

    @Autowired
    private MarketplaceApi api;

    @FXML
    private Button btn;

    @FXML
    public void onClick() {
        api.getProducts().enqueue(new Callback<List<ProductResponse>>() {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {
                System.out.println(response.body().toString());
            }

            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable throwable) {

            }
        });
    }
}
