package com.github.emilg1101.marketplace.fxclient.config;

import com.github.emilg1101.marketplace.fxclient.api.MarketplaceApi;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
@ComponentScan("com.github.emilg1101.marketplace.fxclient")
public class AppConfig {

    @Bean
    public OkHttpClient httpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        return httpClient.build();
    }

    @Bean
    public Retrofit retrofit(OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .baseUrl("http://localhost:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
    }

    @Bean
    public MarketplaceApi getApi(Retrofit retrofit) {
        return retrofit.create(MarketplaceApi.class);
    }
}
