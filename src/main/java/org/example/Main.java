package org.example;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.example.util.Commandline;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        new CommandLine(new Commandline()).execute(args);
    }
}