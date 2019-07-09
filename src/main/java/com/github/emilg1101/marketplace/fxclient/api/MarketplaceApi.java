package com.github.emilg1101.marketplace.fxclient.api;

import com.github.emilg1101.marketplace.fxclient.api.pojo.ProductResponse;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface MarketplaceApi {

    @GET("products")
    Call<List<ProductResponse>> getProducts();

    @GET("product/{id}")
    Call<ProductResponse> getProduct(@Path("id") long id);

    @POST("product")
    Call<ProductResponse> addProduct(@Body ProductResponse productResponse);

    @PUT("product/{id}")
    Call<ProductResponse> editProduct(@Path("id") long id, @Body ProductResponse productResponse);

    @DELETE("product/{id}")
    Call<String> deleteProduct(@Path("id") long id);
}
