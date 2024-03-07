package ar.edu.utn.frc.tup.lc.iv.clients;

import ar.edu.utn.frc.tup.lc.iv.dtos.requests.CargoRequest;
import ar.edu.utn.frc.tup.lc.iv.dtos.requests.DistritoRequest;
import ar.edu.utn.frc.tup.lc.iv.dtos.requests.ResultadoRequest;
import ar.edu.utn.frc.tup.lc.iv.dtos.requests.SeccionResquest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EleccionesClient {
    @Autowired
    private RestTemplate restTemplate;

    private String urlBase = "http://serverapi:8080";

    public ResponseEntity<DistritoRequest[]> getDistritos(){
        return restTemplate.getForEntity(urlBase + "/distritos", DistritoRequest[].class);
    }

    public ResponseEntity<DistritoRequest[]> getDistritosByNombre(String nombre){
        return restTemplate.getForEntity(urlBase + "/distritos?distritoNombre=" + nombre, DistritoRequest[].class);
    }

    public ResponseEntity<DistritoRequest[]> getDistritoById(Long id){
        return restTemplate.getForEntity(urlBase + "/distritos?distritoId=" + id, DistritoRequest[].class);
    }

    public ResponseEntity<CargoRequest[]> getCargosByDistrito(Long distritoId){
        return restTemplate.getForEntity(urlBase + "/cargos?distritoId=" + distritoId, CargoRequest[].class);
    }

    public ResponseEntity<SeccionResquest[]> getSeccionesByDistrito(Long distritoId){
        return restTemplate.getForEntity(urlBase + "/secciones?distritoId=" + distritoId, SeccionResquest[].class);
    }

    public ResponseEntity<SeccionResquest[]> getSeccionesByDistritoYSeccion(Long distritoId, Long seccionId){
        return restTemplate.getForEntity(
                String.format(urlBase + "/secciones?seccionId=%s&distritoId=%s", seccionId, distritoId),
                SeccionResquest[].class);
    }

    public ResponseEntity<ResultadoRequest[]> getResultadosBySeccion(Long seccionId){
        return restTemplate.getForEntity(
                urlBase + "/resultados?seccionId=" + seccionId, ResultadoRequest[].class
        );
    }
}
