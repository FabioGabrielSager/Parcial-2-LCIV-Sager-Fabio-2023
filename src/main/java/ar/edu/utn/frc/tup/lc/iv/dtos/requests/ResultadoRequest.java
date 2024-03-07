package ar.edu.utn.frc.tup.lc.iv.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultadoRequest {
    private Long distritoId;
    private String distritoNombre;
    private String seccionNombre;
    private Long agrupacionId;
    private String agrupacionNombre;
    private String votosTipo;
    private Integer votosCantidad;
}
