package com.github.emilg1101.marketplace.fxclient.controller;

import com.github.emilg1101.marketplace.fxclient.api.MarketplaceApi;
import com.github.emilg1101.marketplace.fxclient.api.pojo.ProductResponse;
import com.github.emilg1101.marketplace.fxclient.loader.StageLoader;
import com.github.emilg1101.marketplace.fxclient.model.Product;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductTableController extends Controller {

    @Autowired
    private StageLoader stageLoader;

    @Autowired
    private MarketplaceApi api;

    @FXML
    private TableColumn<Integer, Product> id;
    @FXML
    private TableColumn<String, Product> title;
    @FXML
    private TableColumn<String, Product> price;
    @FXML
    private TableView<Product> productTable;

    public void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        productTable.setRowFactory(rf -> {
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    ProductDetailsModalStage stage = new ProductDetailsModalStage(stageLoader, new ProductDetailsModalStage.ActionListener() {
                        @Override
                        public void onEdit(long id, Product product) {
                            ProductEditModalStage editModalStage = new ProductEditModalStage(stageLoader, product1 -> {
                                System.out.println(product1.toString());
                                saveProduct(product1.getId(), product1);
                            });
                            editModalStage.showDetails(product);
                        }

                        @Override
                        public void onDelete(long id) {
                            deleteProduct(id);
                        }
                    });
                    stage.showDetails(row.getItem());
                }
            });
            return row;
        });
    }

    public void fillTable(List<Product> products) {
        productTable.setItems(FXCollections.observableArrayList(products));
    }

    private void saveProduct(long id, Product product) {
        ProductResponse productResponse = ProductResponse.builder()
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .imageURL(product.getImageURL())
                .build();
        api.editProduct(id, productResponse).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {

            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });
    }

    private void deleteProduct(long id) {
        api.deleteProduct(id).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                updateTable();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void updateTable() {
        api.getProducts().enqueue(new Callback<List<ProductResponse>>() {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {
                fillTable(response.body().stream().map(Product::map).collect(Collectors.toList()));
            }

            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable throwable) {

            }
        });
    }
}
