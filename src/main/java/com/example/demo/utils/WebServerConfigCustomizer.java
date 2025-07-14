package com.example.demo.utils;


import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.MimeMappings;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class WebServerConfigCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    @Override
    public void customize(ConfigurableWebServerFactory factory) {
//        MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
//        mappings.add("webp", "image/webp");
//        factory.setMimeMappings(mappings);
    }
}



