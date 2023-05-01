package org.example;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiService {
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public ISSCurrentLocationResponse fetchCurrentISSLocation() throws IOException, InterruptedException {
        String url = "http://api.open-notify.org/iss-now.json";
        String response = sendRequest(url);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response, ISSCurrentLocationResponse.class);
    }


    public String sendRequest(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
        }
    }
}
