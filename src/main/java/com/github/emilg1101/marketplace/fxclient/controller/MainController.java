package com.github.emilg1101.marketplace.fxclient.controller;

import com.github.emilg1101.marketplace.fxclient.api.MarketplaceApi;
import com.github.emilg1101.marketplace.fxclient.api.pojo.ProductResponse;
import com.github.emilg1101.marketplace.fxclient.loader.StageLoader;
import com.github.emilg1101.marketplace.fxclient.model.Product;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MainController extends Controller {

    @Autowired
    private MarketplaceApi api;

    @Autowired
    private ProductTableController productTableController;

    @Autowired
    private StageLoader stageLoader;

    @FXML
    public void onClickLoad() {
        api.getProducts().enqueue(new Callback<List<ProductResponse>>() {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {
                productTableController.fillTable(response.body().stream().map(Product::map).collect(Collectors.toList()));
            }

            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable throwable) {

            }
        });
    }

    @FXML
    public void onClickAdd() {
        ProductAddModalStage productAddModalStage = new ProductAddModalStage(stageLoader, product -> {
            ProductResponse productResponse = ProductResponse.builder()
                    .title(product.getTitle())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .imageURL(product.getImageURL())
                    .build();
            api.addProduct(productResponse).enqueue(new Callback<ProductResponse>() {
                @Override
                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {

                }

                @Override
                public void onFailure(Call<ProductResponse> call, Throwable t) {

                }
            });
        });
        productAddModalStage.showDetails();
    }
}
