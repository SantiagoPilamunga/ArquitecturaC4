package com.example.consulta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import java.time.Duration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder webClientBuilder() {
        // Configuración del cliente HTTP con timeout
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofSeconds(15)) // Timeout de respuesta
                .option(io.netty.channel.ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000); // Timeout de conexión

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeader("User-Agent", "Java-SpringBoot-App"); // User-Agent necesario para algunas APIs
    }
}
