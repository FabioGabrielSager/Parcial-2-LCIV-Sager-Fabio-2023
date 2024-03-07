package ar.edu.utn.frc.tup.lc.iv.controllers;

import ar.edu.utn.frc.tup.lc.iv.dtos.responses.CargosDeDistrito;
import ar.edu.utn.frc.tup.lc.iv.dtos.responses.Distrito;
import ar.edu.utn.frc.tup.lc.iv.dtos.responses.ResultadoDeSeccion;
import ar.edu.utn.frc.tup.lc.iv.dtos.responses.Seccion;
import ar.edu.utn.frc.tup.lc.iv.services.EleccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/")
public class EleccionController {
    @Autowired
    private EleccionService eleccionService;

    @GetMapping("distritos")
    public List<Distrito> getDistritos(@RequestParam(name="nombre", required = false) String nombre){
        if(Objects.isNull(nombre))
            return eleccionService.getDistritos();
        else
            return eleccionService.getDistritosByNombre(nombre);
    }

    @GetMapping("cargos")
    public CargosDeDistrito getCargosByDistrito(@RequestParam Long distrito_id){
        return eleccionService.getCargosByDistrito(distrito_id);
    }

    @GetMapping("secciones")
    public List<Seccion> getSeccionesByDistrito(@RequestParam Long distrito_id,
                                                @RequestParam(name = "seccion_id", required = false) Long seccion_id){
        if(Objects.isNull(seccion_id))
            return eleccionService.getSeccionesByDistrito(distrito_id);
        else
            return eleccionService.getSeccionByDistritoYSeccion(distrito_id, seccion_id);
    }

    @GetMapping("resultados")
    public ResultadoDeSeccion getResultadoBySeccion(@RequestParam Long distrito_id,
                                                    @RequestParam Long seccion_id){
        return eleccionService.getResultadoBySeccion(seccion_id, distrito_id);
    }
}
