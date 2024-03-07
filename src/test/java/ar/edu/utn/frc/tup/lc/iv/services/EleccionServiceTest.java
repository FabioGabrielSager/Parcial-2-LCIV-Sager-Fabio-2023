package ar.edu.utn.frc.tup.lc.iv.services;

import ar.edu.utn.frc.tup.lc.iv.clients.EleccionesClient;

import ar.edu.utn.frc.tup.lc.iv.dtos.requests.ResultadoRequest;
import ar.edu.utn.frc.tup.lc.iv.dtos.responses.ResultadoDeSeccion;
import ar.edu.utn.frc.tup.lc.iv.services.imps.EleccionServiceImp;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EleccionServiceTest {
    @Mock
    private EleccionesClient eleccionesClient;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private EleccionServiceImp eleccionService;

    @Test
    public void getResultadoBySeccionTest(){
        ResultadoRequest[] resultados = new ResultadoRequest[]{
            new ResultadoRequest(1L,
                    "Cordoba", "Union", 1L,
                    "La libertad avanza", "POSITIVO", 25),
                new ResultadoRequest(1L,
                    "Cordoba", "Union", 2L,
                    "Juntos por el cambio", "POSITIVO", 20),
                new ResultadoRequest(1L,
                    "Cordoba", "Union", 0L,
                    "", "EN BLANCO", 20),
        };

        when(eleccionesClient.getResultadosBySeccion(1L)).thenReturn(ResponseEntity.ok(resultados));

        ResultadoDeSeccion response = eleccionService.getResultadoBySeccion(1L, 1L);

        assertThat(response.getResultados().size()).isEqualTo(6);
        assertThat(response.getResultados().get(0).getNombre()).isEqualTo("La libertad avanza");
        assertThat(response.getResultados().get(0).getOrden()).isEqualTo(1);
        assertThat(response.getResultados().get(0).getVotos()).isEqualTo(25);
        assertThat(response.getResultados().get(2).getNombre()).isEqualTo("EN BLANCO");
        assertThat(response.getResultados().get(2).getVotos()).isEqualTo(20);
    }
}
