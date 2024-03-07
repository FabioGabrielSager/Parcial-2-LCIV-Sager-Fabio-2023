package ar.edu.utn.frc.tup.lc.iv.services;

import ar.edu.utn.frc.tup.lc.iv.dtos.responses.CargosDeDistrito;
import ar.edu.utn.frc.tup.lc.iv.dtos.responses.Distrito;
import ar.edu.utn.frc.tup.lc.iv.dtos.responses.ResultadoDeSeccion;
import ar.edu.utn.frc.tup.lc.iv.dtos.responses.Seccion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EleccionService {
    List<Distrito> getDistritos();
    List<Distrito> getDistritosByNombre(String nombre);
    CargosDeDistrito getCargosByDistrito(Long distritoId);
    List<Seccion> getSeccionesByDistrito(Long distritoId);
    List<Seccion> getSeccionByDistritoYSeccion(Long distritoId, Long seccionId);
    ResultadoDeSeccion getResultadoBySeccion(Long seccionId, Long distritoId);
}
