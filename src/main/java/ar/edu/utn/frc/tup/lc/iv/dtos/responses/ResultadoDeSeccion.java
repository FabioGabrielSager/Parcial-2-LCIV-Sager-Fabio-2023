package ar.edu.utn.frc.tup.lc.iv.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoDeSeccion {
    private String distrito;
    private String seccion;
    private List<Resultado> resultados;
}
