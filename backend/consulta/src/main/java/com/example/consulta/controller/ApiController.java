package com.example.consulta.controller;

import com.example.consulta.service.LicenciaService;
import com.example.consulta.service.SriService;
import com.example.consulta.service.VehiculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final SriService sriService;
    private final VehiculoService vehiculoService;
    private final LicenciaService licenciaService;

    public ApiController(SriService sriService, VehiculoService vehiculoService, LicenciaService licenciaService) {
        this.sriService = sriService;
        this.vehiculoService = vehiculoService;
        this.licenciaService = licenciaService;
    }

    @GetMapping("/sri/{ruc}")
    public Mono<String> verificarSRI(@PathVariable String ruc) {
        return sriService.verificarContribuyente(ruc)
                .flatMap(existe -> {
                    if (existe.equalsIgnoreCase("true")) {
                        return sriService.obtenerInfoContribuyente(ruc);
                    } else {
                        return Mono.just("No es contribuyente");
                    }
                });
    }

    @GetMapping("/vehiculo/{placa}")
    public Mono<String> obtenerVehiculo(@PathVariable String placa) {
        return vehiculoService.obtenerVehiculo(placa);
    }

    // NUEVO: Devuelve el link directo de la ANT
    @GetMapping("/licencia/{cedula}/{placa}")
    public ResponseEntity<Map<String, String>> obtenerLink(@PathVariable String cedula, @PathVariable String placa) {
        String link = licenciaService.generarLinkLicencia(cedula, placa);
        Map<String, String> response = new HashMap<>();
        response.put("link", link);
        return ResponseEntity.ok(response);
    }
}
