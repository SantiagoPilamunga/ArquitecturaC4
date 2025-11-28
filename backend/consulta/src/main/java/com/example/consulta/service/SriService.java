package com.example.consulta.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SriService {

    private final WebClient webClient;

    public SriService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://srienlinea.sri.gob.ec").build();
    }

    @Cacheable("sri")
    public Mono<String> verificarContribuyente(String ruc) {
        return webClient.get()
                .uri("/sri-catastro-sujeto-servicio-internet/rest/ConsolidadoContribuyente/existePorNumeroRuc?numeroRuc={ruc}", ruc)
                .retrieve()
                .bodyToMono(String.class);
    }

    @Cacheable("sri")
    public Mono<String> obtenerInfoContribuyente(String ruc) {
        return webClient.get()
                .uri("/sri-catastro-sujeto-servicio-internet/rest/ConsolidadoContribuyente/obtenerPorNumerosRuc?&ruc={ruc}", ruc)
                .retrieve()
                .bodyToMono(String.class);
                
    }
    
}
