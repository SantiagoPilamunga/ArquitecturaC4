package com.example.consulta.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class VehiculoService {

    private final WebClient webClient;

    public VehiculoService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://srienlinea.sri.gob.ec").build();
    }

    @Cacheable("vehiculo")
    public Mono<String> obtenerVehiculo(String placa) {
        return webClient.get()
                .uri("/sri-matriculacion-vehicular-recaudacion-servicio-internet/rest/BaseVehiculo/obtenerPorNumeroPlacaOPorNumeroCampvOPorNumeroCpn?numeroPlacaCampvCpn={placa}", placa)
                .retrieve()
                .bodyToMono(String.class);
    }
}
