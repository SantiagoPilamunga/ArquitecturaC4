import React, { useState } from "react";
import axios from "axios";
import './App.css';


export default function App() {
  const [ruc, setRuc] = useState("");
  const [placa, setPlaca] = useState("");
  const [cedula, setCedula] = useState("");
  const [sriData, setSriData] = useState(null);
  const [vehiculoData, setVehiculoData] = useState(null);
  const [licenciaData, setLicenciaData] = useState(null);

  const consultarSRI = async () => {
    try {
      const res = await axios.get(`http://localhost:8080/api/sri/${ruc}`);
      setSriData(res.data[0] || null);
    } catch (err) {
      setSriData({ error: "Error consultando SRI" });
    }
  };

  const consultarVehiculo = async () => {
    try {
      const res = await axios.get(`http://localhost:8080/api/vehiculo/${placa}`);
      setVehiculoData(res.data || null);
    } catch (err) {
      setVehiculoData({ error: "Error consultando vehículo" });
    }
  };

  const consultarLicencia = async () => {
    try {
      const res = await axios.get(`http://localhost:8080/api/licencia/${cedula}/${placa}`);
      setLicenciaData(res.data || null);
    } catch (err) {
      setLicenciaData({ error: "Error consultando licencia" });
    }
  };

  return (
  <div className="container">
    <h1>Consulta SRI / Vehículo / Licencia</h1>

    <div style={{ marginBottom: "20px" }}>
      <input
        placeholder="RUC"
        value={ruc}
        onChange={e => setRuc(e.target.value)}
      />
      <button onClick={consultarSRI}>Consultar SRI</button>
    </div>

    <div style={{ marginBottom: "20px" }}>
      <input
        placeholder="Placa"
        value={placa}
        onChange={e => setPlaca(e.target.value)}
      />
      <button onClick={consultarVehiculo}>Consultar Vehículo</button>
    </div>

    <div style={{ marginBottom: "20px" }}>
      <input
        placeholder="Cédula"
        value={cedula}
        onChange={e => setCedula(e.target.value)}
      />
      <button onClick={consultarLicencia}>Consultar Licencia</button>
    </div>

    <hr />

    {sriData && (
      <div className="data-section">
        <h2>Datos SRI</h2>
        {sriData.error ? (
          <p>{sriData.error}</p>
        ) : (
          <div>
            <p><strong>RUC:</strong> {sriData.numeroRuc}</p>
            <p><strong>Razón Social:</strong> {sriData.razonSocial}</p>
            <p><strong>Estado:</strong> {sriData.estadoContribuyenteRuc}</p>
            <p><strong>Actividad:</strong> {sriData.actividadEconomicaPrincipal}</p>
            <p><strong>Tipo Contribuyente:</strong> {sriData.tipoContribuyente}</p>
            <p><strong>Regimen:</strong> {sriData.regimen}</p>
            <p><strong>Obligado a contabilidad:</strong> {sriData.obligadoLlevarContabilidad}</p>
            <p><strong>Motivo suspensión/ceses:</strong> {sriData.motivoCancelacionSuspension}</p>
            <p><strong>Fechas:</strong> {JSON.stringify(sriData.informacionFechasContribuyente)}</p>
          </div>
        )}
      </div>
    )}

    {vehiculoData && (
      <div className="data-section">
        <h2>Datos Vehículo</h2>
        {vehiculoData.error ? (
          <p>{vehiculoData.error}</p>
        ) : (
          <div>
            <p><strong>Placa:</strong> {vehiculoData.numeroPlaca}</p>
            <p><strong>Marca:</strong> {vehiculoData.descripcionMarca}</p>
            <p><strong>Modelo:</strong> {vehiculoData.descripcionModelo}</p>
            <p><strong>Año:</strong> {vehiculoData.anioAuto}</p>
            <p><strong>País:</strong> {vehiculoData.descripcionPais}</p>
            <p><strong>Último año pagado:</strong> {vehiculoData.ultimoAnioPagado}</p>
            <p><strong>Exoneración:</strong> {vehiculoData.estadoExoneracion}</p>
          </div>
        )}
      </div>
    )}

    {licenciaData && (
      <div className="data-section">
        <h2>Licencia</h2>
        {licenciaData.error ? (
          <p>{licenciaData.error}</p>
        ) : (
          <div>
            <p>
              <strong>Ver citaciones en la página oficial:</strong>{" "}
              <a href={licenciaData.link} target="_blank" rel="noopener noreferrer">
                {licenciaData.link}
              </a>
            </p>
          </div>
        )}
      </div>
    )}
  </div>
);

}
