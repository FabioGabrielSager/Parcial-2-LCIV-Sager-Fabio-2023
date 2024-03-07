package ar.edu.utn.frc.tup.lc.iv.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seccion {
    @JsonProperty("id")
    private Long seccionId;
    @JsonProperty("nombre")
    private String seccionNombre;
}
