package Adapters;

import Models.WeatherData;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiAdapter {

    static final String url_string = "https://api.weatherapi.com/v1/current.json?key=71d0bf30f30a4805b8c15418242001&q=";
    static String q;
    static  WeatherData weatherData;
    public ApiAdapter(String query) {
        q = query;
        connect(q);
    }

    public static void connect(String query){

        String responseBody = "";

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url_string + query))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            responseBody = response.body();

            System.out.println(responseBody.toString());

        } catch (Exception e) {
            System.out.println("Error connecting to the URL");
        }

        Gson gson = new Gson();
        weatherData = gson.fromJson(responseBody, WeatherData.class);

    }

    public WeatherData getWeatherData(){
        return weatherData;
    }

}
