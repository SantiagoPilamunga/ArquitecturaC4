package com.example.consulta.service;

import org.springframework.stereotype.Service;

@Service
public class LicenciaService {

    // Genera el link directo a la página de citaciones de la ANT
    public String generarLinkLicencia(String cedula, String placa) {
        return "https://consultaweb.ant.gob.ec/PortalWEB/paginas/clientes/clp_grid_citaciones.jsp"
                + "?ps_tipo_identificacion=CED"
                + "&ps_identificacion=" + cedula
                + "&ps_placa=" + placa;
    }
}
