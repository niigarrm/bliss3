package org.example.timezoneviewer;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ApiClient {

    private static final String BASE_URL = "http://worldtimeapi.org/api/timezone/";

    public static String getTimeZoneData(String countryName) {
        try {
            if (countryName == null || countryName.isBlank()) {
                return "Invalid country name!";
            }

            String encodedCountryName = URLEncoder.encode(countryName, StandardCharsets.UTF_8);
            String url = BASE_URL + encodedCountryName;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                return "Error fetching data: " + response.statusCode();
            }

            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error in API request: " + e.getMessage();
        }
    }
}
