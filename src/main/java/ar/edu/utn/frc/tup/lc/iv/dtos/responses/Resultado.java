package ar.edu.utn.frc.tup.lc.iv.dtos.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Resultado {
    private Integer orden;
    private String nombre;
    private Integer votos;
    private BigDecimal porcentaje;
}
