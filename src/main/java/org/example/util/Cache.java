package org.example.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Service
public class Cache {
    private Map<String, JsonElement> cache = new ConcurrentHashMap<>();

    public JsonElement getCached(String url){
        return cache.get(url);
    }

    public boolean isCached(String url) {
        return cache.containsKey(url);
    }

    public void addToCache(String url, JsonElement jsonElement){
        cache.put(url, jsonElement);
        System.out.println(cache);
    }

    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void cacheUpdater() throws IOException, InterruptedException {
        String baseUrl = "https://dummyjson.com";
        String url = baseUrl + "/products";


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonElement jsonElement = JsonParser.parseString(response.body());
        cache.put(url,jsonElement);

    }

    public ResponseEntity<String> check(String url) throws IOException, InterruptedException {

        if (isCached(url)){
            HttpHeaders headers = new HttpHeaders();
            headers.add("X-Cache", "HIT");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(getCached(url).toString());
        }else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("X-Cache", "MISS");

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            try {
                JsonElement jsonElement = JsonParser.parseString(response.body());
                cache.put(url,jsonElement);
                return ResponseEntity
                        .ok()
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(getCached(url).toString());
            }catch (Exception e){
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .headers(headers)
                        .body("{\"error\": \"Failed to parse or cache response.\"}");
            }
        }
    }

}
