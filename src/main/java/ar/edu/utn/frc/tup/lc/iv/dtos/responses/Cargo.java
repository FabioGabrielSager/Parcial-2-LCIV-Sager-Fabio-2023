package ar.edu.utn.frc.tup.lc.iv.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cargo {
    @JsonProperty("id")
    private Long cargoId;
    @JsonProperty("nombre")
    private String cargoNombre;
}
