package ar.edu.utn.frc.tup.lc.iv.services.imps;

import ar.edu.utn.frc.tup.lc.iv.clients.EleccionesClient;
import ar.edu.utn.frc.tup.lc.iv.dtos.requests.ResultadoRequest;
import ar.edu.utn.frc.tup.lc.iv.dtos.responses.*;
import ar.edu.utn.frc.tup.lc.iv.services.EleccionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class EleccionServiceImp implements EleccionService {
    @Autowired
    private EleccionesClient eleccionesClient;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Distrito> getDistritos() {
        return List.of(modelMapper.map(Objects.requireNonNull(eleccionesClient.getDistritos().getBody()), Distrito[].class));
    }

    @Override
    public List<Distrito> getDistritosByNombre(String nombre) {
        return List.of(modelMapper.map(Objects.requireNonNull(eleccionesClient.getDistritosByNombre(nombre).getBody()),
                Distrito[].class));
    }

    @Override
    public CargosDeDistrito getCargosByDistrito(Long distritoId) {
        CargosDeDistrito response = new CargosDeDistrito();
        response.setDistrito(
                modelMapper.map(Objects.requireNonNull(eleccionesClient.getDistritoById(distritoId).getBody())[0],
                        Distrito.class));
        response.setCargos(
                List.of(modelMapper.map(eleccionesClient.getCargosByDistrito(distritoId).getBody(), Cargo[].class))
        );

        return response;
    }

    @Override
    public List<Seccion> getSeccionesByDistrito(Long distritoId) {
        return List.of(modelMapper.map(
                eleccionesClient.getSeccionesByDistrito(distritoId).getBody(), Seccion[].class));
    }

    @Override
    public List<Seccion> getSeccionByDistritoYSeccion(Long distritoId, Long seccionId) {
        return List.of(modelMapper.map(
                eleccionesClient.getSeccionesByDistritoYSeccion(distritoId, seccionId).getBody(), Seccion[].class));
    }

    @Override
    public ResultadoDeSeccion getResultadoBySeccion(Long seccionId, Long distritoId) {
        ResultadoRequest[] request = eleccionesClient.getResultadosBySeccion(seccionId).getBody();
        ResultadoDeSeccion resultadoDeSeccion = new ResultadoDeSeccion();
        resultadoDeSeccion.setResultados(new ArrayList<>());
        Map<Long, Integer> partidosVotos = new HashMap<>();
        Map<Long, String> partidosNombres = new HashMap<>();
        Map<String, Integer> otrosVotos = new HashMap<>();

        otrosVotos.put("EN BLANCO", 0);
        otrosVotos.put("NULO", 0);
        otrosVotos.put("IMPUGNADO", 0);
        otrosVotos.put("RECURRIDO", 0);

        int votosTotales = 0;

        for (ResultadoRequest r : Objects.requireNonNull(request)) {
            if(Objects.equals(r.getDistritoId(), distritoId)){
                if(!partidosVotos.containsKey(r.getAgrupacionId())){
                    partidosVotos.put(r.getAgrupacionId(), 0);
                }
                if(r.getAgrupacionId() != 0) {
                    if(!partidosNombres.containsKey(r.getAgrupacionId())){
                        partidosNombres.put(r.getAgrupacionId(), r.getAgrupacionNombre());
                    }
                    partidosVotos.put(r.getAgrupacionId(), r.getVotosCantidad() + partidosVotos.get(r.getAgrupacionId()));
                }
                else
                    if(!Objects.equals(r.getVotosTipo(), "POSITIVO"))
                        otrosVotos.put(r.getVotosTipo(), r.getVotosCantidad());

                if(!r.getVotosTipo().equals("COMANDO"))
                    votosTotales += r.getVotosCantidad();
            }
        }

        for (Long key: partidosVotos.keySet()) {
            if(key == 0)
                continue;
            Resultado resultado = new Resultado();
            resultado.setVotos(partidosVotos.get(key));
            resultado.setNombre(partidosNombres.get(key));
            resultado.setPorcentaje(BigDecimal.valueOf(resultado.getVotos())
                    .multiply(BigDecimal.valueOf(100))
                    .divide(BigDecimal.valueOf(votosTotales), RoundingMode.UP));
            resultadoDeSeccion.getResultados().add(resultado);
        }

        for (String key: otrosVotos.keySet()) {
            Resultado resultado = new Resultado();
            resultado.setVotos(otrosVotos.get(key));
            resultado.setNombre(key);
            resultado.setPorcentaje(BigDecimal.valueOf(resultado.getVotos())
                    .multiply(BigDecimal.valueOf(100))
                    .divide(BigDecimal.valueOf(votosTotales), RoundingMode.UP));
            resultadoDeSeccion.getResultados().add(resultado);
        }

        Collections.sort(resultadoDeSeccion.getResultados(), (r1, r2) -> r2.getVotos() - r1.getVotos());

        for (int i = 0; i < resultadoDeSeccion.getResultados().size(); i++){
            resultadoDeSeccion.getResultados().get(i).setOrden(i+1);
        }

        resultadoDeSeccion.setSeccion(request[0].getSeccionNombre());
        resultadoDeSeccion.setDistrito(request[0].getDistritoNombre());
        return resultadoDeSeccion;
    }

}
