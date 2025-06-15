package org.example.util;


import org.example.Main;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.builder.SpringApplicationBuilder;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "caching-proxy", mixinStandardHelpOptions = true, version = "caching-proxy", description = "A SIMPLE CACHING PROXY")
public class Commandline implements Callable {

    @Value("${server.port}")
    @CommandLine.Option(names = {"--port"}, description = "caching proxy port", defaultValue = "8080")
    private int port;


    @CommandLine.Option(names = {"--origin"}, description = "forwarding url")
    private String url;

    @CommandLine.Option(names = {"--origin"}, description = "forwarding url")
    private String clear;


    @Override
    public Integer call() {
        System.setProperty("server.port", String.valueOf(port));
        new SpringApplicationBuilder(Main.class).run();
        return 0;
    }


}
